package com.example.a04_module_c

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

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

        holder.itemView.setOnClickListener {
            println("-- here: " + tour.activityName)

            val activity = it!!.context as AppCompatActivity
            val frag = TourDetailsFragment()

            val bundle = Bundle()
            bundle.putString("tourTitle", tour.activityName)
            frag.arguments = bundle

            val transaction = activity.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.rightLayout, frag)

            // allow back to original fragment
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun getItemCount(): Int {
        return mTours.size
    }

    public fun setData(tours: ArrayList<Tour>) {
        println("-- set data")
        mTours.clear()
        mTours.addAll(tours)
        mTours.addAll(tours)
        mTours.addAll(tours)

        println(mTours.size)
        println("-- notify the change")
        notifyDataSetChanged()
    }
}