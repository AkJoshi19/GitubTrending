package com.gitTrending.main.adapter;

import android.support.v7.widget.RecyclerView;

import com.gitTrending.main.modal.GitInfoModal;


/**
 * RecyclerView adapter extended with project-specific required methods.
 */
public abstract class ComicsBaseRecyclerAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    /**
     * Return the {@link GitInfoModal.Item} represented by this item in the adapter.
     *
     * @param position Adapter item position.
     *
     * @return A new {@link GitInfoModal.Item} filled with this position's attributes
     *
     * @throws IllegalArgumentException if position is out of the adapter's bounds.
     */
    public abstract GitInfoModal.Item getItem(int position);


}
