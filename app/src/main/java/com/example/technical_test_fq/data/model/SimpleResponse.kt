package com.example.technical_test_fq.data.model

open class SimpleResponse() {
    var message: String? = null
    var status: Boolean = false

    constructor(status: Boolean, message: String) : this() {
        this.status = status
        this.message = message
    }
}
