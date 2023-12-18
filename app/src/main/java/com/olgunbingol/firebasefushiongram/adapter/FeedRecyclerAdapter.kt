package com.olgunbingol.firebasefushiongram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olgunbingol.firebasefushiongram.databinding.RecyclerRowBinding
import com.olgunbingol.firebasefushiongram.model.Post
import com.squareup.picasso.Picasso

class FeedRecyclerAdapter(val postList: ArrayList<Post>) :
    RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context))

        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
       holder.binding.recyclerCommentText.text = postList.get(position).comment
       //Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.recyclerImageView)

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)
}
