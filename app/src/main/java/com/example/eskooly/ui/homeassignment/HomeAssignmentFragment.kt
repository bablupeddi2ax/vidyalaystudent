package com.example.eskooly.ui.homeassignment

import android.content.ContentValues.TAG
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eskooly.ApiService
import com.example.eskooly.HomeAssignmentAdapter
import com.example.eskooly.R
import com.example.eskooly.RetrofitProvider
import com.example.eskooly.databinding.FragmentHomeAssignmentBinding
import com.example.eskooly.model.Homework
import com.example.eskooly.model.HomeworkDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HomeAssignmentFragment : Fragment() {
    private lateinit var adapter: HomeAssignmentAdapter
    private var homeWorks = listOf<Homework>()
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    companion object {
        fun newInstance() = HomeAssignmentFragment()
    }

    private val viewModel: HomeAssignmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentHomeAssignmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_assignment,container,false)
        retrofit = RetrofitProvider.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)
        adapter = HomeAssignmentAdapter(requireContext(),homeWorks,findNavController())
        binding.recyclerViewHomeAssignment.adapter = adapter
        binding.recyclerViewHomeAssignment.layoutManager = LinearLayoutManager(requireContext())
        getHomeworkDetails()
        return binding.root
    }

    private fun getHomeworkDetails() {
        val call = apiService.getHomeworkDetails("345678", "2024-02-25", "A001","T001")
        call.enqueue(object : Callback<HomeworkDetailsResponse> {
            override fun onResponse(call: Call<HomeworkDetailsResponse>, response: Response<HomeworkDetailsResponse>) {
                if (response.isSuccessful) {
                    val homeworkDetailsResponse = response.body()
                    val homeworkDetailsList = homeworkDetailsResponse?.homework
                    if (!homeworkDetailsList.isNullOrEmpty()) {
                        // Update the dataset of the adapter
                       adapter.setItems(homeworkDetailsList)
                        // Notify the adapter of the dataset change
                        adapter.notifyDataSetChanged()

                        val homeworkDetails = homeworkDetailsList[0]
                        Log.d(TAG, "HomeWorkDetails: $homeworkDetails")
                    } else {
                        Log.e(TAG, "Homework details list is null or empty")
                        Log.d(TAG, "Response Code: ${response.code()}")
                        Log.d(TAG, "Response Body: ${response.body()}")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Log.e(TAG, "Error: $errorMessage")
                }
            }

            override fun onFailure(call: Call<HomeworkDetailsResponse>, t: Throwable) {
                Log.e("HomeworkApiClient", "Failed to fetch homework details", t)
            }
        })
    }



}