package com.shiburagi.imageloader.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiburagi.imageloader.entities.Image
import com.shiburagi.imageloader.service.NetworkService


/**
 * ImageList view model
 */
class ImageListViewModel: ViewModel() {

    private val data: MutableLiveData<List<Image>> = MutableLiveData()
    private val service: NetworkService = NetworkService.instance

    /**
     * to get first data
     */
    fun getData(url: String?): LiveData<List<Image>> {
        if (url != null)
            service.getData(url, data::setValue)
        return data
    }

    /**
     * to load more data
     */
    fun loadMore(url: String) {
        service.getData(url, data::postValue)
    }
}

