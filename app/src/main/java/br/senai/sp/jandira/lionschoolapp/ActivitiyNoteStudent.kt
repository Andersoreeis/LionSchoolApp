package br.senai.sp.jandira.lionschoolapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolapp.model.*
import br.senai.sp.jandira.lionschoolapp.service.RetrofitFactory
import br.senai.sp.jandira.lionschoolapp.ui.theme.LionSchoolAppTheme
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import retrofit2.Call
import retrofit2.Response

class NoteStudent : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolAppTheme {
                StudentDetails()
            }
        }
    }
}


@Composable
@Preview(showBackground = true)

fun StudentDetails() {
    val context = LocalContext.current

    val matricula = LocalStorage.getFromSharedPreferences(context, "matricula")

    val student = remember { mutableStateOf(emptyList<Student>()) }
    val call = RetrofitFactory().getNoteStudantService().getStudentByRegistration(matricula)
    call.enqueue(object : retrofit2.Callback<StudentList> {
        override fun onResponse(call: Call<StudentList>, response: Response<StudentList>) {
            student.value = response.body()!!.aluno ?: emptyList()
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
            println("Error")
        }

    })



    var static by remember { mutableStateOf(emptyList<Static>()) }

    val callStatic = RetrofitFactory().getStatisctStudentService().getStaticData(matricula)
    callStatic.enqueue(object : retrofit2.Callback<StaticList> {
        override fun onResponse(call: Call<StaticList>, response: Response<StaticList>) {

            static = response.body()!!.notas

        }

        override fun onFailure(call: Call<StaticList>, t: Throwable) {
            println("Error")
        }
    })





    val firstStudent = student.value.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(69, 87, 183))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(37.dp)
                    .clickable {
                        val intent = Intent(context, ActivitiyStudents::class.java)
                        context.startActivity(intent)
                    }
            )

            firstStudent?.let {
                var fullName = firstStudent.nome.split(" ")
                var firstName = fullName.first()
                Text(
                    text = firstName,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(20.dp)
                )

            }


        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)

                .background(Color(69, 87, 183))
                ,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {


            Row(modifier = Modifier.fillMaxSize()) {

                if (firstStudent != null) {


                    AsyncImage(
                        model = firstStudent.foto,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .size(150.dp)


                    )
                }
            }




        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(
                        elevation = 4.dp, // ajuste o valor conforme necessário
                        shape = RoundedCornerShape(16.dp)
                    ),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {}
        }

        LazyRow(){
            items(static){

                Text(text = it.media)
            }


        }


    }


}