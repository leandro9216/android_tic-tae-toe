package com.example.tictaetoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.R.id.button3
import android.R.id.button2
import android.R.id.button1
import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    var player1Turn = true
    var player1Won = false
    var player2Won = false
    var draw = false
    var winnable = true
    var winsP1 = 0
    var winsP2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disableButtons()

        button1.setOnClickListener {
           setButtonText(button1)
        }
        button2.setOnClickListener {
            setButtonText(button2)
        }
        button3.setOnClickListener {
            setButtonText(button3)
        }
        button4.setOnClickListener {
            setButtonText(button4)
        }
        button5.setOnClickListener {
            setButtonText(button5)
        }
        button6.setOnClickListener {
            setButtonText(button6)
        }
        button6.setOnClickListener {
            setButtonText(button6)
        }
        button7.setOnClickListener {
            setButtonText(button7)
        }
        button8.setOnClickListener {
            setButtonText(button8)
        }
        button9.setOnClickListener {
            setButtonText(button9)
        }
        start.setOnClickListener{
            start.visibility = View.GONE
            reset.visibility = View.VISIBLE
            playerTurn.text = "Player 1"
            turnLayout.visibility = View.VISIBLE
            winLayoutPlayer1.visibility = View.VISIBLE
            winLayoutPlayer2.visibility = View.VISIBLE
            enableButtons()
            winPlayer1.text = winsP1.toString()
            winPlayer2.text = winsP2.toString()
        }
        reset.setOnClickListener{
            if(!isEmpty()) cleanAll()
        }
    }


    fun setButtonText(button:Button) {
        if (button.text.isNullOrEmpty()) {
            if (player1Turn) {
                button.text = "O"
                button.setTextColor(resources.getColor(R.color.naught))

            } else{
                button.text = "X"
                button.setTextColor(resources.getColor(R.color.cross))
            }
            check()
            if(!player2Won && !player1Won && !draw) {
                changeTurn()
            }
        } else setAlertText()
    }

    @SuppressLint("ShowToast")
    fun setAlertText(){
        Toast.makeText(this,"Option already selected", Toast.LENGTH_LONG)
    }

    fun changeTurn() {
        player1Turn = !player1Turn
        if (player1Turn)
            playerTurn.text = "Player 1"
        else
            playerTurn.text = "Player 2"
    }

    fun check() {
        if (crossHorizontal() || crossVertical() || crossDiagonal()) {
            if (player1Turn) {
                player1Won = true
                winsP1++
                setWinnerText("The winner is Player 1!")

            }
            else {
                player2Won = true
                winsP2++
                setWinnerText("The winner is Player 2!")
            }
        } else if (naughtsHorizontal() || naughtsVertical()
            || naughtsDiagonal()
        ) {
            if (player1Turn){
                player1Won = true
                winsP1++
                setWinnerText("The winner is Player 1!")
            }
            else {
                player2Won = true
                winsP2++
                setWinnerText("The winner is Player 2!")
            }
        } else if (NoLine() && !player1Turn && !player2Won) {
            draw = true
            setWinnerText("It's a draw!")

        }
    }

    fun setWinnerText(text:String){
        winnerText.visibility = View.VISIBLE
        winnerText.text = text
        disableButtons()
        winPlayer1.text = winsP1.toString()
        winPlayer2.text = winsP2.toString()
    }

    fun crossHorizontal(): Boolean {
        return button1.text.equals("X") && button2.text.equals("X") && button3.text.equals(
            "X"
        ) ||
                button4.text.equals("X") && button5.text.equals("X") && button6.text.equals(
            "X"
        ) ||
                button7.text.equals("X") && button8.text.equals("X") && button9.text.equals(
            "X"
        )
    }

    fun naughtsHorizontal(): Boolean {
        return button1.text.equals("O") && button2.text.equals("O") && button3.text.equals(
            "O"
        ) ||
                button4.text.equals("O") && button5.text.equals("O") && button6.text.equals(
            "O"
        ) ||
                button7.text.equals("O") && button8.text.equals("O") && button9.text.equals(
            "O"
        )
    }

    fun crossVertical(): Boolean {
        return button1.text.equals("X") && button4.text.equals("X") && button7.text.equals(
            "X"
        ) ||
                button2.text.equals("X") && button5.text.equals("X") && button8.text.equals(
            "X"
        ) ||
                button3.text.equals("X") && button6.text.equals("X") && button9.text.equals(
            "X"
        )
    }

    fun naughtsVertical(): Boolean {
        return button1.text.equals("O") && button4.text.equals("O") && button7.text.equals(
            "O"
        ) ||
                button2.text.equals("O") && button5.text.equals("O") && button8.text.equals(
            "O"
        ) ||
                button3.text.equals("O") && button6.text.equals("O") && button9.text.equals(
            "O"
        )
    }

    fun crossDiagonal(): Boolean {
        return button1.text.equals("X") && button5.text.equals("X") && button9.text.equals(
            "X"
        ) || button3.text.equals("X") && button5.text.equals("X") && button7.text.equals(
            "X"
        )
    }

    fun naughtsDiagonal(): Boolean {
        return button1.text.equals("O") && button5.text.equals("O") && button9.text.equals(
            "O"
        ) || button3.text.equals("O") && button5.text.equals("O") && button7.text.equals(
            "O"
        )
    }

    fun NoLine(): Boolean {
        return (!button1.text.equals("")
                && !button2.text.equals("")
                && !button3.text.equals("")
                && !button4.text.equals("")
                && !button5.text.equals("")
                && !button6.text.equals("")
                && !button7.text.equals("")
                && !button8.text.equals("")
                && !button9.text.equals(""))
    }

    fun isEmpty(): Boolean {
        return (button1.text.equals("")
                && button2.text.equals("")
                && button3.text.equals("")
                && button4.text.equals("")
                && button5.text.equals("")
                && button6.text.equals("")
                && button7.text.equals("")
                && button8.text.equals("")
                && button9.text.equals(""))
    }

    fun cleanAll(){
        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
        button5.text = ""
        button6.text = ""
        button7.text = ""
        button8.text = ""
        button9.text = ""
        player1Turn = !player1Turn
        player1Won = false
        player2Won = false
        draw = false
        playerTurn.text = if(player1Turn) "Player 1" else "Player 2"
        winnerText.visibility = View.GONE
        enableButtons()
    }

    fun disableButtons(){
        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        button5.isEnabled = false
        button6.isEnabled = false
        button7.isEnabled = false
        button8.isEnabled = false
        button9.isEnabled = false
    }

    fun enableButtons(){
        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        button5.isEnabled = true
        button6.isEnabled = true
        button7.isEnabled = true
        button8.isEnabled = true
        button9.isEnabled = true
    }

}
