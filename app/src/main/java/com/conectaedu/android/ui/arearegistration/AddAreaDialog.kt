package com.conectaedu.android.ui.arearegistration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.conectaedu.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAreaDialog(onAddClick: (name: String) -> Unit, onClose: () -> Unit) {
    var name by remember { mutableStateOf("") }
    val isValidName = name.isNotEmpty()
    val focusManager = LocalFocusManager.current

    AlertDialog(onDismissRequest = onClose) {
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(text = stringResource(id = R.string.areas_registration_area_add_name))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.TextFields, contentDescription = null)
                },
                isError = !isValidName,
                supportingText = {
                    if (!isValidName) {
                        Text(text = stringResource(id = R.string.areas_registration_area_add_name_error))
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.clearFocus()
                }),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { onAddClick(name) },
                    enabled = isValidName,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.areas_registration_area_add_button))
                }

                OutlinedButton(
                    onClick = { onClose() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.areas_registration_area_cancel_name))
                }
            }
        }
    }
}