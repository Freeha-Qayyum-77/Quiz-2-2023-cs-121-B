package com.example.quiz_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ComplaintListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var textEmpty: TextView
    private lateinit var adapter: ComplaintAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_list)

        recyclerView = findViewById(R.id.recyclerView)
        textEmpty    = findViewById(R.id.textEmpty)

        adapter = ComplaintAdapter(emptyList()) { complaint ->
            // On card click → open detail screen
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id",          complaint.id)
            intent.putExtra("name",        complaint.studentName)
            intent.putExtra("roll",        complaint.rollNumber)
            intent.putExtra("title",       complaint.complaintTitle)
            intent.putExtra("category",    complaint.category)
            intent.putExtra("priority",    complaint.priority)
            intent.putExtra("description", complaint.description)
            intent.putExtra("status",      complaint.status)
            intent.putExtra("timestamp",   complaint.timestamp)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        loadComplaints()
    }

    private fun loadComplaints() {
        // orderBy timestamp descending = latest first
        db.collection("complaints")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val list = result.toObjects(Complaint::class.java)
                if (list.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    textEmpty.visibility    = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    textEmpty.visibility    = View.GONE
                    adapter.updateList(list)
                }
            }
            .addOnFailureListener {
                textEmpty.text       = "Failed to load complaints"
                textEmpty.visibility = View.VISIBLE
            }
    }
}