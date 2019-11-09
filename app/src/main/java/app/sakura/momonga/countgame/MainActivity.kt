package app.sakura.momonga.countgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var correctNumber = 0
    var player1:Player = Player(1)
    var player2:Player = Player(2)
    var stage:Int = 0

    val imageViewArray: Array<ImageView> by lazy {
        arrayOf(imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countText1.text = "0"
        countText2.text = "0"
        showImage()

        plusButton1.setOnClickListener {
            player1.clickNumber++
            countText1.text = player1.clickNumber.toString()
        }

        plusButton2.setOnClickListener {
            player2.clickNumber++
            countText2.text = player2.clickNumber.toString()
        }

        minusButton1.setOnClickListener {
            player1.clickNumber--
            countText1.text = player1.clickNumber.toString()
        }

        minusButton2.setOnClickListener {
            player2.clickNumber--
            countText2.text = player2.clickNumber.toString()
        }

        countText1.setOnClickListener {

            countText1.text = jadge(player1)

        }

        countText2.setOnClickListener {
            countText2.text = jadge(player2)
        }

    }

    fun showImage(){
        for(i in 0..9){
            val randomNum = Random.nextInt(2)
            when(randomNum){
                0 -> imageViewArray[i].visibility = View.INVISIBLE
                1 -> {
                    imageViewArray[i].visibility = View.VISIBLE
                    correctNumber++
                }
            }
        }
    }


    fun jadge(player: Player):String{
        if(player.clickNumber == correctNumber){
            Log.d("result","Clear")
            player.score += 1
            stage++

            when(stage){
                5 -> finishStage(player)
                else -> nextStage()
            }
            return "OK!"
        }else {
            return "Bad!"
        }

    }

    fun nextStage(){
        player1.clickNumber = 0
        player2.clickNumber = 0
        correctNumber = 0
        countText1.text = "0"
        countText2.text = "0"
        showImage()
    }

    fun finishStage(player: Player){
        Toast.makeText(this,"Player${player.playerNumber}" + "の勝利！",Toast.LENGTH_LONG).show()
        nextStage()
        allReset()
    }

    fun allReset(){
        player1.score = 0
        player2.score = 0
        stage = 0
    }




}
