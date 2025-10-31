package es.ua.eps.filmoteca

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FilmDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)

        // Referencias (ajusta los IDs si en tu XML son otros)
        val imgPoster: ImageView? = findViewById(R.id.imgPoster)
        val txtTitle: TextView? = findViewById(R.id.txtTitle)
        val txtDirector: TextView? = findViewById(R.id.txtDirector) // lo usamos para 2 líneas
        val txtNotes: TextView? = findViewById(R.id.txtNotes)
        val btnImdb: Button? = findViewById(R.id.btnImdb)

        // Datos de ejemplo (o recibidos por Intent)
        val title = getString(R.string.sample_title)
        val directorName = getString(R.string.sample_director)
            .removePrefix("Dirigida por: ").trim()
        val year = 1982
        val genre = "Sci-Fi"
        val format = "Blu-ray"
        val imdbUrl = intent.getStringExtra("imdb_url")
            ?: getString(R.string.sample_imdb)
        val notes = getString(R.string.sample_notes)

        // Imagen: usa tu drawable carlosalfredo.png
        imgPoster?.setImageResource(R.drawable.carlosalfredo)

        // Textos
        txtTitle?.text = title
        val line1 = getString(R.string.directed_by, directorName)
        val line2 = getString(R.string.year_genre_format, year, genre, format)
        txtDirector?.text = "$line1\n$line2"
        txtNotes?.text = notes

        // Botón IMDB
        btnImdb?.setOnClickListener {
            if (imdbUrl.isBlank()) {
                Toast.makeText(this, "Enlace IMDB vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            runCatching {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(imdbUrl)))
            }.onFailure {
                Toast.makeText(this, "No pude abrir el enlace: $imdbUrl", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
