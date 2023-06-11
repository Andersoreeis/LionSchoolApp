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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolapp.model.Courses
import br.senai.sp.jandira.lionschoolapp.model.CoursesList
import br.senai.sp.jandira.lionschoolapp.model.Quantidade
import br.senai.sp.jandira.lionschoolapp.model.Student
import br.senai.sp.jandira.lionschoolapp.model.StudentsList
import br.senai.sp.jandira.lionschoolapp.service.RetrofitFactory
import br.senai.sp.jandira.lionschoolapp.ui.theme.LionSchoolAppTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivitiyStudents : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolAppTheme {
                PageStudents()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PageStudents() {
    val context = LocalContext.current
    val turmas = LocalStorage.getFromSharedPreferences(context, "siglaCurso")

    var textFieldValue = remember { mutableStateOf("") }
    var boxColor1 = remember { mutableStateOf(Color(156, 163, 224)) }
    var boxColor2 = remember { mutableStateOf(Color(156, 163, 224)) }
    var boxColor3 = remember { mutableStateOf(Color(156, 163, 224)) }
    val clickedColor = remember { (Color(69, 87, 183)) }

    var studentList = remember { mutableStateOf(emptyList<Student>()) }
    val quantidadeStudents = remember { mutableStateOf(0) }

    val call = RetrofitFactory().getStudentsService().getStudentsByCourse("ds")

    call.enqueue(object : Callback<StudentsList> {
        override fun onResponse(call: Call<StudentsList>, response: Response<StudentsList>) {
            if (response.isSuccessful) {
                studentList.value = response.body()?.curso ?: emptyList()
                quantidadeStudents.value = response.body()?.quantidade ?: 0
            } else {
                // Tratar erro na resposta da API
            }
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
            // Tratar erro na chamada à API
        }
    })



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null,
                tint = Color(69, 87, 183),
                modifier = Modifier
                    .size(37.dp)
                    .clickable {
                        val intent = Intent(context, ActivityCours::class.java)
                        context.startActivity(intent)
                    }
            )
            Text(
                text = stringResource(id = R.string.page_studants),
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(69, 87, 183),
                textAlign = TextAlign.End

            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val textStyle = TextStyle(
                fontSize = 15.sp,
                color = Color.White
            )


        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(118.dp)
                    .height(40.dp)
                    .background(color = boxColor1.value, shape = RoundedCornerShape(10.dp))
                    .clickable {

                        if (boxColor1.value == Color(156, 163, 224)) {
                            boxColor1.value = clickedColor


                        } else {
                            boxColor1.value = Color(156, 163, 224)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.all),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .width(118.dp)
                    .height(40.dp)
                    .background(color = boxColor2.value, shape = RoundedCornerShape(10.dp))
                    .clickable {

                        if (boxColor2.value == Color(156, 163, 224)) {
                            boxColor2.value = clickedColor


                        } else {
                            boxColor2.value = Color(156, 163, 224)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.coursing),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

            }
            Box(
                modifier = Modifier
                    .width(118.dp)
                    .height(40.dp)
                    .background(color = boxColor3.value, shape = RoundedCornerShape(10.dp))
                    .clickable {

                        if (boxColor3.value == Color(156, 163, 224)) {
                            boxColor3.value = clickedColor


                        } else {
                            boxColor3.value = Color(156, 163, 224)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.finilizid),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_person_24),
                contentDescription = null,
                tint = Color(69, 87, 183),
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        val intent = Intent(context, ActivityCours::class.java)
                        context.startActivity(intent)
                    }
            )
            Text(
                text = "Estudantes: ${quantidadeStudents.value}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(69, 87, 183),
            )


        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(69, 87, 183))
                    .padding(20.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(studentList.value) { student ->
                        Surface(
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(vertical = 10.dp).clickable { val intent = Intent(context, NoteStudent::class.java)
                                    context.startActivity(intent) },
                            shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp, topEnd = 10.dp, bottomEnd = 10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    model = student.foto,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .width(71.dp)
                                        .height(71.dp)


                                )
                               Column(modifier = Modifier.fillMaxSize().padding(top = 15.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                                   Text(
                                       text = student.nome,
                                       color = Color.Gray,
                                       fontSize = 18.sp,
                                       textAlign = TextAlign.Center

                                   )
                                   Row(modifier = Modifier.fillMaxSize().padding(end = 15.dp, bottom = 5.dp), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {

                                       Text(
                                           text = student.status,
                                           modifier = Modifier
                                               .clip(CircleShape),
                                           color = getColorStatus(student.status),
                                           fontSize = 12.sp,
                                           fontWeight = FontWeight.Bold,
                                           textAlign = TextAlign.End

                                       )
                                   }
                               }
                            }

                        }

                    }
                }
            }

        }
    }
}

fun getColorStatus(status: String): Color{
    return if(status != "Finalizado") {
        Color(51, 71, 176)
    }else{
        Color(229,182,87)
    }
}