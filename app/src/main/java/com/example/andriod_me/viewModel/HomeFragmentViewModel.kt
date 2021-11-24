package com.example.andriod_me.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.andriod_me.data.AndroidImageAsset
import com.example.andriod_me.data.Image

class HomeFragmentViewModel: ViewModel() {
    private val mutableSelectedHead = MutableLiveData<Int>()
    private val mutableSelectedBody = MutableLiveData<Int>()
    private val mutableSelectedLeg = MutableLiveData<Int>()


    val selectedHead: LiveData<Int> get() = mutableSelectedHead
    val selectedBody: LiveData<Int> get() = mutableSelectedBody
    val selectedLeg: LiveData<Int> get() = mutableSelectedLeg




    fun selectHead(head: Int) {
        mutableSelectedHead.value = head
    }


    fun selectBody(body: Int) {
        mutableSelectedBody.value = body
    }

    fun selectLeg(leg: Int) {
        mutableSelectedLeg.value = leg
    }

    private val headListLiveData: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    private val bodyListLiveData: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    private val legListLiveData: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    private val bodyPartListLiveData: MutableLiveData<List<Int>> = MutableLiveData()
    private val imageListLiveData: MutableLiveData<ArrayList<Image>> = MutableLiveData()

    val headList: LiveData<ArrayList<Int>> get() = headListLiveData
    val bodyList: LiveData<ArrayList<Int>> get() = bodyListLiveData
    val legList: LiveData<ArrayList<Int>> get() = legListLiveData
    val bodyPartList: LiveData<List<Int>> get() = bodyPartListLiveData
    val imageList: LiveData<ArrayList<Image>> get() = imageListLiveData


    private fun setHeadList(headList: ArrayList<Int>){
        headListLiveData.value = headList
    }
    private fun setLegList(legList: ArrayList<Int>){
        legListLiveData.value = legList
    }
    private fun setBodyList(bodyList: ArrayList<Int>){
        bodyListLiveData.value = bodyList
    }
    private fun setBodyPartList(bodyPartList: List<Int>){
        bodyPartListLiveData.value = bodyPartList
    }

    private fun setImageList(imageList: ArrayList<Image>){
        imageListLiveData.value = imageList
    }

    init {
        setBodyPartList(AndroidImageAsset.allPart)
        setHeadList(AndroidImageAsset.headList)
        setLegList(AndroidImageAsset.legList)
        setBodyList(AndroidImageAsset.bodyList)
    }
}