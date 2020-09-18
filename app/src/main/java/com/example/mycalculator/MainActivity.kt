package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  var lastDot = false
  var lastNumeric = false

  fun OnDigit(view: View){
    tvInput.append((view as Button).text)
    lastNumeric = true
  }

  fun OnClear(view: View) {
    tvInput.text = ""
    lastNumeric = false
    lastDot = false
  }

  fun OnDecimal(view: View){
    if(lastNumeric && !lastDot){
      tvInput.append(".")
      lastDot = true
      lastNumeric = false
    }
  }

  private fun removeZeroAt(result: String): String{
    var value = result
    if(result.contains(".0")){
      value = result.substring(0,result.length - 2)
    }
    return value
  }

  fun OnEqual(view: View){
    if(lastNumeric){
      var tvValue = tvInput.text.toString()
      var prefix = ""

      try{
        if(tvValue.startsWith("-")){
          prefix = "-"
          tvValue = tvValue.substring(1)
        }
        if(tvValue.contains("-")){
          val splitValue = tvValue.split("-")

          var one = splitValue[0]
          var two = splitValue[1]

          if(!prefix.isEmpty()){
            one = prefix + one
          }

          tvInput.text = removeZeroAt((one.toDouble() - two.toDouble()).toString())
        }else if(tvValue.contains("+")){
          val splitValue = tvValue.split("+")

          var one = splitValue[0]
          var two = splitValue[1]

          if(!prefix.isEmpty()){
            one = prefix + one
          }

          tvInput.text = removeZeroAt((one.toDouble() + two.toDouble()).toString())
        }else if(tvValue.contains("*")){
          val splitValue = tvValue.split("*")

          var one = splitValue[0]
          var two = splitValue[1]

          if(!prefix.isEmpty()){
            one = prefix + one
          }

          tvInput.text = removeZeroAt((one.toDouble() * two.toDouble()).toString())
        }else if(tvValue.contains("/")){
          val splitValue = tvValue.split("/")

          var one = splitValue[0]
          var two = splitValue[1]

          if(!prefix.isEmpty()){
            one = prefix + one
          }

          tvInput.text = removeZeroAt((one.toDouble() / two.toDouble()).toString())
        }

      }catch(e: ArithmeticException){
        e.printStackTrace()
      }
    }
  }
  fun OnOperator(view: View){
    if (lastNumeric && !isOperationAdded((tvInput.text.toString()))){
      tvInput.append((view as Button).text)
      lastNumeric = false
      lastDot = false
    }
  }
  private fun isOperationAdded(value:String): Boolean{
    return if (value.startsWith("-")){
      false
    }else{
      value.contains("/") || value.contains("+") || value.contains("-") || value.contains("*")
    }
  }
}