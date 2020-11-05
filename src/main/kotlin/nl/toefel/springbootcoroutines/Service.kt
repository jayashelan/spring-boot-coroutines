package nl.toefel.springbootcoroutines

import kotlinx.coroutines.yield
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.awaitResponse

@Service
class Service (val jsonPlaceHolderClient: JsonPlaceHolderClient){

    suspend fun sayHelloService() :String {
        log.info("executing json placeholder")
        val posts = jsonPlaceHolderClient.posts().awaitResponse()
        log.info("done executing json placeholder ${Thread.currentThread().name}")
        yield()
        return posts.body()?.random()?.title ?: "No title"
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(Service::class.java)
    }
}