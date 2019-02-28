package com.demo.rickandmorty.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.rickandmorty.R;
import com.demo.rickandmorty.android.CharacterFragment.OnListFragmentInteractionListener;
import com.demo.rickandmorty.android.dummy.DummyContent.DummyItem;
import com.demo.rickandmorty.entity.character.Character;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<Character> mValues;
    private final OnListFragmentInteractionListener mListener;

    public CharacterAdapter(List<Character> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void updateDate(List<Character> values) {
        mValues = values;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTextView1.setText(mValues.get(position).getName());
        holder.mTextView2.setText(mValues.get(position).getSpecies());
        holder.mTextView3.setText(mValues.get(position).getGender());
        holder.mTextView4.setText(mValues.get(position).getStatus());
        Picasso.get().load(mValues.get(position).getImage())
                .placeholder(R.drawable.unknow_image)
                .into(holder.mImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues == null)
            return 0;
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView1;
        public final TextView mTextView2;
        public final TextView mTextView3;
        public final TextView mTextView4;
        public Character mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.cimage);
            mTextView1 = (TextView) view.findViewById(R.id.citem1);
            mTextView2 = (TextView) view.findViewById(R.id.citem2);
            mTextView3 = (TextView) view.findViewById(R.id.citem3);
            mTextView4 = (TextView) view.findViewById(R.id.citem4);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView1.getText() + "'";
        }
    }
}
