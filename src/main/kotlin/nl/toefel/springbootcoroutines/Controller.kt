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
        return service.sayHelloServiceAwaitResponse().also {
            log.info("done invoking service")
        }
    }

    @GetMapping("/hello-suspend")
    suspend fun sayHelloSuspend() :String {
        log.info("invoking service suspend")
        return service.sayHelloServiceSuspend().also {
            log.info("done invoking service suspend")
        }
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(Controller::class.java)
    }
}