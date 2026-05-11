package com.example.quiz_2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etRoll: EditText
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerPriority: Spinner
    private lateinit var btnSubmit: Button
    private lateinit var btnViewComplaints: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName          = findViewById(R.id.etName)
        etRoll          = findViewById(R.id.etRoll)
        etTitle         = findViewById(R.id.etTitle)
        etDescription   = findViewById(R.id.etDescription)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        spinnerPriority = findViewById(R.id.spinnerPriority)
        btnSubmit       = findViewById(R.id.btnSubmit)
        btnViewComplaints = findViewById(R.id.btnViewComplaints)

        // Setup category spinner
        val categories = listOf("Select Category","IT","Library","Transport",
            "Hostel","Accounts","Examination","Cafeteria","Administration")
        spinnerCategory.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, categories)

        // Setup priority spinner
        val priorities = listOf("Select Priority","Low","Medium","High","Urgent")
        spinnerPriority.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, priorities)

        btnSubmit.setOnClickListener { submitComplaint() }

        btnViewComplaints.setOnClickListener {
            startActivity(Intent(this, ComplaintListActivity::class.java))
        }
    }

    private fun submitComplaint() {
        val name     = etName.text.toString().trim()
        val roll     = etRoll.text.toString().trim()
        val title    = etTitle.text.toString().trim()
        val desc     = etDescription.text.toString().trim()
        val category = spinnerCategory.selectedItem.toString()
        val priority = spinnerPriority.selectedItem.toString()

        // Validate all fields
        if (name.isEmpty() || roll.isEmpty() || title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        if (category == "Select Category") {
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show()
            return
        }
        if (priority == "Select Priority") {
            Toast.makeText(this, "Please select a priority", Toast.LENGTH_SHORT).show()
            return
        }

        // Build complaint object
        val id = db.collection("complaints").document().id
        val complaint = Complaint(
            id            = id,
            studentName   = name,
            rollNumber    = roll,
            complaintTitle= title,
            category      = category,
            priority      = priority,
            description   = desc,
            status        = "Pending",
            timestamp     = System.currentTimeMillis()
        )

        // Save to Firestore
        db.collection("complaints").document(id)
            .set(complaint)
            .addOnSuccessListener {
                Toast.makeText(this, "Complaint submitted successfully!", Toast.LENGTH_SHORT).show()
                clearForm()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearForm() {
        etName.text.clear()
        etRoll.text.clear()
        etTitle.text.clear()
        etDescription.text.clear()
        spinnerCategory.setSelection(0)
        spinnerPriority.setSelection(0)
    }
}