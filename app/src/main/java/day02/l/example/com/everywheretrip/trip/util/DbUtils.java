package day02.l.example.com.everywheretrip.trip.util;

import java.util.List;

import day02.l.example.com.everywheretrip.dao.DaoMaster;
import day02.l.example.com.everywheretrip.dao.DaoSession;
import day02.l.example.com.everywheretrip.dao.DbBeanDao;
import day02.l.example.com.everywheretrip.trip.base.BaseApp;
import day02.l.example.com.everywheretrip.trip.bean.DbBean;

public class DbUtils {
     private static DbUtils dbUtils;
         private final DbBeanDao dbBeanDao;

         private DbUtils(){
             DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getInstance(),
                     "home.db");

             DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

             DaoSession daoSession = daoMaster.newSession();

             dbBeanDao = daoSession.getDbBeanDao();
         }

         public static DbUtils getDbUtils() {
             if (dbUtils == null){
                 synchronized (DbUtils.class){
                     if (dbUtils == null){
                         dbUtils = new DbUtils();
                     }
                 }
             }
             return dbUtils;
         }

         public long insert(DbBean dbBean){

             if (!has(dbBean)){
                 return  dbBeanDao.insertOrReplace(dbBean);
             }

             return -1;
         }

         public List<DbBean> query(){
             return dbBeanDao.queryBuilder().list();
         }

         public boolean delete(DbBean dbBean){
             if (has(dbBean)){
                 dbBeanDao.delete(dbBean);
                 return true;
             }

             return false;
         }
         public boolean has(DbBean dbBean){
             List<DbBean> list = dbBeanDao.queryBuilder().where(DbBeanDao.Properties.Name.eq(dbBean
                     .getName())).list();

             if (list!=null&&list.size()>0){
                 return true;
             }

             return false;
         }
}
