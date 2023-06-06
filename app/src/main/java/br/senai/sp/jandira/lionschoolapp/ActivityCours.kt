package br.senai.sp.jandira.lionschoolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
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
import br.senai.sp.jandira.lionschoolapp.model.Students
import br.senai.sp.jandira.lionschoolapp.model.StundentsList
import br.senai.sp.jandira.lionschoolapp.service.RetrofitFactory
import br.senai.sp.jandira.lionschoolapp.service.StudentsService
import br.senai.sp.jandira.lionschoolapp.ui.theme.LionSchoolAppTheme
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ActivityCours : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolAppTheme {
                CoursesInitPage()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CoursesInitPage() {

    val coursesList = remember { mutableStateOf(emptyList<Courses>()) }

    val call = RetrofitFactory().getCoursesService().getCourses()
    call.enqueue(object : retrofit2.Callback<CoursesList> {
        override fun onResponse(call: Call<CoursesList>, response: Response<CoursesList>) {
            coursesList.value = response.body()!!.cursos ?: emptyList()
        }

        override fun onFailure(call: Call<CoursesList>, t: Throwable) {
            println("Error")
        }
    })



    val studentList = remember { mutableStateOf(emptyList<Students>()) }

    val callStudents = RetrofitFactory().getStudentsService().getStudentsByCouses()
    callStudents.enqueue(object : retrofit2.Callback<StundentsList?> {
        override fun onResponse(call: Call<StundentsList>, response: Response<StundentsList>) {
            studentList.value = response.body()?.students ?: emptyList()
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
            println("Error")
        }
    })




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(69, 87, 183))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.page_init),
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))

            var textFieldValue = remember { mutableStateOf("") }

            val textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )



            OutlinedTextField(
                value = textFieldValue.value,
                onValueChange = { textFieldValue.value = it },
                label = {
                    Text(
                        text = "",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_search_24),
                        contentDescription = "Icon de Phone",
                        tint = Color.White,
                    )
                },
                modifier = Modifier
                    .width(370.dp)
                    .height(58.dp)
                    .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(16.dp)),

                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color(173, 208, 226, 51),
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Gray
                ),
                textStyle = textStyle
            )
        }


        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            contentAlignment = Alignment.BottomEnd
        ) {           // Outros elementos da tela

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(210, 210, 210))
                    .padding(20.dp)


            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_library_books_24),
                        contentDescription = "Icon de Phone",
                        tint = Color(69, 87, 183),
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.cours),
                        fontSize = (24.sp),
                        fontWeight = FontWeight(600),
                        color = Color(69, 87, 183)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(coursesList.value) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                                .padding(10.dp)
                                .shadow(
                                    elevation = 4.dp, // ajuste o valor conforme necessário
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                        Box(
                                            modifier = Modifier
                                                .height(150.dp)
                                                .width(150.dp)
                                                .clip(CircleShape)
                                                .background(Color(62, 81, 187))
                                                .wrapContentSize(Alignment.Center)
                                        ) {
                                            Text(
                                                text = it.sigla,
                                                fontSize = 64.sp,
                                                fontWeight = FontWeight(700),
                                                color = Color.White
                                            )
                                        }
                                        Text(text = it.nome.substring(5), modifier = Modifier.fillMaxWidth().padding(10.dp), textAlign = TextAlign.Center)

                                }
                            }
                        }
                    }
                }


            }


        }
    }


}