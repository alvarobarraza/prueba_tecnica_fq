package com.example.technical_test_fq.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.technical_test_fq.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}