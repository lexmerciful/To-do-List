package com.lexmerciful.to_dolist.data

import com.google.firebase.auth.AuthResult
import com.lexmerciful.to_dolist.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>

}