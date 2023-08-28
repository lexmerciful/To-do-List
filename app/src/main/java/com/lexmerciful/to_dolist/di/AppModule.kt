package com.lexmerciful.to_dolist.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.lexmerciful.to_dolist.data.AuthRepository
import com.lexmerciful.to_dolist.data.AuthRepositoryImpl
import com.lexmerciful.to_dolist.data.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(auth)
    }

}