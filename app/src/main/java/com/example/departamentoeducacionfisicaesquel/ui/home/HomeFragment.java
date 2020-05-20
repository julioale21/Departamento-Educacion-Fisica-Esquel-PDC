package com.example.departamentoeducacionfisicaesquel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.departamentoeducacionfisicaesquel.Model.Category;
import com.example.departamentoeducacionfisicaesquel.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ArrayList<Category> misCategorias;
    private Category mCategoria;
    private RecyclerView recyclerViewCategorias;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = (ProgressBar) root.findViewById(R.id.progressBar1);

        recyclerViewCategorias = root.findViewById(R.id.rv);
        recyclerViewCategorias.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerViewCategorias.setLayoutManager(llm);

        progressBar.setVisibility(View.VISIBLE);

        homeViewModel.getCategoryList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                cargarVista(categories);
            }
        });

        return root;
    }

    private void cargarVista(ArrayList<Category> categories) {
        CategoryAdapter adapter = new CategoryAdapter(getContext(), categories);
        recyclerViewCategorias.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }
}
