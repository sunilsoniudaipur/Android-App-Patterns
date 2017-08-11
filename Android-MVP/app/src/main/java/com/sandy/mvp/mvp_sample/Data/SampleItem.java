package com.sandy.mvp.mvp_sample.Data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Sunil.Soni on 8/11/2017.
 */

public class SampleItem {

    @NonNull
    public int mId;

    @Nullable
    public String itemName;


    /**
    * Use this constructor to create new Item
    * @param mId id of the item
    * @param itemName name of the item
    * */

    public SampleItem(@NonNull int mId,@Nullable String itemName)
    {

        this.mId=mId;
        this.itemName=itemName;

    }

    @NonNull
    public int getId()
    {
        return mId;
    }
    @Nullable
    public String getItemName(){return itemName;}

    @Override
    public String toString() {
        return "Item :"+mId+" "+itemName;
    }
}
