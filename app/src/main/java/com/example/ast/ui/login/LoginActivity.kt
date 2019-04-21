package com.example.ast.ui.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.ast.R
import com.example.ast.ui.common.BaseActivity
import com.example.ast.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {


    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_enter.setOnClickListener {
            loginPresenter.login(edt_login.text.toString(), edt_password.text.toString())
        }
    }

    //MVP
    override fun succesRegistration() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun onProgress(show: Boolean, text: String) {
        if(show){
            showDialog(text)
        } else {
            hideDialog()
        }
    }


    companion object {



        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }

    }
}
