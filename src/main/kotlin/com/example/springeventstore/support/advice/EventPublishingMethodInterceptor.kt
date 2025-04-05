package com.example.springeventstore.support.advice

import com.example.springeventstore.support.context.EventContext
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.springframework.context.ApplicationEventPublisher
import java.lang.reflect.Method

class EventPublishingMethodInterceptor(
    private val applicationEventPublisher: ApplicationEventPublisher,
) : MethodInterceptor {
    /**
     * Publishes all events stored in the [EventContext] when the `save()` method is called on a JPA repository.
     * After publishing, the events in the [EventContext] are also cleared.
     *
     * This interceptor is typically applied via a custom RepositoryProxyPostProcessor,
     * ensuring that domain events are consistently dispatched after persistence operations.
     *
     * @see com.example.springeventstore.support.context.EventContext
     * @author JiHongKim98
     */
    override fun invoke(invocation: MethodInvocation): Any? {
        val result = invocation.proceed()
        if (isEventPublishingMethod(invocation.method)) {
            EventContext.raiseEvents { event ->
                applicationEventPublisher.publishEvent(event)
            }
        }
        return result
    }

    private fun isEventPublishingMethod(method: Method): Boolean {
        val methodName = method.name
        return methodName.startsWith(SAVE_METHOD_PREFIX)
    }

    companion object {
        private const val SAVE_METHOD_PREFIX = "save"
    }
}
