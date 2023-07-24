package com.marcosmontiel.gameslearning.domain.usecase.post

data class PostUseCases(
    val getPosts: GetPosts,
    val getPostsByUser: GetPostsByUser,
    val create: CreatePost,
    val update: UpdatePost,
    val delete: DeletePost,
    val deleteLike: DeleteLikePost,
    val like: LikePost,
)
