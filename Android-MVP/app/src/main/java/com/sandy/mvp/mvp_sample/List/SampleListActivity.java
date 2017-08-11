package com.sandy.mvp.mvp_sample.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandy.mvp.mvp_sample.Adapters.SampleItemsAdapter;
import com.sandy.mvp.mvp_sample.Data.SampleItem;
import com.sandy.mvp.mvp_sample.Data.Source.ItemsRepository;
import com.sandy.mvp.mvp_sample.Data.Source.Remote.SampleRemoteDataSource;
import com.sandy.mvp.mvp_sample.Data.Source.local.SampleLocalDataSource;
import com.sandy.mvp.mvp_sample.R;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SampleListActivity extends AppCompatActivity implements ListSampleContract.View{


    ListView recyclerView;
    RelativeLayout loader;
    TextView textViewMessage;


    private  ListSampleContract.Presenter mPresenter;
    SampleItemsAdapter mSampleItemsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        //Initialize the components
        recyclerView=(ListView)findViewById(R.id.recycleView);
        loader=(RelativeLayout) findViewById(R.id.loader);
        textViewMessage=(TextView)findViewById(R.id.message);


        //avoiding the injections and instantiate directly
        mPresenter = new ListPresenter(ItemsRepository.getInstance(SampleLocalDataSource.getInstance(this.getApplicationContext()), SampleRemoteDataSource.getInstance()),this);





        mSampleItemsAdapter=new SampleItemsAdapter(new ArrayList<SampleItem>(0));
        recyclerView.setAdapter(mSampleItemsAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.loadItems();
    }

    @Override
    public void setPresenter(ListSampleContract.Presenter presenter) {

        mPresenter=checkNotNull(presenter);
    }

    @Override
    public void showLoading() {

       loader.setVisibility(View.VISIBLE);
       recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {

        loader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showItems(List<SampleItem> listItems) {

        mSampleItemsAdapter.replaceData(listItems);

    }

    @Override
    public void showEmptyView() {


        loader.setVisibility(View.VISIBLE);
        textViewMessage.setText("No items to be shown");
        recyclerView.setVisibility(View.GONE);

    }
}
