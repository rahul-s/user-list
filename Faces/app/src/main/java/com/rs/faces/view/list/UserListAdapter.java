package com.rs.faces.view.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rs.faces.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter {

    private UsersListListener listener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UserViewHolder) holder).bind();
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setListener(UsersListListener listener) {
        this.listener = listener;
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

        void bind() {
            // User image
            Glide.with(userAvatar.getContext())
                    .load("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")
                    .error(R.drawable.ic_user_icon)
                    .placeholder(R.drawable.ic_user_icon)
                    .into(userAvatar);

            // User name
            userName.setText("Rahul Shettigar");

            userRow.setOnClickListener((v) -> {
                if (listener != null) listener.userClicked();
            });
        }
    }

    public interface UsersListListener {

        void fetchNextPage(int page, int pageSize);
        void userClicked();
    }
}
