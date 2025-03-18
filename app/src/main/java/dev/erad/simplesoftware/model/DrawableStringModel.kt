package dev.erad.simplesoftware.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrawableStringModel(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)