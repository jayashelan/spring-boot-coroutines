package nl.toefel.springbootcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.awaitResponse
import java.lang.IllegalStateException

@Service
class Service(val jsonPlaceHolderClient: JsonPlaceHolderClient) {

    suspend fun getPostsCallAwait(): String {
        log.info("executing json placeholder call.awaitResponse")
        val posts = jsonPlaceHolderClient.posts().awaitResponse()
        log.info("done executing json placeholder call.awaitResponse ${Thread.currentThread().name}")
        return posts.body()?.random()?.title ?: "No title"
    }

    suspend fun getPostsCallSuspend(): String {
        log.info("executing json placeholder suspend")
        val posts = jsonPlaceHolderClient.postsSuspend()
        log.info("done executing json placeholder suspend ${Thread.currentThread().name}")
        return posts.random().title
    }

    suspend fun getOverviewAwait(): OverviewDto {
        return coroutineScope {
            log.info("executing get overview")
            val posts = async {
                log.info("fetching posts")
                jsonPlaceHolderClient.posts()
                        .awaitResponse()
                        .body()!!
                        .also {
                            log.info("fetched ${it.size} posts")
                        }
            }
            val todos = async {
                log.info("fetching todos")
                jsonPlaceHolderClient.todos()
                        .awaitResponse()
                        .body()!!
                        .also {
                            log.info("fetched ${it.size} todos")
                        }
            }
            val todo1 = async {
                log.info("fetching todo 400")
                jsonPlaceHolderClient.todo(400)
                        .awaitResponse()
                        .body() ?: throw IllegalStateException("no todo with id 400")
                        .also {
                            log.info("fetched todo 400")
                        }
            }

            OverviewDto(
                    posts = posts.await(),
                    todos = todos.await(),
                    todo1 = todo1.await()
            )
        }
    }

    suspend fun getOverviewSuspend(): OverviewDto {
        return withContext(Dispatchers.IO) {
            log.info("executing get overview suspend")
            val posts = async {
                log.info("fetching posts")
                jsonPlaceHolderClient.postsSuspend()
                        .also {
                            log.info("fetched ${it.size} posts")
                        }
            }
            val todos = async {
                log.info("fetching todos")
                jsonPlaceHolderClient.todosSuspend()
                        .also {
                            log.info("fetched ${it.size} todos")
                        }
            }
            val todo1 = async {
                log.info("fetching todo 400")
                jsonPlaceHolderClient.todoSuspend(20)
                        .also {
                            log.info("fetched todo 400")
                        }
            }

//            withContext(Dispatchers.Default) {
                OverviewDto(
                        posts = posts.await(),
                        todos = todos.await(),
                        todo1 = todo1.await()
                )
//            }
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(Service::class.java)
    }
}