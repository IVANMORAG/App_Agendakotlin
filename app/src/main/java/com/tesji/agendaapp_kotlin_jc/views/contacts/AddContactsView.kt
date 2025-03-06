package com.tesji.agendaapp_kotlin_jc.views.contacts

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tesji.agendaapp_kotlin_jc.R
import com.tesji.agendaapp_kotlin_jc.viewModels.ContactsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactsView(navController: NavController, contactVM: ContactsViewModel) { // NavController como parámetro

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar Contacto") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack() // Regresar a la vista anterior
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var names by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }
            var phone by remember { mutableStateOf("") }
            val context = LocalContext.current

            Image(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.icono_agregar_contacto),
                contentDescription = "Icono agregar contacto"
            )

            // Nombres
            OutlinedTextField(
                value = names,
                onValueChange = { names = it },
                label = { Text(text = "Nombres") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Dirección
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text(text = "Dirección") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Teléfono
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(text = "Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Botón para agregar un contacto
            Button(
                onClick = {
                    if (names.isNotEmpty() && email.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()) {
                        contactVM.saveContact(
                            names, email, address, phone
                        ) {
                            navController.popBackStack()
                            Toast.makeText(context, "Contacto guardado", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(context, "Verifique nuevamente la informacion", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))
            ) {
                Text(text = "Agregar contacto")
            }
        }
    }
}
