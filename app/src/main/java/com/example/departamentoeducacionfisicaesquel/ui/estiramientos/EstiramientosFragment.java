package com.example.departamentoeducacionfisicaesquel.ui.estiramientos;

import androidx.lifecycle.Observer;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.departamentoeducacionfisicaesquel.Model.Exercise;
import com.example.departamentoeducacionfisicaesquel.R;
import java.util.ArrayList;

public class EstiramientosFragment extends Fragment {

    private EstiramientosViewModel mViewModel;
    private RecyclerView recyclerViewExcercises;

    public static EstiramientosFragment newInstance() {
        return new EstiramientosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(EstiramientosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_excercises, container, false);


        recyclerViewExcercises = root.findViewById(R.id.rv_excercises);
        recyclerViewExcercises.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerViewExcercises.setLayoutManager(llm);

        mViewModel.getExercisesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Exercise>>() {
            @Override
            public void onChanged(ArrayList<Exercise> exercises) {
                cargarVista(exercises);
            }
        });

        return root;
    }

    private void cargarVista(ArrayList<Exercise> exercises) {
        ListExcerciseAdapter adapter = new ListExcerciseAdapter(getContext(), exercises);
        recyclerViewExcercises.setAdapter(adapter);
    }

}
