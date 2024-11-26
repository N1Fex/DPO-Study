package ru.fefu.dpo_study

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.dpo_study.ui.theme.DPOStudyTheme

class MainActivity : ComponentActivity() {

    fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun startRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DPOStudyTheme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp, 80.dp, 16.dp, 0.dp)) {
                        Image(
                            painter = painterResource(R.drawable.ic_registration_bicycles),
                            contentDescription = "background_image",
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(Modifier.height(32.dp))
                        Text(
                            text = "Пожалуй, лучший фитнес трекер в ДВФУ",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            lineHeight = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "Созданный студентами",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier.width(400.dp)
                        )
                        Spacer(Modifier.height(32.dp))
                        Button (
                            onClick = {
                                startRegisterActivity()
                            },
                            modifier = Modifier
                                .size(218.dp, 48.dp)
                                .align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                        ){
                            Text("Зарегистрироваться", fontSize = 16.sp)
                        }
                        Spacer(Modifier.height(16.dp))
                        Button (
                            onClick = {
                                startLoginActivity()
                            },
                            modifier = Modifier
                                .size(200.dp, 48.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ){
                            Text("Уже есть аккаунт?", fontSize = 16.sp, color = Color.Blue, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
