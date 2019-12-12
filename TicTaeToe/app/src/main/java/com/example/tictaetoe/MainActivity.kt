package com.example.tictaetoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Toast
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    var player1Turn = true
    var player1Won = false
    var player2Won = false
    var draw = false
    var winsP1 = 0
    var winsP2 = 0
    var button1Copy : Button? = null
    var button2Copy : Button? = null
    var button3Copy : Button? = null
    var button4Copy : Button? = null
    var button5Copy : Button? = null
    var button6Copy : Button? = null
    var button7Copy : Button? = null
    var button8Copy : Button? = null
    var button9Copy : Button? = null
    var minmax : PlayerVsComputerLogic? = null
    var vsComputer = false
    var player2Name = "Player 2"
    var player1Name = ""
    var setPlayer1Name = false
    var setPlayer2Name = false
    var title = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disableButtons()
        setButtonCopy()
        minmax = PlayerVsComputerLogic()
        button1.setOnClickListener {
           setButtonText(button1)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button2.setOnClickListener {
            setButtonText(button2)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button3.setOnClickListener {
            setButtonText(button3)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button4.setOnClickListener {
            setButtonText(button4)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button5.setOnClickListener {
            setButtonText(button5)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button6.setOnClickListener {
            setButtonText(button6)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button6.setOnClickListener {
            setButtonText(button6)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button7.setOnClickListener {
            setButtonText(button7)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button8.setOnClickListener {
            setButtonText(button8)
            if(vsComputer) minmax!!.nextMove(this)
        }
        button9.setOnClickListener {
            setButtonText(button9)
            if(vsComputer) minmax!!.nextMove(this)
        }
        start.setOnClickListener{
            vsComputer = true
            title = "Insert player's name"
            showInputDialog(title)
        }
        start2.setOnClickListener{
            vsComputer = false
            title = "Insert first player name"
            showInputDialog(title)
        }
        reset.setOnClickListener{
            if(!isEmpty()) cleanAll()
        }

        restart.setOnClickListener{
            turnLayout.visibility = View.GONE
            winLayoutPlayer1.visibility = View.GONE
            winLayoutPlayer2.visibility = View.GONE
            mainTitle.visibility = View.VISIBLE
            start.visibility = View.VISIBLE
            start2.visibility = View.VISIBLE
            reset.visibility = View.GONE
            restart.visibility = View.GONE
            cleanAll()
            disableButtons()
        }
    }


    fun setButtonText(button:Button) {
        if (button.text.isNullOrEmpty()) {
            if (player1Turn) {
                button.text = "O"
                button.setTextColor(resources.getColor(R.color.naught))
            } else if(!vsComputer) {
                button.text = "X"
                button.setTextColor(resources.getColor(R.color.cross))
            }
            check()
            if(!player2Won && !player1Won && !draw) {
                changeTurn()
            }
        } else setAlertText("Position already selected")
    }

    fun setAlertText(text:String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    fun changeTurn() {
        player1Turn = !player1Turn
        if (player1Turn) {
            if(vsComputer) enableButtons()
            playerTurn.text = player1Name
        }
        else {
            if(vsComputer) disableButtons()
            playerTurn.text = player2Name
        }
    }

    fun check() {
        val winnerUnicode = 0x1F389
        val drawUnicode = 0x1F60A
        if (crossHorizontal() || crossVertical() || crossDiagonal()) {
            if (player1Turn) {
                player1Won = true
                winsP1++
                setWinnerText(String(Character.toChars(winnerUnicode))+"The winner is $player1Name!"+String(Character.toChars(winnerUnicode)))

            }
            else {
                player2Won = true
                winsP2++
                setWinnerText(String(Character.toChars(winnerUnicode))+ "The winner is $player2Name!" + String(Character.toChars(winnerUnicode)))
            }
        } else if (naughtsHorizontal() || naughtsVertical()
            || naughtsDiagonal()
        ) {
            if (player1Turn){
                player1Won = true
                winsP1++
                setWinnerText(String(Character.toChars(winnerUnicode))+"The winner is $player1Name!"+String(Character.toChars(winnerUnicode)))
            }
            else {
                player2Won = true
                winsP2++
                setWinnerText(String(Character.toChars(winnerUnicode))+"The winner is $player2Name!"+String(Character.toChars(winnerUnicode)))
            }
        } else if (noMoreMovements() && !player1Won && !player2Won) {
            setWinnerText("It's a draw!"+ String(Character.toChars(drawUnicode)))
            draw = true

        }
    }

    fun setWinnerText(text : String){
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

    fun noMoreMovements(): Boolean {
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
        player1Turn = if(!vsComputer) !player1Turn
        else true
        player1Won = false
        player2Won = false
        draw = false
        setPlayer1Name = false
        setPlayer2Name =  false
        playerTurn.text = if(player1Turn) player1Name else player2Name
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

    fun setButtonCopy(){
        button1Copy = button1
        button2Copy = button2
        button3Copy = button3
        button4Copy = button4
        button5Copy = button5
        button6Copy = button6
        button7Copy = button7
        button8Copy = button8
        button9Copy = button9
    }


    fun setComputerConfig(){
        player2Name = "Jack"
        commonConfig()
    }

    fun setPlayerConfig(){
      commonConfig()
    }

    fun commonConfig(){
        start.visibility = View.GONE
        start2.visibility = View.GONE
        reset.visibility = View.VISIBLE
        restart.visibility = View.VISIBLE
        mainTitle.visibility = View.GONE
        turnLayout.visibility = View.VISIBLE
        winLayoutPlayer1.visibility = View.VISIBLE
        winLayoutPlayer2.visibility = View.VISIBLE
        playerTurn.text = player1Name
        winPlayer1.text = winsP1.toString()
        winPlayer2.text = winsP2.toString()
        winsText1.text = "Wins $player1Name:"
        winsText2.text = "Wins $player2Name:"
        enableButtons()
    }

    private fun showInputDialog(alertTitle:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK") {
                dialog, which ->
            if(input.text.toString().isNotEmpty()) {
                if(!setPlayer1Name) {
                    player1Name = input.text.toString()
                    setPlayer1Name = true
                    title = "Insert second player name"
                } else {
                    player2Name = input.text.toString()
                    setPlayer2Name = true
                }
                if (vsComputer) setComputerConfig()
                else {
                    if (!setPlayer2Name) showInputDialog(title)
                    else setPlayerConfig()
                }
            } else {
                setAlertText("Please, insert player's name")
                showInputDialog(title)
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }

        builder.show()
    }

}
