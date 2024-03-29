package com.marcosmontiel.gameslearning.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import com.marcosmontiel.gameslearning.core.Constants.POSTS
import com.marcosmontiel.gameslearning.core.Constants.PROFILES
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

@DelicateCoroutinesApi
class PostRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(PROFILES) private val profilesRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference
) : PostRepository {

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.addSnapshotListener { snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {

                val response = if (snapshot != null) {

                    val posts = snapshot.toObjects(Post::class.java)
                    snapshot.forEachIndexed { index, document ->
                        posts[index].id = document.id
                    }

                    val usersId: List<String> = posts.map { it.userId }.distinct()

                    val users: List<User?> = usersId.map { id ->
                        async {
                            profilesRef.document(id).get().await().toObject(User::class.java)
                        }
                    }.awaitAll()

                    posts.forEach { post ->
                        val user: User? = users.find { it?.id == post.userId }
                        post.user = user
                    }

                    Response.Success(data = posts)

                } else {

                    Response.Failure(exception = e)

                }

                trySend(element = response)

            }

        }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getPostsByUser(userId: String): Flow<Response<List<Post>>> = callbackFlow {
        val reference = postsRef.whereEqualTo("userId", userId)
        val snapshotListener = reference.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {

                val posts = snapshot.toObjects(Post::class.java)
                snapshot.forEachIndexed { index, document ->
                    posts[index].id = document.id
                }

                Response.Success(data = posts)

            } else {

                Response.Failure(exception = e)

            }

            trySend(element = response)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {

            val fileUri = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            ref.putFile(fileUri).await()

            val downloadUrl = ref.downloadUrl.await()

            if (downloadUrl != null) {

                post.image = downloadUrl.toString()
                post.imageName = file.name
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

    override suspend fun update(post: Post, file: File?): Response<Boolean> {
        return try {

            val map: MutableMap<String, Any> = HashMap()
            map["category"] = post.category
            map["description"] = post.description
            map["name"] = post.name

            if (file != null) {

                val fileUri: Uri = Uri.fromFile(file)
                val ref: StorageReference = storagePostsRef.child(file.name)

                ref.putFile(fileUri).await()

                val downloadUrl: Uri = ref.downloadUrl.await()

                val oldRef: StorageReference = storagePostsRef.child(post.imageName)

                oldRef.delete().await()

                map["image"] = downloadUrl.toString()
                map["imageName"] = file.name

            }

            postsRef.document(post.id).update(map).await()

            Response.Success(data = true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

    override suspend fun delete(postId: String): Response<Boolean> {
        return try {

            postsRef.document(postId).delete().await()
            Response.Success(data = true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

    override suspend fun deleteLike(postId: String, userId: String): Response<Boolean> {
        return try {

            val map: MutableMap<String, Any> = HashMap()
            map["likes"] = FieldValue.arrayRemove(userId)

            postsRef.document(postId).update(map).await()
            Response.Success(data = true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

    override suspend fun like(postId: String, userId: String): Response<Boolean> {
        return try {

            val map: MutableMap<String, Any> = HashMap()
            map["likes"] = FieldValue.arrayUnion(userId)

            postsRef.document(postId).update(map).await()
            Response.Success(data = true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

}
