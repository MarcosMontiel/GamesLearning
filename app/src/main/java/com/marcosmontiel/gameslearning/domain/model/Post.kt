package com.marcosmontiel.gameslearning.domain.model

import com.google.gson.Gson

data class Post(
    var category: String = "",
    var description: String = "",
    var id: String = "",
    var idUser: String = "",
    var image: String = "",
    var name: String = "",
    var user: User? = null,
) {

    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): Post = Gson().fromJson(data, Post::class.java)
    }

}
