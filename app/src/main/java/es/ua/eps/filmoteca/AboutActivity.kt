package es.ua.eps.filmoteca

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button as M3Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// Conmutador de modo
enum class Mode { Layouts, Compose }

class AboutActivity : AppCompatActivity() {

    // Cambiar para capturas: Mode.Layouts o Mode.Compose
    private val mode = Mode.Compose

    //  web y tu email
    private val personalUrl = "https://github.com/Carlsalf"
    private val soporteEmail = "mailto:carls.alfred9@gmail.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        when (mode) {
            Mode.Layouts -> initLayouts()
            Mode.Compose -> initCompose()
        }
    }

    // ===== MODO XML =====
    private fun initLayouts() {
        setContentView(R.layout.activity_about)

        findViewById<Button>(R.id.btnWeb).setOnClickListener {
            val uri = Uri.parse(personalUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnSoporte).setOnClickListener {
            val mail = Uri.parse(soporteEmail)
            val intent = Intent(Intent.ACTION_SENDTO, mail)
            // Si quieres añadir asunto:
            // intent.putExtra(Intent.EXTRA_SUBJECT, "Soporte Filmoteca")
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish()
        }
    }

    // ===== MODO COMPOSE =====
    private fun initCompose() {
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AboutScreenCompose(
                        onOpenWeb = {
                            val uri = Uri.parse(personalUrl)
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            startActivity(intent)
                        },
                        onSoporte = {
                            val mail = Uri.parse(soporteEmail)
                            val intent = Intent(Intent.ACTION_SENDTO, mail)
                            startActivity(intent)
                        },
                        onVolver = { finish() }
                    )
                }
            }
        }
    }
}

// UI Compose equivalente
@Composable
fun AboutScreenCompose(
    onOpenWeb: () -> Unit,
    onSoporte: () -> Unit,
    onVolver: () -> Unit
) {
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.autor_texto),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        //  drawable:'carlosalfredo'
        Image(
            painter = painterResource(id = R.drawable.carlosalfredo),
            contentDescription = stringResource(id = R.string.autor_texto),
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
        )

        Spacer(Modifier.height(24.dp))

        M3Button(
            onClick = onOpenWeb,
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = stringResource(id = R.string.btn_web)) }

        Spacer(Modifier.height(12.dp))

        M3Button(
            onClick = onSoporte,
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = stringResource(id = R.string.btn_soporte)) }

        Spacer(Modifier.height(12.dp))

        M3Button(
            onClick = {

                onVolver()
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = stringResource(id = R.string.btn_volver)) }
    }
}
