package com.example.myhomecare.view.fragment;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.model.FavoriteModel;
import com.example.myhomecare.model.FilterColorModel;
import com.example.myhomecare.view.adapter.FavoriteListAdpater;
import com.example.myhomecare.view.adapter.FavoritesFilterItemAdapter1;
import com.example.myhomecare.view.adapter.FilterColorAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.innovattic.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.ceil;

public class FavoritesFragment_1 extends Fragment implements FavoritesFilterItemAdapter1.OnFilterItemClicks, FavoriteListAdpater.OnfavoriteClickListner, FilterColorAdapter.
        OnFilterColorClickListner {

    View view;
    RecyclerView filterItemList, favoriteList;
    List<String> filterList = new ArrayList<>();
    List<FavoriteModel> favoriteModelList = new ArrayList<>();
    FavoritesFilterItemAdapter1 favoritesFilterItemAdapter1;
    FavoriteListAdpater favoriteListAdpater;
    TextView filter, filters;
    BottomSheetDialog bottomSheetDialog;

    RangeSeekBar rangeSeekBar;
    TextView minRange, maxRange;
    RecyclerView colorList;
    List<FilterColorModel> colorModels = new ArrayList<>();
    FilterColorAdapter filterColorAdapter;
    CardView card_xs, card_s, card_m, card_l, card_xl, card_cat_all, card_cat_women, card_cat_men, card_cat_boys, card_cat_girls;
    TextView xs, s, m, l, xl, all, women, men, boys, girls;
    Set<String> sizes = new HashSet<>();
    FilterColorModel filterColorModel;
    String filterName;
    List<String> categoryList = new ArrayList<>();
    ConstraintLayout clBrand;
    TextView brandnames;
    List<String> brandNamesList = new ArrayList<>();
    TextView discardfilterBtn,applyFilterBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorites_1, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        filterItemList = view.findViewById(R.id.rv_favorites1_filter_items);
        favoriteList = view.findViewById(R.id.rv_favorites_1_list);
        filter = view.findViewById(R.id.tv_filter_low_to_high);
        filters = view.findViewById(R.id.tv_favorite1_filters);


        clickEvents();

        /*
        This will load favorite item list
         */
        loadFavoritesList();


           /*
        This will load favorite item list filters
         */
        loadItems();
        loadBrandItems();
    }

    private void loadBrandItems() {
        if(!brandNamesList.isEmpty()){
            brandNamesList.clear();
        }
        brandNamesList.add("addidas");
        brandNamesList.add("addidas Originals");
        brandNamesList.add("Blend");
        brandNamesList.add("Boutique Moschino");
        brandNamesList.add("Champion");
        brandNamesList.add("Diesel");
        brandNamesList.add("Jack & Jones");
        brandNamesList.add("Naf Naf");
        brandNamesList.add("Red Valentino");
        brandNamesList.add("s.Oliver");

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

        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilterBottomSheet();
            }
        });
    }

    private void openFilterBottomSheet() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_bottom_sheet, null, true);
        card_xs = view.findViewById(R.id.card_filter_size_xs);
        card_s = view.findViewById(R.id.card_filter_size_s);
        card_m = view.findViewById(R.id.card_filter_size_m);
        card_l = view.findViewById(R.id.card_filter_size_l);
        card_xl = view.findViewById(R.id.card_filter_size_xl);
        xs = view.findViewById(R.id.tv_filter_size_xs);
        s = view.findViewById(R.id.tv_filter_size_s);
        m = view.findViewById(R.id.tv_filter_size_m);
        l = view.findViewById(R.id.tv_filter_size_l);
        xl = view.findViewById(R.id.tv_filter_size_xl);
        minRange = view.findViewById(R.id.tv_range_seek_min);
        maxRange = view.findViewById(R.id.tv_range_seek_max);
        colorList = view.findViewById(R.id.rv_color_filter_list);
        card_cat_all = view.findViewById(R.id.card_filter_category_all);
        card_cat_women = view.findViewById(R.id.card_filter_category_women);
        card_cat_men = view.findViewById(R.id.card_filter_category_men);
        card_cat_boys = view.findViewById(R.id.card_filter_category_boys);
        card_cat_girls = view.findViewById(R.id.card_filter_category_girls);
        women = view.findViewById(R.id.tv_filter_category_women);
        men = view.findViewById(R.id.tv_filter_category_men);
        all = view.findViewById(R.id.tv_filter_category_all);
        boys = view.findViewById(R.id.tv_filter_category_boys);
        girls = view.findViewById(R.id.tv_filter_category_girls);
        clBrand = view.findViewById(R.id.cl_filter_brand);
        brandnames = view.findViewById(R.id.tv_filter_brand_names);
        discardfilterBtn = view.findViewById(R.id.tv_filter_discard_btn);
        applyFilterBtn = view.findViewById(R.id.tv_filter_apply_btn);

   /*
        Load Color List
         */
        loadFilterColor();

        /*
        Range Seek bar for filter
         */
        rangeSeekBar = view.findViewById(R.id.filter_range_seekBar);
        rangeSeekBar.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
            @Override
            public void onStartedSeeking() {

            }

            @Override
            public void onStoppedSeeking() {

            }

            @Override
            public void onValueChanged(int min, int max) {
                minRange.setText("Rs " + (min * 2));
                maxRange.setText("Rs " + (max * 2));
            }
        });

        clickEventsForBigFilterBottom();
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }

    private void loadFilterColor() {
        if (!colorModels.isEmpty()) {
            colorModels.clear();
        }

        colorModels.add(new FilterColorModel(111, String.valueOf(R.drawable.color_black)));
        colorModels.add(new FilterColorModel(112, String.valueOf(R.drawable.color_white)));
        colorModels.add(new FilterColorModel(113, String.valueOf(R.drawable.color_dark_red)));
        colorModels.add(new FilterColorModel(114, String.valueOf(R.drawable.color_4)));
        colorModels.add(new FilterColorModel(115, String.valueOf(R.drawable.color_5)));
        colorModels.add(new FilterColorModel(116, String.valueOf(R.drawable.color_6)));

        setBigFilterAdapter(colorModels);
    }

    private void setBigFilterAdapter(List<FilterColorModel> colorModels) {
        filterColorAdapter = new FilterColorAdapter(colorModels, getActivity(), FavoritesFragment_1.this);

        // fashionSale2Adpater = new FashionSale2Adpater(fashionModels, HomeActivity.this, HomeActivity.this);
        colorList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        colorList.setAdapter(filterColorAdapter);
    }

    private void openBottomSheet() {
        TextView popular, lowtohigh, hightolow, newest, customerreview;
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
                sortlowtohigh(favoriteModelList);
                bottomSheetDialog.dismiss();
            }
        });
        hightolow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setText("Price: higest to low");
                sortHightolow(favoriteModelList);
                bottomSheetDialog.dismiss();
            }
        });
        customerreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.setText("Customer Review");
                sortByCustomerReview(favoriteModelList);
                bottomSheetDialog.dismiss();
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

    private void clickEventsForBigFilterBottom() {
        discardfilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                bottomSheetDialog.dismiss();
            }
        });

        applyFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                bottomSheetDialog.dismiss();
            }
        });
        clBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!categoryList.isEmpty()){
                    categoryList.clear();
                }
                openFilterForBrandList();

            }
        });
        card_xs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sizes.contains(xs.getText().toString().trim())) {
                    sizes.remove("XS");
                    xs.setTextColor(getResources().getColor(R.color.lightBlack));
                    xs.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    sizes.add("XS");
                    xs.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    xs.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sizes.contains(s.getText().toString().trim())) {
                    sizes.remove("S");
                    s.setTextColor(getResources().getColor(R.color.lightBlack));
                    s.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    sizes.add("S");
                    s.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    s.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sizes.contains(m.getText().toString().trim())) {
                    sizes.remove("M");
                    m.setTextColor(getResources().getColor(R.color.lightBlack));
                    m.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    sizes.add("M");
                    m.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    m.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sizes.contains(l.getText().toString().trim())) {
                    sizes.remove("L");
                    l.setTextColor(getResources().getColor(R.color.lightBlack));
                    l.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    sizes.add("L");
                    l.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    l.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_xl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sizes.contains(xl.getText().toString().trim())) {
                    sizes.remove("XL");
                    xl.setTextColor(getResources().getColor(R.color.lightBlack));
                    xl.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    sizes.add("XL");
                    xl.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    xl.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });

        /////////
        card_cat_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryList.contains(all.getText().toString().trim())) {
                    categoryList.remove("All");
                    all.setTextColor(getResources().getColor(R.color.lightBlack));
                    all.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    categoryList.add("All");
                    all.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    all.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_cat_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryList.contains(women.getText().toString().trim())) {
                    categoryList.remove("Women");
                    women.setTextColor(getResources().getColor(R.color.lightBlack));
                    women.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    categoryList.add("Women");
                    women.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    women.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_cat_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryList.contains(men.getText().toString().trim())) {
                    categoryList.remove("Men");
                    men.setTextColor(getResources().getColor(R.color.lightBlack));
                    men.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    categoryList.add("Men");
                    men.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    men.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_cat_boys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryList.contains(boys.getText().toString().trim())) {
                    categoryList.remove("Boys");
                    boys.setTextColor(getResources().getColor(R.color.lightBlack));
                    boys.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    categoryList.add("Boys");
                    boys.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    boys.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
        card_cat_girls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryList.contains(girls.getText().toString().trim())) {
                    categoryList.remove("Girls");
                    girls.setTextColor(getResources().getColor(R.color.lightBlack));
                    girls.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                } else {
                    categoryList.add("Girls");
                    girls.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    girls.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
    }

    private void openFilterForBrandList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String array[] = new String[brandNamesList.size()];
        for(int j =0;j<brandNamesList.size();j++){
            array[j] = brandNamesList.get(j);
        }

        builder.setTitle("Select Brand");
        builder.setMultiChoiceItems(array, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            categoryList.add(array[which]);
                        }else if(categoryList.contains(array[which])){
                            categoryList.remove(array[which]);
                        }
                    }
                });

        builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                brandnames.setText(categoryList.toString());
            }
        });

        builder.show();


    }


    private void loadFavoritesList() {
        if (!favoriteModelList.isEmpty()) {
            favoriteModelList.clear();
        }
        favoriteModelList.add(new FavoriteModel(301, R.drawable.demo, 5, "Mango", "T-Shirt", "Blue", "L", 70, 20.0F, false));
        favoriteModelList.add(new FavoriteModel(302, R.drawable.demo, 4, "Dorothy Perkins", "T-Shirt", "Blue", "L", 80, 60.0F, true));
        favoriteModelList.add(new FavoriteModel(303, R.drawable.demo, 5, "Mango", "T-Shirt", "Blue", "L", 45, 40.5F, true));
        favoriteModelList.add(new FavoriteModel(304, R.drawable.demo, 3, "Dorothy Perkins", "T-Shirt", "Blue", "L", 106, 26.0F, false));
        favoriteModelList.add(new FavoriteModel(305, R.drawable.demo, 5, "Mango", "T-Shirt", "Blue", "L", 160, 20.2F, true));
        favoriteModelList.add(new FavoriteModel(306, R.drawable.demo, 3, "Dorothy Perkins", "T-Shirt", "Blue", "L", 180, 20.6F, true));

        sortlowtohigh(favoriteModelList);

    }

    private void setFavoritesListAdapter(List<FavoriteModel> favoriteModelList) {
        favoriteListAdpater = new FavoriteListAdpater(favoriteModelList, getActivity(), FavoritesFragment_1.this);
        favoriteList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        favoriteList.setAdapter(favoriteListAdpater);

    }

    private void sortlowtohigh(List<FavoriteModel> favoriteModels) {
        Comparator<FavoriteModel> lowtohigh = new Comparator<FavoriteModel>() {
            @Override
            public int compare(FavoriteModel o1, FavoriteModel o2) {
                return o1.getPrice() - o2.getPrice();

            }
        };
        Collections.sort(favoriteModels, lowtohigh);
        setFavoritesListAdapter(favoriteModelList);
    }

    private void sortHightolow(List<FavoriteModel> favoriteModels) {
        Comparator<FavoriteModel> lowtohigh = new Comparator<FavoriteModel>() {
            @Override
            public int compare(FavoriteModel o1, FavoriteModel o2) {
                return o2.getPrice() - o1.getPrice();
            }
        };
        Collections.sort(favoriteModels, lowtohigh);
        setFavoritesListAdapter(favoriteModelList);
    }

    private void sortByCustomerReview(List<FavoriteModel> favoriteModels) {
        Comparator<FavoriteModel> lowtohigh = new Comparator<FavoriteModel>() {
            @Override
            public int compare(FavoriteModel o1, FavoriteModel o2) {
                return (int) ceil(o1.getRatingNumber() - o2.getRatingNumber());
            }
        };
        Collections.sort(favoriteModels, lowtohigh);
        setFavoritesListAdapter(favoriteModelList);
    }

    private void loadItems() {
        if (!filterList.isEmpty()) {
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
        favoritesFilterItemAdapter1 = new FavoritesFilterItemAdapter1(filterList, getActivity(), FavoritesFragment_1.this);
        filterItemList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        filterItemList.setAdapter(favoritesFilterItemAdapter1);
    }

    @Override
    public void onItemClick(int position) {
        if (favoriteModelList.get(position).isAvailable()) {
            Bundle bundle = new Bundle();
            bundle.putInt("product_id", favoriteModelList.get(position).getId());
            bundle.putString("productName", favoriteModelList.get(position).getProductName());
            bundle.putString("productBrand", favoriteModelList.get(position).getBrandname());
            bundle.putInt("productPrice", favoriteModelList.get(position).getPrice());
            bundle.putFloat("ratingNumber", favoriteModelList.get(position).getRatingNumber());
            bundle.putInt("imageId", favoriteModelList.get(position).getImage());
            bundle.putInt("productId", favoriteModelList.get(position).getId());
            bundle.putFloat("peroff", favoriteModelList.get(position).getOfferPer());
            AddToCartFragment addToCartFragment = new AddToCartFragment();
            addToCartFragment.setArguments(bundle);
            loadFragment(addToCartFragment);
        } else {
            //TODO item not available
        }

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(null)
                    .commit();
            return true;
        }
        if (fragment == null) {
            getActivity().onBackPressed();
        }
        return false;

    }

    @Override
    public void onbagClick(int position) {
        Toast.makeText(getActivity(), "" + favoriteModelList.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFilterColorItemClick(int postion, List<Integer> colorSelectedList) {
        // TODO Color Select from filter bottom list
    }
}