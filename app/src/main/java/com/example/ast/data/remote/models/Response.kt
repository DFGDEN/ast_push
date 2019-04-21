package com.example.ast.data.remote.models

import com.google.gson.annotations.SerializedName

class Response<out T>(@SerializedName("response") val response: T) {
}