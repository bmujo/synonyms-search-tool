package com.test.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.test.ui.preview.SynonymsSearchToolPreview
import com.test.ui.theme.DisabledBlue
import com.test.ui.theme.PrimaryBlue

@Composable
fun CustomIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    disabled: Boolean = false,
    backgroundColor: Color = PrimaryBlue,
    contentColor: Color = Color.White
) {
    val effectiveBackgroundColor = if (disabled) DisabledBlue else backgroundColor
    val effectiveContentColor = if (disabled) Color.White.copy(alpha = 0.38f) else contentColor

    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(effectiveBackgroundColor)
            .clickable(enabled = !disabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = effectiveContentColor
        )
    }
}

@SynonymsSearchToolPreview
@Composable
private fun CustomIconButton_Preview() = SynonymsSearchToolPreview {
    CustomIconButton(
        icon = Icons.Default.Favorite,
        onClick = {}
    )
}

@SynonymsSearchToolPreview
@Composable
private fun CustomIconButton_Disabled_Preview() = SynonymsSearchToolPreview {
    CustomIconButton(
        icon = Icons.Default.Favorite,
        onClick = {},
        disabled = true
    )
}