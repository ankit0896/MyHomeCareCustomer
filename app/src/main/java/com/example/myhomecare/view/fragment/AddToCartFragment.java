package com.example.myhomecare.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomecare.R;
import com.example.myhomecare.view.adapter.CartBrandAdpter;
import com.example.myhomecare.view.adapter.VendorListAdapter;
import com.example.myhomecare.database.CartSqliteHelper;
import com.example.myhomecare.model.CartModel;
import com.example.myhomecare.model.VendorModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddToCartFragment extends Fragment implements VendorListAdapter.OnVendorItemClick, CartBrandAdpter.OnBrandClickListner {

    View view;
    CircleImageView circleImageView;
    TextView price, offerPrice, addtocartbtn, vendorName, size, productName, brandname, startingfrom;
    RatingBar ratingBar;
    LinearLayout vendor, selectSize, selectBrand;
    RecyclerView listView;
    VendorListAdapter vendorListAdapter;
    ImageView back;
    CartBrandAdpter brandAdpter;
    List<String> brandList;
    BottomSheetDialog bottomSheetDialog;
    TextView selectBrandName, vendorNumber, seletedVendorName, selectProductSize, selectPrice, selectofferPrice;
    List<VendorModel> models;
    Float perOff, productPrice, productOfferPrice;
    String name, brandName, productSize="L", shopName;
    int id, image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_add_to_cart, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {


        circleImageView = view.findViewById(R.id.civ_addtocart);
        price = view.findViewById(R.id.tv_addtocart_price);
        offerPrice = view.findViewById(R.id.tv_addtocart_offerPrice);
        addtocartbtn = view.findViewById(R.id.tv_addtoCartButton);
        vendorName = view.findViewById(R.id.tv_addtocart_vendor_number);
        size = view.findViewById(R.id.tv_addtocart_productSize);
        productName = view.findViewById(R.id.tv_addtocart_productName);
        brandname = view.findViewById(R.id.tv_addtocart_brandName);
        ratingBar = view.findViewById(R.id.rb_addtocart_ratingBar);
        vendor = view.findViewById(R.id.ll_addtocart_selectVendor);
        selectSize = view.findViewById(R.id.ll_addtocart_selectSize);
        startingfrom = view.findViewById(R.id.tv_addtocart_startingfrom);
        back = view.findViewById(R.id.iv_addtocart_back);
        selectBrand = view.findViewById(R.id.ll_addtocart_selectbrand);

        selectBrandName = view.findViewById(R.id.tv_addtocart_vendor_brandName);
        vendorNumber = view.findViewById(R.id.tv_addtocart_vendor_number);
        seletedVendorName = view.findViewById(R.id.tv_addtoart_selectVendro_name);
        selectProductSize = view.findViewById(R.id.tv_addtocart_productSize);
        selectPrice = view.findViewById(R.id.tv_addtocart_price);
        selectofferPrice = view.findViewById(R.id.tv_addtocart_offerPrice);



        /*
        getdata and set data from item
         */
        Bundle bundle = getArguments();
        int price = bundle.getInt("productPrice");
        image = bundle.getInt("imageId");
        name = bundle.getString("productName");
        brandName = bundle.getString("productBrand");
        id = bundle.getInt("product_id");


        circleImageView.setImageResource(bundle.getInt("imageId"));
        startingfrom.setText("Starting from: " + price + " Rs/-");
        productName.setText(bundle.getString("productName"));
        brandname.setText(bundle.getString("productBrand"));
        ratingBar.setRating(bundle.getFloat("ratingNumber"));
        perOff = bundle.getFloat("peroff");
        float offerPrice = price - ((price * perOff) / 100);
        selectPrice.setText(price + " Rs/-");
        selectofferPrice.setText("" + offerPrice + " Rs/-");
        selectBrandName.setText(bundle.getString("productBrand"));


        clickEvents();
    }

    private void clickEvents() {
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVendorBottomSheetList();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        selectSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSelectSizeBottomSheet();
            }
        });
        selectBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetForBrandList();
            }
        });

        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shopName==null){
                    Toast.makeText(getActivity(), "Please Select Shop", Toast.LENGTH_SHORT).show();
                }else{
                    CartSqliteHelper cartSqliteHelper = new CartSqliteHelper(getActivity());
                    cartSqliteHelper.insertProduct(new CartModel(id, name, brandName, shopName, productSize, productPrice, perOff, productOfferPrice, 1,""+image ), getActivity());
                }
            }
        });
    }

    private void openBottomSheetForBrandList() {
        TextView name;
        bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_vendor_list, null, true);
        name = view.findViewById(R.id.tv_bottom_sheet_name);
        name.setText("Select Brand");
        listView = view.findViewById(R.id.lv_custom_vendor_list);
        brandList = new ArrayList<>();
        brandList.add("Mango1");
        brandList.add("Mango2");
        brandList.add("Mango3");
        brandList.add("Mango4");
        brandList.add("Mango5");

        brandAdpter = new CartBrandAdpter(brandList, getActivity(), AddToCartFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listView.setLayoutManager(linearLayoutManager);
        listView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(brandAdpter);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    private void openVendorBottomSheetList() {

        bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_vendor_list, null, true);
        listView = view.findViewById(R.id.lv_custom_vendor_list);
        models = new ArrayList<>();
        models.add(new VendorModel(1, 56, 40, "Palasia Store"));
        models.add(new VendorModel(2, 56, 40, "Malwa Mill Store"));
        models.add(new VendorModel(3, 56, 40, "Bhavar Kua Store"));
        models.add(new VendorModel(4, 56, 40, "Kothari Market Store"));
        models.add(new VendorModel(5, 56, 40, "Bangali Square Store"));
        vendorListAdapter = new VendorListAdapter(models, getActivity(), AddToCartFragment.this);
        listView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(vendorListAdapter);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        // loadData();
    }

    private void openSelectSizeBottomSheet() {
        TextView xs, m, s, l, xl;
        bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_select_size_addtocart, null, true);
        xs = view.findViewById(R.id.tv_ss_xs);
        m = view.findViewById(R.id.tv_ss_m);
        s = view.findViewById(R.id.tv_ss_s);
        l = view.findViewById(R.id.tv_ss_l);
        xl = view.findViewById(R.id.tv_ss_xl);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        xs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProductSize.setText("XS");
                productSize = "XS";
                bottomSheetDialog.dismiss();
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProductSize.setText("M");
                productSize = "M";
                bottomSheetDialog.dismiss();
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProductSize.setText("S");
                productSize = "S";
                bottomSheetDialog.dismiss();
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProductSize.setText("L");
                productSize = "L";
                bottomSheetDialog.dismiss();
            }
        });

        xl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProductSize.setText("XL");
                productSize = "XL";
                bottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public void onVendorClick(int position) {
        vendorNumber.setText(models.get(position).getName());
        selectPrice.setText(models.get(position).getPrice() + " Rs/-");
        selectofferPrice.setText(models.get(position).getOfferPrice() + " Rs/-");
        productPrice = Float.valueOf(models.get(position).getPrice());
        shopName = models.get(position).getName();
        productOfferPrice = Float.valueOf(models.get(position).getOfferPrice());
        bottomSheetDialog.dismiss();
    }

    @Override
    public void onViewVendorDetails(int position) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_vendor_details_layout, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle(models.get(position).getName())
                .setIcon(R.drawable.logo);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
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
    public void onCartBrandItemClick(int position) {
        selectBrandName.setText(brandList.get(position));
        brandName = brandList.get(position);
        bottomSheetDialog.dismiss();
    }
}