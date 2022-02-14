package com.lahsuak.apps.instagramclone.model

class User {
    var uid: String = ""
    var userName: String = ""
    var userImage: String? = ""
    var bio: String = ""

    constructor()
    constructor(uid: String, userName: String, userImage: String?, bio: String) {
        this.uid = uid
        this.userName = userName
        this.userImage = userImage
        this.bio = bio
    }
}
