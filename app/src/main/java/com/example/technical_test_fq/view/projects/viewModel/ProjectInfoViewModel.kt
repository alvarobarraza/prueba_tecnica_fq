package com.example.technical_test_fq.view.projects.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technical_test_fq.data.model.ResultProjectApi
import javax.inject.Inject

class ProjectInfoViewModel @Inject constructor(): ViewModel() {

    private val _sharedOpt = MutableLiveData<Boolean>()
    val sharedOpt: LiveData<Boolean> get() = _sharedOpt

    fun updateOpt(opt: Boolean){
        _sharedOpt.value = opt
    }

    private val _sharedProject = MutableLiveData<ResultProjectApi>()
    val sharedProject: LiveData<ResultProjectApi> get() = _sharedProject

    fun updateProject(data: ResultProjectApi){
        _sharedProject.value = data
    }

}