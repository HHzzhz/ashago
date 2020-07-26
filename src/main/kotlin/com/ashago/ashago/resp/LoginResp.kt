package com.ashago.ashago.resp

class LoginResp(
        code: String,
        msg: String,
        val userId : Int?,
        val nickName: String?) : CommonResp(code, msg) {
}