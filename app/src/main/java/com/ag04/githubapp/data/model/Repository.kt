package com.ag04.githubapp.data.model


import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("node_id")
    val nodeId: String? = null,
    @SerializedName("name")
    val name: String? = "Tetris",
    @SerializedName("full_name")
    val fullName: String? = "user/tetris",
    @SerializedName("private")
    val private: Boolean = false,
    @SerializedName("html_url")
    val htmlUrl: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("fork")
    val fork: Boolean = false,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("pushed_at")
    val pushedAt: String? = "",
    @SerializedName("homepage")
    val homepage: String? = "",
    @SerializedName("size")
    val size: Int = 15,
    @SerializedName("stargazers_count")
    val stargazersCount: Long = 100,
    @SerializedName("watchers_count")
    val watchersCount: Long = 200,
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("forks_count")
    val forksCount: Long = 300,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int = 400,
    @SerializedName("master_branch")
    val masterBranch: String? = "",
    @SerializedName("default_branch")
    val defaultBranch: String? = "",
    @SerializedName("score")
    val score: Double = 1.0,
    @SerializedName("owner")
    val user: User? = null
)