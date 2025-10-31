package es.ua.eps.filmoteca

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FilmDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        val title = intent.getStringExtra("title") ?: ""
        val year = intent.getIntExtra("year", 0)
        val synopsis = intent.getStringExtra("synopsis") ?: ""

        findViewById<TextView>(R.id.txtDetailTitle).text = title
        findViewById<TextView>(R.id.txtDetailYear).text = "Año: $year"
        findViewById<TextView>(R.id.txtDetailSynopsis).text = synopsis

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title?.let { supportActionBar?.title = it }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
