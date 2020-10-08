package com.example.myhomecare.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.FavoriteListAdpater;
import com.example.myhomecare.view.adapter.FavoritesFilterItemAdapter1;
import com.example.myhomecare.view.adapter.WomensTops1Adapter;
import com.example.myhomecare.model.WomensTopModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.ceil;

public class WomensFragment_1 extends Fragment  implements FavoritesFilterItemAdapter1.OnFilterItemClicks,WomensTops1Adapter.OnWomenTop1ItemClickListner {

    View view;
    RecyclerView filterItems,productItems;
    List<String> filterList = new ArrayList<>();
    FavoritesFilterItemAdapter1 favoritesFilterItemAdapter1;
    FavoriteListAdpater favoriteListAdpater;
    TextView filter;
    String filterName;
    ImageView back;
    List<WomensTopModel> womensTopModels = new ArrayList<>();
    WomensTops1Adapter womensTops1Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_womens_1, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        filterItems = view.findViewById(R.id.rv_fragment_womens_filter_items);
        filter = view.findViewById(R.id.tv_filter_low_to_high_wt1);
        productItems = view.findViewById(R.id.rv_womens_fragment_1_items);
        back = view.findViewById(R.id.iv_wt1_back);


        clickEvents();

        loadItems();
        loadProductItems();
    }



    private void loadProductItems() {
        if(!womensTopModels.isEmpty()){
            womensTopModels.clear();
        }
        womensTopModels.add(new WomensTopModel(401,R.drawable.face,"Pullover","Mango",4,51));
        womensTopModels.add(new WomensTopModel(402,R.drawable.face,"Pullover","Mango",2,52));
        womensTopModels.add(new WomensTopModel(403,R.drawable.face,"Pullover","Mango",3,56));
        womensTopModels.add(new WomensTopModel(404,R.drawable.face,"Pullover","Mango",4,53));
        womensTopModels.add(new WomensTopModel(405,R.drawable.face,"Pullover","Mango",4,86));
        womensTopModels.add(new WomensTopModel(406,R.drawable.face,"Pullover","Mango",1,58));
        womensTopModels.add(new WomensTopModel(407,R.drawable.face,"Pullover","Mango",5,66));

        /*
        number 1 is for lowest to high
        number 2 is for highest to low
         */
        sortlowtohigh(womensTopModels);


    }

    private void sortlowtohigh(List<WomensTopModel> womensTopModels) {
        Comparator<WomensTopModel> lowtohigh = new Comparator<WomensTopModel>() {
            @Override
            public int compare(WomensTopModel o1, WomensTopModel o2) {
                return o2.getPrice() - o1.getPrice();
            }
        };
        Collections.sort(womensTopModels, lowtohigh);
        setProductAdapter(womensTopModels);
    }

    private void sortHightolow(List<WomensTopModel> womensTopModels) {
        Comparator<WomensTopModel> lowtohigh = new Comparator<WomensTopModel>() {
            @Override
            public int compare(WomensTopModel o1, WomensTopModel o2) {
                return o1.getPrice() - o2.getPrice();
            }
        };
        Collections.sort(womensTopModels, lowtohigh);
        setProductAdapter(womensTopModels);
    }

    private void sortByCustomerReview(List<WomensTopModel> womensTopModels) {
        Comparator<WomensTopModel> lowtohigh = new Comparator<WomensTopModel>() {
            @Override
            public int compare(WomensTopModel o1, WomensTopModel o2) {
                return (int)ceil(o1.getRatingNumber() - o2.getRatingNumber());
            }
        };
        Collections.sort(womensTopModels, lowtohigh);
        setProductAdapter(womensTopModels);
    }

    private void setProductAdapter(List<WomensTopModel> womensTopModels) {
        womensTops1Adapter = new WomensTops1Adapter(womensTopModels, getActivity(), WomensFragment_1.this);
        productItems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        productItems.setAdapter(womensTops1Adapter);
    }

    private void clickEvents() {
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterName = filter.getText().toString();
                if (filterName.contains(":")) {
                    String spl[] = filterName.split(":");
                    filterName = spl[1].trim();
                }
                openBottomSheet();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void openBottomSheet() {
        TextView popular, lowtohigh, hightolow, newest, customerreview;
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.sortby_bottom_sheet, null, true);
        popular = view.findViewById(R.id.tv_sortby_popular);
        lowtohigh = view.findViewById(R.id.tv_sortby_lth);
        hightolow = view.findViewById(R.id.tv_sortby_htl);
        newest = view.findViewById(R.id.tv_sortby_newest);
        customerreview = view.findViewById(R.id.tv_sortby_cr);
        if (filterName.equals("lowest to high")) {
            lowtohigh.setBackgroundColor(getActivity().getResources().getColor(R.color.colorRed));
            lowtohigh.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        } else if (filterName.equals("highest to low")) {
            hightolow.setBackgroundColor(getActivity().getResources().getColor(R.color.colorRed));
            hightolow.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        } else if (filterName.equals("Popular")) {
            popular.setBackgroundColor(getActivity().getResources().getColor(R.color.colorRed));
            popular.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        } else if (filterName.equals("Customer Review")) {
            customerreview.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
            customerreview.setBackgroundColor(getActivity().getResources().getColor(R.color.colorRed));
        } else if (filterName.equals("Newest")) {
            newest.setBackgroundColor(getActivity().getResources().getColor(R.color.colorRed));
            newest.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        }

        newest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setText("Newest");
                bottomSheetDialog.dismiss();
                //TODO
            }
        });
        lowtohigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setText("Price: lowest to high");
                sortlowtohigh(womensTopModels);
                bottomSheetDialog.dismiss();
                //TODO
            }
        });
        hightolow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setText("Price: highest to low");
                sortHightolow(womensTopModels);
                bottomSheetDialog.dismiss();
                //TODO
            }
        });
        customerreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setText("Customer Review");
                sortByCustomerReview(womensTopModels);
                bottomSheetDialog.dismiss();
                //TODO
            }
        });
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setText("Popular");
                bottomSheetDialog.dismiss();
                //TODO
            }
        });
        bottomSheetDialog.setContentView(view);

        bottomSheetDialog.show();
    }



    private void loadItems() {
        if(!filterList.isEmpty()){
            filterList.clear();
        }
        filterList.add("Summer");
        filterList.add("T-Shirts");
        filterList.add("Shirts");
        filterList.add("Jeans");
        filterList.add("Summer");
        filterList.add("T- Shirts");
        filterList.add("Shirts");
        filterList.add("Jeans");
        filterList.add("Summer");
        filterList.add("T- Shirts");
        filterList.add("Shirts");
        filterList.add("Jeans");
        setFilterAdapter(filterList);
    }

    private void setFilterAdapter(List<String> filterList) {
        favoritesFilterItemAdapter1 = new FavoritesFilterItemAdapter1(filterList, getActivity(), WomensFragment_1.this);
        filterItems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        filterItems.setAdapter(favoritesFilterItemAdapter1);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onFavoriteClick(int position) {

    }

    @Override
    public void onWomensItemClickListner(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("product_id",womensTopModels.get(position).getId());
        bundle.putString("productName",womensTopModels.get(position).getProductName());
        bundle.putString("productBrand",womensTopModels.get(position).getBrandName());
        bundle.putInt("productPrice",womensTopModels.get(position).getPrice());
        bundle.putFloat("ratingNumber",womensTopModels.get(position).getRatingNumber());
        bundle.putInt("imageId",womensTopModels.get(position).getImage());
        bundle.putInt("productId",womensTopModels.get(position).getId());
        AddToCartFragment addToCartFragment = new AddToCartFragment();
        addToCartFragment.setArguments(bundle);
        loadFragment(addToCartFragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(null)
                    .commit();
            return true;
        }
        return false;

    }

}
