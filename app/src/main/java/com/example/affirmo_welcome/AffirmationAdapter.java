package com.example.affirmo_welcome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AffirmationAdapter extends RecyclerView.Adapter<AffirmationAdapter.AffirmationViewHolder> {

    private List<String> affirmations;
    private Context context;

    public AffirmationAdapter(Context context, List<String> affirmations) {
        this.context = context;
        this.affirmations = affirmations;
    }

    @NonNull
    @Override
    public AffirmationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_affirmation, parent, false);
        return new AffirmationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AffirmationViewHolder holder, int position) {
        holder.textViewAffirmation.setText(affirmations.get(position));
    }

    @Override
    public int getItemCount() {
        return affirmations.size();
    }

    public static class AffirmationViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAffirmation;

        public AffirmationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAffirmation = itemView.findViewById(R.id.textViewAffirmation);
        }
    }
}
