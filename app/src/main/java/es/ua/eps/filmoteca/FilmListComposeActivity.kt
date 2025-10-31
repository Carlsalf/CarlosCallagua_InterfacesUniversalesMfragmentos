@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package es.ua.eps.filmoteca

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.ua.eps.filmoteca.model.Film
import es.ua.eps.filmoteca.model.SampleData

class FilmListComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FilmListScreen(
                    films = SampleData.films,
                    onBack = { finish() },
                    onClick = { film ->
                        val intent = Intent(this, FilmDetailComposeActivity::class.java).apply {
                            putExtra("title", film.title)
                            putExtra("year", film.year)
                            putExtra("synopsis", film.synopsis)
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun FilmListScreen(
    films: List<Film>,
    onBack: () -> Unit,
    onClick: (Film) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filmoteca (Compose)") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        // Usamos un texto flecha para no depender de material-icons-extended
                        Text("←")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(films) { film ->
                Column(
                    modifier = Modifier
                        .clickable { onClick(film) }
                        .padding(16.dp)
                ) {
                    Text(film.title, style = MaterialTheme.typography.titleMedium)
                    Text("Año: ${film.year}", style = MaterialTheme.typography.bodyMedium)
                }
                Divider()
            }
        }
    }
}
