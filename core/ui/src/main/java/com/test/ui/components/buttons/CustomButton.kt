package com.test.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.test.ui.preview.SynonymsSearchToolPreview
import com.test.ui.theme.DisabledBlue
import com.test.ui.theme.PrimaryBlue
import com.test.ui.theme.SecondaryBlue

enum class ButtonType {
    Primary,
    Secondary,
    Tertiary
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: ButtonType = ButtonType.Primary,
    enabled: Boolean = true,
    fillWidth: Boolean = true
) {
    val colors = when (type) {
        ButtonType.Primary -> ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            contentColor = Color.White,
            disabledContainerColor = DisabledBlue,
            disabledContentColor = Color.White
        )

        ButtonType.Secondary -> ButtonDefaults.buttonColors(
            containerColor = SecondaryBlue,
            contentColor = Color.White,
            disabledContainerColor = DisabledBlue,
            disabledContentColor = Color.White
        )

        ButtonType.Tertiary -> ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = PrimaryBlue,
            disabledContainerColor = DisabledBlue,
            disabledContentColor = Color.White
        )
    }

    val border = when (type) {
        ButtonType.Primary -> null
        ButtonType.Secondary -> BorderStroke(2.dp, SecondaryBlue)
        ButtonType.Tertiary -> null
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
            .height(56.dp)
            .shadow(
                4.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black,
                spotColor = Color.Black
            ),
        shape = RoundedCornerShape(16.dp),
        colors = colors,
        border = if (enabled) border else null,
        enabled = enabled,
        elevation = null
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}


@SynonymsSearchToolPreview
@Composable
fun CustomButton_Primary_Preview() {
    SynonymsSearchToolPreview {
        CustomButton(
            text = "Primary Button",
            onClick = {},
            type = ButtonType.Primary
        )
    }
}

@SynonymsSearchToolPreview
@Composable
private fun CustomButton_Secondary_Preview() {
    SynonymsSearchToolPreview {
        CustomButton(
            text = "Secondary Button",
            onClick = {},
            type = ButtonType.Secondary
        )
    }
}

@SynonymsSearchToolPreview
@Composable
private fun CustomButton_Tertiary_Preview() {
    SynonymsSearchToolPreview {
        CustomButton(
            text = "Tertiary Button",
            onClick = {},
            type = ButtonType.Tertiary
        )
    }
}