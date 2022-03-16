package com.example.momchin.util

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.subscribe
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

enum class AnimatedDirection {
	LEFT, RIGHT, NONE
}

enum class StackState {
	BACK_STACK, FIRST_BACK_STACK, ACTIVE, POPPED
}

data class AnimatedChild<T>(
	val child: T,
	val entrance: AnimatedDirection,
	val state: StackState
)

fun <T: Any> Value<RouterState<*, T>>.asValue(
	lifecycle: Lifecycle,
	coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
): Value<List<AnimatedChild<T>>> {
	return object : Value<List<AnimatedChild<T>>>(), CoroutineScope by coroutineScope {

		private var lastActiveChild: Child.Created<*, T>? = null

		private var lastBackStack: List<Child<*, T>> = listOf()

		private val _value: MutableStateFlow<List<AnimatedChild<T>>> = MutableStateFlow(emptyList())
		override val value: List<AnimatedChild<T>>
			get() = _value.value

		private val routerObserver: ValueObserver<RouterState<*, T>> = { state ->
			when {
				lastActiveChild == null -> {
					lastActiveChild = state.activeChild
					_value.update {
						getAnimatedBackStackChildren(state, StackState.BACK_STACK) +
								AnimatedChild(
									child = state.activeChild.instance,
									entrance = AnimatedDirection.NONE,
									state = StackState.ACTIVE
								)
					}
				}
				else -> {
					val backStack = getAnimatedBackStackChildren(state)
					val isPushed = state.backStack.size > lastBackStack.size
					val newList = buildList {
						addAll(backStack)
						add(
							AnimatedChild(
								child = state.activeChild.instance,
								entrance = if (isPushed) AnimatedDirection.RIGHT else AnimatedDirection.LEFT,
								state = StackState.ACTIVE
							)
						)
						if (!isPushed) {
							add(
								AnimatedChild(
									child = lastActiveChild!!.instance,
									entrance = AnimatedDirection.LEFT,
									state = StackState.POPPED
								)
							)
						}
					}
					_value.update {
						newList
					}
				}
			}

			lastActiveChild = state.activeChild
			lastBackStack = state.backStack
		}

		private fun <T: Any> getAnimatedBackStackChildren(state: RouterState<*, T>, stackState: StackState? = null): List<AnimatedChild<T>> =
			state.backStack.mapIndexed { index, child ->
				child as Child.Created<*, T>
				AnimatedChild(
					child = child.instance,
					entrance = AnimatedDirection.LEFT,
					state = when {
						stackState != null -> stackState
						index == state.backStack.lastIndex -> StackState.FIRST_BACK_STACK
						else -> StackState.BACK_STACK
					}
				)
			}

		init {
			lifecycle.subscribe(
				onCreate = {
					subscribe(routerObserver)
				},
				onDestroy = {
					unsubscribe(routerObserver)
				}
			)
		}

		override fun subscribe(observer: ValueObserver<List<AnimatedChild<T>>>) {
			launch {
				_value.collect(observer)
			}
		}

		override fun unsubscribe(observer: ValueObserver<List<AnimatedChild<T>>>) = cancel()
	}
}