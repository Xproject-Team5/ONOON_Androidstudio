package com.example.xproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_opendoor.*

class OpendoorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opendoor)
        /*
        open_button.setOnClickListener {
            val intent = Intent(this, OpendoorActivity::class.java)
            startActivity(intent)
            finish()
        }
        */
        exit_button.setOnClickListener {
            finish()
        }
    }
}