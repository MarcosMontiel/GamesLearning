package com.marcosmontiel.gameslearning.domain.model

import com.marcosmontiel.gameslearning.R

data class Category(
    val icon: Int,
    val name: String = "",
) {
    companion object {
        fun getCategories(): List<Category> = listOf(
            Category(name = "PC", icon = R.drawable.computer),
            Category(name = "PS4", icon = R.drawable.playstation),
            Category(name = "XBOX", icon = R.drawable.xbox),
            Category(name = "NINTENDO", icon = R.drawable.nintendo),
            Category(name = "MOBILE", icon = R.drawable.smartphone),
        )
    }
}
