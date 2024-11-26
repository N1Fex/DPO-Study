    package ru.fefu.dpo_study

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.fefu.dpo_study.ui.theme.DPOStudyTheme

class RegisterActivity : ComponentActivity() {
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
                        .padding(16.dp, 0.dp)) {
                        TopBar(title = "Регистрация", backButton = {startMainActivity()})
                        Spacer(Modifier.height(16.dp))
                        UsualField(placeholder = "Логин")
                        Spacer(Modifier.height(16.dp))
                        UsualField(placeholder = "Имя или никнейм")
                        Spacer(Modifier.height(16.dp))
                        PasswordField(placeholder = "Пароль")
                        Spacer(Modifier.height(16.dp))
                        PasswordField(placeholder = "Повторите пароль")
                        Spacer(Modifier.height(24.dp))
                        Text(text="Пол", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(16.dp))
                        RadioGenderBlock()
                        Spacer(Modifier.height(32.dp))
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                        ){
                            Text(text="Зарегистрироваться", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(24.dp))
                        BlockPPandUA()
                    }
                }
            }
        }
    }
}

@Composable
fun BlockPPandUA(fontSize : TextUnit = 12.sp, lineHeight : TextUnit = 18.sp, fontWeight : FontWeight = FontWeight(400)){
    val mAnnotatedLinkString = buildAnnotatedString {

        val text = "Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработки персональных данных, а также принимаете пользовательское соглашение"
        val privacyPolicy = "с политикой конфиденциальности"
        val userAgree = "пользовательское соглашение"

        val privacyPolicyStartInd = text.indexOf(privacyPolicy)
        val privacyPolicyEndInd = privacyPolicyStartInd + privacyPolicy.length

        val userAgreeStartInd = text.indexOf(userAgree)
        val userAgreeEndInd = userAgreeStartInd + userAgree.length

        append(text)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
            ), start = privacyPolicyStartInd, end = privacyPolicyEndInd
        )
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
            ), start = userAgreeStartInd, end = userAgreeEndInd
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "",
            start = privacyPolicyStartInd,
            end = privacyPolicyEndInd
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "",
            start = userAgreeStartInd,
            end = userAgreeEndInd
        )

    }
    Text(
        text = mAnnotatedLinkString,
        textAlign = TextAlign.Center,
        fontSize = fontSize,
        lineHeight = lineHeight,
        fontWeight = fontWeight
    )
}

@Composable
fun RadioGenderBlock(selectedColor : Color = Color.Blue, textSize : TextUnit = 16.sp){
    val genders = listOf("Мужской", "Женскиий", "Другой")
    val (selectedOption, onOptionSelected) = remember{mutableStateOf(genders[0])}
    Column(Modifier.selectableGroup()) {
        genders.forEach { text ->
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
            {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) },
                    colors = RadioButtonDefaults.colors(selectedColor=selectedColor)
                )
                Text(text = text, fontSize = textSize)
            }
        }
    }
}
