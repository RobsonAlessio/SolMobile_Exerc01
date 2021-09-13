package com.example.solmobile_exerc01

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("com.example.solmobile_exerc01.PERFIL", Context.MODE_PRIVATE) ?: return
        findViewById<Button>(R.id.btnCalc).setOnClickListener { calculoIMC() }
        atualizaTela()
    }


    fun atualizaTela(){
        val resultadoView = findViewById<TextView>(R.id.calcResult)
        val resultIMCAtualizado = recuperaNome()

        if(resultIMCAtualizado != null && resultIMCAtualizado != ""){
            resultadoView.text = "Último IMC: " + resultIMCAtualizado
        }else {
            resultadoView.text = "Nenhum IMC Calculado"
        }



    }
    fun calculoIMC(){
        val peso = findViewById<EditText>(R.id.inpPeso)
        val altura = findViewById<EditText>(R.id.inpAltura)
        val resultadoView = findViewById<TextView>(R.id.calcResult)
        val classView = findViewById<TextView>(R.id.classResult)

        val pesoString = peso.text.toString().trim()
        val alturaString = altura.text.toString().trim()

        if(pesoString != "" && alturaString != ""){
            val pesoNumero = pesoString.toFloat()
            val alturaNumero = alturaString.toFloat()
            val resultadoIMC = (pesoNumero/(alturaNumero * alturaNumero))

            val resultadoIMCText = resultadoIMC.toString()
            guardaNome(resultadoIMCText)
            resultadoView.text = "IMC: " + resultadoIMCText


            if(resultadoIMC < 18.5){
                classView.text= "Se não tiver moeda no bolso, voa"
            }
            if(resultadoIMC >= 18.5 && resultadoIMC < 24.9){
                classView.text= "Normal, tudo certo meu bom :) "
            }
            if(resultadoIMC >= 25 && resultadoIMC < 29.9){
                classView.text= "Melhor parar de levantar garfo e começar lenvantar peso na academia"
            }
            if(resultadoIMC >= 30 && resultadoIMC < 39.9){
                classView.text= "Começando a ficar uma rolha de poço ;-;"
            }
            if(resultadoIMC > 40){
                classView.text= "Conheço 3 gordos, e vc já é 2 deles"
            }
        }
    }

    fun guardaNome(resultadoIMCText:String){
        with(sharedPref.edit()){
            putString("calcIMC", resultadoIMCText)
            commit()
        }
    }

    fun recuperaNome() : String? {
        return sharedPref.getString("calcIMC", null)
    }

}
