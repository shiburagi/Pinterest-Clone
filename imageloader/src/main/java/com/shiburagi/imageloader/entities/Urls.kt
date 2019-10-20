package com.shiburagi.imageloader.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Urls : Serializable {

    @SerializedName("raw")
    @Expose
    var raw: String? = null
    @SerializedName("full")
    @Expose
    var full: String? = null
    @SerializedName("regular")
    @Expose
    var regular: String? = null
    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

}
