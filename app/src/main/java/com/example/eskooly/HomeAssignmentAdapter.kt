package com.example.eskooly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.eskooly.model.FeesPaidItem
import com.example.eskooly.model.Homework
import com.example.eskooly.model.HomeworkDetailsResponse


class HomeAssignmentAdapter(private val context: Context, var homeAssignments: List<Homework>, val navController: NavController) :
    RecyclerView.Adapter<HomeAssignmentAdapter.HomeWorkViewHolder>() {
    private lateinit var mOnItemClick : MOnItemClickListener
    interface MOnItemClickListener{
        fun onItemClick(item: FeesPaidItem)

    }

    fun setItems(items: List<Homework>?){
        homeAssignments = items!!
        notifyDataSetChanged()
    }
    inner class HomeWorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtSubjectTeacher: TextView = itemView.findViewById(R.id.text_teacher_subject)
        val txtSubject: TextView = itemView.findViewById(R.id.text_teacher_subject)
        val txtHomeAssignment: TextView = itemView.findViewById(R.id.text_homework_details)
        val txtAssignedOn: TextView = itemView.findViewById(R.id.txtAssignedOn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWorkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homework_item, parent, false)
        return HomeWorkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeAssignments.size
    }

    override fun onBindViewHolder(holder: HomeWorkViewHolder, position: Int) {

        val homeWorkItem : Homework = homeAssignments[position]
        holder.txtAssignedOn.text = homeWorkItem.date
        holder.txtHomeAssignment.text = homeWorkItem.homework
        holder.txtSubject.text = homeWorkItem.subject
        holder.txtSubjectTeacher.text = homeWorkItem.teacher
    }
}
