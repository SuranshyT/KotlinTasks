package kz.home.pincode

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text: TextView = findViewById(R.id.text)
        val pin: TextView = findViewById(R.id.pin)
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
        }

        buttonOk.setOnClickListener() {
            val correct = "1567"
            if (correct == pin.text.toString()){
                pin.setTextColor(resources.getColor(R.color.stroke))
                Toast.makeText(this, "Correct PIN", Toast.LENGTH_SHORT).show()
            }else{
                pin.setTextColor(resources.getColor(R.color.wrong))
                Toast.makeText(this, "Wrong PIN", Toast.LENGTH_SHORT).show()
            }
        }
    }
}