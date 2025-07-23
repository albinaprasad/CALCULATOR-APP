package com.example.calculator

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityThemeBinding

class themeActivity : AppCompatActivity() {

    lateinit var  themeBindng: ActivityThemeBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        themeBindng= ActivityThemeBinding.inflate(layoutInflater)
        var view=themeBindng.root



        themeBindng.darkSwitch.setOnCheckedChangeListener { buttonView,isChecked->

            sharedPreferences=this.getSharedPreferences("DarkTheme",MODE_PRIVATE)
            val editor=sharedPreferences.edit()


            if(isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("switch",true)

            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("switch",false)
            }
            editor.apply()

        }
        setContentView(view)
        }

    override fun onResume() {

        sharedPreferences=this.getSharedPreferences("DarkTheme",MODE_PRIVATE)
        var dark=sharedPreferences.getBoolean("switch",false)
        themeBindng.darkSwitch.isChecked=dark

        super.onResume()
    }

    }
