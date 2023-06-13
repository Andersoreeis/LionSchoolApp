package br.senai.sp.jandira.lionschoolapp.service

import br.senai.sp.jandira.lionschoolapp.model.Static
import br.senai.sp.jandira.lionschoolapp.model.StaticList
import retrofit2.http.GET
import retrofit2.http.Path

interface StaticService{
    @GET("v1/lion-school/alunos/mediaCurso/{matricula}")
    fun getStaticData(@Path("matricula") matricula: String?): retrofit2.Call<StaticList>
}