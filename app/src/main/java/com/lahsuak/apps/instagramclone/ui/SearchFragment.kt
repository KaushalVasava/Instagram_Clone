package com.lahsuak.apps.instagramclone.ui

import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.lahsuak.apps.instagramclone.R
import com.lahsuak.apps.instagramclone.adapters.PostAdapter
import com.lahsuak.apps.instagramclone.adapters.UserAdapter
import com.lahsuak.apps.instagramclone.model.User
import kotlinx.android.synthetic.main.fragment_search.*

private const val TAG = "SearchFragment"

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var userList = mutableListOf<User>()
    private lateinit var adapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchRecyclerView.setHasFixedSize(true)
        adapter = UserAdapter(userList)
        searchRecyclerView.adapter = adapter

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (searchView.text.toString() == "") {
                } else {
                    Log.d(TAG, "onDataChange: text ${s.toString()}")
                    searchRecyclerView.visibility = View.VISIBLE
                    retrieveUsers()
                    searchUser(s.toString().lowercase())
                }
            }
        })
    }

    private fun retrieveUsers() {
        val usersRef = FirebaseDatabase.getInstance().reference.child("Users")
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (searchView.text.toString() == "") {
                    userList.clear()
                    for (sp in snapshot.children) {
                        val user = sp.getValue(User::class.java)
                        if (user != null) {
                            userList.add(user)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun searchUser(input: String) {
        val query = FirebaseDatabase.getInstance().reference
            .child("Users")
            .orderByChild("username")
            .startAt(input)
            .endAt(input + "\uf8ff")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "onDataChange: size ${userList.size} and ${snapshot.children.toList()}")
                userList.clear()

                for (sp in snapshot.children) {
                    Log.d(TAG, "onDataChange: snapshot ${sp.children}")
                    val user = sp.getValue(User::class.java)
                    if (user != null) {
                        userList.add(user)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}