package com.karine.eventosapp.ui.screens.addDonation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.karine.eventosapp.api.model.DonationRequest
import java.text.SimpleDateFormat
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDonationScreen(
    viewModel: AddDonationViewModel,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    var uiState by remember { mutableStateOf(DonationRequest()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var datePickerState = rememberDatePickerState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nova Doação") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) { Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null) }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFF17a34a)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.title,
                    onValueChange = { uiState = uiState.copy(title = it) },
                    label = { Text("Título") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.quantity.toString(),
                    onValueChange = { uiState = uiState.copy(quantity = it.toIntOrNull() ?: 0) },
                    label = { Text("Quantidade") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.address,
                    onValueChange = { uiState = uiState.copy(address = it) },
                    label = { Text("Endereço") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.contato,
                    onValueChange = { uiState = uiState.copy(contato = it) },
                    label = { Text("Contato") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true },
                    enabled = false,
                    value = uiState.expirationDate,
                    onValueChange = { },
                    label = { Text("Data de Expiração") },
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.White,
                        disabledTextColor = Color.Black,
                        disabledLabelColor = Color.Black
                    )
                )
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    datePickerState.selectedDateMillis.let {
                                        uiState =
                                            uiState.copy(expirationDate = convertLongToTime(it!!))
                                    }
                                    showDatePicker = false
                                }
                            ) { Text("Selecionar data") }
                        }
                    ) {
                        DatePicker(
                            state = datePickerState
                        )
                    }
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (uiState.title.isNotEmpty() && uiState.quantity > 0 &&
                            uiState.address.isNotEmpty() && uiState.contato.isNotEmpty() &&
                            uiState.expirationDate.isNotEmpty()
                        ) {
                            viewModel.addTransaction(uiState)
                            uiState = DonationRequest()
                        } else {
                            Toast.makeText(
                                context,
                                "Verifique os campos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd.MM.yyy")
    return format.format(date)
}

