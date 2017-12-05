package com.anthonycr.tagger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

@Tag
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Hello World")
        Test.log()
    }
}