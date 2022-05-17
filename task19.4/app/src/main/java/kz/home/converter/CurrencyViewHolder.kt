package kz.home.converter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_choose_currency.view.*

class CurrencyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_choose_currency, parent, false)) {
    private val flagImageView = itemView.flagImageView
    private val nameTextView = itemView.currencyTextView
    private val hintTextView = itemView.outlinedTextField


    fun bind(item: Currency, clickListener: (name: Currency) -> Unit) {
        nameTextView.text = item.name
        hintTextView.hint = item.name
        flagImageView.setBackgroundResource(item.imageRes)

        nameTextView.setOnClickListener {
            clickListener(item)
        }
    }
}