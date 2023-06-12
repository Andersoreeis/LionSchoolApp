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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolapp.model.Courses
import br.senai.sp.jandira.lionschoolapp.model.CoursesList
import br.senai.sp.jandira.lionschoolapp.model.Student
import br.senai.sp.jandira.lionschoolapp.model.StudentList
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
fun StudentDetails() {

    val student = remember { mutableStateOf(emptyList<Student>()) }
    val context = LocalContext.current
    val matricula = LocalStorage.getFromSharedPreferences(context, "matricula")
    val call = RetrofitFactory().getNoteStudantService().getStudentByRegistration(matricula)
    call.enqueue(object : retrofit2.Callback<StudentList> {
        override fun onResponse(call: Call<StudentList>, response: Response<StudentList>) {
            student.value = response.body()!!.aluno ?: emptyList()
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
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
            Text(
                text = stringResource(id = R.string.page_studant),
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.End

            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(69, 87, 183))
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))


        ) {
            if (firstStudent != null) {

                AsyncImage(
                    model = firstStudent.foto,
                    contentDescription = "",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)


                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                firstStudent?.let {
                    Text(
                        text = firstStudent.nome,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "RA: ${firstStudent.matricula}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
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
    }


}