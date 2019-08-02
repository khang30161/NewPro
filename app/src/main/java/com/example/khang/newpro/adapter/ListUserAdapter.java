package com.example.khang.newpro.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khang.newpro.R;
import com.example.khang.newpro.adapter.viewholder.BindingViewHolder;
import com.example.khang.newpro.adapter.viewholder.EmptyViewHolder;
import com.example.khang.newpro.base.BaseAdapter;
import com.example.khang.newpro.databinding.RowListUserBinding;
import com.example.khang.newpro.listener.RowListUserListener;
import com.example.khang.newpro.model.User;
import com.example.khang.newpro.viewmodel.ListUserItemViewModel;

public class ListUserAdapter extends BaseAdapter {

    private static final String TAG = ListUserAdapter.class.getSimpleName();

    private static final int VIEW_TYPE_LOAD_MORE = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public ListUserAdapter(Context context, OnItemClickListener onItemClickListener) {
        super(context, onItemClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_LOAD_MORE: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loadmore_layout, parent, false);
                return new EmptyViewHolder(v);
            }
            case VIEW_TYPE_ITEM: {
                RowListUserBinding rowOfflineMapAreaBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_list_user, parent, false);
                return new BindingViewHolder(rowOfflineMapAreaBinding);
            }
            default: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_empty, parent, false);
                return new EmptyViewHolder(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mHolder, int position) {
        if (mHolder instanceof EmptyViewHolder || list.get(position) == null) {
            return;
        }

        BindingViewHolder holder = (BindingViewHolder) mHolder;
        RowListUserBinding binding = (RowListUserBinding) holder.getBinding();
        User user = (User) list.get(position);
        if (user == null) {
            return;
        }
        ListUserItemViewModel itemViewModel = new ListUserItemViewModel(user);
        RowListUserListener listener = new RowListUserListener() {
            @Override
            public void onDeleteClick(User user) {
                Toast.makeText(context, "Delete User: " + user.getUserId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onShowAllClick(User user) {
                Toast.makeText(context, "Show All User: " + user.getUserId(), Toast.LENGTH_LONG).show();
            }
        };

        binding.setListUserViewModel(itemViewModel);
        binding.setListUserListener(listener);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) != null ? VIEW_TYPE_ITEM : VIEW_TYPE_LOAD_MORE;
    }

}
