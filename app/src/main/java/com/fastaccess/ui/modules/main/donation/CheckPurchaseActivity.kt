package com.fastaccess.ui.modules.main.donation

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import com.fastaccess.App
import com.fastaccess.R
import io.reactivex.disposables.Disposable

/**
 * Created by kosh on 14/07/2017.
 */
class CheckPurchaseActivity : Activity() {

    private var progress: ProgressDialog? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress = ProgressDialog(this)
                .apply {
                    setMessage(getString(R.string.in_progress))
                    setOnCancelListener { finishActivity() }
                    show()
                }
        DonateActivity.enableProduct(getString(R.string.fasthub_all_features_purchase), App.getInstance())
    }

    override fun onStart() {
        super.onStart()
        setVisible(true)
    }

    override fun onBackPressed() = Unit

    private fun finishActivity() {
        finish()
    }

    override fun onDestroy() {
        progress?.let {
            it.dismiss()
        }
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
        super.onDestroy()
    }
}