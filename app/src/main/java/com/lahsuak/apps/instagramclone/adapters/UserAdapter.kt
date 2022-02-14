package com.lahsuak.apps.instagramclone.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lahsuak.apps.instagramclone.R
import com.lahsuak.apps.instagramclone.model.User
import kotlinx.android.synthetic.main.user_item.view.*


class UserAdapter(var list:List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.userImage.setImageURI(Uri.parse(list[position].userImage))
        holder.itemView.userId.text = list[position].userName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}