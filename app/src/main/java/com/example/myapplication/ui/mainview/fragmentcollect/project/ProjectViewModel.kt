package com.example.myapplication.ui.mainview.fragmentcollect.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.repository.MavenSearchRepository
import com.example.myapplication.logic.repository.ProjectRepository
import com.example.myapplication.ui.base.BaseViewModel

class ProjectViewModel : BaseViewModel() {
    private val checkLiveData = MutableLiveData<Boolean>()
    private val projectBack = Transformations.switchMap(checkLiveData) {
        ProjectRepository.getProject()
    }

    val proList = Transformations.map(projectBack) {
        it.data
    }
    private val repository by lazy { MavenSearchRepository() }

    fun setCheck(boolean: Boolean) {
        checkLiveData.value = boolean
    }


}