package com.lahsuak.apps.instagramclone.model

data class Instagram(
    var id: Long,
    var userImage: Int,
    var userName: String,
    var userPosts: List<Post>
)

data class Post(
    var postId: Long,
    var postImage: Int,
    var caption: String,
    var likesCount: Long,
    var commentsCount: Long
)
