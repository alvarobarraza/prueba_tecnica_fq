package com.example.technical_test_fq.view.utils

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.technical_test_fq.databinding.DialogNotificationGenericBinding

class GenericDialogFragment : DialogFragment() {

    private var _binding: DialogNotificationGenericBinding? = null
    private val binding get() = _binding!!

    private var positiveListener: (() -> Unit)? = null
    private var negativeListener: (() -> Unit)? = null

    enum class DialogType {
        SUCCESS, WARNING, ERROR
    }

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_DESCRIPTION = "arg_description"
        private const val ARG_TYPE = "arg_type"
        private const val ARG_POSITIVE_TITLE = "arg_positive_title"
        private const val ARG_NEGATIVE_TITLE = "arg_negative_title"

        fun newInstance(
            title: String?,
            description: String,
            type: DialogType,
            positiveTitle: String?,
            negativeTitle: String?,
            positiveListener: (() -> Unit)? = null,
            negativeListener: (() -> Unit)? = null
        ): GenericDialogFragment {
            val fragment = GenericDialogFragment()
            fragment.positiveListener = positiveListener
            fragment.negativeListener = negativeListener
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            args.putSerializable(ARG_TYPE, type)
            args.putString(ARG_POSITIVE_TITLE, positiveTitle)
            args.putString(ARG_NEGATIVE_TITLE, negativeTitle)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogNotificationGenericBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString(ARG_TITLE) ?: ""
        val description = arguments?.getString(ARG_DESCRIPTION) ?: ""
        val type = arguments?.getSerializable(ARG_TYPE) as DialogType
        val positiveTitle = arguments?.getString(ARG_POSITIVE_TITLE) ?: ""
        val negativeTitle = arguments?.getString(ARG_NEGATIVE_TITLE) ?: ""

        binding.tvTitleDialog.text = title
        binding.tvDescriptionDialog.text = description

        if (positiveTitle != ""){
            binding.btnPositiveDialog.text = positiveTitle
        }
        if (negativeTitle != ""){
            binding.btnNegativeDialog.text = negativeTitle
        }

        val animation = when (type) {
            DialogType.SUCCESS -> "success.json"
            DialogType.WARNING -> "warning.json"
            DialogType.ERROR -> "error.json"
        }

        binding.ltAnimation.apply {
            setAnimation(animation)
            playAnimation()
        }

        binding.btnPositiveDialog.apply {
            visibility = if (positiveListener != null) View.VISIBLE else View.GONE
            setOnClickListener {
                dismiss()
                positiveListener?.invoke()
            }

        }

        binding.btnNegativeDialog.apply {
            visibility = if (negativeListener != null) View.VISIBLE else View.GONE
            setOnClickListener {
                dismiss()
                negativeListener?.invoke()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}