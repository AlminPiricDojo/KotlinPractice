package com.example.kotlinpractice

import android.content.DialogInterface
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
    private lateinit var clRoot: ConstraintLayout
    private lateinit var guessField: EditText
    private lateinit var guessButton: Button
    private lateinit var messages: ArrayList<String>
    private lateinit var tvPrompt: TextView

    private var answer = 0
    private var guesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answer = Random.nextInt(10)

        clRoot = findViewById(R.id.clRoot)
        messages = ArrayList()

        tvPrompt = findViewById(R.id.tvPrompt)

        rvMessages.adapter = MessageAdapter(this, messages)
        rvMessages.layoutManager = LinearLayoutManager(this)

        guessField = findViewById(R.id.etGuessField)
        guessButton = findViewById(R.id.btGuessButton)

        guessButton.setOnClickListener { addMessage() }
    }

    private fun addMessage(){
        val msg = guessField.text.toString()
        if(msg.isNotEmpty()){
            if(guesses>0){
                if(msg.toInt() == answer){
                    disableEntry()
                    showAlertDialog("You win!\n\nPlay again?")
                }else{
                    guesses--
                    messages.add("You guessed $msg")
                    messages.add("You have $guesses guesses left")
                }
                if(guesses==0){
                    disableEntry()
                    messages.add("You lose - The correct answer was $answer")
                    messages.add("Game Over")
                    showAlertDialog("You lose...\nThe correct answer was $answer.\n\nPlay again?")
                }
            }
            guessField.text.clear()
            guessField.clearFocus()
            rvMessages.adapter?.notifyDataSetChanged()
        }else{
            Snackbar.make(clRoot, "Please enter a number", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun disableEntry(){
        guessButton.isEnabled = false
        guessButton.isClickable = false
        guessField.isEnabled = false
        guessField.isClickable = false
    }

    private fun showAlertDialog(title: String) {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(title)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game Over")
        // show alert dialog
        alert.show()
    }
}