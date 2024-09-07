package com.bizzan.utils;


import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import com.bizzan.app.MyApplication;
import com.bizzan.data.DatabaseOpenHelper;
import com.bizzan.entity.ChatTable;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class WonderfulDatabaseUtils {

    private DbManager db;

    //接收构造方法初始化的DbManager对象
    public WonderfulDatabaseUtils() {
        db = DatabaseOpenHelper.getInstance();
    }


    public void saveChat(ChatTable entity){
        try {
            db.saveBindingId(entity);
            WonderfulLogUtils.logi("WonderfulDatabaseUtils","save成功   "+entity.toString());
        } catch (DbException e) {
            e.printStackTrace();
            WonderfulLogUtils.logi("WonderfulDatabaseUtils","save失败  "+e.toString());
        }
    }

    public List<ChatTable> findAll(){
        List<ChatTable> list = new ArrayList<>();
        try {
            list = db.selector(ChatTable.class).where("uidTo","=", MyApplication.app.getCurrentUser().getId()).findAll();
            //WonderfulLogUtils.logi("WonderfulDatabaseUtils","findAll成功   "+list.size());
        } catch (DbException e) {
            e.printStackTrace();
            WonderfulLogUtils.logi("WonderfulDatabaseUtils","findAll失败  "+e.toString());
        }
        if (list == null) {
            return null;
        }else return list;
    }

    public List<ChatTable> findByOrder(String orderId){
        List<ChatTable> list = new ArrayList<>();
        try {
            list = db.selector(ChatTable.class).where("orderId","=",orderId).findAll();
//            WonderfulLogUtils.logi("WonderfulDatabaseUtils","findByOrder成功   "+orderId+"   list.size   "+list.size());
        } catch (DbException e) {
            e.printStackTrace();
            WonderfulLogUtils.logi("WonderfulDatabaseUtils","findByOrder失败  "+e.toString());
        }
        if (list == null) {
            return null;
        }else return list;
    }

    public void update(ChatTable table){
        try {
            db.update(table,"content","isRead","hasNew");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void deleteByOrderId(String orderId){
        WhereBuilder builder = WhereBuilder.b("orderId","=",orderId);
        try {
            db.delete(ChatTable.class,builder);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
