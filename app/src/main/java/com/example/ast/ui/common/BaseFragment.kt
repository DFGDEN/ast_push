package com.example.ast.ui.common


import android.app.ProgressDialog
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment

open class BaseFragment : MvpAppCompatFragment() {

    private var mProgressDialog: ProgressDialog? = null


    protected fun showDialog(message: String) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            mProgressDialog!!.setCancelable(false)
        }
        mProgressDialog!!.setMessage(message)
        mProgressDialog!!.show()
    }

    protected fun hideDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing()) {
            mProgressDialog!!.dismiss()
        }
    }

    open fun onError(err: String) {
        Toast.makeText(activity?.applicationContext, err, Toast.LENGTH_SHORT).show()
    }
}