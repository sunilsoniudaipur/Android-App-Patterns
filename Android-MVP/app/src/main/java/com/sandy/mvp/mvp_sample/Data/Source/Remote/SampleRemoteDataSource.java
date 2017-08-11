package com.sandy.mvp.mvp_sample.Data.Source.Remote;

import android.support.annotation.NonNull;

import com.sandy.mvp.mvp_sample.Data.SampleItem;
import com.sandy.mvp.mvp_sample.Data.Source.SampleDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil.Soni on 8/11/2017.
 */

public class SampleRemoteDataSource implements SampleDataSource {

    private static SampleRemoteDataSource INSTANCE;
   // private final static Map<String, SampleItem> SAMPLE_SERVICE_DATA;


    private SampleRemoteDataSource(){}
    public static SampleRemoteDataSource getInstance(){

        if(INSTANCE==null){

            INSTANCE=new SampleRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getItems(@NonNull LoadItemsCallback callback) {

        List<SampleItem> sampleItemList=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            sampleItemList.add(new SampleItem(i,"REMOTE ITEM "+i));
        }
        callback.onItemsLoaded(sampleItemList);

    }
}
