package com.example.momchin.util.extension

import com.badoo.reaktive.base.Consumer

fun <T> Consumer(output: T.() -> Unit): Consumer<T> = object : Consumer<T> {
    override fun onNext(value: T) {
        output(value)
    }
}