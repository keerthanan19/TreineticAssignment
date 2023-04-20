package com.assignment.treineticassignment.ui.home;

import static com.assignment.treineticassignment.Utils.Controller.getFeaturedProducts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.treineticassignment.Adapter.CategoryListRecycleViewAdapter;
import com.assignment.treineticassignment.Adapter.ProductListRecycleViewAdapter;
import com.assignment.treineticassignment.CallBack.OnClick;
import com.assignment.treineticassignment.Database.DBUtils;
import com.assignment.treineticassignment.Object.Data;
import com.assignment.treineticassignment.ProductDetails;
import com.assignment.treineticassignment.R;
import com.assignment.treineticassignment.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment implements OnClick{

    private FragmentHomeBinding binding;
    CategoryListRecycleViewAdapter categoryListRecycleViewAdapter;
    RecyclerView category_list_view ;

    OnClick onClick;

    ProductListRecycleViewAdapter productListRecycleViewAdapter;
    RecyclerView product_list_view ;

    ArrayList<Data> dataArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        category_list_view = root.findViewById(R.id.category_list_view);

        String[] category_array = getResources().getStringArray(R.array.category_list);

        ArrayList<String> categoryArrayList = new ArrayList<>(Arrays.asList(category_array));

        LinearLayoutManager ListRecyclerView = new LinearLayoutManager(getActivity()); // (Context context)
        categoryListRecycleViewAdapter = new CategoryListRecycleViewAdapter(getActivity(),categoryArrayList);
        category_list_view.setAdapter(categoryListRecycleViewAdapter);
        category_list_view.setLayoutManager(ListRecyclerView);
        ListRecyclerView.setOrientation(LinearLayoutManager.HORIZONTAL);

        onClick = this;
        product_list_view = root.findViewById(R.id.product_list_view);

        dataArrayList = DBUtils.getAllData(getActivity());

        LinearLayoutManager ProductListRecyclerView = new LinearLayoutManager(getActivity()); // (Context context)
        productListRecycleViewAdapter = new ProductListRecycleViewAdapter(getActivity(), dataArrayList ,onClick);
        product_list_view.setAdapter(productListRecycleViewAdapter);
        product_list_view.setLayoutManager(ProductListRecyclerView);
        ProductListRecyclerView.setOrientation(LinearLayoutManager.HORIZONTAL);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(String ID) {
        getFeaturedProducts(getActivity());

    }
}