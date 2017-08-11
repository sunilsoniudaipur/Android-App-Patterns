package com.sandy.mvp.mvp_sample.Data.Source;

import android.support.annotation.NonNull;

import com.sandy.mvp.mvp_sample.Data.SampleItem;

import java.util.List;

/**
 * Created by Sunil.Soni on 8/11/2017.
 */

public interface SampleDataSource {


    interface LoadItemsCallback{

        void onItemsLoaded(List<SampleItem> listItems);
        void onDataNotAvailable();
    }


    void getItems(@NonNull LoadItemsCallback callback);

}
