package com.example.departamentoeducacionfisicaesquel.ui.estiramientos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.util.Util;
import com.example.departamentoeducacionfisicaesquel.Model.Exercise;
import com.example.departamentoeducacionfisicaesquel.Utils.Utils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EstiramientosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Exercise>> exercisesLiveData;
    private FirebaseFirestore firestore;
    private ArrayList<Exercise> exercisesList;
    private Exercise exercise;

    public LiveData<ArrayList<Exercise>> getExercisesList() {
        if(exercisesLiveData == null){
            exercisesLiveData = new MutableLiveData<ArrayList<Exercise>>();

            loadExercises();
        }
        return exercisesLiveData;
    }

    private void loadExercises() {
        firestore = FirebaseFirestore.getInstance();

        firestore.collection(Utils.CLASSIFICATIONS.EXERCISES)
                .whereEqualTo("category", Utils.CLASSIFICATIONS.STRETCHING)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                         @Override
                         public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        exercisesList = new ArrayList<>();
                        for(int i=0; i< queryDocumentSnapshots.getDocuments().size(); i++){

                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);

                            exercise = new Exercise(
                                    documentSnapshot.get(Utils.ENTITY.NAME).toString(),
                                    documentSnapshot.get(Utils.ENTITY.SHORTDESCRIPTION).toString(),
                                    documentSnapshot.get(Utils.ENTITY.DESCRIPTION).toString(),
                                    documentSnapshot.get(Utils.ENTITY.MUSCLEGROUP).toString(),
                                    documentSnapshot.get(Utils.ENTITY.CATEGORY).toString(),
                                    documentSnapshot.get(Utils.ENTITY.IMAGEURL).toString()
                            );

                            exercisesList.add(exercise);

                            exercisesLiveData.setValue(exercisesList);
                }
            }
        });
    }
}
