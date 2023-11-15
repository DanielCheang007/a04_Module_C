package com.example.a04_module_c

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class TourDetailsFragment: Fragment() {
    private var activityName: String? = ""
    private var activityDate: Date? = null
    private var activityType: String? = ""
    private var activityDescription: String? = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tour_details_fragment, container, false)

        activityName = arguments?.getString("activityName")
        view?.findViewById<TextView>(R.id.activityName)?.text = activityName

        val time = arguments?.getLong("activityDate")
        activityDate = time?.let { Date(it) }
        val pattern = "yyyy-MM-dd"
        val formatter = SimpleDateFormat(pattern)
        val dateStr = formatter.format(activityDate)
        view?.findViewById<TextView>(R.id.activityDate)?.text = dateStr

        activityType = arguments?.getString("activityType")
        view?.findViewById<TextView>(R.id.activityType)?.text = activityType

        activityDescription = arguments?.getString("activityDescription")
        view?.findViewById<TextView>(R.id.activityDescription)?.text = activityDescription


        return view
    }


}