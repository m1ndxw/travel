package com.example.teeest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border

class LoginActivity : ComponentActivity() {

    private lateinit var databaseHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = UserDatabaseHelper(this)

        setContent {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var isRegisterMode by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = if (isRegisterMode) "Регистрация" else "Авторизация")
                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(8.dp),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Имя пользователя", modifier = Modifier.padding(end = 8.dp))
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(8.dp),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Пароль", modifier = Modifier.padding(end = 8.dp))
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (isRegisterMode) {
                            val isRegistered = databaseHelper.registerUser(username, password)
                            if (isRegistered) {
                                Toast.makeText(this@LoginActivity, "Регистрация успешна", Toast.LENGTH_SHORT).show()
                                username = ""
                                password = ""
                            } else {
                                Toast.makeText(this@LoginActivity, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            val isAuthenticated = databaseHelper.authenticateUser(username, password)
                            if (isAuthenticated) {
                                Toast.makeText(this@LoginActivity, "Добро пожаловать!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            } else {
                                Toast.makeText(this@LoginActivity, "Неверные данные!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .shadow(12.dp, shape = RoundedCornerShape(16.dp))
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                ) {
                    Text(text = if (isRegisterMode) "Зарегистрироваться" else "Войти")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isRegisterMode) "Уже есть аккаунт? Войти" else "Нет аккаунта? Регистрация",
                    modifier = Modifier.clickable {
                        isRegisterMode = !isRegisterMode
                    }
                )
            }
        }
    }
}
