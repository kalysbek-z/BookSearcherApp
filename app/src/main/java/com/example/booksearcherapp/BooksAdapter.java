package com.example.booksearcherapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private Context mContext;
    private List<Items> mItems;
    private int ItemPosition;

    public BooksAdapter(Context context, List<Items> items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items book = mItems.get(position);
        ItemPosition = position;

        //main image w Glide
        Glide
                .with(mContext)
                .load(book.getVolumeInfo().getImageLinks().getSmallThumbnail().replace("http", "https"))
                .into(holder.bookImage);

        //set title
        holder.bookTitle.setText(book.getVolumeInfo().getTitle());

        //set author (if author is not specified view is invisible)
        if ((book.getVolumeInfo().getAuthors() != null)) {
            holder.bookAuthor.setText(book.getVolumeInfo().getAuthors().toString().replace("[", "").replace("]", ""));
        } else {
            holder.bookAuthor.setVisibility(View.INVISIBLE);
        }

        //set pub. date
        holder.bookPublishedDate.setText(book.getVolumeInfo().getPublishedDate());

        //set pages num
        if (book.getVolumeInfo().getPageCount() != null) {
            holder.bookPages.setText(book.getVolumeInfo().getPageCount().toString());
        } else {
            holder.bookPages.setVisibility(View.INVISIBLE);
        }
        //set rating
        holder.bookRating.setRating((float) book.getVolumeInfo().getAverageRating());

        holder.readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sourceIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getVolumeInfo().getInfoLink()));
                mContext.startActivity(sourceIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mItems != null) ? mItems.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookPublishedDate;
        TextView bookPages;
        RatingBar bookRating;
        Button readButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = (ImageView) itemView.findViewById(R.id.book_image);
            bookTitle = (TextView) itemView.findViewById(R.id.title);
            bookAuthor = (TextView) itemView.findViewById(R.id.author);
            bookPublishedDate = (TextView) itemView.findViewById(R.id.date);
            bookPages = (TextView) itemView.findViewById(R.id.number_of_pages);
            bookRating = (RatingBar) itemView.findViewById(R.id.rating);
            readButton = (Button) itemView.findViewById(R.id.read_button);
        }
    }
}

