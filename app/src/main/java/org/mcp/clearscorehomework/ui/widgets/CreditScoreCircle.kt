package org.mcp.clearscorehomework.ui.widgets

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.*
import org.mcp.clearscorehomework.R
import org.mcp.clearscorehomework.databinding.CreditScoreWidgetBinding
import org.mcp.clearscorehomework.models.CreditScoreModel

/*
If I had more time, I would have found the correct font, used a custom painting method, to get the
text strings to appear closer together and to deal with the coloured arc that visually shows the
credit score. Currently it decides to not start from the top for large values and also doesn't have
nicely rounded ends.
 */
class CreditScoreCircle(mContext: Context, attrs: AttributeSet?) : FrameLayout(mContext, attrs) {

    private var binding: CreditScoreWidgetBinding =
        CreditScoreWidgetBinding.inflate(LayoutInflater.from(context))

    lateinit var creditScore: MutableLiveData<CreditScoreModel?>

    init {
        addView(binding.root)
    }

    fun creditScoreUpdate(creditScore: CreditScoreModel?) {
        creditScore ?: return

        binding.scoreText.text = creditScore.creditReportInfo.score.toString()
        binding.postScoreText.text = String.format(
            resources.getString(R.string.out_of_700),
            creditScore.creditReportInfo.maxScoreValue
        )

        binding.creditScoreProgressBar.progress = creditScore.creditReportInfo.score
        binding.creditScoreProgressBar.max = creditScore.creditReportInfo.maxScoreValue
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.creditScoreProgressBar.min = creditScore.creditReportInfo.minScoreValue
        }
        binding.creditScoreProgressBar.invalidate()
    }
}