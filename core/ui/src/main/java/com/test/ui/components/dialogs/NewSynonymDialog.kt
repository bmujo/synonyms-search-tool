package com.test.ui.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.test.ui.components.buttons.ButtonType
import com.test.ui.components.buttons.CustomButton
import com.test.ui.components.inputs.CustomInput

@Composable
fun NewSynonymDialog(
    value: String,
    onValueChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    errorMessage: String? = null
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.secondary,
        onDismissRequest = onDismiss,
        title = { Text("New Synonym") },
        text = {
            CustomInput(
                value = value,
                onValueChange = onValueChange,
                placeholder = "Enter new synonym",
                isError = errorMessage != null,
                errorMessage = errorMessage,
            )
        },
        confirmButton = {
            CustomButton(
                text = "Add",
                onClick = onConfirm,
                fillWidth = false
            )
        },
        dismissButton = {
            CustomButton(
                text = "Cancel",
                type = ButtonType.Tertiary,
                onClick = onDismiss,
                fillWidth = false
            )
        }
    )
}
