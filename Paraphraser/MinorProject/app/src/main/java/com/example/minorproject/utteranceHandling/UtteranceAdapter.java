package com.example.minorproject.utteranceHandling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UtteranceAdapter extends RecyclerView.Adapter<UtteranceAdapter.UtteranceViewHolder>{

    private List<String> utteranceEntries;
    private Context mContext;
    private onUtteranceClickListener listener;

    public UtteranceAdapter(Context context,onUtteranceClickListener listener){
        mContext = context;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public UtteranceViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new UtteranceViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UtteranceViewHolder holder, int position) {
        TextView textView = holder.getTextView();
        textView.setText(utteranceEntries.get(position));
    }

    @Override
    public int getItemCount() {
        if(utteranceEntries == null){
            return 0;
        }
        else
            return utteranceEntries.size();
    }

    public void setDataset(List<String> utteranceEntries){
        this.utteranceEntries = utteranceEntries;
        notifyDataSetChanged();
    }

    public static class UtteranceViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private onUtteranceClickListener listener;

        public UtteranceViewHolder(View view,onUtteranceClickListener listener){
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            this.listener = listener;

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUtteranceClick(getAdapterPosition());
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public interface onUtteranceClickListener{
        public void onUtteranceClick(int position);
    }
}
