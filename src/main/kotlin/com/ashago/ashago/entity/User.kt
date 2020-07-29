package com.ashago.ashago.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
        var userId: String,
        var userIdType: String = "email",
        var token: String,
        var subscribed : Boolean?,
        var userName:String?,
        var activated: Boolean?,
        @Id @GeneratedValue var id: Int? = null
) {
}