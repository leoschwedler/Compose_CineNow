package com.devspacecinenow

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MmQ2YzRjMmNmMTA4YjhhNTMzMmM2MjRhOTY4MDAzOCIsIm5iZiI6MTcwOTM5MTY4Mi42Nywic3ViIjoiNjVlMzNmNDJiN2ExNTQwMTg2NzhjNTNiIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.z-S1STkaRZ1JD6pUuRERLQfVo8jEoshSEwG3VnBYMfc"
private const val BASE_URL: String = "https://api.themoviedb.org/3/movie/"

object RetrofitClient {

    private val httpClient: OkHttpClient
        get() {
           val clientBuilder = OkHttpClient.Builder()


            clientBuilder.addInterceptor { chain ->
                val original: Request = chain.request()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .header("Authorization", "Bearer $TOKEN")
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }

            return clientBuilder.build()
        }

    val retrofitInstance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}