package com.example.admin_joinsport_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplicationproject.rest.local.Preferrences
import kotlinx.android.synthetic.main.activity_login_.*

class Login_Activity : AppCompatActivity() {
    var mPreferrences = Preferrences(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        initview()
    }

    private fun initview() {
            login_btn.setOnClickListener {
                var username = ET_username.text.toString()
                var Password = Password_ET.text.toString()
                if (username == "Admin" && Password == "12345678") {
                    mPreferrences.saveUsername(username)
                    startActivity(
                        Intent(
                            applicationContext, MainActivity::class.java
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(applicationContext, "รหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT)
                        .show()
                    Password_ET.text.clear()
                }
            }
    }
}