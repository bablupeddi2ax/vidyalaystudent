package com.example.eskooly.ui.admissionletter

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.eskooly.R
import com.example.eskooly.databinding.FragmentAdmissionLetterBinding

class AdmissionLetterFragment : Fragment() {
    companion object {
        fun newInstance() = AdmissionLetterFragment()
    }

    private val viewModel: AdmissionLetterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentAdmissionLetterBinding = DataBindingUtil.inflate(
            layoutInflater,R.layout.fragment_admission_letter,container,false
        )
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)


        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        val maxWidthPercentage = 20
        val maxHeightPercentage = 20
        val minimumHeightPercentage = 12
        val minimumWidthPercentage = 12

        val maxWidth = screenWidth * maxWidthPercentage / 100
        val maxHeight = screenHeight * maxHeightPercentage / 100
        val minimumHeight = screenHeight * minimumHeightPercentage / 100
        val minimumWidth = screenWidth * minimumWidthPercentage / 100

        binding.circleImgViewProfile.maxWidth = maxWidth
        binding.circleImgViewProfile.maxHeight = maxHeight
        binding.circleImgViewProfile.minimumHeight = minimumHeight
        binding.circleImgViewProfile.minimumWidth = minimumWidth

        val lp = ConstraintLayout.LayoutParams(maxWidth, maxHeight)
        lp.horizontalBias = 0.5F
        lp.verticalBias = 0.12F // Adjust this value as needed
//        binding.circleImgViewProfile.layoutParams = lp
//        binding.circleImgViewProfile.updateLayoutParams{true}


        // add data to table
        binding.txtClass.text = "10"
        binding.txtAdmissionDate.text ="12-03-24"
        binding.txtStudentName.text = "Simpson"
        binding.txtPassword.text = "23476508969"
        binding.txtUserName.text = "23476508969"
        binding.txtRegistrationId.text = "398722435"
        binding.txtAccountStatus.text = "Active"
        setClickListeners(binding)
        return binding.root
    }

    private fun setClickListeners(binding:FragmentAdmissionLetterBinding) {
        binding.printAdmissionLetterll.setOnClickListener{
            Toast.makeText(requireContext(),"Printing Collect Your Admission Letter",Toast.LENGTH_SHORT).show()
        }
    }
}