package com.tesji.agendaapp_kotlin_jc.views.contacts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tesji.agendaapp_kotlin_jc.components.CardContact
import com.tesji.agendaapp_kotlin_jc.viewModels.ContactsViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AllContactsView(navController: NavController, contactVM : ContactsViewModel){

    LaunchedEffect(Unit) {
        contactVM.getContacts()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Mis contactos") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "")
                    }
                })
        }
    ) {paddingValues ->
        Column (
            modifier =  Modifier.padding(paddingValues),
            horizontalAlignment =  Alignment.CenterHorizontally
            ) {
            val contact by contactVM.contactData.collectAsState()

            LazyColumn {
                items(contact){item->
                    CardContact(
                        names = item.names,
                        email = item.email,
                        address = item.address,
                        phone = item.phone) {

                        navController.navigate("EditContact/${item.idContact}")
                    }
                }
            }

        }

    }
}