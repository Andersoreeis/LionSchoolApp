package br.senai.sp.jandira.lionschoolapp.service

import br.senai.sp.jandira.lionschoolapp.model.StudentList
import retrofit2.http.GET
import retrofit2.http.Path

interface StudantService {
    @GET("v1/lion-school/alunos/{matricula}")
    fun getStudentByRegistration(@Path("matricula") matricula: String?): retrofit2.Call<StudentList>


}