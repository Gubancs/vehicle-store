package hu.gubancs.vehiclestore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan("hu.gubancs.vehiclestore")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}