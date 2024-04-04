package com.example.eskooly.ui.updatelogindetails

import android.content.res.Resources
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.example.eskooly.R
import com.example.eskooly.databinding.FragmentUpdateLoginDetailsBinding

class UpdateLoginDetailsFragment : Fragment() {
    private var  isVisibilityBigOn = false
    private var isVisibilitySmallOn  = false
    companion object {
        fun newInstance() = UpdateLoginDetailsFragment()
    }

    private val viewModel: UpdateLoginDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentUpdateLoginDetailsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_update_login_details,container,false,null)
        setOnClickListeners(binding)

        return binding.root
    }

    private fun setOnClickListeners(binding: FragmentUpdateLoginDetailsBinding) {

        binding.updatell.setOnClickListener {
            updateEditTexts(binding)
        }

        binding.imgBtnPasswordVisibility.setOnClickListener {
            toggleEditTextPasswordVisibility(binding)
        }

    //hiding text password using tag of textview when we set text we need to set that to text and tag both ten we can toggle visibility
        binding.txtShowPassword.text = "23098465259874"
        binding.txtShowPassword.tag = "23098465259874"    // or just pass the textview text to tag

        binding.imgBtnPasswordVisibilitySmall.setOnClickListener{
            togglePasswordTextVisibility(binding)

        }
    }

    private fun togglePasswordTextVisibility(binding: FragmentUpdateLoginDetailsBinding) {
        if(isVisibilitySmallOn) {
            binding.imgBtnPasswordVisibilitySmall.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.baseline_visibility_off_12,
                    null
                )
            )
            binding.txtShowPassword.text = binding.txtShowPassword.tag.toString()
            isVisibilitySmallOn=!isVisibilitySmallOn
        }else
        {
            binding.imgBtnPasswordVisibilitySmall.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.baseline_visibility_12,
                    null
                )
            )
            binding.txtShowPassword.text = hidePassword(binding.txtShowPassword.text.toString())
            isVisibilitySmallOn=!isVisibilitySmallOn
        }
    }

    private fun updateEditTexts(binding: FragmentUpdateLoginDetailsBinding) {

        val newPassword = binding.edtUserPassword.text.toString()
        val newUsername = binding.edtUser.text.toString()
        if (isUserNameChangedOrBlank(newUsername,binding)) {
            binding.txtShowUserName.text = newUsername
            showToast("Username Updated Successfully")
            // Update database with new username
        }

        if (isUserPasswordChangedOrBlank(newPassword,binding)) {
            binding.txtShowPassword.text = newPassword
            showToast("Password Updated Successfully")
            // Update database with new password
        } else if (newPassword.isBlank()) {
            showToast("Password cannot be empty")
        }
    }
    private fun isUserNameChangedOrBlank(newUsername:String,binding:FragmentUpdateLoginDetailsBinding):Boolean{
        return newUsername != binding.txtShowUserName.text && !binding.edtUser.text.isNullOrBlank()
    }
    private fun isUserPasswordChangedOrBlank(newPassword:String,binding: FragmentUpdateLoginDetailsBinding):Boolean{
        return newPassword.isNotBlank() && newPassword!=binding.txtShowPassword.text
    }
    private fun toggleEditTextPasswordVisibility(binding: FragmentUpdateLoginDetailsBinding) {
        binding.edtUserPassword.inputType = if (isVisibilityBigOn) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        binding.imgBtnPasswordVisibility.setImageResource(if (isVisibilityBigOn) R.drawable.baseline_visibility_off_24 else R.drawable.baseline_visibility_24)
        isVisibilityBigOn = !isVisibilityBigOn
    }


    private fun hidePassword(text: String): CharSequence {
        val res =  "".plus('*').repeat(text.length)
        return res
    }
    private fun showToast(message: String) {
        parentFragment?.context?.let { context ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

}