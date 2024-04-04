package com.example.eskooly.ui.paidfeerecipt


import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eskooly.ApiService
import com.example.eskooly.FeePaidRecyclerViewAdapter

import com.example.eskooly.R
import com.example.eskooly.RetrofitProvider
import com.example.eskooly.databinding.FragmentPaidFeeReciptBinding
import com.example.eskooly.model.FeesPaid
import com.example.eskooly.model.FeesPaidItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PaidFeeReceiptFragment : Fragment(),FeePaidRecyclerViewAdapter.MOnItemClickListener{
    private var _noRecordsFound = true
    private lateinit var adapter:FeePaidRecyclerViewAdapter
    private var feePaid =FeesPaid()
    private lateinit var retrofit:Retrofit
    private lateinit var apiService: ApiService

    companion object {
        fun newInstance() = PaidFeeReceiptFragment()
    }

    private val viewModel: PaidFeeReciptViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding:FragmentPaidFeeReciptBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_paid_fee_recipt,container,false)
        retrofit = RetrofitProvider.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        adapter = FeePaidRecyclerViewAdapter(requireContext(),feePaid,findNavController())
        binding.recyclerViewPaidFees.adapter = adapter
        binding.recyclerViewPaidFees.layoutManager = LinearLayoutManager(requireContext())
        loadData(apiService,adapter,binding)

        binding.txtPaid.setOnClickListener{
            if(binding.txtPaid.background!= ResourcesCompat.getDrawable(resources,R.drawable.meet_cards_selector_bg,null)) {
                binding.txtPaid.setBackgroundResource(R.drawable.meet_cards_selector_bg)
                binding.txtUnpaid.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)

                updateRecyclerView("paid",binding,adapter)

            }
        }
        binding.txtUnpaid.setOnClickListener{
            if(binding.txtUnpaid.background!= ResourcesCompat.getDrawable(resources,R.drawable.meet_cards_selector_bg,null)) {
                binding.txtPaid.setBackgroundResource(R.drawable.three_card_btn_not_selected_bg)
                binding.txtUnpaid.setBackgroundResource(R.drawable.meet_cards_selector_bg)

                updateRecyclerView("unpaid",binding,adapter)
            }
        }

        adapter.setOnItemClickListener(object :FeePaidRecyclerViewAdapter.MOnItemClickListener{
            override fun onItemClick(item: FeesPaidItem) {
                val bundle = Bundle()

                bundle.putAll(item.toBundle())
                findNavController().navigate(
                    R.id.action_paidFeeReceiptFragment_to_feePaidDetailsFragment,bundle,
                    NavOptions.Builder().build(),
                    )
            }

        })

        return binding.root
    }
    private fun loadData(
        service: ApiService,
        adapter: FeePaidRecyclerViewAdapter,
        binding: FragmentPaidFeeReciptBinding
    ){

        try{
            CoroutineScope(Dispatchers.IO).launch {
                this@PaidFeeReceiptFragment.adapter = FeePaidRecyclerViewAdapter(requireContext(),feePaid,findNavController())

                val response = service.getPaidFeesList("priya342").enqueue(object:Callback<FeesPaid>{
                    override fun onResponse(call: Call<FeesPaid>, response: Response<FeesPaid>) {
                        if(response.isSuccessful){
                            feePaid = response.body()!!
                            adapter.setItems(feePaid)

                            this@PaidFeeReceiptFragment.adapter.notifyDataSetChanged()
                            Log.i("fee paid success","")
                        }
                    }

                    override fun onFailure(call: Call<FeesPaid>, t: Throwable) {
                        Log.i("fee paid failure","")
                    }
                })

                Log.i("response",response.toString())

            }

        }catch (e:Exception){
            Log.i("exc",e.message.toString())
        }

    }
    private fun updateRecyclerView(type:String, binding: FragmentPaidFeeReciptBinding, adapter: FeePaidRecyclerViewAdapter){
        when(type){
            "unpaid"->{
               //call unpaid api
                binding.recyclerViewPaidFees.visibility = View.GONE
                binding.cardNoRecordsFound.visibility = View.VISIBLE
            }
            "paid"->{
                binding.recyclerViewPaidFees.visibility = View.VISIBLE
                binding.cardNoRecordsFound.visibility = View.GONE
                adapter.setItems(feePaid)
                adapter.notifyDataSetChanged()
            }
        }
    }
    override fun onItemClick(item: FeesPaidItem) {
        val navHost = parentFragmentManager.findFragmentById(R.id.nav_home)
        val c = navHost?.childFragmentManager?.fragments?.get(0)
        val nc = findNavController()
        if(nc!=null) {

            nc.navigate(R.id.action_paidFeeReceiptFragment_to_feePaidDetailsFragment)
            Log.i("is not null","ot null")
        }else{
            Log.i("null","navnull")
        }


    }
}