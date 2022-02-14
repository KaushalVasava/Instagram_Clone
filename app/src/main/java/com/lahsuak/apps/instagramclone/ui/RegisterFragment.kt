package com.lahsuak.apps.instagramclone.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.lahsuak.apps.instagramclone.R
import com.lahsuak.apps.instagramclone.util.UserUtility.notifyUser
import com.lahsuak.apps.instagramclone.util.UserUtility.setSharedPref
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBtn.setOnClickListener {
            createAccount()
        }
        signUpLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun createAccount() {
        val userName = signUpUserName.text.toString()
        val email = signUpEmail.text.toString()
        val password = signUpUserPassword.text.toString()

        when {
            TextUtils.isEmpty(userName) -> notifyUser(requireContext(), "Please enter name")
            TextUtils.isEmpty(password) -> notifyUser(
                requireContext(),
                "Please enter valid password"
            )
            TextUtils.isEmpty(email) -> notifyUser(requireContext(), "Please enter valid email")
            else -> {
                val progressDialog = ProgressDialog(requireContext())
                progressDialog.setTitle("SignUp")
                progressDialog.setMessage("Please wait, this may take a while...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth = FirebaseAuth.getInstance()
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUserInfo(userName, email,progressDialog)
                        } else {
                            val message = task.exception.toString()
                            notifyUser(requireContext(), message)
                            mAuth.signOut()
                            progressDialog.dismiss()
                        }
                    }
            }
        }
    }

    private fun saveUserInfo(userName: String, email: String,progressDialog: ProgressDialog) {
       val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
        val userRef = FirebaseDatabase.getInstance().reference.child("Users")

        val userMap = HashMap<String,Any>()
        userMap["uid"]= currentUserId
        userMap["username"]= userName
        userMap["email"]= email
        userMap["bio"] = "Hey I'm android developer"
        userMap["profileImage"] = "https://firebasestorage.googleapis.com/v0/b/instagram-5005b.appspot.com/o/DefaultImage%2Fscreen_light.png?alt=media&token=92bae618-a87b-4797-ba5d-7434b5c5a57c"

        userRef.child(currentUserId).setValue(userMap)
            .addOnCompleteListener { task->
            if(task.isSuccessful) {
                progressDialog.dismiss()
                notifyUser(requireContext(), "Account successfully created")
                setSharedPref(requireContext(), true)
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            }else{
                notifyUser(requireContext(),task.exception.toString())
                FirebaseAuth.getInstance().signOut()
                progressDialog.dismiss()
            }
        }
    }
}