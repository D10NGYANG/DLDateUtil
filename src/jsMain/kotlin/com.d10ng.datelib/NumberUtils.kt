package com.d10ng.datelib

/**
 * 将Number类型转换为长整型
 * @receiver [Number]
 * @return [Long]
 */
@JsExport
@Suppress("NON_EXPORTABLE_TYPE")
fun Number.asLong() = this.toLong()