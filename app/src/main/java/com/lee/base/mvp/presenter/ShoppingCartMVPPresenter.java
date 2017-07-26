package com.lee.base.mvp.presenter;

import android.database.SQLException;
import android.util.Log;

import com.lee.base.mvp.MVPBasePresenter;
import com.lee.mvp.inter.ShoppingCartMVPInter;
import com.lee.shoppingcart.bean.ShoppingCartData;
import com.lee.shoppingcart.bean.ShoppingCartDataDao;

import java.util.List;

/******************************************
 * 类描述： 操作购物车presenter 类名称：ShoppingCartMVPPresenter
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-7-26 15:09
 ******************************************/

public class ShoppingCartMVPPresenter extends MVPBasePresenter<ShoppingCartMVPInter> {
    private static final String TAG = "ShoppingCartPresenter";

    /**
     * 更新购物车数据
     *
     * @param shoppingCartDataDao
     * @param shoppingCartData
     */
    public void updateShoppingCart(ShoppingCartDataDao shoppingCartDataDao, ShoppingCartData shoppingCartData) {
        List<ShoppingCartData> shoppingCartDataList = shoppingCartDataDao.getSession().queryBuilder(ShoppingCartData.class).
                where(ShoppingCartDataDao.Properties.ProductId.eq(shoppingCartData.getProductId().toString())).build().list();

        boolean result;
        String msg = null;

        if (shoppingCartDataList == null || shoppingCartDataList.size() == 0) {
            long rect = 0L;
            try {
                rect = shoppingCartDataDao.insert(shoppingCartData);
            } catch (SQLException sqlE) {
                msg = sqlE.getLocalizedMessage();
            }
            if (rect > 0) {
                result = true;
            } else {
                result = false;
            }
        } else {
            try {
                shoppingCartDataDao.update(shoppingCartData);
                result = true;
            } catch (SQLException sqlE) {
                result = false;
                msg = sqlE.getLocalizedMessage();
            }
        }

        if (result) {
            List<ShoppingCartData> shoppingCartDataList1 = shoppingCartDataDao.getSession().queryBuilder(ShoppingCartData.class).
                    where(ShoppingCartDataDao.Properties.ProductId.eq(shoppingCartData.getProductId().toString())).build().list();
            for (int i = 0, size = shoppingCartDataList1.size(); i < size; i++) {
                ShoppingCartData shoppingCartData1 = shoppingCartDataList1.get(i);
                Log.d(TAG, "updateShoppingCart----->productId = " + shoppingCartData1.getProductId() +
                        "\nproductName = " + shoppingCartData1.getProductName() +
                        "\nproductPrice = " + shoppingCartData1.getProductPrice() +
                        "\nproductNum = " + shoppingCartData1.getProductNum() +
                        "\nupdateDate = " + shoppingCartData1.getUpdateDate());
            }
            onChangeShoppingCartSuccess();
        } else {
            onChangeShoppingCartError(-1, msg);
        }
    }

    /**
     * 根据特定示例删除购物车特定数据
     *
     * @param shoppingCartDataDao
     * @param shoppingCartData
     */
    public void deleteShoppingCartItem(ShoppingCartDataDao shoppingCartDataDao, ShoppingCartData shoppingCartData) {
        try {
            shoppingCartDataDao.delete(shoppingCartData);
        } catch (SQLException sqlE) {
            onChangeShoppingCartError(-1, sqlE.getLocalizedMessage());
        }
        onChangeShoppingCartSuccess();
    }

    /**
     * 根据id删除购物车特定数据
     *
     * @param shoppingCartDataDao
     * @param id
     */
    public void deleteShoppingCartItem(ShoppingCartDataDao shoppingCartDataDao, Long id) {
        try {
            shoppingCartDataDao.deleteByKey(id);
        } catch (SQLException sqlE) {
            onChangeShoppingCartError(-1, sqlE.getLocalizedMessage());
        }
        onChangeShoppingCartSuccess();
    }

    /**
     * 清空购物车
     *
     * @param shoppingCartDataDao
     */
    public void clearShoppingCart(ShoppingCartDataDao shoppingCartDataDao) {
        try {
            shoppingCartDataDao.deleteAll();
        } catch (SQLException sqlE) {
            onChangeShoppingCartError(-1, sqlE.getLocalizedMessage());
            return;
        }
        onChangeShoppingCartSuccess();
    }

    private void onChangeShoppingCartSuccess() {
        ShoppingCartMVPInter shoppingCartMVPInter = getViewInterface();
        if (shoppingCartMVPInter == null) {
            return;
        }
        shoppingCartMVPInter.onChangeShoppingCartSuccess();
    }

    private void onChangeShoppingCartError(int code, String msg) {
        ShoppingCartMVPInter shoppingCartMVPInter = getViewInterface();
        if (shoppingCartMVPInter == null) {
            return;
        }
        shoppingCartMVPInter.onChangeShoppingCartError(code, msg);
    }
}
