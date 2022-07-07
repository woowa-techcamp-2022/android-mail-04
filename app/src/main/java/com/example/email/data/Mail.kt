package com.example.email.data

data class Mail(
    val id : Long, //메일의 key 값
    val sender : String, //발신인
    val title : String, //제목
    val content : String, // 본문
    val type : String, // 타입
    val sDate : String = "2022-07-07" // 발신일 (**따로 사용되지 않아 임의의 값으로 초기화**)
)