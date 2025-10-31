package es.ua.eps.filmoteca

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class FilmEditActivity : AppCompatActivity() {

    private lateinit var imgPoster: ImageView
    private lateinit var btnTakePhoto: Button
    private lateinit var btnPickImage: Button

    private var lastTempUri: Uri? = null

    // Cámara: guarda en nuestra URI
    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { ok ->
        val uri: Uri? = lastTempUri       // <- variable local inmutable
            if (ok && uri != null) {
            imgPoster.setImageURI(uri)
        }
    }

    // Selector de imágenes (Photo Picker)
    private val pickPhotoLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { imgPoster.setImageURI(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)

        imgPoster = findViewById(R.id.imgPosterEdit)
        btnTakePhoto = findViewById(R.id.btnTakePhoto)
        btnPickImage = findViewById(R.id.btnPickImage)

        // Tomar fotografía
        btnTakePhoto.setOnClickListener {
            val imagesDir = cacheDir.resolve("images").apply { mkdirs() }
            val photoFile = File(imagesDir, "captura_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(
                this,
                "${packageName}.fileprovider",
                photoFile
            )
            lastTempUri = uri
            takePictureLauncher.launch(uri)   // <- usamos la local 'uri'
        }

        // Seleccionar imagen
        btnPickImage.setOnClickListener {
            pickPhotoLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    }
}
