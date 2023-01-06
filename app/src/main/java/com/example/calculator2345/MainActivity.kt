package com.example.calculator2345

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?=null
    private var lastNumeric =true
   private var lastDot=false
   private var dots=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)
    }

    fun onDigit(view: View)
    {
        //view is type casted to a button
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDot=false
       // Toast.makeText(this,"Button clicked", Toast.LENGTH_SHORT).show()
    }
    fun onClear(view: View)
    {
        tvInput?.text=""
        var lastNumeric =true
        var lastDot=false
    }
    fun onDecimal(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            var str2=tvInput?.text.toString()
            lateinit var string:List<String>
           if(str2.contains("+"))
           {
               string=str2.split("+")
           }
            else if(str2.contains("-"))
           {
               string=str2.split("-")
           }else if(str2.contains("*"))
           {
               string=str2.split("*")
           }else if(str2.contains("/"))
           {
               string=str2.split("/")
           }
            else
           {
               string= listOf(str2)
           }
            for (x in string) {
                if(!numberHasDots(x))
                {
                    tvInput?.append(".")
                    lastNumeric=false
                    lastDot=true
                }

            }

        }
    }
    fun numberHasDots(str:String):Boolean
    {
        return str.contains(".")
    }
    fun onOperator(view: View)
    {
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }
    }

    fun onEquals(view: View)
    {
        if(lastNumeric )
        {
            var tvStr= tvInput?.text.toString()
            var prefix=""
            try {
                if(tvStr[0]=='-')
                {
                    prefix="-"
                    tvStr=tvStr.substring(1)
                }
                if(tvStr.contains("-"))
                {
                    val splitValue=tvStr.split("-")
                    var one=splitValue[0].toDouble()
                    val two=splitValue[1].toDouble()
                    if(prefix=="-")
                    {
                        one *= -1
                    }
                    tvInput?.text = (one-two).toString()
                } else  if(tvStr.contains("+"))
                {
                    val splitValue=tvStr.split("+")
                    var one=splitValue[0].toDouble()
                    val two=splitValue[1].toDouble()
                    if(prefix=="-")
                    {
                        one *= -1
                    }
                    tvInput?.text = (one+two).toString()
                }else  if(tvStr.contains("*"))
                {
                    val splitValue=tvStr.split("*")
                    var one=splitValue[0].toDouble()
                    val two=splitValue[1].toDouble()
                    if(prefix=="-")
                    {
                        one *= -1
                    }
                    tvInput?.text = (one*two).toString()
                }else if(tvStr.contains("/"))
                {
                    val splitValue=tvStr.split("/")
                    var one=splitValue[0].toDouble()
                    val two=splitValue[1].toDouble()
                    if(prefix=="-")
                    {
                        one *= -1
                    }
                    tvInput?.text = (one/two).toString()
                }

            }catch (e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
        tvInput?.text=removeZeroAtEnd(tvInput?.text.toString())
    }
    private fun removeZeroAtEnd(str:String):String
    {
        return if(str.contains(".0")){str.substring(0,str.length-2)} else{str}
    }

    private fun isOperatorAdded(str:String):Boolean
    {
      return  if(str.startsWith("-"))
      {
          false
      }
          else {
          str.contains("+")||str.contains("-")||str.contains("*")||str.contains("/")
          }
       // tvInput?.text.contains("+")
    }

}