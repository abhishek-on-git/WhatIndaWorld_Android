package com.wcp.whatindaworld.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun fetchTopHeadlines_sendRequest_receivedRequest() {
        runBlocking {
            enqueueMockResponse("dummy_response.json")
            val responseBody = service.fetchTopHeadlines("us", 1)
            val request = server.takeRequest()

            assertThat(request.path).isEqualTo(
                "/v2/top-headlines?country=us&page=1&apiKey=006a4109a0a0459e834e127f21867431"
            )
            assertThat(responseBody).isNotNull()
        }
    }

    @Test
    fun fetchTopHeadlines_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse("dummy_response.json")
            val response = service.fetchTopHeadlines("us", 1)
            val body = response.body()
            assertThat(body!!.articles.size).isEqualTo(20)
        }
    }

    @Test
    fun fetchTopHeadlines_receivedResponse_correctData() {
        runBlocking {
            enqueueMockResponse("dummy_response.json")
            val response = service.fetchTopHeadlines("us", 1)
            val body = response.body()
            assertThat(body!!.articles[0].author).isEqualTo(
                "Nick Perry"
            )
            assertThat(body!!.articles[0].title).isEqualTo(
                "First aid flights arrive in Tonga after big volcano eruption - Associated Press"
            )
            assertThat(body!!.articles[0].url).isEqualTo(
                "https://apnews.com/article/science-business-tonga-new-zealand-united-nations-9c84b745e4a3e4278db5b45e456601ed"
            )
        }
    }
}