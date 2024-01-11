package com.awesomeplaylist.api.rest

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class DataSourceUrlLogger {
    @Value("\${spring.datasource.url}")
    private val dataSourceUrl: String? = null
    @PostConstruct
    fun logDataSourceUrl() {
        println("DataSource URL: $dataSourceUrl")
    }
}

