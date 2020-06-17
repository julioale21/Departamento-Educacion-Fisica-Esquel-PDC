package com.example.departamentoeducacionfisicaesquel.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.departamentoeducacionfisicaesquel.Model.Category;
import com.example.departamentoeducacionfisicaesquel.Utils.Utils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Category>> categoryListLiveData;
    private FirebaseFirestore mFirestone;
    private ArrayList<Category> misCategorias;
    private Category mCategoria;

    public LiveData<ArrayList<Category>> getCategoryList() {
        if (categoryListLiveData == null){
            categoryListLiveData = new MutableLiveData<ArrayList<Category>>();

            loadCategories();
        }
        return categoryListLiveData;
    }

    private void loadCategories() {
        mFirestone = FirebaseFirestore.getInstance();

        mFirestone.collection(Utils.CLASSIFICATIONS.PRINCIPAL).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                misCategorias = new ArrayList<>();
                for(int i=0; i< queryDocumentSnapshots.getDocuments().size(); i++){

                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);

                    mCategoria = new Category(
                            documentSnapshot.get(Utils.ENTITY.NAME).toString(),
                            documentSnapshot.get(Utils.ENTITY.DESCRIPTION).toString(),
                            documentSnapshot.get(Utils.ENTITY.IMAGEURL).toString()
                    );

                    misCategorias.add(mCategoria);

                    categoryListLiveData.setValue(misCategorias);
                }
            }
        });
    }
}