package com.example.jobseeker.app.homePage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.jobseeker.R;
import com.example.jobseeker.databinding.ItemInboxBinding;
import com.example.jobseeker.databinding.ItemJobBoardBinding;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ProgressCallback;

import java.util.ArrayList;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private ArrayList<ParseObject> parseObjects;
    private OnInboxListener mOnInboxListener;

    public InboxAdapter(ArrayList<ParseObject> object, OnInboxListener onInboxListener) {
        parseObjects = object;
        mOnInboxListener = onInboxListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new InboxAdapter.ViewHolder(ItemInboxBinding.inflate((inflater), parent, false), parent.getContext(), mOnInboxListener);
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        Context context;
        ItemInboxBinding binding;
        OnInboxListener onInboxListener;

        public ViewHolder(ItemInboxBinding b, Context context, OnInboxListener onInboxListener) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.

            super(b.getRoot());
            binding = b;
            this.context = context;
            this.onInboxListener = onInboxListener;
            b.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onInboxListener.onInboxClick(getAdapterPosition(), parseObjects);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int pos) {
        holder.binding.getRoot().setAnimation(AnimationUtils.loadAnimation(holder.context, R.anim.fade_scale_in));

        holder.binding.firstName.setText(parseObjects.get(pos).getString("firstName"));

        parseObjects.get(pos).getParseFile("proPic").getDataInBackground(new ProgressCallback() {
            @Override
            public void done(Integer percentDone) {
                if (percentDone == 100){
                    try {
                        byte[] data = parseObjects.get(pos).getParseFile("proPic").getData();
                        Glide.with(holder.context)
                                .asBitmap()
                                .load(data)
                                .override(500, 500)
                                .transform(new CircleCrop())
                                .into(holder.binding.proPic);

                    } catch (ParseException e) {
                        Toast.makeText(holder.context, "error! "  + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else{

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return parseObjects.size();
    }

    public interface OnInboxListener {
        void onInboxClick(int position, List<ParseObject> parseObjects);
    }

    public void filter(ArrayList<ParseObject> filteredList){
        if (!parseObjects.equals(filteredList)){
            parseObjects = filteredList;
            notifyDataSetChanged();
        }
    }
}