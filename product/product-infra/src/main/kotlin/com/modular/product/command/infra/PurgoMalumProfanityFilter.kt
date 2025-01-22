package com.modular.product.command.infra

import com.modular.product.command.domain.service.ProfanityFilter
import com.modular.product.command.infra.dto.PurgoMalumResultDto
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder

@Component
class PurgoMalumProfanityFilter : ProfanityFilter {

    override fun doFilter(productName: String): String {
        val restClient = createRestTemplate()
        val uriComponents = UriComponentsBuilder.fromUriString(PURGO_URL)
            .queryParam("text", productName)
            .encode()
            .build()

        val response = restClient.get()
            .uri(uriComponents.toUri())
            .retrieve()
            .body(PurgoMalumResultDto::class.java)

        return response?.result ?: throw IllegalArgumentException("통신에 실패했습니다.")
    }

    private fun createRestTemplate(): RestClient {
        val httpComponentsClientHttpRequestFactory = HttpComponentsClientHttpRequestFactory().apply {
            setConnectTimeout(5000)
            setReadTimeout(5000)
        }
        val restClient = RestClient.builder()
            .baseUrl(PURGO_URL)
            .requestFactory(httpComponentsClientHttpRequestFactory)
            .build()

        return restClient
    }

    companion object {
        private const val PURGO_URL = "https://www.purgomalum.com/service/json"
    }

}