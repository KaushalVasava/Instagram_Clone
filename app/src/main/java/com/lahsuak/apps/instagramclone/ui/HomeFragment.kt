package com.lahsuak.apps.instagramclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lahsuak.apps.instagramclone.R
import com.lahsuak.apps.instagramclone.adapters.PostAdapter
import com.lahsuak.apps.instagramclone.model.Instagram
import com.lahsuak.apps.instagramclone.model.Post
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var adapter: PostAdapter
    private var list = mutableListOf<Instagram>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postList = mutableListOf<Post>()
        postList.add(Post(1, R.drawable.ic_home, "Hello", 12, 1))
        list.add(Instagram(0, R.drawable.ic_user, "Kaushal", postList))
        list.add(Instagram(1, R.drawable.ic_message, "Ka", postList))
        list.add(Instagram(3, R.drawable.ic_bookmark, "MDf", postList))
        adapter = PostAdapter(list)
        postRecyclerView.adapter = adapter
    }
}