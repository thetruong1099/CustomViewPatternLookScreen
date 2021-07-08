package com.example.customviewpatternlookscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.customviewpatternlookscreen.customView.patternLockScreenView.PatternViewState
import com.example.customviewpatternlookscreen.data.CurrentData
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.android.synthetic.main.activity_singup.tv_error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val currentData = CurrentData.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        plv_login.setOnChangeStateListener { state ->
            when (state) {

                is PatternViewState.Started -> {
                    tv_error.text = ""
                }

                is PatternViewState.Success -> {
                    if (plv_login.getPassword() == currentData.password) {
                        showDialog()
                    } else {
                        plv_login.setErrorColor()
                    }
                }
                is PatternViewState.Error -> {
                    tv_error.text = "Wrong password"
                }
            }
        }

    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Logged in successfully!!")
            .setPositiveButton("Yes") { dialogInterface, which -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

}