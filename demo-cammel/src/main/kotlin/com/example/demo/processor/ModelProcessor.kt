package com.example.demo.processor

import com.example.demo.ModelTest
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.springframework.stereotype.Component

@Component
class ModelProcessor(private val serviceSpring: ServiceSpring) : Processor{


    override fun process(exchange: Exchange?) {
        var model = exchange?.getIn()?.getBody(ModelTest::class.java)
        println(model)
        model = model?.let { serviceSpring.processNewName(it) }
        println(model)
        exchange?.getIn()?.body = model
    }


}