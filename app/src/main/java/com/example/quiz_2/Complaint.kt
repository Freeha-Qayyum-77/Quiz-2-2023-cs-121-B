package com.example.quiz_2

data class Complaint(
    val id: String = "",
    val studentName: String = "",
    val rollNumber: String = "",
    val complaintTitle: String = "",
    val category: String = "",
    val priority: String = "",
    val description: String = "",
    val status: String = "Pending",
    val timestamp: Long = 0L  // used to sort latest first
)