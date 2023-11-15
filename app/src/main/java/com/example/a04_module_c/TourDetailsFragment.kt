package com.example.a04_module_c

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class TourDetailsFragment: Fragment() {
    private var tourTitle: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.tour_details_fragment, container, false)

        tourTitle = arguments?.getString("tourTitle")
        println("-- get argument: " + tourTitle)
        val titleView = view?.findViewById<TextView>(R.id.tourTitle)
        titleView?.text = tourTitle

        return view
    }


}