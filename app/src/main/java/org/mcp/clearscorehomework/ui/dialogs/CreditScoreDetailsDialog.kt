package org.mcp.clearscorehomework.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.mcp.clearscorehomework.R
import org.mcp.clearscorehomework.databinding.DialogCreditScoreDetailsBinding
import org.mcp.clearscorehomework.models.CreditScoreModel

// Only some of the details are shown, I'm not sure if the customer would need to see them all.
// Ideally I would have a mapping of display strings to the appropriate data in the model
class CreditScoreDetailsDialog(private val creditScoreModel: CreditScoreModel) : DialogFragment() {

    private var _binding: DialogCreditScoreDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = DialogCreditScoreDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.scoreText.text = String.format(
            resources.getString(R.string.your_credit_score_string),
            creditScoreModel.creditReportInfo.score,
            creditScoreModel.creditReportInfo.maxScoreValue
        )
        binding.scoreBandText.text = String.format(
            resources.getString(R.string.score_band_string),
            creditScoreModel.creditReportInfo.scoreBand
        )
        binding.equifaxScoreBandText.text = String.format(
            resources.getString(R.string.equifax_score_band_string),
            creditScoreModel.creditReportInfo.equifaxScoreBand,
            creditScoreModel.creditReportInfo.equifaxScoreBandDescription
        )
        binding.refText.text = String.format(
            resources.getString(R.string.reference_string),
            creditScoreModel.creditReportInfo.clientRef
        )

        return view
    }
}