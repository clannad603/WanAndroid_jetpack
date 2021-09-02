package com.example.myapplication.logic.repository

import com.example.myapplication.logic.model.bean.ProjectChildInfo

object ProjectRepository : NetRepository() {
    fun getProject() = api.getProject()
    fun getChildProject(projectChildInfo: ProjectChildInfo) =
        api.getProjectChild(projectChildInfo.page, projectChildInfo.cid)

}