package kz.home.converter

import android.database.CursorIndexOutOfBoundsException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.Comparator

class MainActivity : AppCompatActivity(R.layout.activity_main), ItemTouchDelegate {

    private val currencyList = listOf("Тенге, Казахстан", "Доллары, США",
        "Лира, Турция", "Евро, ЕС")
    private val flagList = listOf(R.drawable.kz, R.drawable.usa, R.drawable.tur, R.drawable.eu)
    private val valueList = listOf(430.72, 1.00, 15.62, 0.96)
    private var nameList = mutableListOf(
        Currency(currencyList[0], flagList[0], valueList[0]),
        Currency(currencyList[1], flagList[1], valueList[1]),
        Currency(currencyList[2], flagList[2], valueList[2]),
        Currency(currencyList[3], flagList[3], valueList[3])
    )
    private var currencyAdapter: CurrencyAdapter? = null
    private var currencyManager: LinearLayoutManager? = null
    private var position = nameList.size
    private var chosenIndex = 2

    private val itemTouchHelper by lazy {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                val currencyAdapter = recyclerView.adapter as CurrencyAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                currencyAdapter.moveItem(from, to)
                currencyAdapter.notifyItemMoved(from, to)
                val fromData=nameList[from]
                nameList.removeAt(from)
                nameList.add(to, fromData)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val positionToRemove = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        currencyAdapter?.removeItem(positionToRemove)
                        nameList.removeAt(positionToRemove)
                    }
                }
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.alpha = 0.5f
                }
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)

                viewHolder.itemView.alpha = 1.0f
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setSupportActionBar(findViewById(R.id.toolbar))

        setupCurrency(nameList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when(chosenIndex) {
            0 -> menu?.findItem(R.id.menu_sort_alphabetic)?.isChecked = true
            1 -> menu?.findItem(R.id.menu_sort_cost)?.isChecked = true
            else -> Unit
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort_alphabetic -> {
                val sortedNameList = nameList.toMutableList()
                sortedNameList.sortBy {it.name}
                setupCurrency(sortedNameList)
                chosenIndex=0
                true
            }
            R.id.menu_sort_cost -> {
                val sortedNameList = nameList.toMutableList()
                sortedNameList.sortBy {it.value}
                setupCurrency(sortedNameList)
                chosenIndex=1
                true
            }
            R.id.menu_sort_cancel -> {
                setupCurrency(nameList)
                chosenIndex=2
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun startDragging(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    private fun setupCurrency(list: List<Currency>) {
        currencyAdapter = CurrencyAdapter(
            clickListener = {
                Log.d("currency", it.name)
            }, this)
        currencyAdapter?.setHasStableIds(true)
        currencyManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.apply {
            adapter = currencyAdapter
            layoutManager = currencyManager
        }
        recyclerView.setHasFixedSize(true)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        currencyAdapter?.setItems(list)
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
            val indexOfRandom = currencyList.indexOf(randomCurrency)
            nameList.add(Currency(randomCurrency, flagList[indexOfRandom], valueList[indexOfRandom]))
            currencyAdapter?.addItem(Currency(randomCurrency, flagList[indexOfRandom], valueList[indexOfRandom]))
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