package com.example.eskooly.ui.liveclass

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.example.eskooly.R
import com.example.eskooly.databinding.FragmentLiveClassBinding

class LiveClassFragment : Fragment() {

    companion object {
        fun newInstance() = LiveClassFragment()
    }

    private val viewModel: LiveClassViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentLiveClassBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_live_class,container,false)

       binding.txtToday.setOnClickListener{
           if(binding.txtToday.background!=ResourcesCompat.getDrawable(resources,R.drawable.meet_cards_selector_bg,null)) {
               binding.txtToday.setBackgroundResource(R.drawable.meet_cards_selector_bg)
               binding.txtTomorrow.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)
               binding.txtAllMeetings.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)


           }
       }
        binding.txtTomorrow.setOnClickListener{
            if(binding.txtTomorrow.background!=ResourcesCompat.getDrawable(resources,R.drawable.meet_cards_selector_bg,null)) {
                binding.txtToday.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)
                binding.txtTomorrow.setBackgroundResource(R.drawable.meet_cards_selector_bg)
                binding.txtAllMeetings.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)
            }
        }
        binding.txtAllMeetings.setOnClickListener{
            if(binding.txtAllMeetings.background!=ResourcesCompat.getDrawable(resources,R.drawable.meet_cards_selector_bg,null)) {
                binding.txtToday.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)
                binding.txtTomorrow.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)
                binding.txtAllMeetings.setBackgroundResource(R.drawable.meet_cards_selector_bg)
            }
        }
        return binding.root
    }

}