package com.rs.faces.view.list;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rs.faces.R;
import com.rs.faces.models.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter {

    private UsersListListener listener;

    private List<User> users = new ArrayList<>();
    private int loadedPages = 0;
    private boolean isLoading = false;

    private static final int LIST_ITEM_TYPE_USER = 1;
    private static final int LIST_ITEM_TYPE_LOADER = 2;

    private static final int PAGE_SIZE = 3;     // Change this as needed to change the number of users to be fetched on each load

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LIST_ITEM_TYPE_LOADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_loader, parent, false);
            return new LoaderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
            return new UserViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == LIST_ITEM_TYPE_LOADER) {
            ((LoaderViewHolder) holder).bind();
        }
        else {
            ((UserViewHolder) holder).bind(users.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return users.size() + 1;    // One extra row for loading indicator
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return LIST_ITEM_TYPE_LOADER;
        }
        else {
            return LIST_ITEM_TYPE_USER;
        }
    }

    public void setListener(UsersListListener listener) {
        this.listener = listener;
    }

    public void addUsers(int page, int pageSize, List<User> users) {
        loadedPages = page;
        isLoading = false;

        if (users != null && users.size() > 0) {

            // Insert the new users
            int currentlastPosition = this.users.size();
            this.users.addAll(users);
            notifyItemRangeInserted(currentlastPosition+1, users.size());
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user_row)
        View userRow;
        @Bind(R.id.user_avatar)
        ImageView userAvatar;
        @Bind(R.id.user_name)
        TextView userName;

        UserViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void bind(User user) {
            // User image
            Glide.with(userAvatar.getContext())
                    .load(user.getAvatarUrl())
                    .error(R.drawable.ic_user_icon)
                    .placeholder(R.drawable.ic_user_icon)
                    .into(userAvatar);

            // User name
            userName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));

            if (getAdapterPosition()%2 == 0) {
                userRow.setBackgroundColor(ContextCompat.getColor(userRow.getContext(), R.color.backgroundRow));
            }
            else {
                userRow.setBackgroundColor(ContextCompat.getColor(userRow.getContext(), R.color.backgroundRowAlternate));
            }

            userRow.setOnClickListener((v) -> {
                if (listener != null) listener.userClicked(user, userAvatar, userName);
            });
        }
    }

    public class LoaderViewHolder extends RecyclerView.ViewHolder {

        LoaderViewHolder(View itemView) {
            super(itemView);
        }

        void bind() {
            if (!isLoading) {               // To avoid making multiple request
                if (listener != null) {
                    listener.fetchNextPage(loadedPages+1, PAGE_SIZE);
                    isLoading = true;
                }
            }
        }
    }

    public interface UsersListListener {

        void fetchNextPage(int page, int pageSize);
        void userClicked(User user, View userAvatar, View userName);
    }
}
