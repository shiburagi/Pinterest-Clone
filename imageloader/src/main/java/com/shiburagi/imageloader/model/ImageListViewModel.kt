package com.shiburagi.imageloader.model

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiburagi.imageloader.entities.Image
import com.shiburagi.imageloader.service.NetworkService


class ImageListViewModel: ViewModel() {

    private val data: MutableLiveData<List<Image>> = MutableLiveData()
    private val service: NetworkService = NetworkService.instance;
    fun getData(url: String?): LiveData<List<Image>> {
        if (url != null)
            service.getData(url, data::setValue);
        return data;
    }

    fun loadMore(url: String) {
        service.getData(url, data::postValue);
    }

    fun loadImage(url: String, view: ImageView) {
        service.getImage(url, view);
    }
}

