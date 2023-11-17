package com.example.a04_module_c

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val agent = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameField = findViewById<TextView>(R.id.editTextTextPersonName)
        val passwordField = findViewById<TextView>(R.id.editTextTextPassword)
        val loginBtn = findViewById<Button>(R.id.button)

        loginBtn.setOnClickListener {
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            asyncLogin(username, password)
        }
    }

    private fun asyncLogin(username: String, password: String) {
        val url = getString(R.string.api_base) + "/login"

        // the data that will post to server
        val requestBody = FormBody.Builder()
            .add("name", username)
            .add("pwd", password)
            .build()

        // wrap the data to a request package
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        agent.newCall(request)
            .enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    println("-- something wrong")
                    Toast.makeText(this@MainActivity, "login failed", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call, response: Response) {
                    println(response)
                    if (!response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "login failed", Toast.LENGTH_LONG).show()
                    } else {
                        val workPage = Intent(this@MainActivity, WorkPage::class.java)
                        startActivity(workPage)
                    }
                }
            })
    }
}