package com.example.mobile_hw2.data

class LoginRepository {

    fun login(username: String, password: String): Result<Boolean> {
        return if (username == "test@test.com" && password == "1234") {
            Result.success(true)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}