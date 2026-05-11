package com.example.quiz_2

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComplaintAdapter(
    private var complaints: List<Complaint>,
    private val onClick: (Complaint) -> Unit
) : RecyclerView.Adapter<ComplaintAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle: TextView    = view.findViewById(R.id.textTitle)
        val textName: TextView     = view.findViewById(R.id.textName)
        val textRoll: TextView     = view.findViewById(R.id.textRoll)
        val textCategory: TextView = view.findViewById(R.id.textCategory)
        val textPriority: TextView = view.findViewById(R.id.textPriority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_complaint, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val c = complaints[position]
        holder.textTitle.text    = c.complaintTitle
        holder.textName.text     = "Name: ${c.studentName}"
        holder.textRoll.text     = "Roll: ${c.rollNumber}"
        holder.textCategory.text = "Category: ${c.category}"
        holder.textPriority.text = "Priority: ${c.priority}"
        holder.itemView.setOnClickListener { onClick(c) }
    }

    override fun getItemCount() = complaints.size

    fun updateList(newList: List<Complaint>) {
        complaints = newList
        notifyDataSetChanged()
    }
}