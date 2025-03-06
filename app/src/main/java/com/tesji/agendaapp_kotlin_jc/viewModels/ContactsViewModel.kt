package com.tesji.agendaapp_kotlin_jc.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.tesji.agendaapp_kotlin_jc.models.ContactModel
import com.tesji.agendaapp_kotlin_jc.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class ContactsViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore //Base de datos

    private val _contactData = MutableStateFlow<List<ContactModel>>(emptyList())
    val contactData : StateFlow<List<ContactModel>> = _contactData

    var state by mutableStateOf(ContactModel())
    private set

    fun onValue(value : String, text : String){
        when(text){
            "names" -> state = state.copy(names = value)
            "email" -> state = state.copy(names = value)
            "address" -> state = state.copy(names = value)
            "phone" -> state = state.copy(names = value)
        }
    }

    fun saveContact (
        names : String,
        email : String,
        address : String,
        phone : String,
        onSuccess :()-> Unit
    ){

        val myEmail = auth.currentUser?.email //Obtenemos nuestro email

        viewModelScope.launch(Dispatchers.IO) {
            try{
               val contact =  hashMapOf(
                   "myEmail" to myEmail.toString().trim(),
                   "names" to names,
                   "email" to email,
                   "address" to address,
                   "phone" to phone
               )
                firestore.collection("Contactos").add(contact)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener{ e->
                        Log.d("Error al registrar el contacto", "No registrado")
                    }

            }catch (e: Exception){
                Log.d("Error al registrar el contacto", "No registrado")
            }
        }

    }

    fun getContacts(){
        val myEmail = auth.currentUser?.email

        firestore.collection("Contactos")
            .whereEqualTo("myEmail", myEmail.toString())
            .orderBy("names", Query.Direction.ASCENDING)
            .addSnapshotListener{query , error->
                if (error!=null){
                    return@addSnapshotListener
                }

                val contacts = mutableListOf<ContactModel>()
                if (query!=null){
                    for (contact in query){
                        val myContacts = contact.toObject(ContactModel::class.java)
                            .copy(idContact = contact.id)
                        contacts.add(myContacts)
                    }
                }

                _contactData.value = contacts

            }

    }

    fun getContactById(idContact : String){
        firestore.collection("Contactos")
            .document(idContact)
            .addSnapshotListener{snapShot, error ->
                if (snapShot!=null){
                    val contact = snapShot.toObject(ContactModel::class.java)
                    state = state.copy(
                        names = contact?.names?: "",
                        email = contact?.email?: "",
                        address = contact?.address?: "",
                        phone = contact?.phone?: "",
                    )
                }
            }
    }

    fun editContact(idContacto : String, onSuccess: () -> Unit){
        viewModelScope.launch (Dispatchers.IO){
            try{
                val editContact = hashMapOf(
                    "names" to state.names,
                    "email" to state.email,
                    "address" to state.address,
                    "phone" to state.phone,
                )

                firestore.collection("Contactos").document(idContacto)
                    .update(editContact as Map<String, Any>)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener{ e->
                        Log.d("Error", "${e.message}")
                    }
            }catch (e:Exception){
                Log.d("Error", "${e.message}")

            }
        }
    }

    fun deleteContact(idContact: String, onSuccess: () -> Unit){
        viewModelScope.launch ( Dispatchers.IO ){
            try {
                firestore.collection("Contactos").document(idContact)
                    .delete()
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener{ e->
                        Log.d("Error", "${e.message}")
                    }
            }catch (e:Exception){
                Log.d("Error", "${e.message}")
            }
        }
    }
}