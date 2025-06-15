package com.test.ui.components.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.test.ui.R

@Composable
fun CustomHeader(title: String, onNavigateBack: () -> Unit) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp + statusBarPadding)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4FC3F7),
                        Color(0xFF0288D1)
                    )
                )
            )
            .padding(top = statusBarPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = "Back Icon",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.clickable { onNavigateBack() }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                title,
                style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.BottomCenter)
                .background(Color.White.copy(alpha = 0.2f))
        )
    }
}