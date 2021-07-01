package com.example.kotlinpractice

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_guess_the_phrase.*
import kotlin.random.Random

class NumbersGame : AppCompatActivity() {
    private lateinit var clRootNumbersGame: ConstraintLayout
    private lateinit var guessField: EditText
    private lateinit var guessButton: Button
    private lateinit var messages: ArrayList<String>

    private var answer = 0
    private var guesses = 3

    private var newGame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_game)

        answer = Random.nextInt(10)

        clRootNumbersGame = findViewById(R.id.clRootNumbersGame)
        messages = ArrayList()

        rvMessages.adapter = MessageAdapter(this, messages)
        rvMessages.layoutManager = LinearLayoutManager(this)

        guessField = findViewById(R.id.etGuessField)
        guessButton = findViewById(R.id.btGuessButton)

        guessButton.setOnClickListener { addMessage() }

        title = "Numbers Game"
    }

    override fun recreate() {
        super.recreate()
        answer = Random.nextInt(10)
        guesses = 3
        messages.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("answer", answer)
        outState.putInt("guesses", guesses)
        outState.putStringArrayList("messages", messages)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        answer = savedInstanceState.getInt("answer", 0)
        guesses = savedInstanceState.getInt("guesses", 0)
        messages.addAll(savedInstanceState.getStringArrayList("messages")!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item: MenuItem = menu!!.getItem(1)
        if(item.title == "Other Game"){ item.title = "Guess The Phrase" }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_new_game -> {
                CustomAlertDialog(this,"Are you sure you want to abandon the current game?")
                return true
            }
            R.id.mi_other_game -> {
                changeScreen(GuessThePhrase())
                return true
            }
            R.id.mi_back -> {
                changeScreen(MainActivity())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeScreen(activity: Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    private fun addMessage(){
        val msg = guessField.text.toString()
        if(msg.isNotEmpty()){
            if(guesses>0){
                if(msg.toInt() == answer){
                    disableEntry()
                    CustomAlertDialog(this,"You win!\n\nPlay again?")
                }else{
                    guesses--
                    messages.add("You guessed $msg")
                    messages.add("You have $guesses guesses left")
                }
                if(guesses==0){
                    disableEntry()
                    messages.add("You lose - The correct answer was $answer")
                    messages.add("Game Over")
                    CustomAlertDialog(this,"You lose...\nThe correct answer was $answer.\n\nPlay again?")
                }
            }
            guessField.text.clear()
            guessField.clearFocus()
            rvMessages.adapter?.notifyDataSetChanged()
        }else{
            Snackbar.make(clRootNumbersGame, "Please enter a number", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun disableEntry(){
        guessButton.isEnabled = false
        guessButton.isClickable = false
        guessField.isEnabled = false
        guessField.isClickable = false
    }
}