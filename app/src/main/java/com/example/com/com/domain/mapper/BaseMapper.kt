package com.example.com.com.domain.mapper

interface BaseMapper<From, To> {
    fun map(from: From): To
}