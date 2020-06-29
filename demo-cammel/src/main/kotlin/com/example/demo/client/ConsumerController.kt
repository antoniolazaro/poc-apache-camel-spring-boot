package com.example.demo.client

import com.example.demo.ModelTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity


@RestController
@RequestMapping
class ConsumerController(private val restTemplate: RestTemplate) {

    val url = "http://localhost:8080/demo/"

    @GetMapping("/rest")
    fun start(): ModelTest? {

        val headers = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        val responseFraude = restTemplate.getForEntity<ModelTest>(url+"/verificarFraude")
        println(responseFraude.body)
        val responseVerificarData = restTemplate.postForEntity<ModelTest>(url+"/verificarData",HttpEntity<ModelTest>(responseFraude.body, headers), ModelTest::class.java)
        println(responseVerificarData.body)
        val responseSalvarBanco = restTemplate.postForEntity<ModelTest>(url+"/salvarBanco",HttpEntity<ModelTest>(responseVerificarData.body, headers), ModelTest::class.java)
        println(responseSalvarBanco.body)
        val responseprocessarDiaCerto = restTemplate.postForEntity<ModelTest>(url+"/processarDiaCerto",HttpEntity<ModelTest>(responseSalvarBanco.body, headers), ModelTest::class.java)
        println(responseprocessarDiaCerto.body)
        val responseLoginMatera = restTemplate.postForEntity<ModelTest>(url+"/loginMatera",HttpEntity<ModelTest>(responseprocessarDiaCerto.body, headers), ModelTest::class.java)
        println(responseLoginMatera.body)
        val responseSenhaAcessoVault = restTemplate.postForEntity<ModelTest>(url+"/senhaAcessoVault",HttpEntity<ModelTest>(responseLoginMatera.body, headers), ModelTest::class.java)
        println(responseSenhaAcessoVault.body)
        val responseSenhaTransacionalVault = restTemplate.postForEntity<ModelTest>(url+"/senhaTransacionalVault",HttpEntity<ModelTest>(responseSenhaAcessoVault.body, headers), ModelTest::class.java)
        println(responseSenhaTransacionalVault.body)
        val responseEfetivarTransacao = restTemplate.postForEntity<ModelTest>(url+"/efetivarTransacao",HttpEntity<ModelTest>(responseSenhaTransacionalVault.body, headers), ModelTest::class.java)
        println(responseEfetivarTransacao.body)
        val responseNotificarCliente = restTemplate.postForEntity<ModelTest>(url+"/notificarCliente",HttpEntity<ModelTest>(responseEfetivarTransacao.body, headers), ModelTest::class.java)
        println(responseNotificarCliente.body)
        return responseNotificarCliente.body
    }

}