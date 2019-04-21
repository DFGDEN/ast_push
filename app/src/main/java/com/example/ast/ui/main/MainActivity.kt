package com.example.ast.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.ast.R
import kotlinx.android.synthetic.main.activity_main.*
import android.R.menu
import com.example.ast.ui.common.BaseActivity
import com.example.ast.ui.login.LoginActivity


class MainActivity : BaseActivity(), MainView {


    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview.loadUrl("http://178.124.158.226:9784/index.php")
        toolbar.inflateMenu(R.menu.activity_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> {
                    mainPresenter.logout()
                }

                else -> {


                }


            }
            return@setOnMenuItemClickListener true
        }
    }


    //MVP
    override fun logout() {
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
        finish()
    }


    companion object {



        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }

    }
}
