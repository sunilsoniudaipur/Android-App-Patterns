package com.sandy.mvp.mvp_sample.Data.Source;

import android.support.annotation.NonNull;

import com.sandy.mvp.mvp_sample.Data.SampleItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Sunil.Soni on 8/11/2017.
 */

public class ItemsRepository implements SampleDataSource {

   private static ItemsRepository INSTANCE=null;
   private final SampleDataSource mSampleLocalDataSource;
   private final SampleDataSource mSampleRemoteDataSource;
   /**
    * This item can be accessible from tests
   * */
    Map<String,SampleItem> mCachedItems;


    /**
     * Marks cached as invalid, it will force to request data to load from server
    * */
    boolean mCacheIsDirty=false;
    @Override
    public void getItems(@NonNull final LoadItemsCallback callback) {


        if(mCachedItems!=null && !mCacheIsDirty) {
            callback.onItemsLoaded(new ArrayList<SampleItem>(mCachedItems.values()));
            return;
        }
        if(mCacheIsDirty) {

            //because cache is dirty so we need to get data from remote data source
            getTasksFromRemoteDataSource(callback);
        }
        else{


            mSampleLocalDataSource.getItems(new LoadItemsCallback() {
                @Override
                public void onItemsLoaded(List<SampleItem> listItems) {


                    refreshCache(listItems);
                    callback.onItemsLoaded(listItems);

                }

                @Override
                public void onDataNotAvailable() {

                }
            });

        }


    }



    /**
     * this function used to store/cache items in memory
     *
    * */
    public void refreshCache(List<SampleItem> items)
    {
        if(mCachedItems==null){
            mCachedItems=new LinkedHashMap<>();

        }
        mCachedItems.clear();
        for(SampleItem sampleItem:items){
            mCachedItems.put(sampleItem.getId()+"",sampleItem);
        }
        mCacheIsDirty=false;

    }
    public void refreshItems(){
        mCacheIsDirty=true;
    }

    private ItemsRepository(SampleDataSource mSampleLocalDataSource,SampleDataSource mSampleRemoteDataSource)
    {
       this. mSampleLocalDataSource=checkNotNull(mSampleLocalDataSource);
        this.mSampleRemoteDataSource=checkNotNull(mSampleRemoteDataSource);

    }


    private  void refreshLocalDataSource(List<SampleItem> items){

        /*USE THIS FUNCTION TO SAVE DATA IN LOCAL DATABASE*/
        //mSampleLocalDataSource.saveALLItems

    }

    private void getTasksFromRemoteDataSource(@NonNull final LoadItemsCallback callback) {
        mSampleRemoteDataSource.getItems(new LoadItemsCallback() {
            @Override
            public void onItemsLoaded(List<SampleItem> items) {
                refreshCache(items);
                refreshLocalDataSource(items);
                callback.onItemsLoaded(new ArrayList<>(mCachedItems.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    /**
     * get a singleton instance
     * @param mSampleLocalDataSource this items will use local data sources
     * @return the {@link ItemsRepository} instance
    * */
    public static ItemsRepository getInstance(SampleDataSource mSampleLocalDataSource,SampleDataSource mSampleRemoteDataSource)
    {

        if(INSTANCE==null) {

            INSTANCE=new ItemsRepository(mSampleLocalDataSource,mSampleRemoteDataSource);
        }
        return INSTANCE;


    }

    public static void destroyInstance()
    {
        INSTANCE=null;
    }

}
