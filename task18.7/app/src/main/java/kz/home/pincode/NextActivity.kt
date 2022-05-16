package kz.home.pincode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val buttonShare: Button = findViewById(R.id.button_share)
        val buttonEmail: Button = findViewById(R.id.button_email)
        val buttonCall: Button = findViewById(R.id.button_call)
        val buttonCamera: Button = findViewById(R.id.button_camera)

        buttonShare.setOnClickListener() {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "My name: ${getString(R.string.name)}, " +
                    "email: ${getString(R.string.email)} and phone: ${getString(R.string.phone)}")
            startActivity(Intent.createChooser(shareIntent, "Share"))
        }

        buttonEmail.setOnClickListener() {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            emailIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My contacts")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "My name: ${getString(R.string.name)}, " +
                    "email: ${getString(R.string.email)} and phone: ${getString(R.string.phone)}")
            startActivity(Intent.createChooser(emailIntent, "Send email"))
        }

        buttonCall.setOnClickListener() {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${getString(R.string.phone)}"))
            startActivity(callIntent)
        }

        buttonCamera.setOnClickListener() {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(cameraIntent)
        }
    }
}