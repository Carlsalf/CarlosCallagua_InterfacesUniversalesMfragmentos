package es.ua.eps.filmoteca

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import es.ua.eps.filmoteca.model.SampleData
import es.ua.eps.filmoteca.ui.FilmAdapter

class FilmListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // status/nav bar
        setContentView(R.layout.activity_film_list)

        // APLICA PADDING POR INSETS (evita que la toolbar quede debajo del status bar)
        val root = findViewById<android.view.View>(R.id.rootFilmList)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, bars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        // Toolbar y flecha atrás
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Filmoteca (XML)"
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Recycler
        val rv = findViewById<RecyclerView>(R.id.recyclerFilms)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = FilmAdapter(SampleData.films) { film ->
            val i = Intent(this, FilmDetailActivity::class.java).apply {
                putExtra("title", film.title)
                putExtra("year", film.year)
                putExtra("synopsis", film.synopsis)
            }
            startActivity(i)
        }
    }

    // Soporte adicional al botón home por si el sistema lo envía vía options item
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed(); true
        } else super.onOptionsItemSelected(item)
}
