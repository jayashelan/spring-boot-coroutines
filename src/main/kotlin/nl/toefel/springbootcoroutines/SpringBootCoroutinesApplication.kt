package nl.toefel.springbootcoroutines

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@SpringBootApplication
class SpringBootCoroutinesApplication {

	@Bean
	fun jsonPlaceHolderClient(): JsonPlaceHolderClient {
		return Retrofit.Builder()
			.baseUrl("https://jsonplaceholder.typicode.com")
			.addConverterFactory(JacksonConverterFactory.create(ObjectMapper().findAndRegisterModules()))
			.build()
			.create(JsonPlaceHolderClient::class.java)
	}
}

fun main(args: Array<String>) {
	runApplication<SpringBootCoroutinesApplication>(*args)
}
