package com.example.eskooly.ui.timetable

import android.content.ContentValues
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.eskooly.ApiService
import com.example.eskooly.R
import com.example.eskooly.RetrofitProvider
import com.example.eskooly.databinding.FragmentMyTimeTableBinding
import com.example.eskooly.model.HomeworkDetailsResponse
import com.example.eskooly.model.TimetableEntry
import com.example.eskooly.model.TimetableResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MyTimeTableFragment : Fragment() {

    companion object {
        fun newInstance() = MyTimeTableFragment()
    }

    private val viewModel: MyTimeTableViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentMyTimeTableBinding>(inflater,R.layout.fragment_my_time_table,container,false)
        val retrofit = RetrofitProvider.getRetrofit()
        val apiService = retrofit.create(ApiService::class.java)
        val request = apiService.getTimetableClassWise("jingle122","jIBTj1CvU")
        request.enqueue(object : Callback<TimetableResponse> {
            override fun onResponse(call: Call<TimetableResponse>, response: Response<TimetableResponse>) {
                if (response.isSuccessful) {
                    val timetableResponse = response.body()
                    val timeTableList = timetableResponse?.timetableEntries
                    if (!timeTableList.isNullOrEmpty()) {
                        val timetableList = timeTableList
                        timetableList.forEach {
                            addRowToTableLayout(it,binding)
                        }

                        Log.d(ContentValues.TAG, "HomeWorkDetails: $timeTableList")
                    } else {
                        Log.e(ContentValues.TAG, "Homework details list is null or empty")
                        Log.d(ContentValues.TAG, "Response Code: ${response.code()}")
                        Log.d(ContentValues.TAG, "Response Body: ${response.body()}")

                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Log.e(ContentValues.TAG, "Error: $errorMessage")
                }
            }


            override fun onFailure(call: Call<TimetableResponse>, t: Throwable) {

                Log.e("HomeworkApiClient", "Failed to fetch homework details", t)
            }
        })
        return binding.root
    }
    private fun addRowToTableLayout(entry: TimetableEntry,binding:FragmentMyTimeTableBinding) {
        val tableRow = TableRow(requireContext())

        // Set background color for alternate rows
        if (binding.tableLayout.childCount % 2 == 0) {
            tableRow.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        }

        val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        tableRow.layoutParams = layoutParams

        val dayTextView = createTextView(entry.day, 1f)
        val startTimeTextView = createTextView(entry.startTime, 1f)
        val endTimeTextView = createTextView(entry.endTime, 1f)
        val subjectTextView = createTextView(entry.subject, 2f)
        val teacherTextView = createTextView(entry.teacher, 1.5f)

        tableRow.addView(dayTextView)
        tableRow.addView(startTimeTextView)
        tableRow.addView(endTimeTextView)
        tableRow.addView(subjectTextView)
        tableRow.addView(teacherTextView)
        binding.tableLayout.addView(tableRow)
    }
    private fun createTextView(text: String, weight: Float): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        textView.gravity = Gravity.CENTER_HORIZONTAL // Center the text horizontally

        val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(10, 16, 10, 16)
        textView.layoutParams = layoutParams
        return textView
    }
}