package com.lee.mvp.inter;

import com.lee.base.mvp.MVPBaseInter;

/******************************************
 * 类描述： 操作购物车接口 类名称：ShoppingCartMVPInter
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-7-26 15:08
 ******************************************/

public interface ShoppingCartMVPInter extends MVPBaseInter {
    void onChangeShoppingCartSuccess();

    void onChangeShoppingCartError(int code, String msg);
}
