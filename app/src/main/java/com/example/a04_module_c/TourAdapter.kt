package com.example.a04_module_c

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TourAdapter(private val mTours: ArrayList<Tour>): RecyclerView.Adapter<TourAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.tourName)
        val dateTextView = itemView.findViewById<TextView>(R.id.tourDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val tourView = inflater.inflate(R.layout.item_tour, parent, false)
        return ViewHolder(tourView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tour: Tour = mTours.get(position)
        holder.nameTextView.setText(tour.activityName)
        holder.dateTextView.setText(tour.activityDate.toString())
    }

    override fun getItemCount(): Int {
        return mTours.size
    }

    public fun setData(tours: ArrayList<Tour>) {
        println("-- set data")
        mTours.clear()
        mTours.addAll(tours)

        println("-- notify the change")
        notifyDataSetChanged()
    }
}