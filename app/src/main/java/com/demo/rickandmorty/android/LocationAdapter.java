package com.demo.rickandmorty.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.rickandmorty.R;
import com.demo.rickandmorty.android.LocationFragment.OnListFragmentInteractionListener;
import com.demo.rickandmorty.android.dummy.DummyContent.DummyItem;
import com.demo.rickandmorty.entity.location.Location;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Location> mValues;
    private final OnListFragmentInteractionListener mListener;

    public LocationAdapter(List<Location> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void updateDate(List<Location> values) {
        mValues = values;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTextView1.setText(mValues.get(position).getName());
        holder.mTextView2.setText(mValues.get(position).getDimension());
        holder.mTextView3.setText(mValues.get(position).getType());

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
        public Location mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.imageLocation);
            mTextView1 = (TextView) view.findViewById(R.id.litem1);
            mTextView2 = (TextView) view.findViewById(R.id.litem2);
            mTextView3 = (TextView) view.findViewById(R.id.litem3);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView1.getText() + "'";
        }
    }
}
