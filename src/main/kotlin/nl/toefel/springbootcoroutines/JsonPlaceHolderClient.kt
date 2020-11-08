package nl.toefel.springbootcoroutines

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class Post(
    val userId: Long,
    val id : Long,
    val title: String,
    val body: String
)

data class Todo(
        val userId: Long,
        val id : Long,
        val title: String,
        val completed: Boolean
)

interface JsonPlaceHolderClient {
    @GET("/posts")
    fun posts(): Call<List<Post>>

    @GET("/posts")
    suspend fun postsSuspend(): List<Post>

    @GET("/todos")
    fun todos(): Call<List<Todo>>

    @GET("/todos")
    suspend fun todosSuspend(): List<Todo>

    @GET("/todos/{id}")
    fun todo(@Path("id") id: Long): Call<Todo>

    @GET("/todos/{id}")
    suspend fun todoSuspend(@Path("id") id: Long): Todo

}