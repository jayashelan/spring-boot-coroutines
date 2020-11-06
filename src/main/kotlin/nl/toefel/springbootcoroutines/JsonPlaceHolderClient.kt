package nl.toefel.springbootcoroutines

import retrofit2.Call
import retrofit2.http.GET

data class Post(
    val userId: Long,
    val id : Long,
    val title: String,
    val body: String
)

interface JsonPlaceHolderClient {
    @GET("/posts")
    fun posts(): Call<List<Post>>

    @GET("/posts")
    suspend fun postsSuspend(): List<Post>
}