package com.example.ast.ui.common

import android.app.ProgressDialog
import android.support.annotation.LayoutRes
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity


open class BaseActivity : MvpAppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null


    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
    }


    protected fun showDialog(message: String) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            mProgressDialog!!.setCancelable(false)
        }
        mProgressDialog!!.setMessage(message)
        mProgressDialog!!.show()
    }

    protected fun hideDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    open fun onError(err: String) {
        Toast.makeText(applicationContext, err, Toast.LENGTH_SHORT).show()
    }

}

