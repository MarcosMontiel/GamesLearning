package com.marcosmontiel.gameslearning.presentation.screens.detail_post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.domain.model.Category
import com.marcosmontiel.gameslearning.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Get params

    private val _postParam: String = savedStateHandle.get<String>("post") ?: ""
    val post: Post = Post.fromJson(_postParam)

    private val _categoryRes = MutableLiveData<Int>()
    val categoryRes: LiveData<Int> = _categoryRes

    init {
        getCategoryRes()
    }

    // Private functions

    private fun getCategoryRes() {
        val categoryName: String = post.category
        val categories: List<Category> = Category.getCategories()

        _categoryRes.value = categories.firstOrNull { category ->
            category.name == categoryName
        }?.icon ?: R.drawable.playstation
    }

}
