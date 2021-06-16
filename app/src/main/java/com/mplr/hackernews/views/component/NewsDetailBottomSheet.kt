package com.mplr.hackernews.views.component

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mplr.hackernews.R
import com.mplr.hackernews.databinding.LayoutNewsDetailBinding
import com.mplr.hackernews.models.HitModel

class NewsDetailBottomSheet(private val hitModel: HitModel) : BottomSheetDialogFragment() {

    private var binding: LayoutNewsDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true
        setupHeight()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        binding = LayoutNewsDetailBinding.inflate(
            LayoutInflater.from(requireContext()),
            null,
            false
        )

        configBottomSheet()
        setupListener()
        startWebPage()

        return binding?.root
    }

    private fun configBottomSheet() {
        binding?.root?.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_bottom_sheet
        )
    }

    private fun startWebPage() {
        val contentPage = Base64.encodeToString(
            ("<div style='text-align: justify;'>" + hitModel.commentText + "</div>")?.toByteArray(),
            Base64.NO_PADDING
        )
        val webPage = hitModel.url ?: hitModel.storyUrl

        binding?.progressBar?.visibility = View.VISIBLE


        binding?.webNews?.apply {

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    binding?.progressBar?.visibility = View.GONE
                }
            }


            if (webPage.isNullOrEmpty()) {
                this.loadData(contentPage, "text/html", "base64")
            } else {
                this.loadUrl(webPage)
            }
        }
    }

    private fun setupListener() {
        binding?.backContainer?.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupHeight() {
        val displayMetrics = activity?.resources?.displayMetrics
        val height = displayMetrics?.heightPixels
            ?: resources.getDimension(R.dimen.modal_height)
                .toInt()
        val maxHeight = height * HEIGHT

        binding?.root?.viewTreeObserver?.addOnGlobalLayoutListener {
            binding?.container?.setLayoutHeight(maxHeight.toFloat())
        }

        binding?.root?.post {
            val params =
                (binding?.root?.parent as View).layoutParams as CoordinatorLayout.LayoutParams

            params.behavior?.let { behavior ->
                if (behavior is BottomSheetBehavior<*>) {
                    behavior.peekHeight = maxHeight.toInt()
                    behavior.skipCollapsed = true
                }
            }
        }
    }

    override fun getTheme(): Int = R.style.CustomSheetDialog

    companion object {
        private const val HEIGHT = .90

        @JvmStatic
        fun newInstance(hitModel: HitModel): NewsDetailBottomSheet {
            return NewsDetailBottomSheet(hitModel)
        }
    }
}