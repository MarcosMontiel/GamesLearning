package com.marcosmontiel.gameslearning.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Post(
    var category: String = "",
    var description: String = "",
    var id: String = "",
    var image: String = "",
    var name: String = "",
    var user: User? = null,
    var userId: String = "",
) {

    fun toJson(): String = Gson().toJson(this.let { data ->
        data.copy(
            description = stringEncoder(data.description),
            image = imageEncoder(data.image),
            name = stringEncoder(data.name),
            user = data.user?.toEncoded(),
        )
    })

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
        fun fromJson(data: String): Post = Gson().fromJson(data, Post::class.java)
    }

}
