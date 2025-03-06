package com.tesji.agendaapp_kotlin_jc.views.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CheckSesionView(navController: NavController){
    LaunchedEffect(Unit) {
        if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate("Home")
        }else{
            navController.navigate("Login")
        }
    }
}