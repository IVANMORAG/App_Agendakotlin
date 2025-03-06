package com.tesji.agendaapp_kotlin_jc.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tesji.agendaapp_kotlin_jc.R
import com.tesji.agendaapp_kotlin_jc.viewModels.LoginViewModel


@Composable
fun LoginView(navController: NavHostController, loginVM: LoginViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Login de usuario",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )

        Image(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(15.dp),
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Icono de login"
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            value = email ,
            onValueChange = {email = it},
            label = { Text(text= "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            value = password ,
            onValueChange = {password = it},
            label = { Text(text= "Password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(20.dp))
// Boton de login
        Button(
           onClick = {
               loginVM.login(email, password){
                   navController.navigate("Home")
               }
           },
           modifier = Modifier.fillMaxWidth()
               .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))){
           Text(text = "Ingresar")
        }
// Boton de registro
        Button(
            onClick = {
                navController.navigate("Register")
            },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))){
            Text(text = "Registrarme")
        }
    }
}