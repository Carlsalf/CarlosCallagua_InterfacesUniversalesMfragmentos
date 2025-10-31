package es.ua.eps.filmoteca

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.ua.eps.filmoteca.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Insets opcional si tu root tiene id @id/main (lo mantiene seguro)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        fun launchOrToast(clazz: Class<*>) {
            runCatching { startActivity(Intent(this, clazz)) }
                .onFailure {
                    Toast.makeText(
                        this,
                        "No pude abrir: ${clazz.simpleName}. Revisa el Manifest/clase.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        // About
        binding.btnAbout.setOnClickListener { launchOrToast(AboutActivity::class.java) }

        // Lista XML/Recycler
        binding.btnListaXml.setOnClickListener { launchOrToast(FilmListActivity::class.java) }

        // Lista Compose
        binding.btnListaCompose.setOnClickListener { launchOrToast(FilmListComposeActivity::class.java) }

        // ===== XML =====
        binding.btnDetailXml.setOnClickListener { launchOrToast(FilmDataActivity::class.java) }
        binding.btnEditXml.setOnClickListener { launchOrToast(FilmEditActivity::class.java) }

        // ===== Compose =====
        binding.btnDetailCompose.setOnClickListener { launchOrToast(FilmDetailComposeActivity::class.java) }
        binding.btnEditCompose.setOnClickListener { launchOrToast(FilmEditComposeActivity::class.java) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_about -> { startActivity(Intent(this, AboutActivity::class.java)); true }
        R.id.action_web -> { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Carlsalf"))); true }
        R.id.action_share -> {
            val i = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Prueba la app Filmoteca")
            }
            startActivity(Intent.createChooser(i, "Compartir vía")); true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
