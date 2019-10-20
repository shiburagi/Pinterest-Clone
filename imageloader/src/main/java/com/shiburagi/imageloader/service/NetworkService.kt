package com.shiburagi.imageloader.service

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.shiburagi.imageloader.entities.Image
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkService private constructor() {
    val cacheUrl: HashMap<String, String?> = HashMap();
    val gson = Gson();
    private var client = OkHttpClient()
    companion object{
        val instance = NetworkService();
    }

    fun getData(url: String, onComplete: (List<Image>?) -> Unit) {
        if (cacheUrl.containsKey(url))
            onComplete(toObject(cacheUrl[url]));
        else {
            val request = Request.Builder()
                .url(url)
                .get()
                .build()
            client.newCall(request)
//                .execute()
                .enqueue(
                    object : okhttp3.Callback {
                        override fun onFailure(call: okhttp3.Call, e: IOException) {}
                        override fun onResponse(call: okhttp3.Call, response: Response) {
                            runOnUiThread {
                                if (response.code == 200) {
                                    cacheUrl[url] = response.body?.string();
                                    onComplete(toObject(cacheUrl[url]));
                                }
                            }
                        }
                    }
                )
        }
    }

    fun toObject(json: String?): List<Image> {
        return gson.fromJson(json, Array<Image>::class.java).asList()
    }

    private fun runOnUiThread(callback: () -> Unit) {
        Handler(Looper.getMainLooper()).post { callback(); }
    }

    fun getImage(url: String, view: ImageView) {

        Glide.with(view)
            .load(Uri.parse(url))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(view);

    }
}