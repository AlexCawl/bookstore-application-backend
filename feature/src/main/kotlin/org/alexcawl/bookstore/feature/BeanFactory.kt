package org.alexcawl.bookstore.feature

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class BeanFactory {
    @Bean
    fun getMe(): String = "Mick"
}