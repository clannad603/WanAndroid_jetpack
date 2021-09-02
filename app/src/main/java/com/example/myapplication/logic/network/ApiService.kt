package com.example.myapplication.logic.network

import androidx.lifecycle.LiveData
import com.example.myapplication.logic.model.BaseResponse
import com.example.myapplication.logic.model.bean.*
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /***
     * 厂库列表
     */
    @GET("maven_pom/package/json")
    fun getMavenList(): LiveData<BaseResponse<List<String>>>

    /***
     * 获取文章
     */
    @GET("article/list/{page}/json")
    fun articleList(
        @Path("page") page: Int
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    /***
     * 用户信息
     */
    @GET("lg/coin/userinfo/json")
    fun getMyCoin(): LiveData<BaseResponse<UserInfo>>


    /***
     * 退出
     */
    @GET("user/logout/json")
    fun logout(): LiveData<BaseResponse<String>>

    /***
     * 注册
     */
    @POST("user/register")
    fun register(
        @Query("username") username: String?,
        @Query("password") password: String?,
        @Query("repassword") repassword: String?
    ): LiveData<BaseResponse<RegisterVO>>

    /***
     * 登录
     */
    @POST("user/login")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): LiveData<BaseResponse<RegisterVO>>

    /**
     * 搜索
     */
    @POST("article/query/{page}/json")
    fun searchArticlePage(
        @Path("page") page: Int,
        @Query("k") keyword: String
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    /***
     * 知识体系下文章
     */
    @GET("article/list/{page}/json")
    fun articleList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    /***
     * 广场
     */
    @GET("user_article/list/{page}/json")
    fun getSquare(
        @Path("page") page: Int
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    /***
     * 获取项目
     */

    @GET("project/tree/json")
    fun getProject(): LiveData<BaseResponse<MutableList<ProjectVO>>>

    //项目列表数据
    @GET("project/list/{page}/json?")
    fun getProjectChild(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    @GET("wenda/list/{page}/json ")
    fun qaList(
        @Path("page") page: Int
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    /***
     * 积分变化
     */
    @GET("lg/coin/list/1/json")
    fun getCoinChange(): LiveData<BaseResponse<PageVO<UserInfoVO>>>

    /***
     * 个人收藏
     */
    @GET("lg/collect/list/{page}/json")
    fun getMyCollect(
        @Path("page") page: Int
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    /***
     * 公众号
     */
    @GET("wxarticle/chapters/json")
    fun getOfficialAccount(): LiveData<BaseResponse<MutableList<ProjectVO>>>

    //公众号列表数据
    @GET(" wxarticle/list/{id}/1/json?")
    fun getOfficialChild(
        @Path("id") id: Int,
    ): LiveData<BaseResponse<PageVO<ArticleVO>>>

    //获取首页banner
    @GET("banner/json")
    fun getBanner(): LiveData<BaseResponse<MutableList<BannerVO>>>

    @POST("lg/collect/{id}/json")
    fun collect(
        @Path("id") page: Int
    ): LiveData<BaseResponse<JSONObject>>

    /**
     * 取消收藏
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun unCollectArticle(
        @Path("id") id: Int
    ): LiveData<BaseResponse<JSONObject>>

    /**
     * 收藏站外文章
     */
    @POST("lg/collect/add/json")
    fun collectOutArticle(
        @Query("title") title: String,
        @Query("author") author: String,
        @Query("link") link: String
    ): LiveData<BaseResponse<JSONObject>>

    //搜索热词
    @GET("hotkey/json")
    fun getHotkey(): LiveData<BaseResponse<MutableList<HotKeyVO>>>
}