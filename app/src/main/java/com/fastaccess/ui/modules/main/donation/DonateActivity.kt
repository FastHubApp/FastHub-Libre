package com.fastaccess.ui.modules.main.donation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.fastaccess.R
import com.fastaccess.helper.*
import com.fastaccess.ui.base.BaseActivity
import com.fastaccess.ui.base.mvp.BaseMvp
import com.fastaccess.ui.base.mvp.presenter.BasePresenter
import io.reactivex.disposables.Disposable

/**
 * Created by Kosh on 10 Jun 2017, 1:04 PM
 */

class DonateActivity : BaseActivity<BaseMvp.FAView, BasePresenter<BaseMvp.FAView>>() {

    private var subscription: Disposable? = null

    override fun layout(): Int = 0

    override fun isTransparent(): Boolean = true

    override fun canBack(): Boolean = false

    override fun isSecured(): Boolean = true

    override fun providePresenter(): BasePresenter<BaseMvp.FAView> = BasePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMessage(R.string.success, R.string.success_purchase_message)
        val sku = getString(R.string.fasthub_all_features_purchase)
        enableProduct(sku, applicationContext)
        val intent = Intent()
        intent.putExtra(BundleConstant.ITEM, sku)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onDestroy() {
        subscription?.let { if (!it.isDisposed) it.dispose() }
        super.onDestroy()
    }

    companion object {
        fun start(context: Activity, product: String?) {
            val intent = Intent(context, DonateActivity::class.java)
            intent.putExtras(Bundler.start()
                    .put(BundleConstant.EXTRA, product)
                    .end())
            context.startActivityForResult(intent, BundleConstant.REQUEST_CODE)
        }

        fun start(context: Fragment, product: String?) {
            val intent = Intent(context.context, DonateActivity::class.java)
            intent.putExtras(Bundler.start()
                    .put(BundleConstant.EXTRA, product)
                    .end())
            context.startActivityForResult(intent, BundleConstant.REQUEST_CODE)
        }

        fun enableProduct(productKey: String, context: Context) {
            when (productKey) {
                context.getString(R.string.donation_product_1), context.getString(R.string.amlod_theme_purchase) -> PrefGetter.enableAmlodTheme()
                context.getString(R.string.midnight_blue_theme_purchase) -> PrefGetter.enableMidNightBlueTheme()
                context.getString(R.string.theme_bluish_purchase) -> PrefGetter.enableBluishTheme()
                context.getString(R.string.donation_product_2), context.getString(R.string.fasthub_pro_purchase) -> PrefGetter.setProItems()
                context.getString(R.string.fasthub_enterprise_purchase) -> PrefGetter.setEnterpriseItem()
                context.getString(R.string.donation_product_3), context.getString(R.string.donation_product_4),
                context.getString(R.string.fasthub_all_features_purchase) -> {
                    PrefGetter.setProItems()
                    PrefGetter.setEnterpriseItem()
                }
                else -> Logger.e(productKey)
            }
        }
    }
}