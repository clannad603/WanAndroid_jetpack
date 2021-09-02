package com.example.myapplication.ui.mainview.fragmentcollect.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.logic.model.bean.ProjectChildInfo
import com.example.myapplication.logic.repository.ProjectRepository
import com.example.myapplication.ui.base.BaseViewModel

class ProjectChildViewModel : BaseViewModel() {
    private val childLiveData = MutableLiveData<ProjectChildInfo>()
    private val articleLists = Transformations.switchMap(childLiveData) { it ->
        ProjectRepository.getChildProject(it)
    }
    val pageVO = Transformations.map(articleLists) {
        it.data
    }
    val articles = Transformations.map(pageVO) {
        it?.datas
    }

    fun giveCheck(projectChildInfo: ProjectChildInfo) {
        childLiveData.value = projectChildInfo
    }

}