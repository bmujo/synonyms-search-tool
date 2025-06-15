package com.test.ui.preview

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

private const val LightModePreview = "Light"
private const val DarkModePreview = "Dark"

private const val LightModePreviewGroup = "Light"
private const val DarkModePreviewGroup = "Dark"

const val LightModePreviewBackground = 0xFFFFFFFF // Solid White
const val DarkModePreviewBackground = 0xFF0E0E0E // Solid Black

@Preview(
    name = LightModePreview,
    group = LightModePreviewGroup,
    showBackground = true,
    backgroundColor = LightModePreviewBackground,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = DarkModePreview,
    group = DarkModePreviewGroup,
    showBackground = true,
    backgroundColor = DarkModePreviewBackground,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class SynonymsSearchToolPreview

@Composable
fun SynonymsSearchToolPreview(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)
) {
    SynonymsSearchToolPreview{
        Surface(
            color = colorScheme.background,
            content = content
        )
    }
}