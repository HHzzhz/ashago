package com.ashago.ashago.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
        var email: String,
        var token: String,
        var subscribed : Boolean,
        var nickName:String,
        @Id @GeneratedValue var id: Int? = null
) {
}