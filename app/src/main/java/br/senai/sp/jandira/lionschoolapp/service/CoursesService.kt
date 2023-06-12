package br.senai.sp.jandira.lionschoolapp.service

import android.telecom.Call
import br.senai.sp.jandira.lionschoolapp.model.CoursesList
import br.senai.sp.jandira.lionschoolapp.model.StudentList
import br.senai.sp.jandira.lionschoolapp.model.StudentsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoursesService {
    @GET("v1/lion-school/cursosnome")
    fun getCourses(@Query("nome") nome: String?): retrofit2.Call<CoursesList>

}