package br.senai.sp.jandira.lionschoolapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val BASE_URL = "https://apilionschool.cyclic.app/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCoursesService(): CoursesService {
        return retrofitFactory.create(CoursesService::class.java)


    }

    fun getStudentsService(): StudentsService{
        return retrofitFactory.create(StudentsService::class.java)
    }

    fun getNoteStudantService(): StudantService{
        return retrofitFactory.create(StudantService::class.java)
    }

    fun getStatisctStudentService(): StaticService{
        return retrofitFactory.create(StaticService::class.java)
    }
}