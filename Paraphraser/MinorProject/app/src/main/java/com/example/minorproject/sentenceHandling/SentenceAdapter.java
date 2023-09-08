package com.example.minorproject.sentenceHandling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.SentenceViewHolder> {
    private onSentenceClickListener mlistener;
    private List<Sentence> mSentences;
    private Context mContext;

    public SentenceAdapter(Context context,onSentenceClickListener listener){
        mContext = context;
        mlistener = listener;
    }

    public List<Sentence> getDataset(){
        return mSentences;
    }

    @Override
    public int getItemCount() {
        if(mSentences == null){
            return 0;
        }
        else{
            return mSentences.size();
        }
    }

    @NonNull
    @NotNull
    @Override
    public SentenceViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.edit_sentence_list_item,parent,false);
        return new SentenceViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SentenceViewHolder holder, int position) {
        holder.getTextView().setText(mSentences.get(position).getRephrased());
    }

    void changeDataSet(List<Sentence> sentences){
        mSentences = sentences;
        notifyDataSetChanged();
    }


    public class SentenceViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;
        private onSentenceClickListener listener;

        public SentenceViewHolder(View view,onSentenceClickListener listener){
            super(view);
            textView = view.findViewById(R.id.sentenceTextView);
            imageView = view.findViewById(R.id.editButton);
            this.listener = listener;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSentenceClick(getAdapterPosition());
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public interface onSentenceClickListener{
        public void onSentenceClick(int position);
    }
}
