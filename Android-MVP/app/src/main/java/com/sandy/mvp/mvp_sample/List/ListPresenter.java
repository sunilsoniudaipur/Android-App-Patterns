package com.sandy.mvp.mvp_sample.List;

import android.support.annotation.NonNull;

import com.sandy.mvp.mvp_sample.Data.SampleItem;
import com.sandy.mvp.mvp_sample.Data.Source.ItemsRepository;
import com.sandy.mvp.mvp_sample.Data.Source.SampleDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Sunil.Soni on 8/11/2017.
 */

public class ListPresenter implements ListSampleContract.Presenter {


    private final ItemsRepository mItemsRepository;
    private final ListSampleContract.View mView;

    private boolean mFirstLoad=true;


    public ListPresenter(@NonNull ItemsRepository itemsRepository,@NonNull ListSampleContract.View view){

        mView=checkNotNull(view);
        mItemsRepository=checkNotNull(itemsRepository);
        mView.setPresenter(this);
    }



    private void loadItems(boolean showLoadingUI){

        if(showLoadingUI){
            mView.showLoading();
        }
        mItemsRepository.getItems(new SampleDataSource.LoadItemsCallback() {


            @Override
            public void onItemsLoaded(List<SampleItem> listItems) {
                List<SampleItem> itemsToShow = new ArrayList<SampleItem>();

                for (SampleItem sampleItem:listItems){

                    itemsToShow.add(sampleItem);

                }
                processItems(itemsToShow);
                mView.hideLoading();

            }

            @Override
            public void onDataNotAvailable() {
                processEmptyItems();
            }
        });

    }



    private void processEmptyItems(){

        mView.showEmptyView();
    }
    private void processItems(List<SampleItem> sampleItems)
    {

        if(sampleItems.isEmpty()){
            processEmptyItems();
        }
        else{
            mView.showItems(sampleItems);
        }

    }

    @Override
    public void start() {

        loadItems(true);
    }

    @Override
    public void loadItems() {
        loadItems(true);
        mFirstLoad=false;
    }

    @Override
    public void addNewItem() {

    }
}
