package com.bizzan.app;

import android.content.Context;

import com.bizzan.data.DataRepository;
import com.bizzan.data.LocalDataSource;
import com.bizzan.data.RemoteDataSource;


/**
 * Created by Administrator on 2017/9/25.
 */

public class Injection {
    public static DataRepository provideTasksRepository(Context context) {
        return DataRepository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(context));
    }
}
