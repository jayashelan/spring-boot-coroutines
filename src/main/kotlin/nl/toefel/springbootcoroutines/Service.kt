package nl.toefel.springbootcoroutines

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.awaitResponse

@Service
class Service (val jsonPlaceHolderClient: JsonPlaceHolderClient){

    suspend fun sayHelloServiceAwaitResponse() :String {
        log.info("executing json placeholder")
        val posts = jsonPlaceHolderClient.posts().awaitResponse()
        log.info("done executing json placeholder ${Thread.currentThread().name}")
        return posts.body()?.random()?.title ?: "No title"
    }

    suspend fun sayHelloServiceSuspend() :String {
        log.info("executing json placeholder suspend")
        val posts = jsonPlaceHolderClient.postsSuspend()
        log.info("done executing json placeholder suspend ${Thread.currentThread().name}")
        return posts.random().title
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(Service::class.java)
    }
}