package com.tesji.agendaapp_kotlin_jc.views.notes

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tesji.agendaapp_kotlin_jc.R
import com.tesji.agendaapp_kotlin_jc.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, notesVM : NotesViewModel){
    Scaffold (
        topBar = {
            TopAppBar(title ={ Text(text = "Inicio")},
                actions = {
                    IconButton(onClick = {
                        notesVM.lodOut()
                        navController.popBackStack()
                    }) {
                         Icon(
                             imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                             contentDescription = "")
                    }
                })
        }
    ){padding->
        Column (modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally){

            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.icono_notas),
                contentDescription = "Icono de nota")


            //Agregar una nota
            Button(onClick = {
                navController.navigate("AddNote")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))
            ){

                Text(text = "Agregar nota")

            }
            //Ver mis notas
            Button(onClick = {
                navController.navigate("AllNotes")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))){

                Text(text = "Ver mis notas")
            }

            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.icono_contactos),
                contentDescription = "Icono de contactos")
            //Boton para agregar un contacto
            Button(onClick = {
                navController.navigate("AddContact")
            }, modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))) {
                Text(text = "Agregar contacto")
            }
            // Boton para visualizar los contactos
            Button(onClick = {
                navController.navigate("AllContacts")
            },
                modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))) {
                Text(text = "Ver mis contactos")
            }

        }
    }
}