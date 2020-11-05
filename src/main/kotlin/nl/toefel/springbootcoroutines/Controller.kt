package nl.toefel.springbootcoroutines

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller (val service: Service){

    @GetMapping("/hello")
    suspend fun sayHello() :String {
        log.info("invoking service")
        return service.sayHelloService().also {
            log.info("done invoking service")
        }
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(Controller::class.java)
    }
}