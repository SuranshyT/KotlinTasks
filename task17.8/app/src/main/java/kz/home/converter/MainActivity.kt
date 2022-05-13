package kz.home.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), ItemTouchDelegate {

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

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        currencyAdapter?.removeItem(position)
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

        setupCurrency()
    }

    override fun startDragging(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    private fun setupCurrency() {
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