package com.example.eskooly.ui.messaging

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.eskooly.R
import com.example.eskooly.databinding.FragmentMessagingBinding

class MessagingFragment : Fragment() {
    var isComposeMessageUiVisible :Boolean= false
    companion object {
        fun newInstance() = MessagingFragment()
    }

    private val viewModel: MessagingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentMessagingBinding =DataBindingUtil.inflate(inflater,R.layout.fragment_messaging,container,false)
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,listOf<String>("Admin","Specific Teacher"))
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener= object:OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position==1) {
                    binding.edtSpecificTeacher.visibility = View.VISIBLE
                }
                if(position==0){
                    binding.edtSpecificTeacher.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.imageButtonComposeNewMessage.setOnClickListener{
            if(isComposeMessageUiVisible){

                hideComposeMessageViews(binding)
                isComposeMessageUiVisible =false
            }else {
                    showComposeMessageView(binding)
                isComposeMessageUiVisible =true
            }
            binding.linearLayoutAttachFile.setOnClickListener{
                Toast.makeText(requireContext(),"Attach a file",Toast.LENGTH_SHORT).show()
            }
            binding.linearLayoutSendMessage.setOnClickListener{
                Toast.makeText(requireContext(),"Sent a msg",Toast.LENGTH_SHORT).show()
                hideComposeMessageViews(binding)
                isComposeMessageUiVisible =false
            }
        }
        return binding.root
    }

    private fun showComposeMessageView(binding: FragmentMessagingBinding) {
        binding.edtWriteMessage.visibility = View.VISIBLE
        binding.linearLayoutAttachFile.visibility = View.VISIBLE
        binding.llSPinner.visibility = View.VISIBLE
        binding.txtSendMessageToTag.visibility = View.VISIBLE
        binding.linearLayoutSendMessage.visibility = View.VISIBLE


    }

    private fun hideComposeMessageViews(binding: FragmentMessagingBinding) {
        binding.edtWriteMessage.visibility = View.GONE
        binding.linearLayoutAttachFile.visibility = View.GONE
        binding.llSPinner.visibility = View.GONE
        binding.txtSendMessageToTag.visibility = View.GONE
        binding.linearLayoutSendMessage.visibility = View.GONE
        binding.edtSpecificTeacher.visibility = View.GONE

    }
}