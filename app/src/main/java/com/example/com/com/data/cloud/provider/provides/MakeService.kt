package com.example.com.com.data.cloud.provider.provides

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}