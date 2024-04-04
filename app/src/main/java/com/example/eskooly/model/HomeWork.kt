package com.example.eskooly.model

data class Homework(
    val _id: String,
    val institutionId: String,
    val date: String,
    val className: String,
    val classId: String,
    val teacher: String,
    val teacherId: String,
    val subject: String,
    val homework: String,
    val __v: Int
)