package com.example.springeventstore.support.aspect

import com.example.springeventstore.support.context.EventContext
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Aspect
@Component
class EventPublisherAspect(
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    @Pointcut("@within(org.springframework.stereotype.Service)")
    fun hasServiceAnnotation() {
    }

    /**
     * Publishes domain events collected in the [EventContext] when a service-layer method finishes execution.
     *
     * This aspect targets classes annotated with {@code @Service}, and ensures that any domain events
     * registered during the execution of a service method are published at the end of the method.
     *
     * Before the method executes, it clears any existing events in the [EventContext] to prevent
     * cross-request contamination.
     * After the method completes, it raises and publishes all collected events via the [ApplicationEventPublisher].
     *
     * @author JiHongKim98
     */
    @Around("hasServiceAnnotation()")
    fun execute(joinPoint: ProceedingJoinPoint): Any? {
        // Clears the EventContext before executing the service logic
        EventContext.clearEvents()

        return runCatching {
            joinPoint.proceed()
        }.onSuccess {
            // Publishes any domain events that were registered but not yet published during service execution
            EventContext.raiseEvents { event ->
                applicationEventPublisher.publishEvent(event)
            }
        }.onFailure { throwable ->
            EventContext.clearEvents()
            throw throwable
        }
    }
}
