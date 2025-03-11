package com.d10ng.datelib

fun String.padStartForce(length: Int, padChar: Char = '0'): String {
    return padStart(length, padChar).let { it.substring(it.length - length) }
}