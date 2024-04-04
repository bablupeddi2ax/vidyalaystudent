    package com.example.eskooly.ui.home

    import android.graphics.Color
    import android.os.Bundle
    import android.text.Spannable
    import android.text.SpannableString
    import android.text.style.AbsoluteSizeSpan
    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.core.content.res.ResourcesCompat
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.commit
    import androidx.lifecycle.ViewModelProvider
    import androidx.navigation.fragment.findNavController
    import com.example.eskooly.R
    import com.example.eskooly.databinding.FragmentHomeBinding
    import com.example.eskooly.ui.feepiaddetails.FeePaidDetailsFragment
    import com.github.mikephil.charting.data.PieData
    import com.github.mikephil.charting.data.PieDataSet
    import com.github.mikephil.charting.data.PieEntry
    import java.time.LocalDate
    import java.time.Month
    import java.util.Calendar
    import java.time.LocalDateTime

    class HomeFragment : Fragment() {

        private var _binding: FragmentHomeBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val homeViewModel =
                ViewModelProvider(this)[HomeViewModel::class.java]

            _binding = FragmentHomeBinding.inflate(inflater, container, false)
            val root: View = binding.root

            val st = SpannableString("\uD83D\uDC4B Welcome Anuj Pandey at Student portal")
            val startIndex = st.toString().indexOf("Anuj Pandey")
            val endIndex = startIndex+ "Anuj Pandey".length
            st.setSpan(AbsoluteSizeSpan(15,true),startIndex,endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.txtWelcomeMessgae.text = st
            binding.include1.imgTop.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.piechart,null))
            binding.include1.txtTitle.text = "Attendance Report "
            binding.include2.imgTop.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.baseline_edit_24,null))
            binding.include2.txtTitle.text = "Pending Fees Report"
            binding.txtTotalPendingAmount.text = "$90000"
            binding.txtViewDetails.setOnClickListener{

                findNavController().navigate(R.id.action_nav_home_to_paidFeeReceiptFragment)

            }
            binding.include3.imgTop.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.testreport,null))
            binding.include3.txtTitle.text = "Class Tests Report"
            binding.txtProgress.text = "0%"
            binding.subjectMarksPercentageProgressBar.progress=0
            binding.subjectMarksPercentageProgressBar.invalidate()

            binding.progressBarOverallAbsent.progress = 30
            binding.progressBarCurrentMonthAbsent.progress=20
            binding.currentMonth.text = getCurrentMonth()


            showPieChart()
            return root
        }

        private fun getCurrentMonth(): CharSequence {
            val calendar = Calendar.getInstance()
            val months = listOf(Month.entries.toTypedArray())
            return months[0][calendar.get(Calendar.MONTH)].toString()
        }


        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
        private fun showPieChart() {
            val pieEntries = ArrayList<PieEntry>()
            val label = "type"

            //initializing data
            val typeAmountMap: MutableMap<String, Int> = HashMap()
            typeAmountMap["Presents"] = 200
            typeAmountMap["Leaves"] = 230
            typeAmountMap["Absents"] = 100


            //initializing colors for the entries
            val colors = ArrayList<Int>()
            colors.add(Color.parseColor("#6787f4"))
            colors.add(Color.parseColor("#9698d5"))
            colors.add(Color.parseColor("#fb8792"))

            //input data and fit data into pie chart entry
            for (type in typeAmountMap.keys) {
                pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
            }

            //collecting the entries with label name
            val pieDataSet = PieDataSet(pieEntries, "")
            //setting text size of the value
            pieDataSet.valueTextSize = 12f
            //providing color list for coloring different entries
            pieDataSet.colors = colors
            //grouping the data set from entry to chart
            val pieData = PieData(pieDataSet)
            //showing the value of the entries, default true if not set
            pieData.setDrawValues(true)

            binding.attendancePieChart.setData(pieData)
            binding.attendancePieChart.invalidate()
            binding.attendancePieChart.holeRadius = 0f;
            binding.attendancePieChart.transparentCircleRadius = 0f;
            binding.attendancePieChart.setDrawEntryLabels(false)
            binding.attendancePieChart.description.text=""
            binding.attendancePieChart.legend.isEnabled=false
    // Toggle visibility of labels on click
            binding.attendancePieChart.setOnClickListener {
                binding.attendancePieChart.setDrawEntryLabels(!binding.attendancePieChart.isDrawEntryLabelsEnabled)
                binding.attendancePieChart.invalidate()
            }
            binding.attendancePieChart.startLayoutAnimation()

        }
    }