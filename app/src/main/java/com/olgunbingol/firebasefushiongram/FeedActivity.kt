package com.olgunbingol.firebasefushiongram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.olgunbingol.firebasefushiongram.adapter.FeedRecyclerAdapter
import com.olgunbingol.firebasefushiongram.databinding.ActivityFeedBinding
import com.olgunbingol.firebasefushiongram.databinding.ActivityUploadBinding
import com.olgunbingol.firebasefushiongram.model.Post

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var postArrayList : ArrayList<Post>
    private lateinit var feedadapter : FeedRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        db = Firebase.firestore
        postArrayList = ArrayList<Post>()
        getData()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        feedadapter = FeedRecyclerAdapter(postArrayList)
        binding.recyclerView.adapter
    }

    private fun getData() {
        db.collection("Posts").addSnapshotListener { value, error ->
            if(error != null) {
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()

            }
            else {
                if(value != null) {
                    if(!value.isEmpty) {
                        val documents = value.documents
                        for(document in documents) {
                            val comment = document.get("comment") as String
                            //val downloadUrl = document.get(downloadurl)
                            //val userEmail = document.get(userEmail)
                            val post = Post(comment)
                            postArrayList.add(post)





                        }
                        feedadapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }


    fun signoutClicked(view: View) {
     auth.signOut()
        val intent = Intent(this@FeedActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun uploadClicker(view: View) {
        val intent = Intent(this@FeedActivity,UploadActivity::class.java)
        startActivity(intent)
    }




}