package com.lahsuak.apps.instagramclone.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.lahsuak.apps.instagramclone.R
import kotlinx.android.synthetic.main.fragment_editprofile.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.toolbar
import kotlinx.android.synthetic.main.fragment_login.*

class EditProfileFragment: Fragment(R.layout.fragment_editprofile) {
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

        deleteBtn.setOnClickListener {
            deleteAcocunt()
        }
        logoutBtn.setOnClickListener {
            val mAuth = FirebaseAuth.getInstance()
            mAuth.signOut()
            findNavController().navigate(R.id.action_editProfileFragment_to_loginFragment)
        }
    }
    private fun deleteAcocunt() {
        val email = changeEmailId.text.toString()
        val password = "123456"
        val user = FirebaseAuth.getInstance().currentUser

        val credential = EmailAuthProvider.getCredential(email, password);

        // Prompt the user to re-provide their sign-in credentials
        if (user != null) {
            user.reauthenticate(credential)
                .addOnCompleteListener {
                    user.delete()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("TAG", "User account deleted.");
                                findNavController().navigate(R.id.action_editProfileFragment_to_registerFragment)
                                Toast.makeText(
                                    requireContext(),
                                    "Deleted User Successfully,",
                                    Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                }
        }
    }

}