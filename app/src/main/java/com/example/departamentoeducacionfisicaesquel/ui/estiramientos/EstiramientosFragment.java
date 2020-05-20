package com.example.departamentoeducacionfisicaesquel.ui.estiramientos;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.departamentoeducacionfisicaesquel.Model.Excercise;
import com.example.departamentoeducacionfisicaesquel.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EstiramientosFragment extends Fragment {

    private EstiramientosViewModel mViewModel;
    private FirebaseFirestore mFirestone;
    private ArrayList<Excercise> misEjercicios;
    private Excercise mEjercicio;
    private RecyclerView recyclerViewExcercises;

    public static EstiramientosFragment newInstance() {
        return new EstiramientosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_excercises, container, false);


        recyclerViewExcercises = root.findViewById(R.id.rv_excercises);
        recyclerViewExcercises.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerViewExcercises.setLayoutManager(llm);

        mFirestone = FirebaseFirestore.getInstance();

        mFirestone.collection("estiramientos").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                misEjercicios = new ArrayList<>();
                for(int i=0; i< queryDocumentSnapshots.getDocuments().size(); i++){

                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);

                    mEjercicio = new Excercise(
                            documentSnapshot.get("name").toString(),
                            documentSnapshot.get("shortDescription").toString(),
                            documentSnapshot.get("description").toString(),
                            documentSnapshot.get("imageName").toString(),
                            "estiramientos"
                    );

                    misEjercicios.add(mEjercicio);
                }

                cargarVista();
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EstiramientosViewModel.class);
        // TODO: Use the ViewModel
    }

    private void cargarVista() {
        ListExcerciseAdapter adapter = new ListExcerciseAdapter(getContext(), misEjercicios);
        recyclerViewExcercises.setAdapter(adapter);
    }

}
