package com.marcosmontiel.gameslearning.domain.model

import com.google.gson.Gson

data class User(
    var avatar: String = "",
    var email: String = "",
    var id: String = "",
    var password: String = "",
    var username: String = "",
) {

    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}
