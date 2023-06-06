package br.senai.sp.jandira.lionschoolapp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentsService {
   // https://apilionschool.cyclic.app/v1/lion-school/alunos


    @GET("alunos")
    fun getStudentsByCouses(@Query("cursos") curso: String ): retrofit2.Call<StudentsService>

}