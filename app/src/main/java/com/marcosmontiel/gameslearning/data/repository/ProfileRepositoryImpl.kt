package com.marcosmontiel.gameslearning.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.marcosmontiel.gameslearning.core.Constants.PROFILES
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.repository.ProfileRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class ProfileRepositoryImpl @Inject constructor(
    @Named(PROFILES) private val profilesRef: CollectionReference,
    @Named(PROFILES) private val storageProfilesRef: StorageReference
) : ProfileRepository {

    override fun currentProfile(userId: String): Flow<User> = callbackFlow {
        val snapshotListener = profilesRef.document(userId).addSnapshotListener { snapshot, _ ->
            val profile = snapshot?.toObject(User::class.java) ?: User()
            trySend(element = profile)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun create(user: User): Response<Boolean> {
        return try {

            user.password = ""

            profilesRef.document(user.id).set(user).await()

            Response.Success(data = true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {

            val map: MutableMap<String, Any> = HashMap()
            map["username"] = user.username

            if (user.avatar.isNotEmpty()) {
                map["avatar"] = user.avatar
            }

            profilesRef.document(user.id).update(map).await()

            Response.Success(data = true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

    override suspend fun savePhoto(file: File): Response<String> {
        return try {

            val fileUri = Uri.fromFile(file)
            val ref = storageProfilesRef.child(file.name)
            ref.putFile(fileUri).await()

            val downloadUrl = ref.downloadUrl.await()

            if (downloadUrl != null) {

                Response.Success(data = downloadUrl.toString())

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
