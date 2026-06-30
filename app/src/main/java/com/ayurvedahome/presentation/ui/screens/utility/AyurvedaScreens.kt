package com.ayurvedahome.presentation.ui.screens.utility

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ayurvedahome.ads.AdManager
import com.ayurvedahome.domain.content.AyurvedaRemedy
import com.ayurvedahome.domain.content.AyurvedaCatalog
import com.ayurvedahome.presentation.ui.components.AdBanner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyurvedaHomeScreen(
    onCategory: (String) -> Unit,
    onRemedy: (String) -> Unit,
    adManager: AdManager,
    adsEnabled: Boolean
) {
    var query by rememberSaveable { mutableStateOf("") }
    val searchResults = AyurvedaCatalog.search(query)
    val isSearching = query.isNotBlank()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Ayurveda Home") }) }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            AdBanner(adManager = adManager, adsEnabled = adsEnabled)
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    DisclaimerCard()
                    Spacer(Modifier.height(4.dp))
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Search remediesâ€¦") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        singleLine = true
                    )
                }
                if (isSearching) {
                    if (searchResults.isEmpty()) {
                        item { Text("No remedies match your search.", style = MaterialTheme.typography.bodyMedium) }
                    } else {
                        items(searchResults, key = { it.id }) { remedy ->
                            RemedyListCard(remedy) { onRemedy(remedy.id) }
                        }
                    }
                } else {
                    item {
                        Text("Remedy Categories", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                    }
                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.height(520.dp)
                        ) {
                            items(AyurvedaCatalog.categories, key = { it.id }) { category ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onCategory(category.id) },
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                ) {
                                    Column(Modifier.padding(12.dp)) {
                                        Text(category.name, fontWeight = FontWeight.Bold)
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            "${AyurvedaCatalog.remediesForCategory(category.id).size} remedies",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DisclaimerCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
    ) {
        Text(
            AyurvedaCatalog.DISCLAIMER,
            Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyurvedaCategoryScreen(categoryId: String, onRemedy: (String) -> Unit, onBack: () -> Unit) {
    val category = AyurvedaCatalog.category(categoryId) ?: return
    val remedies = AyurvedaCatalog.remediesForCategory(categoryId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                DisclaimerCard()
                Spacer(Modifier.height(8.dp))
                Text(category.description, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
            }
            items(remedies, key = { it.id }) { remedy ->
                RemedyListCard(remedy) { onRemedy(remedy.id) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyurvedaRemedyDetailScreen(remedyId: String, onBack: () -> Unit) {
    val remedy = AyurvedaCatalog.remedy(remedyId) ?: return
    val categoryName = AyurvedaCatalog.category(remedy.categoryId)?.name.orEmpty()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(remedy.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                DisclaimerCard()
                if (!remedy.hindiName.isNullOrBlank()) {
                    Text(remedy.hindiName, style = MaterialTheme.typography.titleMedium)
                }
                Text(categoryName, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            }
            item { SectionTitle("Ingredients") }
            items(remedy.ingredients) { ingredient ->
                Text("â€¢ $ingredient", modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
            }
            item { SectionTitle("Steps") }
            items(remedy.steps.withIndex().toList()) { (index, step) ->
                Text("${index + 1}. $step", modifier = Modifier.padding(bottom = 4.dp))
            }
            item {
                SectionTitle("Usage Note")
                Card(Modifier.fillMaxWidth()) {
                    Text(remedy.usageNote, Modifier.padding(12.dp))
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
}

@Composable
private fun RemedyListCard(remedy: AyurvedaRemedy, onClick: () -> Unit) {
    Card(Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Column(Modifier.padding(12.dp)) {
            Text(remedy.name, fontWeight = FontWeight.Bold)
            if (!remedy.hindiName.isNullOrBlank()) {
                Text(remedy.hindiName, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                remedy.ingredients.take(2).joinToString(", "),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        }
    }
}
