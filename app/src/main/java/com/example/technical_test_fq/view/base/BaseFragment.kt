package com.example.technical_test_fq.view.base

import android.app.Dialog
import androidx.fragment.app.Fragment
import com.example.technical_test_fq.R
import com.example.technical_test_fq.view.utils.GenericDialogFragment

open class BaseFragment: Fragment() {

    private val dialogLoading by lazy {
        Dialog(this.requireContext()).apply {
            setContentView(
                layoutInflater.inflate(
                    R.layout.dialog_loading,
                    findViewById(android.R.id.content),
                    false
                )
            )
            window?.setBackgroundDrawableResource(R.color.transparent)
            setCancelable(false)
        }
    }

    fun showLoading() {
        if (dialogLoading.isShowing.not()) {
            dialogLoading.show()
        }
    }

    fun hideLoading() {
        dialogLoading.dismiss()
    }

    fun showGenericDialogNotification(
        title: String,
        description: String,
        type: GenericDialogFragment.DialogType,
        positiveTitle: String? = null,
        negativeTitle: String? = null,
        positiveListener: (() -> Unit)? = null,
        negativeListener: (() -> Unit)? = null
    ) {
        val dialog = GenericDialogFragment.newInstance(
            title,
            description,
            type,
            positiveTitle,
            negativeTitle,
            positiveListener,
            negativeListener
        )
        dialog.show(childFragmentManager, "GenericDialog")
    }

}