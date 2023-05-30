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
import br.senai.sp.jandira.lionschoolapp.service.RetrofitFactory
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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(69, 87, 183))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_search_24),
                        contentDescription = "Icon de Phone",
                        tint = Color.White,
                    )
                },
                modifier = Modifier
                    .width(370.dp)
                    .height(58.dp),
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
                    .background(Color.White)
                    .padding(36.dp)


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
                Spacer(modifier = Modifier.height(50.dp))

            }
            LazyColumn {
                items(coursesList.value) {
                    Card(
                        modifier = Modifier
                            .height(250.dp)
                            .padding(20.dp)
                            .width(350.dp),
                        backgroundColor = Color(69, 87, 183),
                        border = BorderStroke(width = 3.dp, color = Color(255, 196, 71)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .wrapContentSize(Alignment.Center)
                        ) {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                        .background(Color(217, 217, 217))
                                        .wrapContentSize(Alignment.Center)
                                ) {
                                    Text(
                                        text = it.sigla,
                                        fontSize = 64.sp,
                                        fontWeight = FontWeight(700),
                                        color = Color(255, 196, 71),
                                        modifier = Modifier.border( width = 1.dp, color = Color.White)

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
