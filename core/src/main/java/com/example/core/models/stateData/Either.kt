package com.example.core.models.stateData

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    fun isLeft(): Boolean = this is Left<L>
    fun isRight(): Boolean = this is Right<R>

    fun <L> leftValue(): L? = (this as? Left<L>)?.value
    fun <R> rightValue(): R? = (this as? Right<R>)?.value
}