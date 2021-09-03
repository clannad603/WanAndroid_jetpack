# WanAndroid

### 一.app功能

0.启动动画

1.登录，注册，退出

2.首页文章，轮播图，刷新

3.项目

4.问答

5.收藏（收藏站内文章及获取账号收藏列表）

6.广场

7.搜索

8.个人信息（积分详情，获取，排名等）

9.历史（本地访问过的所有文章）

10.公众号

11.热词（网络和本地搜索过的）

### 二.实现的接口

```kotlin
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
```

### 三。使用了什么架构

使用的MVVM架构

kotlin+livedata+viewmodel+room

### 四。实现的思路

从网络及本地获取数据，在仓库层进行管理（这里由于历史原因，livedata的生产并没有在仓库层产生），viewmodel获取仓库层的数据，view层使用viewmodel层的livedata进行数据驱动视图

### 五。项目的优点和优势

使用的jetpack构建的mvvm架构，使得代码耦合度降低，项目开发使用的kotlin也比java使用更加方便，封装了基类和一些工具类，使用编写代码的过程中更加方便

##### 六。实现过程中的难点

因为是在原有项目的基础上进行的二次开发，鉴于时间原因，没有对ui进行更改和美化，以及原本项目使用的viewbinding导致不太好更换为 databinding来更好的书写（虽然这个也有不少的坑）

### 七。使用的第三方库

1.lottie动画加载库

此次项目中使用lottie库来实现的启动动画

使用方法为，在官网或者自己用ae写（因为我不会ae，所以用的官网的）拿到json文件，导入即可

2.retrofit

使用retrofit进行网络请求，使用其下自带的okhttp进行拦截来保留登录状态，使用其下的gson来进行数据解析




因为原有项目过于粗糙，不太好改，导致ui八太行

分包当时参考的《第一行代码第三版》

原有项目地址：https://github.com/clannad603/WanAndroid

