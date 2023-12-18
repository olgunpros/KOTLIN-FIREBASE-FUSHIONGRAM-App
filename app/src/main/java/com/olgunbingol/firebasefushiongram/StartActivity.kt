package com.olgunbingol.firebasefushiongram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.olgunbingol.firebasefushiongram.databinding.ActivityMainBinding
import com.olgunbingol.firebasefushiongram.databinding.ActivityStartBinding
import com.olgunbingol.firebasefushiongram.databinding.ActivityUploadBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        val currentuser = auth.currentUser
        if(currentuser != null) {
            val intent = Intent(this@StartActivity,FeedActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun signinClicked(view: View) {
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this@StartActivity,FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@StartActivity,it.localizedMessage,Toast.LENGTH_LONG).show()

            }
        }
        else {
            Toast.makeText(this,"Enter email and password!",Toast.LENGTH_LONG).show()

        }


    }
    fun signupClicked(view: View) {
    val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()) {
         auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
             val intent = Intent(this@StartActivity,FeedActivity::class.java)
             startActivity(intent)
             finish()
         }.addOnFailureListener {
             Toast.makeText(this@StartActivity,it.localizedMessage,Toast.LENGTH_LONG).show()

         }
        }
        else {
            Toast.makeText(this,"Enter email and password!",Toast.LENGTH_LONG).show()

        }


    }
}