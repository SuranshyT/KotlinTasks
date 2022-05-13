package kz.home.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val currencyList = listOf("Тенге, Казахстан", "Доллары, США",
        "Лира, Турция", "Евро, ЕС")
    private val flagList = listOf(R.drawable.kz, R.drawable.usa, R.drawable.tur, R.drawable.eu)
    private var nameList = mutableListOf(
        Currency(currencyList[0], flagList[0]),
        Currency(currencyList[1], flagList[1]),
        Currency(currencyList[2], flagList[2]),
        Currency(currencyList[3], flagList[3])
    )
    private var currencyAdapter: CurrencyAdapter? = null
    private var currencyManager: LinearLayoutManager? = null
    private var position = nameList.size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setupCurrency()
    }

    private fun setupCurrency() {
        currencyAdapter = CurrencyAdapter(
            clickListener = {
                Log.d("currency", it.name)
            }
        )
        currencyManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.apply {
            adapter = currencyAdapter
            layoutManager = currencyManager
        }

        currencyAdapter?.setItems(nameList)
        buttonAdd()
    }

    private fun Group.setAllOnClickListener(listener: View.OnClickListener?) {
        referencedIds.forEach { id ->
            rootView.findViewById<View>(id).setOnClickListener(listener)
        }
    }

    private fun buttonAdd() {
        group.setAllOnClickListener(View.OnClickListener {
            val randomCurrency = currencyList.random()
            val indexOfRandomFlag = currencyList.indexOf(randomCurrency)
            currencyAdapter?.addItem(Currency(randomCurrency, flagList[indexOfRandomFlag]))
            position += 1
            scrollToBottom()
        })
    }

    private fun scrollToBottom() {
        val smoothScroller = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int = LinearSmoothScroller.SNAP_TO_START
        }
        smoothScroller.targetPosition = position
        currencyManager?.startSmoothScroll(smoothScroller)
    }
}