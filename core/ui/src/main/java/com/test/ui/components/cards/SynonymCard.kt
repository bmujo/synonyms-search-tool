package com.test.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.test.model.Word
import com.test.ui.theme.LightYellow
import com.test.ui.theme.SolidBlack

@Composable
fun SynonymCard(
    word: String,
    synonyms: List<Word>
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = LightYellow),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = word.uppercase(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = SolidBlack
            )
            Text(
                text = "Synonyms",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = synonyms.joinToString(", ") { it.value },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}