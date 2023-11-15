package com.example.a04_module_c

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class LeftFragment: Fragment() {
    private val agent = OkHttpClient()
    private val gson = Gson()

    lateinit var adapter: TourAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.left_fragment, container, false)

        // init the view
        val tourListView = view?.findViewById<View>(R.id.tourListRecyclerView) as RecyclerView

        adapter = TourAdapter(ArrayList<Tour>())

        tourListView.adapter = adapter
        tourListView.layoutManager = LinearLayoutManager(activity)
        getTours()

        return view
    }

    private fun getTours() {
        val url = "http://172.18.20.111/ghmc/public/tour"

        // the data that will post to server
        val request = Request.Builder()
            .url(url)
            .method("GET", null)
            .build()

        agent.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("-- something wrong")
                }

                override fun onResponse(call: Call, response: Response) {
                    println(response)
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val jsonData: String = response.body?.string().toString()
                    val resObj = JSONObject(jsonData)
                    val tourArr = resObj.getJSONArray("tours")

                    val sType = object: TypeToken<ArrayList<Tour>>(){}.type
                    val tourList = gson.fromJson<ArrayList<Tour>>(tourArr.toString(), sType)

                    activity?.runOnUiThread{
                        println("-- run on ui thread")
                        adapter.setData(tourList)
                    }
                }
            })
    }
}