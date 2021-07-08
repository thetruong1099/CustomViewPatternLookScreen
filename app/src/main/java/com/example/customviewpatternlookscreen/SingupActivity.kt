package com.example.customviewpatternlookscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customviewpatternlookscreen.customView.patternLockScreenView.PatternViewState
import com.example.customviewpatternlookscreen.data.CurrentData
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SingupActivity : AppCompatActivity() {

    private val currentData = CurrentData.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        plv_sing_up.setOnChangeStateListener { state ->
            when (state) {
                is PatternViewState.Success -> {
                    currentData.password = plv_sing_up.getPassword()
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(300)
                        startMainActivity()
                    }
                }
                is PatternViewState.Error -> {
                    tv_error.text = "Connect at least 4 dot! Try Again "
                }
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        finish()
    }
}