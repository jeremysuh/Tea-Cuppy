package com.jeremysuh.teacuppy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class TeaAdapter extends RecyclerView.Adapter<TeaAdapter.TeaViewHolder>{

    private List<Tea> teaList;
    private Context context;
    public int idCount = 0;



    public TeaAdapter(List<Tea> tealist, Context context){

        this.context = context;
        this.teaList = tealist;
        idCount = 0;
        Log.d("fire", "cell constructo begin begin");

    }
    @NonNull
    @Override
    public TeaAdapter.TeaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_list_row, parent, false);
        //  itemView.setId(idCount);

      //  Log.d("fire", ""+idCount);

       /// idCount++;


       // itemView.setOnClickListener(new View.OnClickListener() {
    //      @Override
       //     public void onClick(View view) {
            ///    Log.d("fire", ""+itemView.getId());
           //     Intent intent = new Intent(context, TeaDetail.class);
          //      intent.putExtra("tea_name", teaList.get(itemView.getId()).get_name());
         //       intent.putExtra("tea_temperature",
          //              "Temp: "+teaList.get(itemView.getId()).get_temperature()+" F");
        //        intent.putExtra("tea_time",
         //               "Time: "+teaList.get(itemView.getId()).get_brew_time() + " s");
          //      intent.putExtra("tea_caffeine",
             //           "Caffeine: "+teaList.get(itemView.getId()).get_caffeine() + " mg");
            //    intent.putExtra("tea_description", teaList.get(itemView.getId()).get_description());
          //      ((Activity) context).startActivityForResult(intent, 2);
       //     }
     //   });
        return new TeaViewHolder(itemView);
    }

    public void clear_tea(){
        teaList.clear();
    }


    @Override
    public void onBindViewHolder(@NonNull TeaAdapter.TeaViewHolder holder, final int position) {
        holder.tea_name.setText(teaList.get(position).get_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.d("fire", ""+position);
                     Intent intent = new Intent(context, TeaDetail.class);
                      intent.putExtra("tea_name", teaList.get(position).get_name());
                       intent.putExtra("tea_temperature",
                              ""+teaList.get(position).get_temperature());
                        intent.putExtra("tea_time",
                               ""+teaList.get(position).get_brew_time());
                      intent.putExtra("tea_caffeine",
                           ""+teaList.get(position).get_caffeine());
                    intent.putExtra("tea_description", teaList.get(position).get_description());
                      ((Activity) context).startActivityForResult(intent, 2);
            }
        });

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
            tea_name = (TextView) view.findViewById(R.id.ff);
        }
    }
}