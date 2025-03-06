package com.tesji.agendaapp_kotlin_jc.views.contacts

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun EditContactView(navController: NavController, contactVM : ContactsViewModel,
                    idContact : String){
    LaunchedEffect(Unit) {
        contactVM.getContactById(idContact)
    }
    val state = contactVM.state
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Informacion del Contacto") },
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


            val context = LocalContext.current



            // Nombres
            OutlinedTextField(
                value = state.names,
                onValueChange = {contactVM.onValue(it, "names") },
                label = { Text(text = "Nombres") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Email
            OutlinedTextField(
                value = state.email,
                onValueChange = {contactVM.onValue(it, "email") },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Dirección
            OutlinedTextField(
                value = state.address,
                onValueChange = {contactVM.onValue(it, "address") },
                label = { Text(text = "Dirección") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Teléfono
            OutlinedTextField(
                value = state.phone,
                onValueChange = {contactVM.onValue(it, "phone") },
                label = { Text(text = "Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            // Botón para editar un contacto
            Button(
                onClick = {
                    contactVM.editContact(idContact){
                        navController.popBackStack()
                        Toast.makeText(context, "Contacto Actualizado", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))) {
                Text(text = "Confirmar")
            }

            // Boton para eliminar un contacto
            Button(onClick = {
                contactVM.deleteContact(idContact){
                    navController.popBackStack()
                    Toast.makeText(context, "Contacto Eliminado", Toast.LENGTH_SHORT).show()
                }
            },
                modifier =Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))) {
                Text(text = "Eliminar Contacto")
            }

            Button(
                onClick = {
                    val phone = state.phone.trim()
                    if (phone.isEmpty() || phone.any { !it.isDigit() }) {
                        Toast.makeText(context, "Número de teléfono inválido", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))
            ) {
                Text(text = "Realizar llamada")
            }

            Button(
                onClick = {
                    val phone = state.phone.trim()
                    if (phone.isEmpty() || phone.any { !it.isDigit() }) {
                        Toast.makeText(context, "Número de teléfono inválido", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("smsto:$phone") // 'smsto:' asegura que se abra una app de SMS
                            putExtra("sms_body", "Hola, este es un mensaje enviado desde mi aplicación de notas .")
                        }
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4BF651))
            ) {
                Text(text = "Enviar SMS")
            }

        }
    }

}