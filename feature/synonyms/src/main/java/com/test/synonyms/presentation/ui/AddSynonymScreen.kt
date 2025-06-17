package com.test.synonyms.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.model.Word
import com.test.synonyms.presentation.viewmodel.AddSynonymViewModel
import com.test.ui.components.buttons.ButtonType
import com.test.ui.components.buttons.CustomButton
import com.test.ui.components.dialogs.NewSynonymDialog
import com.test.ui.components.headers.CustomHeader
import com.test.ui.components.inputs.CustomInput
import com.test.ui.preview.SynonymsSearchToolPreview
import com.test.ui.theme.SecondaryBlue
import com.test.ui.theme.TertiaryBlue

@Composable
fun AddSynonymScreen(
    viewModel: AddSynonymViewModel = hiltViewModel(), onNavigateBack: () -> Unit
) {
    val word by viewModel.word.collectAsState()
    val selectedWords by viewModel.selectedWords.collectAsState()
    val sortedWords by viewModel.sortedWords.collectAsState()

    AddSynonymContent(
        word = word,
        sortedWords = sortedWords,
        selectedWords = selectedWords,
        onWordChange = viewModel::onWordChange,
        onToggleSynonym = viewModel::toggleSynonym,
        onSaveClick = {
            viewModel.addSynonyms()
            onNavigateBack()
        },
        onDialogConfirm = viewModel::onConfirmAddSynonym,
        onNavigateBack = { onNavigateBack() })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AddSynonymContent(
    word: String,
    sortedWords: List<Word>,
    selectedWords: Set<Word>,
    onWordChange: (String) -> Unit,
    onToggleSynonym: (Word) -> Unit,
    onSaveClick: () -> Unit,
    onDialogConfirm: (String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var newSynonymInput by remember { mutableStateOf("") }

    var newWordError by remember { mutableStateOf<String?>(null) }
    var newSynonymError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CustomHeader(title = "Add New Word", onNavigateBack = onNavigateBack)
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CustomButton(
                    text = "Save",
                    onClick = {
                        val trimmed = word.trim()
                        newWordError = when {
                            trimmed.isEmpty() -> "Word cannot be empty"
                            trimmed.length < 2 -> "Word must be at least 2 characters"
                            else -> null
                        }

                        if (newWordError == null) {
                            onSaveClick()
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CustomInput(
                value = word,
                onValueChange = {
                    onWordChange(it)
                    newWordError = null
                },
                placeholder = "Enter new word",
                isError = newWordError != null,
                errorMessage = newWordError,
                modifier = Modifier.padding(16.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = TertiaryBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Select synonyms:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(sortedWords, key = { it.value }) { existingWord ->
                            val isSelected = selectedWords.contains(existingWord)
                            val backgroundColor =
                                if (isSelected) SecondaryBlue else Color.Transparent
                            val contentColor =
                                if (isSelected) Color.White else Color.Black

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onToggleSynonym(existingWord) }
                                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                                    .animateItemPlacement()
                            ) {
                                Checkbox(
                                    checked = isSelected,
                                    onCheckedChange = { onToggleSynonym(existingWord) },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color.White,
                                        checkmarkColor = backgroundColor,
                                        uncheckedColor = Color.Black
                                    )
                                )
                                Text(
                                    text = existingWord.value,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = contentColor
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomButton(
                        text = "Add New Synonym",
                        onClick = { showDialog = true },
                        type = ButtonType.Secondary,
                        enabled = sortedWords.isNotEmpty()
                    )
                }
            }
        }

        if (showDialog) {
            NewSynonymDialog(
                value = newSynonymInput,
                onValueChange = {
                    newSynonymInput = it
                },
                onConfirm = {
                    val trimmed = newSynonymInput.trim()
                    newSynonymError = when {
                        trimmed.isEmpty() -> "Input cannot be empty"
                        trimmed.length < 2 -> "Must be at least 2 characters"
                        else -> null
                    }

                    if (newSynonymError == null) {
                        onDialogConfirm(trimmed)
                        showDialog = false
                        newSynonymInput = ""
                    }
                },
                onDismiss = {
                    showDialog = false
                    newSynonymInput = ""
                },
                errorMessage = newSynonymError
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddSynonymContentPreview() {
    SynonymsSearchToolPreview {
        AddSynonymContent(
            word = "clean",
            sortedWords = listOf(
                Word("wash"),
                Word("rinse"),
                Word("scrub"),
                Word("tidy")
            ),
            selectedWords = setOf(Word("wash"), Word("rinse")),
            onWordChange = {},
            onToggleSynonym = {},
            onSaveClick = {},
            onDialogConfirm = {},
            onNavigateBack = {}
        )
    }
}
