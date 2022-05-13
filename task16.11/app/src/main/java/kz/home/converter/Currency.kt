package kz.home.converter

import androidx.annotation.DrawableRes

data class Currency(
    val name: String,
    @DrawableRes val imageRes: Int
)