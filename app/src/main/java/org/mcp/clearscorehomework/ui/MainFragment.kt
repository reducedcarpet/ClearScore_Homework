package org.mcp.clearscorehomework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import org.mcp.clearscorehomework.databinding.FragmentMainBinding
import org.mcp.clearscorehomework.models.CreditScoreModel
import org.mcp.clearscorehomework.ui.dialogs.CreditScoreDetailsDialog
import org.mcp.clearscorehomework.viewmodels.CreditScoreViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: CreditScoreViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val creditScoreObserver = Observer<CreditScoreModel?> {
        it?: return@Observer

        binding.creditScoreCircle.creditScoreUpdate(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        val model: CreditScoreViewModel by requireActivity().viewModels()
        viewModel = model

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fetchButton.setOnClickListener {
            viewModel.fetchCreditScore()
        }

        binding.creditScoreCircle.setOnClickListener {
            if(viewModel.creditScore.value != null) {
                val creditScoreModel = viewModel.creditScore.value
                val detailDialog = CreditScoreDetailsDialog(creditScoreModel!!)
                val TAG = "CreditScoreDetailsDialog"
                detailDialog.show(this.parentFragmentManager, TAG)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        viewModel.creditScore.observe(requireActivity(), creditScoreObserver)
    }

    override fun onPause() {
        viewModel.creditScore.removeObserver(creditScoreObserver)

        super.onPause()
    }
}