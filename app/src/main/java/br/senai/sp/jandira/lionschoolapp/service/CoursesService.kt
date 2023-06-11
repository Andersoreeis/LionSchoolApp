package br.senai.sp.jandira.lionschoolapp.service

import android.telecom.Call
import br.senai.sp.jandira.lionschoolapp.model.CoursesList
import retrofit2.http.GET

interface CoursesService {
    @GET("v1/lion-school/cursos")
    fun getCourses(): retrofit2.Call<CoursesList>

}