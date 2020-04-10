package dev.ujjwal.paging

import com.google.gson.annotations.SerializedName

class GitRepo {
    @field:SerializedName("full_name")
    var fullName: String? = null

    val description: String? = null

    @field:SerializedName("stargazers_count")
    val stars: Int = 0

    @field:SerializedName("forks_count")
    val froks: Int = 0

    val language: String? = null
}

class GitRepoResponse {
    @field:SerializedName("total_count")
    var totalCount: Int = 0

    var items: List<GitRepo>? = null
}