package com.modular.support

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestController {

    @Autowired
    lateinit var mockMvc: MockMvc
    protected val mapper = ObjectMapper()
}