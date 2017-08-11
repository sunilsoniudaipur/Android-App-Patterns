package com.sandy.mvp.mvp_sample.List;

import com.sandy.mvp.mvp_sample.BaseMVP.BasePresenter;
import com.sandy.mvp.mvp_sample.BaseMVP.BaseView;
import com.sandy.mvp.mvp_sample.Data.SampleItem;

import java.util.List;

/**
 * Created by Sunil.Soni on 8/11/2017.
 */

public interface ListSampleContract {


    interface View extends BaseView<Presenter>
    {

        void showLoading();
        void hideLoading();
        void showItems(List<SampleItem> listItems);
        void showEmptyView();

    }


    interface Presenter extends BasePresenter{


        void addNewItem();
        void loadItems();

    }
}
