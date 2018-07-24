package com.example.deer.kotlinpratices

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.deer.kotlinpratices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val hello = HelloObject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinder:ActivityMainBinding
                                = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinder.hello = hello
    }

    override fun onResume() {
        super.onResume()
        with(hello) {
            detail = detail.reversed()
        }
    }
}