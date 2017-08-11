package com.sandy.mvp.mvp_sample.Data.Source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sandy.mvp.mvp_sample.Data.SampleItem;
import com.sandy.mvp.mvp_sample.Data.Source.SampleDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Sunil.Soni on 8/11/2017.
 */

public class SampleLocalDataSource implements SampleDataSource {


    private static SampleLocalDataSource INSTANCE;

    //not implementing the DB rightnow

    private SampleLocalDataSource(@NonNull Context context)
    {
        checkNotNull(context);
    }

    public static SampleLocalDataSource getInstance(@NonNull Context context){

        if(INSTANCE==null){
            INSTANCE=new SampleLocalDataSource(context);
        }
        return INSTANCE;

    }


    @Override
    public void getItems(@NonNull LoadItemsCallback callback) {

        List<SampleItem> sampleItemList=new ArrayList<>();

        /*
        Here you can implement the local database and read the cache values
        * */

        for(int i=0;i<20;i++)
        {
            sampleItemList.add(new SampleItem(i,"ITEM "+i));
        }
        callback.onItemsLoaded(sampleItemList);

    }
}
