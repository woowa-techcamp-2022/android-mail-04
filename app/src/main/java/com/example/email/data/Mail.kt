package com.example.email.data

data class Mail(
    val id : Long,
    val sender : String,
    val title : String,
    val content : String,
    val type : String
)
