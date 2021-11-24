package com.example.andriod_me.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.andriod_me.data.AndroidImageAsset


class DashboardFragmentViewModel : ViewModel() {

    private val imageListLiveData: MutableLiveData<List<Int>> = MutableLiveData()

    val imageList: LiveData<List<Int>> get() = imageListLiveData


    private fun setImageList(imageList: List<Int>) {
        imageListLiveData.value = imageList
    }

    init {
        setImageList(AndroidImageAsset.allPart)
    }

}
