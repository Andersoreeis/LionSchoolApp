package br.senai.sp.jandira.lionschoolapp.service

import br.senai.sp.jandira.lionschoolapp.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentsService {
    @GET("v1/lion-school/alunos")
    fun getStudentsByCourse(@Query("curso") course: String): Call<StudentsList>
}

