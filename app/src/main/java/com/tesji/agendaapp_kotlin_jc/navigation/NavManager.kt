package com.tesji.agendaapp_kotlin_jc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tesji.agendaapp_kotlin_jc.viewModels.ContactsViewModel
import com.tesji.agendaapp_kotlin_jc.viewModels.LoginViewModel
import com.tesji.agendaapp_kotlin_jc.viewModels.NotesViewModel
import com.tesji.agendaapp_kotlin_jc.viewModels.RegisterViewModel
import com.tesji.agendaapp_kotlin_jc.views.contacts.AddContactsView
import com.tesji.agendaapp_kotlin_jc.views.contacts.AllContactsView
import com.tesji.agendaapp_kotlin_jc.views.contacts.EditContactView
import com.tesji.agendaapp_kotlin_jc.views.login.CheckSesionView
import com.tesji.agendaapp_kotlin_jc.views.login.LoginView
import com.tesji.agendaapp_kotlin_jc.views.notes.AddNoteView
import com.tesji.agendaapp_kotlin_jc.views.notes.AllNotesView
import com.tesji.agendaapp_kotlin_jc.views.notes.EditNoteView
import com.tesji.agendaapp_kotlin_jc.views.notes.HomeView
import com.tesji.agendaapp_kotlin_jc.views.register.RegisterView

@Composable
fun NavManager(
    loginVM : LoginViewModel,
    registerVM : RegisterViewModel,
    notesVM : NotesViewModel,
    contactVM : ContactsViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "CheckSession"){
        composable("CheckSession"){
            CheckSesionView(navController)
        }


        composable("Login"){
            LoginView(navController, loginVM)
        }
        composable("Register"){
            RegisterView(navController, registerVM)
        }

        composable("Home"){
            HomeView(navController, notesVM)
        }

        composable("AddNote"){
            AddNoteView(navController, notesVM)
        }

        composable("AllNotes"){
            AllNotesView(navController, notesVM)
        }
        composable("EditNote/{idNote}", arguments = listOf(
            navArgument("idNote"){type = NavType.StringType}
        )){
            val idNote = it.arguments?.getString("idNote")?: ""
            EditNoteView(navController, notesVM, idNote )
        }

        composable("AddContact"){
            AddContactsView(navController, contactVM)
        }

        composable("AllContacts"){
            AllContactsView(navController, contactVM)
        }
        composable("EditContact/{idContact}", arguments = listOf(
            navArgument("idContact"){type = NavType.StringType}
        )){
            val idContact = it.arguments?.getString("idContact")?:""
            EditContactView(navController , contactVM, idContact)
        }
    }

}