package com.example.tictaetoe

import android.os.Handler
import android.widget.Button

class PlayerVsComputerLogic {

    private var mainBoard: MainActivity? = null
    private val boardTextValue = arrayOfNulls<String>(9)
    private var max: String? = null
    private var min: String? = null
    private var currentValueMinMax: String? = null


    fun nextMove(board: MainActivity) {
        mainBoard = board

        if (board.player1Turn)
            return
        max = "X"
        min = "O"
        currentValueMinMax = "max"

        //Delay to process function
        val handler = Handler()
        handler.postDelayed({
            setTextBoard() // instance board with value
            val bestMove = minimax(boardTextValue!!, currentValueMinMax!!, 0, 0)
            applyTextBoard(bestMove.matrix) // Apply the text board to the actual view
            board.check()
            if (!board.draw && !board.player1Won && !board.player2Won) board.changeTurn()
        }, 1000)
    }


    private fun minimax(currentBoardTextValue: Array<String?>, minMaxValue: String, recurse: Int, depth: Int): MinMax {
        val children = generateSuccessor(currentBoardTextValue, minMaxValue) // Get list of all possible moves

        if (children == null || isGameOver(currentBoardTextValue)) {
            return MinMax(currentBoardTextValue, getScore(currentBoardTextValue), depth) // if no children or game over
        } else {
            val listScore = arrayListOf<MinMax>() // Get a list of scores for all available children
            for (i in 0 until children.size) {
                listScore.add(
                    minimax(children[i], setCurrentMinMaxValue(minMaxValue), 1, depth + 1)
                )
            }

            val res = getResult(listScore, minMaxValue) //get child for max or min score
            if (recurse == 1)
                res.matrix = currentBoardTextValue // If this is a recursive call, set the result
            return res
        }
    }

    private fun setCurrentMinMaxValue(minMaxValue: String): String {
        return if (minMaxValue == "max") "min" else "max"
    }

    private fun isGameOver(textBoard: Array<String?>): Boolean {
        return getScore(textBoard) != 0
    }

    private fun getResult(listScore: ArrayList<MinMax>, minMaxValue: String): MinMax {
        var result = listScore[0]

        if (minMaxValue == "max") {
            for (i in 1 until listScore.size) {
                if (listScore[i].score > result.score || listScore[i].score == result.score && listScore[i].depth < result.depth)
                    result = listScore[i]
            }
        } else {
            for (i in 1 until listScore.size) {
                if (listScore[i].score < result.score || listScore[i].score == result.score && listScore[i].depth < result.depth)
                    result = listScore[i]
            }
        }

        return result
    }


    private fun generateSuccessor(currentBoardTextValue: Array<String?>, currentMinMaxValue: String): ArrayList<Array<String?>>? {
        val successor = arrayListOf<Array<String?>>()

        for (i in currentBoardTextValue.indices) {
            if (currentBoardTextValue[i] == "") {
                val child = arrayOfNulls<String>(9)
                System.arraycopy(currentBoardTextValue, 0, child, 0, 9)
                if (currentMinMaxValue == "max")
                    child[i] = max
                else if (currentMinMaxValue == "min")
                    child[i] = min

                successor.add(child)
            }
        }

        return if (successor.size == 0) null else successor
    }



