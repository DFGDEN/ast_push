package com.example.ast.data.remote

import com.example.ast.data.remote.models.Login
import com.example.ast.data.remote.models.Notification
import com.example.ast.data.remote.models.Response
import io.reactivex.Single
import retrofit2.http.*

interface AstHelper {


    @GET("/api/")
    fun login(@QueryMap queryMap: Map<String, String>): Single<Response<Login>>


    @POST("/api/")
    fun updatePushToken(@QueryMap queryMap: Map<String, String>,@Body gcm: Notification): Single<Any>
}