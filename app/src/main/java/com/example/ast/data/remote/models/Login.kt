package com.example.ast.data.remote.models

import com.google.gson.annotations.SerializedName

data class Login(@SerializedName("userid") val userId: Long,
                 @SerializedName("nikname") val nikname: String,
                 @SerializedName("position") val position: String,
                 @SerializedName("email") val email: String,
                 @SerializedName("token") val token: String) {
}