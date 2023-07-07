package com.marcosmontiel.gameslearning.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    var avatar: String = "",
    var email: String = "",
    var id: String = "",
    var password: String = "",
    var username: String = "",
) {

    fun toJson(): String = Gson().toJson(this.toEncoded())

    fun toEncoded(): User = this.let { data ->
        data.copy(
            avatar = imageEncoder(data.avatar),
            email = stringEncoder(data.email),
            username = stringEncoder(data.username),
        )
    }

    private fun imageEncoder(data: String): String =
        if (data.isEmpty()) data else URLEncoder.encode(data, StandardCharsets.UTF_8.toString())

    private fun stringEncoder(data: String): String {
        val regex = buildString { append("[?=&#+%/.; ]") }

        return data.replace(Regex(regex)) { matchResult ->
            when (matchResult.value) {
                "?" -> "%3F"
                "=" -> "%3D"
                "&" -> "%26"
                "#" -> "%23"
                "+" -> "%2B"
                "%" -> "%25"
                "/" -> "%2F"
                "." -> "%2E"
                ";" -> "%3B"
                " " -> "%20"
                else -> matchResult.value
            }
        }
    }

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}
