package com.example.hesapmakinesiuygulamasi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hesapmakinesiuygulamasi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            btn0.appendClick("0")
            btn1.appendClick("1")
            btn2.appendClick("2")
            btn3.appendClick("3")
            btn4.appendClick("4")
            btn5.appendClick("5")
            btn6.appendClick("6")
            btn7.appendClick("7")
            btn8.appendClick("8")
            btn9.appendClick("9")
            btnCikarma.appendClick("-")
            btnCarpma.appendClick("*")
            btnToplama.appendClick("+")
            btnBolum.appendClick("/")
            btnParantez.appendClick("(")
            btnParantez.setOnLongClickListener {
                binding.processEditText.append(")")
                true
            }

            btnNokta.appendClick(".")
            btnAC.setOnClickListener {
                binding.processEditText.text = null
                binding.resultText.text = ""

            }

            btnSilme.setOnClickListener{
                val expression = processEditText.text.toString()
                if (expression.isNotEmpty()){
                    processEditText.text = expression.substring(0,expression.length - 1)
                }
            }

            btnEsittir.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(binding.processEditText.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    if (result == longResult.toDouble()) {
                        binding.resultText.text = longResult.toString()
                    } else {
                        binding.resultText.text = result.toString()
                    }
                }catch (e:Exception){
                    Log.d("Exception", "Message: ${e.message}")
                }

            }
        }
    }

    private fun View.appendClick(string: String) {
        setOnClickListener {
            binding.processEditText.append(string)
        }
    }
}