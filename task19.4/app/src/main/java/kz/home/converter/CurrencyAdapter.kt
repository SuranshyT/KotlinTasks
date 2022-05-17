package kz.home.converter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_choose_currency.view.*

class CurrencyAdapter(
    private val clickListener: (name: Currency) -> Unit,
    private val itemTouchDelegate: ItemTouchDelegate)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = CurrencyViewHolder(inflater, parent)

        viewHolder.itemView.card_view.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                itemTouchDelegate.startDragging(viewHolder)
            }
            return@setOnTouchListener true
        }

        return viewHolder
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CurrencyViewHolder).bind(data[position], clickListener)
    }

    fun addItem(item: Currency) {
        data.add(item)
        notifyItemInserted(data.size)
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(list: List<Currency>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun moveItem(from: Int, to: Int) {
        val fromData = data[from]
        data.removeAt(from)
        if (to < from) {
            data.add(to, fromData)
        } else {
            data.add(to - 1, fromData)
        }
    }
}