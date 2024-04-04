package com.example.eskooly.model

data class TimetableEntry(
    val _id: String,
    val institutionId: String,
    val classId: String,
    val className: String,
    val startTime: String,
    val endTime: String,
    val day: String,
    val subject: String,
    val teacher: String,
    val teacherId: String,
    val __v: Int
)

data class TimetableResponse(
    val timetableEntries: List<TimetableEntry>
)
