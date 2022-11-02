package com.example.simondiceperuanoBien

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.simondiceperuano.R

class Verde : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verde)

        // variables de botones y text areas
        val title = findViewById<TextView>(R.id.titleText)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val green = findViewById<Button>(R.id.greenBtn)
        val yellow = findViewById<Button>(R.id.yellowBtn)
        val blue = findViewById<Button>(R.id.blueBtn)
        val red = findViewById<Button>(R.id.redBtn)
        val restart = findViewById<Button>(R.id.restartBtn)
        val activitiesArray = arrayOf(Verde::class.java, Amarillo::class.java, Azul::class.java, Rojo::class.java)

        // contador de rondas y puntos
        var score = intent.getIntExtra("puntuacion", -2)
        var count = intent.getIntExtra("contador", -3)
        val colors = intent.getStringArrayListExtra("colores")

        // texto puntuacion
        scoreText.text = score.toString()

        // taxto de simon dice
        if(score != count){
            val temp = "Color: " + (count + 1)
            title.text = temp
        } else {
            val temp = "Simon dice " + colors!![count]
            title.text = temp
        }

        // Volver a jugar
        fun gameOver(newTitle: String){
            colors!![count] = newTitle
            title.text = newTitle
            restart.visibility = View.VISIBLE
            red.text = newTitle
            yellow.text = newTitle
            green.text = newTitle
        }

        // Comprueba la seleccion del jugador y actualiza
        fun onCorrect(answer: String, classNum: Int){
            if (colors!![count] == answer){
                val intent = Intent(this@Verde, activitiesArray[classNum])
                if((count+1) == colors.size){
                    gameOver("Ganaste capo!")
                } else {
                    if (count == score) {
                        count = -1
                        score++
                    }
                    count++
                    intent.putStringArrayListExtra("colors", colors)
                    intent.putExtra("count", count)
                    intent.putExtra("score", score)
                    startActivity(intent)
                }
            }
            else if(restart.visibility != 0){
                gameOver("gameOver")
            }
        }

        // On click listeners de cada boton
        green.setOnClickListener {
            onCorrect("Green", 0)
        }
        yellow.setOnClickListener {
            onCorrect("Yellow", 1)
        }
        blue.setOnClickListener {
            onCorrect("Blue", 2)
        }
        red.setOnClickListener {
            onCorrect("Red", 3)
        }
        restart.setOnClickListener {
            val intent = Intent(this@Verde, MainActivity::class.java)
            startActivity(intent)
        }
    }
}















