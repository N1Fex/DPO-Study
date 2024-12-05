package ru.fefu.dpo_study.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.dpo_study.MainActivity
import ru.fefu.dpo_study.R
import ru.fefu.dpo_study.ui.theme.DPOStudyTheme

class LoginActivity : ComponentActivity() {
    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DPOStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp, 0.dp)
                    ){
                        TopBar(
                            title="Вход",
                            backButton={startMainActivity()}
                        )
                        Spacer(Modifier.height(24.dp))
                        Image(
                            painter = painterResource(R.drawable.ic_registration_bicycles),
                            contentDescription = "background_image",
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(Modifier.height(16.dp))

                        UsualField("Логин")
                        Spacer(Modifier.height(16.dp))
                        PasswordField()
                        Spacer(Modifier.height(32.dp))
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                        ){
                            Text(text="Войти", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(title: String = "", backButton: () -> Unit){
    Row(){
        Button(
            onClick = backButton,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier)
        {
            Image(
                painter = painterResource(R.drawable.ic_back_arrow),
                contentDescription = "back_button",
                contentScale = ContentScale.FillBounds
            )
        }
        Text(text=title, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Composable
fun PasswordField(placeholder : String = "Пароль"){
    val passwordVisible = remember { mutableStateOf(false)}
    val password = remember { mutableStateOf("") }
    OutlinedTextField(
        value = password.value,
        onValueChange = {password.value = it.trimEnd{ it == '\n'}},
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Text(placeholder) },
        trailingIcon = {
            val image = if (passwordVisible.value)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Localized description for accessibility services
            val description = if (passwordVisible.value) "Скрыть пароль" else "Показать пароль"

            // Toggle button to hide or display password
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Composable
fun UsualField(placeholder: String = "Логин"){
    val text = remember { mutableStateOf("") }
    OutlinedTextField(
        value = text.value,
        onValueChange = {text.value = it.trimEnd('\n',' ')},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        maxLines = 1
    )
}