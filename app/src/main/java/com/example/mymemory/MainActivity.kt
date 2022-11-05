package com.example.mymemory

import android.app.SearchManager.OnCancelListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymemory.models.BoardSize
import com.example.mymemory.models.MemoryGame

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

     private lateinit var rvBoard: RecyclerView
     private lateinit var tvNumMoves: TextView
     private lateinit var tvNumPairs: TextView

     private var boardSize: BoardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

//        var chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
//        val randomizedImages = (chosenImages + chosenImages).shuffled()
//        var memoryCards = randomizedImages.map{MemoryCard(it)}

        var memoryGame = MemoryGame(boardSize)

        rvBoard.adapter = MemoryBoardManager(this, boardSize, memoryGame.cards, object: MemoryBoardManager.CardClickListener{
            override fun onCardClick(position: Int) {
                Log.i(TAG, "Card clicked $position")
            }
        })
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }
}