package com.awesomeplaylist.api.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

@TestConfiguration
class MysqlConfig : AbstractJdbcConfiguration() {

    companion object {
        val MYSQL: MySQLContainer<*> = MySQLContainer(
                DockerImageName.parse("mariadb:10.6")
                        .asCompatibleSubstituteFor("mysql")
        )
                .withDatabaseName("mydatabase")
                .withUsername("integration")
                .withPassword("integration")
                .withReuse(true)

        init {
            MYSQL.start()
        }
    }

    class TestContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            val jdbcUrl = MYSQL.jdbcUrl
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=$jdbcUrl?verifyServerCertificate=false&useSSL=false&requireSSL=false&allowMultiQueries=true",
                    "spring.datasource.username=${MYSQL.username}",
                    "spring.datasource.password=${MYSQL.password}"
            )
        }
    }
}
