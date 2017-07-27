package com.lee.greendaodemo;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.lee.base.mvp.MVPBaseActivity;
import com.lee.base.mvp.presenter.ShoppingCartMVPPresenter;
import com.lee.mvp.inter.ShoppingCartMVPInter;
import com.lee.shoppingcart.bean.ShoppingCartData;
import com.lee.shoppingcart.bean.ShoppingCartDataDao;

import butterknife.OnClick;

public class MainActivity extends MVPBaseActivity<ShoppingCartMVPInter, ShoppingCartMVPPresenter>
        implements ShoppingCartMVPInter, View.OnClickListener {

    @Override
    protected int getLayoutContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected ShoppingCartMVPPresenter createPresenter() {
        return new ShoppingCartMVPPresenter();
    }

    @OnClick({
            R.id.update_shoppingCartData_button,
            R.id.del_shoppingCartData_by_entity_button,
            R.id.del_shoppingCartData_by_id_button,
            R.id.del_shoppingCartData_all_button,
    })
    @Override
    public void onClick(View v) {
        ShoppingCartDataDao shoppingCartDataDao = ((MyApplication) getApplication()).getShoppingCartDataDao();
        switch (v.getId()) {
            case R.id.update_shoppingCartData_button: {
                mPresenter.updateShoppingCart(shoppingCartDataDao, new ShoppingCartData(123456L,
                        "MacBook Pro", 1, System.currentTimeMillis(), "13500"));
                break;
            }
            case R.id.del_shoppingCartData_by_entity_button: {
                ShoppingCartData shoppingCartData = new ShoppingCartData();
                shoppingCartData.setProductId(123456L);
                mPresenter.deleteShoppingCartItem(shoppingCartDataDao, shoppingCartData);
                break;
            }
            case R.id.del_shoppingCartData_by_id_button: {
                mPresenter.deleteShoppingCartItem(shoppingCartDataDao, 123456L);
                break;
            }
            case R.id.del_shoppingCartData_all_button: {
                mPresenter.clearShoppingCart(shoppingCartDataDao);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onChangeShoppingCartSuccess() {
        Toast.makeText(getApplicationContext(), R.string.update_shoppingCart_success_str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangeShoppingCartError(int code, String msg) {
        Toast.makeText(getApplicationContext(), TextUtils.isEmpty(msg) ? getString(R.string.update_shoppingCart_failed_str) : msg,
                Toast.LENGTH_SHORT).show();
    }
}
