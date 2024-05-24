package com.d10ng.datelib

fun String.padStartForce(length: Int, padChar: Char = '0'): String {
    return this.padStart(length, padChar).substring(0, length)
}