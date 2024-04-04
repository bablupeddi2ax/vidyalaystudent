package com.example.eskooly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.eskooly.model.FeesPaid
import com.example.eskooly.model.FeesPaidItem


class FeePaidRecyclerViewAdapter(private val context: Context, var feeList: FeesPaid, val navController:NavController) :
    RecyclerView.Adapter<FeePaidRecyclerViewAdapter.FeeViewHolder>() {
        private lateinit var mOnItemClick : MOnItemClickListener
    interface MOnItemClickListener{
        fun onItemClick(item: FeesPaidItem)

    }
    var _itemClickListener: MOnItemClickListener? = null
    fun setOnItemClickListener(listener:MOnItemClickListener){
        _itemClickListener = listener
    }
    fun setItems(items: FeesPaid){
        feeList = items
        notifyDataSetChanged()
    }
    inner class FeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTransactionAddedDate: TextView = itemView.findViewById(R.id.transactionAddedDate)
        val txtPaidOn: TextView = itemView.findViewById(R.id.txtPaidOn)
        val txtFeePaid: TextView = itemView.findViewById(R.id.txtFeePaid)
        val imgBtnViewDetails:ImageButton = itemView.findViewById(R.id.imgBtnViewDetails)
        init {
            itemView.setOnClickListener{
                val position= adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    val clickedItem = feeList[position]
                    _itemClickListener?.onItemClick(clickedItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.paid_receipt_item_layout, parent, false)
        return FeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feeList.size
    }

    override fun onBindViewHolder(holder: FeeViewHolder, position: Int) {

        val feeItem : FeesPaidItem = feeList[position]
        holder.txtTransactionAddedDate.text = feeItem.paymentDate
        holder.txtPaidOn.text = "Paid On: ${feeItem.paymentDate}"
        holder.txtFeePaid.text = "Amount: ${feeItem.totalAmount}"


    }
}
