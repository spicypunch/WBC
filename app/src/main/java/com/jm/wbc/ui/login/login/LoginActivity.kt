package com.jm.wbc.ui.login.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jm.wbc.databinding.ActivityLoginBinding
import com.jm.wbc.ui.login.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (binding.editId.text.toString().isEmpty() && binding.editPw.text.toString().isEmpty()) {
                Toast.makeText(this, "빈칸을 전부 채워주세요", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.signIn(binding.editId.text.toString(), binding.editPw.text.toString())
            }
        }

        binding.btnSignup.setOnClickListener {
            Intent(this, SignUpActivity::class.java).run {
                startActivity(this)
            }
        }

        loginViewModel.success.observe(this, Observer {
            if (it == false) {
                Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            } else {
                 finish()
            }
        })
    }
}