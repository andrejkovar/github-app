package com.ag04.githubapp.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("gravatar_id")
    val gravatarId: String?,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("following")
    val following: Int?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,
    @SerializedName("type")
    val type: String
)