package com.example.springeventstore.support.config

import com.example.springeventstore.support.advice.CustomJpaRepositoryFactoryBean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = [
        "com.example.springeventstore.adapter.out.persistence",
    ],
    repositoryFactoryBeanClass = CustomJpaRepositoryFactoryBean::class,
)
class JpaConfig
