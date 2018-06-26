package com.frozen.dailys.model

import com.google.gson.annotations.SerializedName


data class Article(
        @SerializedName("curPage") val curPage: Int = 0,
        @SerializedName("datas") val datas: List<ArticleList>?,
        @SerializedName("offset") val offset: Int = 0,
        @SerializedName("over") val over: Boolean = false,
        @SerializedName("pageCount") val pageCount: Int = 0,
        @SerializedName("size") val size: Int = 0,
        @SerializedName("total") val total: Int = 0
)

data class ArticleList(
        @SerializedName("apkLink") val apkLink: String = "",
        @SerializedName("author") val author: String = "",
        @SerializedName("chapterId") val chapterId: Int = 0,
        @SerializedName("chapterName") val chapterName: String = "",
        @SerializedName("collect") val collect: Boolean = false,
        @SerializedName("courseId") val courseId: Int = 0,
        @SerializedName("desc") val desc: String = "",
        @SerializedName("envelopePic") val envelopePic: String = "",
        @SerializedName("fresh") val fresh: Boolean = false,
        @SerializedName("id") val id: Int = 0,
        @SerializedName("link") val link: String = "",
        @SerializedName("niceDate") val niceDate: String = "",
        @SerializedName("origin") val origin: String = "",
        @SerializedName("projectLink") val projectLink: String = "",
        @SerializedName("publishTime") val publishTime: Long = 0,
        @SerializedName("superChapterId") val superChapterId: Int = 0,
        @SerializedName("superChapterName") val superChapterName: String = "",
        @SerializedName("tags") val tags: List<String> = listOf(),
        @SerializedName("title") val title: String = "",
        @SerializedName("type") val type: Int = 0,
        @SerializedName("userId") val userId: Int = 0,
        @SerializedName("visible") val visible: Int = 0,
        @SerializedName("zan") val zan: Int = 0
)