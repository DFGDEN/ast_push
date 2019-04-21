package com.example.ast.data.remote.models

import com.google.gson.annotations.SerializedName

class Notification(@SerializedName("gcm") val gcm: String) {
}