package com.example.appdevweek1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.DataUser;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.MainViewholder> {

    private ArrayList<DataUser> listUser;
    private UserViewholderClick userViewholderClick;

    public MainRVAdapter(ArrayList<DataUser> listUser, UserViewholderClick userViewholderClick) {
        this.listUser = listUser;
        this.userViewholderClick = userViewholderClick;
    }

    @NonNull
    @Override
    public MainRVAdapter.MainViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_viewholder, parent, false);
        return new MainViewholder(view, userViewholderClick);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRVAdapter.MainViewholder holder, int position) {
        holder.txtNamaMainViewholder.setText(listUser.get(position).getNama());
        holder.txtUmurMainViewholder.setText(listUser.get(position).getUmur());
        holder.txtAlamatMainViewholder.setText(listUser.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class MainViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNamaMainViewholder, txtAlamatMainViewholder, txtUmurMainViewholder;
        private UserViewholderClick userViewholderClick;

        public MainViewholder(@NonNull View itemView, UserViewholderClick userViewholderClick) {
            super(itemView);

            txtNamaMainViewholder = itemView.findViewById(R.id.txtNamaMainViewholder);
            txtAlamatMainViewholder = itemView.findViewById(R.id.txtAlamatMainViewholder);
            txtUmurMainViewholder = itemView.findViewById(R.id.txtUmurMainViewholder);
            this.userViewholderClick = userViewholderClick;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            userViewholderClick.onClick(getAdapterPosition());
        }
    }

    public interface UserViewholderClick{
        void onClick(int position);
    }
}
