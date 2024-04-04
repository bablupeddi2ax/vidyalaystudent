package com.example.eskooly.ui.feepiaddetails

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.eskooly.R
import com.example.eskooly.databinding.FragmentFeePaidDetailsBinding
import com.example.eskooly.model.FeesPaidItem

class FeePaidDetailsFragment : Fragment() {
    companion object {
        fun newInstance() = FeePaidDetailsFragment()
    }

    private val viewModel: FeePaidDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding :FragmentFeePaidDetailsBinding=
            DataBindingUtil.inflate(inflater,R.layout.fragment_fee_paid_details,container,false) as FragmentFeePaidDetailsBinding
            val args = arguments
        if(args!=null) {
            arguments.let {
                //get item data and display
            }
        }

        return binding.root
    }
}