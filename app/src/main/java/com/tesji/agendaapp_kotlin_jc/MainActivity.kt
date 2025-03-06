package com.tesji.agendaapp_kotlin_jc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tesji.agendaapp_kotlin_jc.navigation.NavManager
import com.tesji.agendaapp_kotlin_jc.ui.theme.AgendaAppKotlinJCTheme
import com.tesji.agendaapp_kotlin_jc.viewModels.ContactsViewModel
import com.tesji.agendaapp_kotlin_jc.viewModels.LoginViewModel
import com.tesji.agendaapp_kotlin_jc.viewModels.NotesViewModel
import com.tesji.agendaapp_kotlin_jc.viewModels.RegisterViewModel
import com.tesji.agendaapp_kotlin_jc.views.login.LoginView

class MainActivity : ComponentActivity() {

    val loginVM : LoginViewModel by viewModels()
    val registerVM : RegisterViewModel by viewModels()
    val notesVM : NotesViewModel by viewModels()
    val contactVM : ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AgendaAppKotlinJCTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                  NavManager(loginVM, registerVM, notesVM, contactVM)
                   // LoginView()
                }
            }
        }
    }
}


