package com.marcosmontiel.gameslearning.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.marcosmontiel.gameslearning.core.Constants.POSTS
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference
) : PostRepository {

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {

            val fileUri = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            ref.putFile(fileUri).await()

            val downloadUrl = ref.downloadUrl.await()

            if (downloadUrl != null) {

                post.image = downloadUrl.toString()
                postsRef.add(post).await()

                Response.Success(data = true)

            } else {

                Response.Failure(
                    exception = Exception("Error: File download URL is null.")
                )

            }

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

}
