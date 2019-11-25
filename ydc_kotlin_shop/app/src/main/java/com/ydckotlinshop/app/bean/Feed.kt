package com.ydckotlinshop.app.bean


data class Feed<T>(var code: String,var message: String,var token: String,var data: T){}
