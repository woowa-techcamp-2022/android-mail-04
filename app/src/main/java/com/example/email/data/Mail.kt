package com.example.email.data

data class Mail(
    val id : Long,
    val sender : String,
    val title : String,
    val content : String,
    val type : String,
    val sDate : String = "2022-07-07"
)