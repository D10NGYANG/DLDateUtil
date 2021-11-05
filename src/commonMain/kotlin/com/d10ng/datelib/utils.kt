package com.d10ng.datelib

internal fun String.up2Length(
    length: Int,
    filler: Char = '0',
    isInStart: Boolean = true,
    isForced: Boolean = true
): String {
    val result = StringBuilder()
    if (!isInStart) result.append(this)
    if (this.length < length) {
        for (i in 0 until length - this.length) {
            result.append(filler)
        }
    }
    if (isInStart) result.append(this)
    return if (isForced) {
        if (isInStart) result.toString().substring(result.length - length)
        else result.toString().substring(0, length)
    } else result.toString()
}