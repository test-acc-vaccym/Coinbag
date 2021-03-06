package uk.co.richyhbm.coinbag.view_model

import android.databinding.ObservableField
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable

class WalletRowViewModel {
    val walletId = ObservableField<Int>(0)
    val cryptoIcon = ObservableField<Drawable>(ColorDrawable(Color.TRANSPARENT))
    val walletName = ObservableField<String>("")
    val walletBalance = ObservableField<String>("")
    val walletValue = ObservableField<String>("")
    val walletAddress = ObservableField<String>("")
    val walletType = ObservableField<String>("")
}