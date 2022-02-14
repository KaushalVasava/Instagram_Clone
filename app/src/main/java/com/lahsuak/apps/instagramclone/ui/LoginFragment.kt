package com.lahsuak.apps.instagramclone.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.lahsuak.apps.instagramclone.R
import com.lahsuak.apps.instagramclone.util.UserUtility
import com.lahsuak.apps.instagramclone.util.UserUtility.notifyUser
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn.setOnClickListener {
            val mAuth = FirebaseAuth.getInstance()
            val email = loginUserName.text.toString()
            val pass = loginUserPassword.text.toString()

            when {
                TextUtils.isEmpty(email) -> notifyUser(requireContext(), "Please enter name")
                TextUtils.isEmpty(pass) -> notifyUser(
                    requireContext(),
                    "Please enter valid password"
                )
                TextUtils.isEmpty(email) -> notifyUser(requireContext(), "Please enter valid email")

                else -> {
                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            UserUtility.setSharedPref(requireContext(), true)
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            notifyUser(requireActivity(), "Successfully LoggedIn")
                        } else
                            notifyUser(requireActivity(), "Login failed")
                    }
                }
            }

        }
        registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}