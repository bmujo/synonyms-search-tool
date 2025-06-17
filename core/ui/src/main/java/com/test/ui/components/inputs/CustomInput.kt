package com.test.ui.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.test.ui.components.buttons.CustomIconButton
import com.test.ui.preview.SynonymsSearchToolPreview
import com.test.ui.theme.SolidBlack
import com.test.ui.theme.SolidWhite
import com.test.ui.theme.TertiaryBlue

@Composable
fun CustomInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Placeholder",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    maxLength: Int? = null,
    showSearchIcon: Boolean = false,
    onSearchClick: (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    if (maxLength == null || newValue.length <= maxLength) {
                        onValueChange(newValue)
                    }
                },
                placeholder = { Text(text = placeholder) },
                isError = isError,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = if (showSearchIcon) 8.dp else 0.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = TertiaryBlue,
                    unfocusedContainerColor = TertiaryBlue,
                    focusedTextColor = SolidBlack,
                    unfocusedTextColor =SolidBlack,
                    focusedPlaceholderColor = SolidBlack.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = SolidBlack.copy(alpha = 0.6f),
                    focusedIndicatorColor = TertiaryBlue,
                    unfocusedIndicatorColor = SolidWhite,
                    cursorColor = SolidBlack,
                ),
                singleLine = true,
                keyboardOptions = keyboardOptions
            )

            if (showSearchIcon && onSearchClick != null) {
                CustomIconButton(
                    icon = Icons.Default.Search,
                    onClick = onSearchClick
                )
            }
        }

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}

@SynonymsSearchToolPreview
@Composable
private fun CustomInput_Preview() {
    SynonymsSearchToolPreview {
        CustomInput(
            value = "Custom input",
            onValueChange = {},
        )
    }
}