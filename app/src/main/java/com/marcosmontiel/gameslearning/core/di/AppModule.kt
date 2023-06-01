package com.marcosmontiel.gameslearning.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.marcosmontiel.gameslearning.core.Constants.POSTS
import com.marcosmontiel.gameslearning.core.Constants.PROFILES
import com.marcosmontiel.gameslearning.data.repository.AuthRepositoryImpl
import com.marcosmontiel.gameslearning.data.repository.PostRepositoryImpl
import com.marcosmontiel.gameslearning.data.repository.ProfileRepositoryImpl
import com.marcosmontiel.gameslearning.domain.repository.AuthRepository
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import com.marcosmontiel.gameslearning.domain.repository.ProfileRepository
import com.marcosmontiel.gameslearning.domain.usecase.auth.*
import com.marcosmontiel.gameslearning.domain.usecase.post.CreatePost
import com.marcosmontiel.gameslearning.domain.usecase.post.GetPosts
import com.marcosmontiel.gameslearning.domain.usecase.post.PostUseCases
import com.marcosmontiel.gameslearning.domain.usecase.profile.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Firebase Authentication

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // Firebase Firestone

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    @Named(POSTS)
    fun providePostsRef(database: FirebaseFirestore): CollectionReference =
        database.collection(POSTS)

    @Singleton
    @Provides
    @Named(PROFILES)
    fun provideProfilesRef(database: FirebaseFirestore): CollectionReference =
        database.collection(PROFILES)

    // Firebase Storage

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Singleton
    @Provides
    @Named(POSTS)
    fun provideStoragePostsRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(POSTS)

    @Singleton
    @Provides
    @Named(PROFILES)
    fun provideStorageProfilesRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(PROFILES)

    // Repositories

    @Singleton
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Singleton
    @Provides
    fun providePostRepository(impl: PostRepositoryImpl): PostRepository = impl

    @Singleton
    @Provides
    fun provideProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository = impl

    // Use cases

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases =
        AuthUseCases(
            getCurrentUser = GetCurrentUser(repository = repository),
            login = Login(repository = repository),
            logout = Logout(repository = repository),
            register = Register(repository = repository)
        )

    @Singleton
    @Provides
    fun providePostUseCases(repository: PostRepository): PostUseCases =
        PostUseCases(
            getPosts = GetPosts(repository),
            create = CreatePost(repository)
        )

    @Singleton
    @Provides
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases =
        ProfileUseCases(
            create = Create(repository = repository),
            getCurrentProfile = GetCurrentProfile(repository = repository),
            savePhoto = SavePhoto(repository),
            update = Update(repository = repository)
        )

}
