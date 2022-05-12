package kz.home.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setupCurrency()
    }

    private fun setupCurrency() {
        val currencyAdapter = CurrencyAdapter(
            clickListener = {
                Log.d("currency", it.name)
            }
        )
        val currencyManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.apply {
            adapter = currencyAdapter
            layoutManager = currencyManager
        }

        val nameList = listOf(
            Currency(resources.getString(R.string.kz), R.drawable.kz),
            Currency(resources.getString(R.string.usa), R.drawable.usa),
            Currency(resources.getString(R.string.tur), R.drawable.tur),
            Currency(resources.getString(R.string.eu), R.drawable.eu)
        )
        currencyAdapter.setItems(nameList)
    }
}