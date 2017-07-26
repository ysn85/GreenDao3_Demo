package com.lee.greendaodemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.lee.base.db.helper.MySQLiteUpdateOpenHelper;
import com.lee.shoppingcart.bean.DaoMaster;
import com.lee.shoppingcart.bean.DaoSession;
import com.lee.shoppingcart.bean.ShoppingCartDataDao;

import org.greenrobot.greendao.database.Database;


/******************************************
 * 类描述： Application入口类 类名称：MyApplication
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-7-26 15:25
 ******************************************/
public class MyApplication extends Application {

    private final boolean ENCRYPTED = false;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        if (inMainProcess()) {
            MySQLiteUpdateOpenHelper helper;
            Database db;
            if (ENCRYPTED) {
                helper = new MySQLiteUpdateOpenHelper(this, "test-db-encrypted");
                db = helper.getEncryptedWritableDb("super-secret");
            } else {
                helper = new MySQLiteUpdateOpenHelper(this, "test-db");
                db = helper.getWritableDb();
            }
            mDaoSession = new DaoMaster(db).newSession();
        }
    }

    private boolean inMainProcess() {
        String packageName = getPackageName();
        String processName = getProcessName(this);
        return packageName.equals(processName);
    }

    private String getProcessName(Context context) {
        String processName = null;
        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;
                    break;
                }
            }
            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }
            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public ShoppingCartDataDao getShoppingCartDataDao() {
        if (mDaoSession == null) {
            return null;
        }
        return mDaoSession.getShoppingCartDataDao();
    }
}
