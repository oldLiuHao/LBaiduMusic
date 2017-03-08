package com.a3g.lanou.lbaidumusic.tools;

import com.a3g.lanou.lbaidumusic.Collect;
import com.a3g.lanou.lbaidumusic.CollectDao;
import com.a3g.lanou.lbaidumusic.MyApp;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by liuHao on 17/3/4.
 */
public class DBTool {
    private static DBTool ourInstance ;
    private CollectDao collectDao;
    public static DBTool getInstance() {
        if (ourInstance==null){
            synchronized (DBTool.class){
                if (ourInstance==null){
                    ourInstance = new DBTool();
                }
            }
        }

        return ourInstance;
    }

    private DBTool() {
        collectDao = MyApp.getDaoSession().getCollectDao();

    }
    //增加单个对象
    public void insertCollect(Collect collect){
        collectDao.insert(collect);
    }

    //增加集合对象
    public void insertAllCollect(List<Collect> collects){
        collectDao.insertInTx(collects);
    }
    //删除一个collect对象
    public void deleteCollect(Collect collect){
        collectDao.delete(collect);
    }
    //删除所有
    public void deleteAll(){
        collectDao.deleteAll();

    }
    //通过songId字段删除
    public void deleteBySongId(String songId){

        DeleteQuery<Collect> deleteQuery = collectDao.queryBuilder().
                where(CollectDao.Properties.SongId.eq(songId)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    //通过name字段删除
    public void deleteByName(String name){
        DeleteQuery<Collect> deleteQuery = collectDao.queryBuilder().
                where(CollectDao.Properties.SongName.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();

    }


    public List<Collect> queryAll(){
        List<Collect> list = collectDao.loadAll();

        return list;
    }

    //查重方法
    public boolean isSave(Collect collect){
        QueryBuilder<Collect> builder = collectDao.queryBuilder().
                where(CollectDao.Properties.SongName.eq(collect.getSongName()));
        Long count = builder.buildCount().count();
        return count>0?true:false;
    }

}
