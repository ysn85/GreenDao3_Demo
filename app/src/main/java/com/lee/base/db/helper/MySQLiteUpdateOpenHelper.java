package com.lee.base.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lee.shoppingcart.bean.DaoMaster;
import com.lee.shoppingcart.bean.ShoppingCartDataDao;

import org.greenrobot.greendao.database.Database;

/******************************************
 * 类描述： 数据库升级帮助类 类名称：MySQLiteUpdateOpenHelper
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-7-26 10:23
 ******************************************/

public class MySQLiteUpdateOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteUpdateOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteUpdateOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, ShoppingCartDataDao.class);
    }
}
