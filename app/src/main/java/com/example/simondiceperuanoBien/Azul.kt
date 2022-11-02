package com.example.simondiceperuanoBien

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.simondiceperuano.R

class Azul : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azul)

        // variables de botones y text areas
        val title = findViewById<TextView>(R.id.titleText)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val green = findViewById<Button>(R.id.greenBtn)
        val yellow = findViewById<Button>(R.id.yellowBtn)
        val blue = findViewById<Button>(R.id.blueBtn)
        val red = findViewById<Button>(R.id.redBtn)
        val restart = findViewById<Button>(R.id.restartBtn)
        val activitiesArray = arrayOf(Verde::class.java, Amarillo::class.java, Azul::class.java, Rojo::class.java)

        // contador de puntuacion y rondas
        var score = intent.getIntExtra("puntruacion", -2)
        var count = intent.getIntExtra("contador", -3)
        val colors = intent.getStringArrayListExtra("colores")

        // texto puntuacion
        scoreText.text = score.toString()

        // texto simon dice
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
                val intent = Intent(this@Azul, activitiesArray[classNum])
                if((count+1) == colors.size){
                    gameOver("Ganaste capo!")
                } else {
                    if (count == score) {
                        count = -1
                        score++
                    }
                    count++
                    intent.putStringArrayListExtra("colores", colors)
                    intent.putExtra("contador", count)
                    intent.putExtra("puntuacion", score)
                    startActivity(intent)
                }
            }
            else if(restart.visibility != 0){
                gameOver("Perdiste manco")
            }
        }

        // On click listeners de cada boton
        green.setOnClickListener {
            onCorrect("Verde", 0)
        }
        yellow.setOnClickListener {
            onCorrect("Amarillo", 1)
        }
        blue.setOnClickListener {
            onCorrect("Azul", 2)
        }
        red.setOnClickListener {
            onCorrect("Rojo", 3)
        }
        restart.setOnClickListener {
            val intent = Intent(this@Azul, MainActivity::class.java)
            startActivity(intent)
        }
    }
}















