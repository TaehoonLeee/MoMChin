package com.example.momchin.util.extension

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun <T: Any> Store<*, T, *>.asValue(): Value<T> = object : Value<T>(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main + Job()

    override val value: T get() = state

    override fun subscribe(observer: ValueObserver<T>) {
        launch {
            states.collect(observer)
        }
    }

    override fun unsubscribe(observer: ValueObserver<T>) = cancel()

}