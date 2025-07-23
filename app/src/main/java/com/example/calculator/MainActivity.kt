package com.example.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    var number: String?=null

    var firstNumber:Double=0.0
    var secondNumber:Double=0.0

    var status:String?=null
    var operator: Boolean=false

    var history:String=""
    var currentResult:String=""

    var enableDotVutton=true
    var equalButtonControl=false

    lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        sharedPreferences=this.getSharedPreferences("DarkTheme",MODE_PRIVATE)
        val dark_switch=sharedPreferences.getBoolean("switch",false)

        if(dark_switch)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }



        mainBinding= ActivityMainBinding.inflate(layoutInflater)
         var view=mainBinding.root


        mainBinding.toolBar.setOnMenuItemClickListener {
            item->
            when(item.itemId)
            {
                R.id.setting_item->{
                    var intent=Intent(this@MainActivity, themeActivity::class.java)
                    startActivity(intent)
                    true
                }
                else->false

            }
        }



        mainBinding.btn0.setOnClickListener {

            whenNumberClicked("0")
        }

        mainBinding.btn1.setOnClickListener {

            whenNumberClicked("1")
        }

        mainBinding.btn2.setOnClickListener {

            whenNumberClicked("2")
        }

        mainBinding.btn3.setOnClickListener {

            whenNumberClicked("3")
        }

        mainBinding.btn4.setOnClickListener {

            whenNumberClicked("4")
        }

        mainBinding.btn5.setOnClickListener {

            whenNumberClicked("5")
        }

        mainBinding.btn6.setOnClickListener {

            whenNumberClicked("6")
        }

        mainBinding.btn7.setOnClickListener {

            whenNumberClicked("7")
        }

        mainBinding.btn8.setOnClickListener {

            whenNumberClicked("8")
        }

        mainBinding.btn9.setOnClickListener {

            whenNumberClicked("9")
        }

        mainBinding.btnPlus.setOnClickListener {

            history=mainBinding.textHistory.text.toString()
            currentResult=mainBinding.textResult.text.toString()


                mainBinding.textHistory.text=history.plus(currentResult).plus("+")



            if(operator){

                when(status){

                    "multiplication"->multiply()
                    "substraction"->substract()
                    "division"->divide()
                    "addition"->plus()
                    else -> firstNumber=mainBinding.textResult.text.toString().toDouble()
                }
            }
            status="addition"
            operator=false
            number=null
            enableDotVutton=true

        }

        mainBinding.btnSub.setOnClickListener {


            history=mainBinding.textHistory.text.toString()
            currentResult=mainBinding.textResult.text.toString()


            mainBinding.textHistory.text=history.plus(currentResult).plus("-")



            if(operator){

                when(status){

                    "multiplication"->multiply()
                    "substraction"->substract()
                    "division"->divide()
                    "addition"->plus()
                    else -> firstNumber=mainBinding.textResult.text.toString().toDouble()
                }
            }
            status="substraction"
            operator=false
            number=null
            enableDotVutton=true
        }

        mainBinding.btnMulti.setOnClickListener {


            history=mainBinding.textHistory.text.toString()
            currentResult=mainBinding.textResult.text.toString()


            mainBinding.textHistory.text=history.plus(currentResult).plus("*")



            if(operator){

                when(status){

                    "multiplication"->multiply()
                    "substraction"->substract()
                    "division"->divide()
                    "addition"->plus()
                    else -> firstNumber=mainBinding.textResult.text.toString().toDouble()
                }
            }
            status="multiplication"
            operator=false
            number=null
            enableDotVutton=true
        }

        mainBinding.btnDiv.setOnClickListener {


            history=mainBinding.textHistory.text.toString()
            currentResult=mainBinding.textResult.text.toString()


            mainBinding.textHistory.text=history.plus(currentResult).plus("/")




            if(operator){

                when(status){

                    "multiplication"->multiply()
                    "substraction"->substract()
                    "division"->divide()
                    "addition"->plus()
                    else -> firstNumber=mainBinding.textResult.text.toString().toDouble()
                }
            }
            status="division"
            operator=false
            number=null
            enableDotVutton=true
        }

        mainBinding.btnEqual.setOnClickListener {

            history=mainBinding.textHistory.text.toString()
            currentResult=mainBinding.textResult.text.toString()


            if(operator){

                when(status){

                    "multiplication"->multiply()
                    "substraction"->substract()
                    "division"->divide()
                    "addition"->plus()
                    else -> firstNumber=mainBinding.textResult.text.toString().toDouble()
                }

                mainBinding.textHistory.text=history.plus(currentResult).plus("=").plus(mainBinding.textResult.text.toString())
                enableDotVutton=true

            }

            operator=false
            enableDotVutton=true
            equalButtonControl=true
           
        }
        mainBinding.btnAc.setOnClickListener {

            acButtonClicked()
        }

        mainBinding.btnDel.setOnClickListener {

            number?.let {


                if (it.length == 1) {

                    acButtonClicked()
                } else {
                    number = it.substring(0, it.length - 1)
                    mainBinding.textResult.text = number
                    enableDotVutton=!number!!.contains(".")
                }
            }

        }

        mainBinding.btnDot.setOnClickListener {


            if (enableDotVutton) {
                if (number == null) {
                    number = "0."
                }
                else if (equalButtonControl)
                {
                    number = "0."
                    firstNumber = 0.0
                    secondNumber = 0.0
                    status = null
                    mainBinding.textHistory.text = ""
                    equalButtonControl = false
                }


                else {
                    number = "$number."
                }
                mainBinding.textResult.text = number

            }
            enableDotVutton=false
        }
        setContentView(view)

    }


    fun whenNumberClicked(ClickedNumber:String ){

        if( number==null)
        {
            number=ClickedNumber
        }

        else if(equalButtonControl)
        {

             number= ClickedNumber

                //number=mainBinding.textResult.text.toString()+ClickedNumber

            firstNumber=0.0
            secondNumber=0.0
            status=null
            mainBinding.textHistory.text=""
            equalButtonControl=false
        }
        else{
            number=number+ClickedNumber
        }

        mainBinding.textResult.setText(number)

        operator=true
        equalButtonControl=false


    }

    fun acButtonClicked(){

        firstNumber=0.0
        secondNumber=0.0
        status=null
        number=null
        enableDotVutton=true
        equalButtonControl=false
        mainBinding.textResult.text="0"
        mainBinding.textHistory.text=""


    }

    fun plus(){
         secondNumber=mainBinding.textResult.text.toString().toDouble()

        firstNumber=firstNumber+secondNumber

        //mainBinding.textResult.text=""

        mainBinding.textResult.text=firstNumber.toString()
    }

    fun substract()
    {
        secondNumber=mainBinding.textResult.text.toString().toDouble()

        firstNumber=firstNumber-secondNumber

        mainBinding.textResult.text=firstNumber.toString()
    }

     fun multiply() {
         secondNumber=mainBinding.textResult.text.toString().toDouble()

         firstNumber=firstNumber*secondNumber

         mainBinding.textResult.text=firstNumber.toString()
    }

    fun divide(){

        secondNumber=mainBinding.textResult.text.toString().toDouble()

        if(secondNumber == 0.0)
        {
            Toast.makeText(applicationContext,"CANNOT DIVIDE BY ZERO",Toast.LENGTH_SHORT).show()
        }
        else{


            firstNumber=firstNumber/secondNumber

            mainBinding.textResult.text=firstNumber.toString()
        }

    }


}