package com.broood.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var lastDigit = false
    private var lastdot = false
    private var lastOp = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.calculated)
    }

    fun onDigit(view: View) {
        if ((view as TextView).text == "clr") {
            tvInput?.text = ""
            lastDigit = false
            lastOp = false
        } else {
            lastDigit = true
            lastOp = false
            lastdot = false
            tvInput?.append(view.text)
        }
    }
    fun onOperator(view: View) = if(lastDigit && !lastOp){
        tvInput?.append((view as TextView).text)
        lastOp = true
        lastdot = false
    }
    else Toast.makeText(this, "Incorrect expression", Toast.LENGTH_SHORT).show()
    fun onDecimal(view: View){
        if(!lastdot && lastDigit && !lastOp){
            tvInput?.append(".")
            lastdot = true
            lastDigit = false
            lastOp = false
        }
        else{
            Toast.makeText(this, "Incorrect expression", Toast.LENGTH_SHORT).show()
        }
    }
    fun calculate(view: View){
        if(lastDigit){
            var tvValue = tvInput?.text.toString()
            var truHaiKya: Boolean = false
            try{
                if(tvValue.startsWith("-")) {
                    truHaiKya = true
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    if(truHaiKya){
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        tvInput?.text = (-1*one.toDouble() + two.toDouble()*-1).toString()
                    }
                    else{
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("+")){
                    if(truHaiKya){
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        tvInput?.text = (-1*one.toDouble() + two.toDouble()).toString()
                    }
                    else{
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("*")){
                    if(truHaiKya){
                        val splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        tvInput?.text = (-1*one.toDouble() * two.toDouble()).toString()
                    }
                    else{
                        val splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                    }
                }
                else if(tvValue.contains("/")){
                        if(truHaiKya){
                            val splitValue = tvValue.split("/")
                            var one = splitValue[0]
                            var two = splitValue[1]
                            tvInput?.text = (-1*one.toDouble() / two.toDouble()).toString()
                        }
                        else {
                            val splitValue = tvValue.split("/")
                            var one = splitValue[0]
                            var two = splitValue[1]
                            tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                        }
                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
            catch (e: IllegalStateException){
                e.printStackTrace()
            }
            catch (e: Exception){
                Toast.makeText(this, "Invalid Expression, only one operator at a time", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }
}