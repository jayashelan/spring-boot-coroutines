package nl.toefel.springbootcoroutines

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class Controller (val service: Service){

    @GetMapping("/posts")
    suspend fun getPosts() :String {
        log.info("invoking getPosts call.awaitResponse")
        return service.getPostsCallAwait().also {
            log.info("done invoking service call.awaitResponse")
        }
    }

    @GetMapping("/posts-suspend")
    suspend fun getPostsSuspend() :String {
        log.info("invoking getPosts suspend")
        return service.getPostsCallSuspend().also {
            log.info("done invoking getPosts suspend")
        }
    }

    @GetMapping("/overview")
    suspend fun getOverview() :OverviewDto {
        log.info("invoking getOverview call.awaitResponse")
        return service.getOverviewAwait().also {
            log.info("done invoking getOverview call.awaitResponse")
        }
    }

    @GetMapping("/overview-suspend")
    suspend fun getOverviewSuspend() :OverviewDto {
        log.info("invoking getOverview suspend")
        return service.getOverviewSuspend().also {
            log.info("done invoking getOverview suspend")
        }
    }



    companion object {
        val log : Logger = LoggerFactory.getLogger(Controller::class.java)
    }
}