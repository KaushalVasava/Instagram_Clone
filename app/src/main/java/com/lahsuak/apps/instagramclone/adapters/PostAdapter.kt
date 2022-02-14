package com.lahsuak.apps.instagramclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lahsuak.apps.instagramclone.R
import com.lahsuak.apps.instagramclone.model.Instagram
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter(var list:List<Instagram>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
         holder.itemView.postIcon.setImageResource(list[position].userImage)
         holder.itemView.postImage.setImageResource(list[position].userPosts[0].postImage)
         holder.itemView.userName.text = list[position].userName
         holder.itemView.txtCaption.text = list[position].userPosts[0].caption
         holder.itemView.txtLike.text = list[position].userPosts[0].likesCount.toString() + " likes"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}