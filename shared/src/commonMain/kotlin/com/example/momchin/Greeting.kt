package com.example.momchin

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}