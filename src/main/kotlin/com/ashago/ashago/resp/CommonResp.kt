package com.ashago.ashago.resp

open class CommonResp(
        val code: String,
        val msg: String,
        private val data: MutableMap<String, Any?> = HashMap()
) {

    fun appendData(key: String, value: Any?): CommonResp {
        this.data[key] = value
        return this
    }

    fun getData(key: String): Any?{
        return data[key]
    }

    companion object {
        fun success() :CommonResp {
            return CommonResp("0", "success")
        }
    }
}