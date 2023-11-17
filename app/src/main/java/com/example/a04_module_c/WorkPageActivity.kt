package com.example.a04_module_c

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class WorkPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_page)

        // 接收 MainActivity 傳來的 user instance
        val user: User = intent?.getSerializableExtra("user") as User
        println(user)

        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener{
            replaceFragment(TourDetailsFragment())
        }
        replaceFragment(RightFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)

        // allow back to original fragment
        transaction.addToBackStack(null)
        transaction.commit()
    }
}