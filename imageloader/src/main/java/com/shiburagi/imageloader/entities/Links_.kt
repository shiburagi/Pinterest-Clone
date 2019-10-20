package com.shiburagi.imageloader.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Links_ : Serializable {

    @SerializedName("self")
    @Expose
    var self: String? = null
    @SerializedName("photos")
    @Expose
    var photos: String? = null

}
