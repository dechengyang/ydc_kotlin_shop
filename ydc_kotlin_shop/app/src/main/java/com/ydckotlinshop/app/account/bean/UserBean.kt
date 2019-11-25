package com.ydckotlinshop.app.account.bean


data class UserBean (
    val uid: String,
    val username: String,
    val avatar_url: String,
    val email: String?
) {
}