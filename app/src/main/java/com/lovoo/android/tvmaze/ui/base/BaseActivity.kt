package com.lovoo.android.ui.base

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.lovoo.android.tvmaze.R
import com.lovoo.android.tvmaze.utils.network.NetworkUtils
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), MvpView {
    private var mProgressView: ProgressBar? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        DependencyInjection()
        super.onCreate(savedInstanceState)
    }

    override val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(this)

    override fun showLoading() {
        hideLoading()
        mProgressView = findViewById(R.id.progressView)
        mProgressView?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mProgressView?.visibility = View.INVISIBLE
    }

    override fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.something_wrong))
        }
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }


    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val textView = snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }


    private fun DependencyInjection() {
        AndroidInjection.inject(this)
    }
}