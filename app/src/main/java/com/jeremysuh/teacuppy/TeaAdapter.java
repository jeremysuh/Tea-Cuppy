package com.jeremysuh.teacuppy;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class TeaAdapter extends RecyclerView.Adapter<TeaAdapter.TeaViewHolder>{

    private List<Tea> teaList;

    public TeaAdapter(List<Tea> tealist){

        this.teaList = tealist;
    }
    @NonNull
    @Override
    public TeaAdapter.TeaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_list_row, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        return new TeaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeaAdapter.TeaViewHolder holder, int position) {
        holder.tea_name.setText(teaList.get(position).get_name());
     //   holder.tea_temperature.setText(teaList.get(position).get_temperature());

    }

    @Override
    public int getItemCount() {
        return teaList.size();
    }

    public class TeaViewHolder extends RecyclerView.ViewHolder {
        public TextView tea_name;
      //  public TextView tea_temperature;

        public TeaViewHolder(View view) {
            super(view);
            tea_name = (TextView) view.findViewById(R.id.tea_name);
        }
    }
}