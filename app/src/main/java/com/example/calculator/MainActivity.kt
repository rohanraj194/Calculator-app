package com.example.calculator

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    
//        enableEdgeToEdge()
 //       setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.btnClear.setOnClickListener {           //for clear button
            binding.input.text=""
            binding.output.text=""
        }

        binding.btnBack.setOnClickListener {                        //for delete previous value
            binding.input.text = binding.input.text.dropLast(1)
        }

        binding.btn0.setOnClickListener {
            binding.input.append("0")
        }

        binding.btn1.setOnClickListener {
            binding.input.append("1")
        }

        binding.btn2.setOnClickListener {
            binding.input.append("2")
        }

        binding.btn3.setOnClickListener {
            binding.input.append("3")
        }

        binding.btn4.setOnClickListener {
            binding.input.append("4")
        }

        binding.btn5.setOnClickListener {
            binding.input.append("5")
        }

        binding.btn6.setOnClickListener {
            binding.input.append("6")
        }

        binding.btn7.setOnClickListener {
            binding.input.append("7")
        }

        binding.btn8.setOnClickListener {
            binding.input.append("8")
        }

        binding.btn9.setOnClickListener {
            binding.input.append("9")
        }

        binding.btnDot.setOnClickListener {
            binding.input.append(".")
        }

        binding.btn00.setOnClickListener {
            binding.input.append("00")
        }

        binding.btnAdd.setOnClickListener {
            binding.input.append("+")
        }

        binding.btnMinus.setOnClickListener {
            binding.input.append("-")
        }

        binding.btnMultiply.setOnClickListener {
            binding.input.append("*")
        }

        binding.btnDivide.setOnClickListener {
            binding.input.append("/")
        }

        binding.btnPercent.setOnClickListener {
            binding.input.append("%")
        }


        //for equal operation including percentage button
        binding.btnEqual.setOnClickListener {
            try {
                var inputText = binding.input.text.toString()

                // Handle percentages like a real calculator
                // e.g., "200+10%" becomes "200+(200*10/100)"
                val regex = Regex("(\\d+(?:\\.\\d+)?)([+\\-*/])(\\d+(?:\\.\\d+)?)%")
                var modifiedText = inputText

                regex.findAll(inputText).forEach { match ->
                    val firstNumber = match.groupValues[1]
                    val operator = match.groupValues[2]
                    val percentValue = match.groupValues[3]
                    // Replace "a + b%" with "a + (a * b / 100)"
                    modifiedText = modifiedText.replace(match.value, "$firstNumber$operator($firstNumber*$percentValue/100)")
                }

                val expression = ExpressionBuilder(modifiedText).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                binding.output.text =
                    if (result == longResult.toDouble()) longResult.toString()
                    else result.toString()

            } catch (e: Exception) {
                binding.output.text = "Error"
            }
        }

