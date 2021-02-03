package com.example.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class LoginActivity : AppCompatActivity() {
    private lateinit var goBackButton: Button
    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var pwdEditText: EditText
    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        goBackButton = findViewById(R.id.goBackButton)
        emailEditText = findViewById(R.id.loginEmailText)
        pwdEditText = findViewById(R.id.loginPwdEditText)
        loginButton = findViewById(R.id.signInButton)
        auth = FirebaseAuth.getInstance()

        goBackButton.setOnClickListener{
            finish()
        }

        loginButton.setOnClickListener {
            val email: String = emailEditText.text.toString()
            val password: String = pwdEditText.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Fields are empty!", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "You Logged in!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                   }else{
                        Toast.makeText(this, "Login failed!", Toast.LENGTH_LONG).show()
                    }

                })


            }

        }
        }
    }