package com.example.kotlinpractice

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var numbersGameButton: Button
    private lateinit var guessThePhraseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numbersGameButton = findViewById(R.id.btNumbersGame)
        numbersGameButton.setOnClickListener { startGame(NumbersGame()) }
        guessThePhraseButton = findViewById(R.id.btGuessThePhrase)
        guessThePhraseButton.setOnClickListener { startGame(GuessThePhrase()) }
    }

    private fun startGame(activity: Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}