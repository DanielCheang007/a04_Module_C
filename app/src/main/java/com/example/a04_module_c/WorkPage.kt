package com.example.a04_module_c

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import org.json.JSONObject
import java.io.IOException

class WorkPage : AppCompatActivity() {
    private val agent = OkHttpClient()

    lateinit var adapter: TourAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_page)

        // init the view
        val tourListView = findViewById<View>(R.id.tourListRecyclerView) as RecyclerView
        adapter = TourAdapter(ArrayList<Tour>())
        tourListView.adapter = adapter
        tourListView.layoutManager = LinearLayoutManager(this@WorkPage)

        getTours()
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

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    val jsonData: String = response.body?.string().toString()
                    println(jsonData)
                    val resObj = JSONObject(jsonData)
                    val tourArr = resObj.getJSONArray("tours")

                    for(i in 0 until tourArr.length()) {
                        println(tourArr.getJSONObject(i))
                    }

                    println(tourArr.toString())

                    val gson = Gson()
                    val sType = object: TypeToken<ArrayList<Tour>>(){}.type
                    val tourList = gson.fromJson<ArrayList<Tour>>(tourArr.toString(), sType)

                    for(tour in tourList) {
                        println(tour)
                    }

                    runOnUiThread{
                        println("-- run on ui thread")
                        adapter.setData(tourList)
                    }
                }
            })
    }
}