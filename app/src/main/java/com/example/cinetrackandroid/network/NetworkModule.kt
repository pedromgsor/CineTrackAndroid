package com.example.cinetrackandroid.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class) // TODO Check Later
class NetworkModule {
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YjdiM2Y5MzhjMWYzM2E4NDM4N2VmNmViN2Q1YzI3YSIsIm5iZiI6MTc2OTI3MTA1OS44MTIsInN1YiI6IjY5NzRlZjEzYjU4NWQyYTQyNjFhMDU2NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1a_1Hzken6wlVAeG7NzhR7HF7Gf1DzrndZ2bv5nkH3c")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}
