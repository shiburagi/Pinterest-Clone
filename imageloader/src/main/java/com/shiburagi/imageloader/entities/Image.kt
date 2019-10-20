package com.shiburagi.imageloader.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image :Serializable{

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("width")
    @Expose
    var width: Long = 0
    @SerializedName("height")
    @Expose
    var height: Long = 0
    @SerializedName("color")
    @Expose
    var color: String? = null
    @SerializedName("likes")
    @Expose
    var likes: Long = 0
    @SerializedName("liked_by_user")
    @Expose
    var likedByUser: Boolean = false
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("current_user_collections")
    @Expose
    var currentUserCollections: List<Any>? = null
    @SerializedName("urls")
    @Expose
    var urls: Urls? = null
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
    @SerializedName("links")
    @Expose
    var links: Links__? = null

}
