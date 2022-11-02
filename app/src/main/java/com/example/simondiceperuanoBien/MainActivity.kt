package com.example.simondiceperuanoBien

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.simondiceperuano.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Definicion de varibles
        var random = (0..3).random()
        val cuatrocolores = arrayOf("Verde", "Amarillo", "Azul", "Rojo")
        val todosloscolores: ArrayList<String> = arrayListOf(cuatrocolores[random])
        val inicio = findViewById<Button>(R.id.startBtn)
        val activitiesArray = arrayOf(Verde::class.java, Amarillo::class.java, Azul::class.java, Rojo::class.java)

        // Colores random
        for(i in 0..3){
            random = (0..3).random()
            todosloscolores.add(cuatrocolores[random])
        }

        // Boton de inicio del juego
        inicio.setOnClickListener {
            val intent = Intent(this@MainActivity, activitiesArray[random])
            intent.putStringArrayListExtra("colores", todosloscolores)
            intent.putExtra("contador", 0)
            intent.putExtra("puntuacion", 0)
            startActivity(intent)
        }
    }
}






