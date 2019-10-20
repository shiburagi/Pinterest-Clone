package com.shiburagi.imageloader.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category: Serializable {

    @SerializedName("id")
    @Expose
    var id: Long = 0
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("photo_count")
    @Expose
    var photoCount: Long = 0
    @SerializedName("links")
    @Expose
    var links: Links_? = null

}