    private fun getScore(currentBoardTextValue: Array<String?>): Int {
     if (max.equals("X")) {
            if (currentBoardTextValue[0] == "X" && currentBoardTextValue[1] == "X" && currentBoardTextValue[2] == "X" ||
                currentBoardTextValue[3] == "X" && currentBoardTextValue[4] == "X" && currentBoardTextValue[5] == "X" ||
                currentBoardTextValue[6] == "X" && currentBoardTextValue[7] == "X" && currentBoardTextValue[8] == "X" ||
                currentBoardTextValue[0] == "X" && currentBoardTextValue[3] == "X" && currentBoardTextValue[6] == "X" ||
                currentBoardTextValue[1] == "X" && currentBoardTextValue[4] == "X" && currentBoardTextValue[7] == "X" ||
                currentBoardTextValue[2] == "X" && currentBoardTextValue[5] == "X" && currentBoardTextValue[8] == "X" ||
                currentBoardTextValue[0] == "X" && currentBoardTextValue[4] == "X" && currentBoardTextValue[8] == "X" ||
                currentBoardTextValue[2] == "X" && currentBoardTextValue[4] == "X" && currentBoardTextValue[6] == "X"
            )
                return 1
            else if (currentBoardTextValue[0] == "O" && currentBoardTextValue[1] == "O" && currentBoardTextValue[2] == "O" ||
                currentBoardTextValue[3] == "O" && currentBoardTextValue[4] == "O" && currentBoardTextValue[5] == "O" ||
                currentBoardTextValue[6] == "O" && currentBoardTextValue[7] == "O" && currentBoardTextValue[8] == "O" ||
                currentBoardTextValue[0] == "O" && currentBoardTextValue[3] == "O" && currentBoardTextValue[6] == "O" ||
                currentBoardTextValue[1] == "O" && currentBoardTextValue[4] == "O" && currentBoardTextValue[7] == "O" ||
                currentBoardTextValue[2] == "O" && currentBoardTextValue[5] == "O" && currentBoardTextValue[8] == "O" ||
                currentBoardTextValue[0] == "O" && currentBoardTextValue[4] == "O" && currentBoardTextValue[8] == "O" ||
                currentBoardTextValue[2] == "O" && currentBoardTextValue[4] == "O" && currentBoardTextValue[6] == "O"
            )
                return -1
        }
        return 0
    }


    private fun setTextBoard() {
        boardTextValue[0] = mainBoard?.button1Copy?.text.toString()
        boardTextValue[1] = mainBoard?.button2Copy?.text.toString()
        boardTextValue[2] = mainBoard?.button3Copy?.text.toString()
        boardTextValue[3] = mainBoard?.button4Copy?.text.toString()
        boardTextValue[4] = mainBoard?.button5Copy?.text.toString()
        boardTextValue[5] = mainBoard?.button6Copy?.text.toString()
        boardTextValue[6] = mainBoard?.button7Copy?.text.toString()
        boardTextValue[7] = mainBoard?.button8Copy?.text.toString()
        boardTextValue[8] = mainBoard?.button9Copy?.text.toString()
    }

    private fun applyTextBoard(currentBoardTextValue: Array<String?>) {
        mainBoard?.button1Copy?.text = currentBoardTextValue[0]
        mainBoard?.button2Copy?.text = currentBoardTextValue[1]
        mainBoard?.button3Copy?.text = currentBoardTextValue[2]
        mainBoard?.button4Copy?.text = currentBoardTextValue[3]
        mainBoard?.button5Copy?.text = currentBoardTextValue[4]
        mainBoard?.button6Copy?.text = currentBoardTextValue[5]
        mainBoard?.button7Copy?.text = currentBoardTextValue[6]
        mainBoard?.button8Copy?.text = currentBoardTextValue[7]
        mainBoard?.button9Copy?.text = currentBoardTextValue[8]
        setButtonColor(mainBoard?.button1Copy)
        setButtonColor(mainBoard?.button2Copy)
        setButtonColor(mainBoard?.button3Copy)
        setButtonColor(mainBoard?.button4Copy)
        setButtonColor(mainBoard?.button5Copy)
        setButtonColor(mainBoard?.button6Copy)
        setButtonColor(mainBoard?.button7Copy)
        setButtonColor(mainBoard?.button8Copy)
        setButtonColor(mainBoard?.button9Copy)
    }


    private fun setButtonColor(button: Button?){
        if(button?.text == "O") button.setTextColor(mainBoard!!.resources.getColor(R.color.naught))
        else if(button?.text == "X") button.setTextColor(mainBoard!!.resources.getColor(R.color.cross))
    }


}