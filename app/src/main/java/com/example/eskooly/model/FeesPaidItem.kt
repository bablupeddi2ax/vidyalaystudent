package com.example.eskooly.model

import android.os.Bundle

data class FeesPaidItem(
    val _v: Int,
    val _id: String,
    val admissionFee: Int,
    val booksFee: Int,
    val classId: String,
    val className: String,
    val deposit: Int,
    val discount: Int,
    val examFee: Int,
    val feeMonth: String,
    val fineFee: Int,
    val institutionId: String,
    val otherFee: Int,
    val paymentDate: String,
    val previousBalance: Int,
    val registrationFee: Int,
    val registrationNumber: String,
    val remainingBalance: Int,
    val studentname: String,
    val totalAmount: Int,
    val transactionNumber: String,
    val transportationFee: Int,
    val tuitionFee: Int,
    val uniformFee: Int
) {
    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt("__v", _v)
        bundle.putString("_id", _id)
        bundle.putInt("admissionFee", admissionFee)
        bundle.putInt("booksFee", booksFee)
        bundle.putString("classId", classId)
        bundle.putString("className", className)
        bundle.putInt("deposit", deposit)
        bundle.putInt("discount", discount)
        bundle.putInt("examFee", examFee)
        bundle.putString("feeMonth", feeMonth)
        bundle.putInt("fineFee", fineFee)
        bundle.putString("institutionId", institutionId)
        bundle.putInt("otherFee", otherFee)
        bundle.putString("paymentDate", paymentDate)
        bundle.putInt("previousBalance", previousBalance)
        bundle.putInt("registrationFee", registrationFee)
        bundle.putString("registrationNumber", registrationNumber)
        bundle.putInt("remainingBalance", remainingBalance)
        bundle.putString("studentname", studentname)
        bundle.putInt("totalAmount", totalAmount)
        bundle.putString("transactionNumber", transactionNumber)
        bundle.putInt("transportationFee", transportationFee)
        bundle.putInt("tuitionFee", tuitionFee)
        bundle.putInt("uniformFee", uniformFee)
        return bundle
    }
}
