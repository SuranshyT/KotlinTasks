package kz.home.pincode

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.TextUtils
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


private const val KEY_PIN = "PIN"
private const val KEY_COLOR = "COLOR"

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var pin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text)
        pin = findViewById(R.id.pin)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonC: Button = findViewById(R.id.buttonC)
        val button0: Button = findViewById(R.id.button0)
        val buttonOk: Button = findViewById(R.id.buttonOk)

        button0.setOnClickListener() {
            text.text = ""
            pin.append("0")
        }
        button1.setOnClickListener() {
            text.text = ""
            pin.append("1")
        }
        button2.setOnClickListener() {
            text.text = ""
            pin.append("2")
        }
        button3.setOnClickListener() {
            text.text = ""
            pin.append("3")
        }
        button4.setOnClickListener() {
            text.text = ""
            pin.append("4")
        }
        button5.setOnClickListener() {
            text.text = ""
            pin.append("5")
        }
        button6.setOnClickListener() {
            text.text = ""
            pin.append("6")
        }
        button7.setOnClickListener() {
            text.text = ""
            pin.append("7")
        }
        button8.setOnClickListener() {
            text.text = ""
            pin.append("8")
        }
        button9.setOnClickListener() {
            text.text = ""
            pin.append("9")
        }

        buttonC.setOnClickListener() {
            var pincode = pin.text.toString()
            if (!TextUtils.isEmpty(pincode)){
                pincode = pincode.dropLast(1)
                pin.text = pincode
            }
            pin.setTextColor(resources.getColor(R.color.text))
        }

        buttonC.setOnLongClickListener() {
            pin.text = ""
            pin.setTextColor(resources.getColor(R.color.text))
            return@setOnLongClickListener true
        }

        buttonOk.setOnClickListener() {
            val correct = "1567"
            if (correct == pin.text.toString()){
                pin.setTextColor(resources.getColor(R.color.stroke))
                Toast.makeText(this, "Correct PIN", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NextActivity::class.java)
                startActivity(intent)
            }else{
                pin.setTextColor(resources.getColor(R.color.wrong))
                val animationShake = AnimationUtils.loadAnimation(this, R.anim.shake)
                pin.startAnimation(animationShake)
                val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(500)
                }
                Toast.makeText(this, "Wrong PIN", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_PIN, pin.text.toString())
        outState.putInt(KEY_COLOR, pin.currentTextColor)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pin.text = savedInstanceState.getString(KEY_PIN)
        pin.setTextColor(savedInstanceState.getInt(KEY_COLOR))
        if (pin.text.toString().isNotEmpty()){
            text.text = ""
        }
    }
}