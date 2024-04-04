package com.example.eskooly
import com.example.eskooly.model.FeesPaid
import com.example.eskooly.model.HomeworkDetailsResponse
import com.example.eskooly.model.StudentDetails
import com.example.eskooly.model.TimetableResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("fees/getAllFeeDetailsList")
      fun getPaidFeesList(
        @Query("registrationNumber") regNo:String
    ):Call<FeesPaid>

    @GET("student/getstudentbyid")
    suspend fun getStudentDetails(
        @Query("registrationNumber") regNo: String,
        @Query("institutionId") institutionId: String
    ): Call<StudentDetails>

    @GET("timetable/getTimetableClasswise")
     fun getTimetableClassWise(
        @Query("institutionId") institutionId: String,
        @Query("classId") classId: String
    ): Call<TimetableResponse>

    @GET("homework/getHomework")
    fun getHomeworkDetails(
        @Query("institutionId") institutionId: String,
        @Query("date") date: String,
        @Query("classId") classId: String,
        @Query("teacherId") teacherId: String
    ): Call<HomeworkDetailsResponse>
}
