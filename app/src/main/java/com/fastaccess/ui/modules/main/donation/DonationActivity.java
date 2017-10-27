package com.fastaccess.ui.modules.main.donation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import com.fastaccess.App;
import com.fastaccess.R;
import com.fastaccess.helper.AnimHelper;
import com.fastaccess.ui.base.BaseActivity;
import com.fastaccess.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.ui.modules.main.premium.PremiumActivity;

import net.grandcentrix.thirtyinch.TiPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kosh on 24 Mar 2017, 9:16 PM
 */

public class DonationActivity extends BaseActivity {
    @BindView(R.id.cardsHolder) View cardsHolder;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;

    @Override protected int layout() {
        return R.layout.support_development_layout;
    }

    @Override protected boolean isTransparent() {
        return false;
    }

    @Override protected boolean canBack() {
        return true;
    }

    @Override protected boolean isSecured() {
        return true;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimHelper.animateVisibility(cardsHolder, true);
        checkPurchase();
    }

    @OnClick(R.id.two) void onTwoClicked(View v) {
        onProceed(getString(R.string.donation_product_1));
    }

    @OnClick(R.id.five) void onFiveClicked(View v) {
        onProceed(getString(R.string.donation_product_2));
    }

    @OnClick(R.id.ten) void onTenClicked(View v) {
        onProceed(getString(R.string.donation_product_3));
    }

    @OnClick(R.id.twenty) void onTwentyClicked(View v) {
        onProceed(getString(R.string.donation_product_4));
    }

    @OnClick(R.id.premium) void onNavToPremium() {
        PremiumActivity.Companion.startActivity(this);
    }

    @NonNull @Override public TiPresenter providePresenter() {
        return new BasePresenter();
    }

    private void onProceed(@NonNull String productKey) {
        DonateActivity.Companion.start(this, productKey);
    }

    private void checkPurchase() {
        String sku = getString(R.string.fasthub_all_features_purchase);
        DonateActivity.Companion.enableProduct(sku, App.getInstance());
    }
}
