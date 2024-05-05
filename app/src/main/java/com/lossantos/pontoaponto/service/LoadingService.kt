package com.lossantos.pontoaponto.service

import android.app.Dialog
import android.content.Context
import androidx.compose.ui.platform.ComposeView
import com.lossantos.pontoaponto.feature.auth.dialog.DialogLoading

object LoadingService {
    private var progressDialog: Dialog? = null

    fun showLoading(context: Context) {
        if (progressDialog == null) {
            progressDialog = Dialog(context)
            progressDialog?.setCancelable(false)
            progressDialog?.setCanceledOnTouchOutside(false)

            val composeView = ComposeView(context)
            composeView.setContent {
                DialogLoading()
            }
            progressDialog?.setContentView(composeView)

            progressDialog?.show()
        }
    }

    fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}
