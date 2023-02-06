package com.example.com.com.presentation.utils.exception

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


interface ResourceProvider {

    fun handleException(exception: Exception): String

    fun handleException(throwable: Throwable): String

    class Base() : ResourceProvider {


        override fun handleException(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> "Нету подключение к интернету"
                is SocketTimeoutException -> "Нету подключение к интернету"
                is ConnectException -> "Нету подключение к интернету"
                is HttpException -> "Нету подключение к интернету"
                else -> "Что то пошло не так"
            }
        }

        override fun handleException(throwable: Throwable): String {
            return when (throwable) {
                is UnknownHostException -> "Нету подключение к интернету"
                is SocketTimeoutException -> "Нету подключение к интернету"
                is ConnectException -> "Нету подключение к интернету"
                is HttpException -> "Нету подключение к интернету"
                else -> "Что то пошло не так"
            }
        }

    }
}
