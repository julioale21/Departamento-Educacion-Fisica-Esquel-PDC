package com.example.departamentoeducacionfisicaesquel.ui.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.departamentoeducacionfisicaesquel.Model.Category;
import com.example.departamentoeducacionfisicaesquel.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    private StorageReference mStorageRef;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context,ArrayList<Category> categories){
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dashboard, parent, false);
        CategoryViewHolder cvh = new CategoryViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getName());
        cargarFoto(categories.get(position).getImage(), holder);
    }

    private void cargarFoto(String image, CategoryViewHolder holder) {
        mStorageRef = FirebaseStorage.getInstance().getReference();

        mStorageRef.child("dashboard/" + image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context)
                        .load(uri)
                        .into(holder.categoryPhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView categoryName;
        TextView categoryDescription;
        ImageView categoryPhoto;

        CategoryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card1);
            categoryName = (TextView)itemView.findViewById(R.id.category_name);
            categoryPhoto = (ImageView)itemView.findViewById(R.id.image);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
