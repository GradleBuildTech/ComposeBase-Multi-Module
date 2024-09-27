package com.example.core.models.stateData

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    fun isLeft(): Boolean = this is Left<L>
    fun isRight(): Boolean = this is Right<R>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    //Get Left
    fun leftValue(): L? {
        return when (this) {
            is Left -> value
            is Right -> null
        }
    }

    //Get Right
    //Example: val result = when (val response = dataRepository.getProfile()) {
    //    is Either.Left -> response.value
    //    is Either.Right -> response.value
    //}
    fun rightValue(): R? {
        return when (this) {
            is Left -> null
            is Right -> value
        }
    }
}