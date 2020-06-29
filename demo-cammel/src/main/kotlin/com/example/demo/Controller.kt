package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.ProducerTemplate
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping
class Controller(private val producerTemplate: ProducerTemplate) {

    @GetMapping("/start")
    fun start() {
        println("start")
        producerTemplate.sendBody("direct:route#start",ModelTest(0,"verificarFraude processado"))
    }

    @GetMapping("/verificarFraude")
    fun verificarFraude(): ModelTest {
        println("verificarFraude")
        return ModelTest(1,"verificarFraude processado")
    }

    @PostMapping("/verificarData")
    fun verificarData(@RequestBody modelTest: ModelTest) : ModelTest {
        println("verificarData: ${modelTest}")
        return ModelTest(2,"verificarData processado")
    }

    @PostMapping("/salvarBanco")
    fun salvarBanco(@RequestBody modelTest: ModelTest) : ModelTest {
        println("salvarBanco: ${modelTest}")
        return ModelTest(3,"salvarBanco processado")
    }

    @PostMapping("/processarDiaCerto")
    fun processarDiaCerto(@RequestBody modelTest: ModelTest) : ModelTest {
        println("processarDiaCerto: ${modelTest}")
        return ModelTest(4,"processarDiaCerto processado")
    }

    @PostMapping("/loginMatera")
    fun loginMatera(@RequestBody modelTest: ModelTest) : ModelTest {
        println("loginMatera: ${modelTest}")
        return ModelTest(5,"loginMatera processado")
    }

    @PostMapping("/senhaAcessoVault")
    fun senhaAcessoVault(@RequestBody modelTest: ModelTest) : ModelTest {
        println("senhaAcessoVault: ${modelTest}")
        return ModelTest(6,"senhaAcessoVault processado")
    }

    @PostMapping("/senhaTransacionalVault")
    fun senhaTransacionalVault(@RequestBody modelTest: ModelTest) : ModelTest {
        println("senhaTransacionalVault: ${modelTest}")
        return ModelTest(7,"senhaTransacionalVault processado")
    }

    @PostMapping("/efetivarTransacao")
    fun efetivarTransacao(@RequestBody modelTest: ModelTest) : ModelTest {
        println("efetivarTransacao: ${modelTest}")
        return ModelTest(8,"efetivarTransacao processado")
    }

    @PostMapping("/notificarCliente")
    fun notificarCliente(@RequestBody modelTest: ModelTest) : ModelTest {
        println("notificarCliente: ${modelTest}")
        return ModelTest(9,"notificarCliente processado")
    }

//    @GetMapping("/processResponse")
//    fun processResponse() : ModelTest {
//        println("processResponse: ")
//        return ModelTest(10,"processResponse processado")
//    }

    @GetMapping("/error")
    fun error() :ModelTest {
        println("erro")
        return ModelTest(-1,"Error")
    }

}