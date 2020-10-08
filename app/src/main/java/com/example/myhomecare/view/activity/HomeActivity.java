package com.example.myhomecare.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.FashionSale2Adpater;
import com.example.myhomecare.view.adapter.HomeBestServicesAdapter;
import com.example.myhomecare.view.adapter.HomeTopCategoryAdapter;
import com.example.myhomecare.model.FashionModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeTopCategoryAdapter.OnCategoryClickListner ,HomeBestServicesAdapter.OnBestServicesItemClickListner,FashionSale2Adpater.OnFashionSaleClickListner{

    RecyclerView topcategory,bestServices;
    List<String> topCatlist = new ArrayList<>();
    RecyclerView homeList;
    List<FashionModel> fashionModels = new ArrayList<>();
    FashionSale2Adpater fashionSale2Adpater;
    TextView viewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initViews();
//        Fragment fragment = new FavoritesFragment_1();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();


    }

    private void initViews() {
        topcategory = findViewById(R.id.rv_top_category_list);
        bestServices = findViewById(R.id.rv_home_best_servies);
        homeList = findViewById(R.id.rv_main_home_list);
        viewAll = findViewById(R.id.tv_home_view_all);
        clickEvents();
        setNewItemHomeList();
        setTopCategoryList();
        setBestServicesList();

    }

    private void setNewItemHomeList() {
        loadItems();
    }

    private void loadItems() {
        if(!fashionModels.isEmpty()){
            fashionModels.clear();
        }
        fashionModels.add(new FashionModel(501,R.drawable.face,3.2F,false,"Mango1","lower",150,0));
        fashionModels.add(new FashionModel(502,R.drawable.face,3.5F,false,"Mang2o","lower",160,0));
        fashionModels.add(new FashionModel(503,R.drawable.face,3.00F,true,"Mango3","lower",170,0));
        fashionModels.add(new FashionModel(504,R.drawable.face,4.2F,false,"Mango4","lower",180,0));
        fashionModels.add(new FashionModel(505,R.drawable.face,5.6F,true,"Mango5","lower",190,0));
        fashionModels.add(new FashionModel(506,R.drawable.face,1.2F,false,"Mango6","lower",250,0));
        setAdapter(fashionModels);
    }

    private void setAdapter(List<FashionModel> fashionModels) {
        fashionSale2Adpater = new FashionSale2Adpater(fashionModels, HomeActivity.this, HomeActivity.this);
        homeList.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
        homeList.setAdapter(fashionSale2Adpater);
    }

    private void clickEvents() {
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ShoppingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    private void setBestServicesList() {
        HomeBestServicesAdapter adapter = new HomeBestServicesAdapter(topCatlist, HomeActivity.this, HomeActivity.this);
        bestServices.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
        bestServices.setAdapter(adapter);
    }

    private void setTopCategoryList() {
        if(!topCatlist.isEmpty()){
            topCatlist.clear();
        }
        topCatlist.add("T-shirts");
        topCatlist.add("Crops tops");
        topCatlist.add("Sleeveless");
        topCatlist.add("Shirts");

        HomeTopCategoryAdapter adapter = new HomeTopCategoryAdapter(topCatlist, HomeActivity.this, HomeActivity.this);
        topcategory.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
        topcategory.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int postion) {
      //  Toast.makeText(this, "TopCat "+topCatlist.get(postion), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBestItemClick(int postion) {
     //   Toast.makeText(this, "BestClick "+topCatlist.get(postion), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFashionItemClick(int position) {
     //   Toast.makeText(this, "fashion Item Clicked "+fashionModels.get(position).getOffPrice(), Toast.LENGTH_SHORT).show();
    }
}