package com.example.quiz_2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Enable back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name      = intent.getStringExtra("name") ?: ""
        val roll      = intent.getStringExtra("roll") ?: ""
        val title     = intent.getStringExtra("title") ?: ""
        val category  = intent.getStringExtra("category") ?: ""
        val priority  = intent.getStringExtra("priority") ?: ""
        val desc      = intent.getStringExtra("description") ?: ""
        val status    = intent.getStringExtra("status") ?: ""
        val timestamp = intent.getLongExtra("timestamp", 0L)

        val date = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            .format(Date(timestamp))

        findViewById<TextView>(R.id.textTitle).text       = title
        findViewById<TextView>(R.id.textName).text        = "Student: $name"
        findViewById<TextView>(R.id.textRoll).text        = "Roll No: $roll"
        findViewById<TextView>(R.id.textCategory).text    = "Category: $category"
        findViewById<TextView>(R.id.textPriority).text    = "Priority: $priority"
        findViewById<TextView>(R.id.textDescription).text = "Description: $desc"
        findViewById<TextView>(R.id.textStatus).text      = "Status: $status"
        findViewById<TextView>(R.id.textDate).text        = "Submitted: $date"
    }

    // Back button in action bar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}