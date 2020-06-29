package com.example.demo.client

import com.example.demo.ModelTest
import com.example.demo.processor.ModelProcessor
import org.apache.camel.Exchange
import org.apache.camel.LoggingLevel
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.dataformat.JsonLibrary
import org.apache.camel.model.rest.RestBindingMode
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

@Component
class DemoCamelRouter(private val modelProcessor: ModelProcessor) : RouteBuilder() {

    override fun configure() {

        from("direct:route#start").
                streamCaching().
                routeId("route#start").
//                tracing().
                log(LoggingLevel.INFO, "CAMMEL invocando verificarFraude").
        process(modelProcessor).
//                removeHeader("CamelHttp").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.GET.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        to("http://localhost:8080/demo/verificarFraude").
                log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
                setBody().body().
        to("http://localhost:8080/demo/verificarData").
        log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        setBody().body().
        to("http://localhost:8080/demo/salvarBanco").
        log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        setBody().body().
        to("http://localhost:8080/demo/processarDiaCerto").
        log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        setBody().body().
        to("http://localhost:8080/demo/loginMatera").
        log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        setBody().body().
        to("http://localhost:8080/demo/senhaAcessoVault").
        log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        setBody().body().
        to("http://localhost:8080/demo/senhaTransacionalVault").
        log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        setBody().body().
        to("http://localhost:8080/demo/efetivarTransacao").
        log("Before unmarshall ${body()}").
        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        process(modelProcessor).
        log("Response 2 ${body()}").
        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.POST.toString())).
        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
        setBody().body().
        to("http://localhost:8080/demo/notificarCliente").
//        unmarshal().json(JsonLibrary.Jackson, ModelTest::class.java).
//        process(modelProcessor).
//        log("Response 2 ${body()}").
//        setHeader(Exchange.HTTP_METHOD,constant(HttpMethod.GET.toString())).
//        setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE)).
//        marshal().json(JsonLibrary.Jackson, ModelTest::class.java).
//        setBody().body().
//        to("http://localhost:8080/demo/processResponse").
        end()

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)

        rest().get("/processResponse")
                .to("direct:hello");

        from("direct:hello")
                .log(LoggingLevel.INFO, "Hello World")
                .transform().simple("Hello World");
    }
}