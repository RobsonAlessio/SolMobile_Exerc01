package com.example.solmobile_exerc01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnCalc).setOnClickListener { calculoIMC() }
    }

    private fun calculoIMC(){
        val peso = findViewById<EditText>(R.id.inpPeso)
        val altura = findViewById<EditText>(R.id.inpAltura)
        val resultadoView = findViewById<TextView>(R.id.calcResult)

        val pesoString = peso.text.toString().trim()
        val alturaString = altura.text.toString().trim()

        if(pesoString != "" && alturaString != ""){
            val pesoNumero = pesoString.toFloat()
            val alturaNumero = alturaString.toFloat()/100

            val resultadoIMC = (pesoNumero/(alturaNumero * alturaNumero))

            val resultadoIMCText = resultadoIMC.toString()
            resultadoView.text= resultadoIMCText

            if(resultadoIMC < 18.5){
                resultadoView.text= "Magreza"
            }
            if(resultadoIMC >= 18.5 && resultadoIMC < 24.9){
                resultadoView.text= "Normal"
            }
            if(resultadoIMC >= 25 && resultadoIMC < 29.9){
                resultadoView.text= "Sobrepeso"
            }
            if(resultadoIMC >= 30 && resultadoIMC < 39.9){
                resultadoView.text= "Obesidade"
            }
            if(resultadoIMC > 40){
                resultadoView.text= "Obesidade Grave"
            }
        }
    }

}
