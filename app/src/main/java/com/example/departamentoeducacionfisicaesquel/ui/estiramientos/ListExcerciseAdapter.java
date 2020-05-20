package com.example.departamentoeducacionfisicaesquel.ui.estiramientos;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;
import com.example.departamentoeducacionfisicaesquel.Model.Excercise;
import com.example.departamentoeducacionfisicaesquel.R;
import java.util.ArrayList;


public class ListExcerciseAdapter extends RecyclerView.Adapter<ListExcerciseAdapter.ListExcerciseViewHolder> {

    private Context context;
    private ArrayList<Excercise> excercises;

    public ListExcerciseAdapter(Context context, ArrayList<Excercise> excercises){
        this.context = context;
        this.excercises = excercises;
    }

    @NonNull
    @Override
    public ListExcerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_excercises, parent, false);
        ListExcerciseViewHolder vh = new ListExcerciseViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListExcerciseViewHolder holder, int position) {
        holder.title.setText(excercises.get(position).getName());
        holder.shortDescription.setText(excercises.get(position).getShortDescription());
        holder.description.setText(Html.fromHtml(excercises.get(position).getDescription().replace(".", ".\n")));
        Glide.with(context)
                .load(excercises.get(position).getImageURL())
                .into(holder.photo);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.linearLayout.getVisibility() == View.GONE) {
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    holder.shortDescription.setVisibility(View.GONE);
                }else{
                    holder.linearLayout.setVisibility(View.GONE);
                    holder.shortDescription.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return excercises.size();
    }

    public static class ListExcerciseViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView shortDescription;
        JustifiedTextView description;
        ImageView photo;
        Button button;
        LinearLayout linearLayout;

        ListExcerciseViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.rv_excercises);
            title = (TextView)itemView.findViewById(R.id.title_list_excercises);
            shortDescription = (TextView)itemView.findViewById(R.id.short_description_list_excercises);
            photo = (ImageView)itemView.findViewById(R.id.image_list_excercises);
            description = (JustifiedTextView) itemView.findViewById(R.id.textViewInfo);
            button = (Button)itemView.findViewById(R.id.buttonMore);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayoutDetails);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
