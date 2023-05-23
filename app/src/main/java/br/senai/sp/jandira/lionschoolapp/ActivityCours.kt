package br.senai.sp.jandira.lionschoolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolapp.ui.theme.LionSchoolAppTheme

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
  Column(modifier = Modifier
      .fillMaxSize()
      .background(Color(69, 87, 183))) {
      Column(modifier = Modifier
          .fillMaxWidth()
          .padding(25.dp)) {
          Text(text = stringResource(id = R.string.page_init), textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)

          OutlinedTextField(
              value = "", // Valor do texto inserido
              onValueChange = { /* Função para atualizar o valor do texto */ },
              label = { Text(text = stringResource(id = R.string.all), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 20.sp, color = Color.White) },
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
                  backgroundColor = Color(173,208,226,51),
                  cursorColor = Color.Blue,
                  focusedBorderColor = Color.Transparent,
                  unfocusedBorderColor = Color.Gray
              )
          )



      }
  }
}