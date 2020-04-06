package dev.aisdev.example.model.converters

interface Converter<F, T> {

    fun from(from: F): T
    fun to(from: T): F {
        throw NotImplementedError()
    }
}