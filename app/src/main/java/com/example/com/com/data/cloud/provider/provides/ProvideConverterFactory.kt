package com.example.com.com.data.cloud.provider.provides

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory():Converter.Factory
}