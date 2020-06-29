package com.example.demo.processor

import com.example.demo.ModelTest
import org.springframework.stereotype.Service

@Service
class ServiceSpring {

    fun processNewName(modelTest: ModelTest): ModelTest = modelTest.copy(name = "Nome modificado no service...")

}