package com.example.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.tools.CardAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var rvBoard: RecyclerView
    private lateinit var sampleText: TextView
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sampleText = findViewById(R.id.tvSample)
        refreshButton = findViewById(R.id.btRefresh)
        refreshButton.setOnClickListener { changeText() }

        rvBoard = findViewById(R.id.rvBoard)

        rvBoard.adapter = CardAdapter(this, 3)
//        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = LinearLayoutManager(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val myText: String = sampleText.text.toString()
        outState.putString("savedString", myText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val myText = savedInstanceState.getString("savedString", "this is the default")
        sampleText.text = myText
    }

    private fun changeText(){
        val randomNum = Random.nextInt(2, 9)
        Log.i("MainActivity", "NUM: $randomNum")
        if(randomNum%2==0){sampleText.text = "The text has been changed"}
        else{sampleText.text = "The number was not even"}
    }
}