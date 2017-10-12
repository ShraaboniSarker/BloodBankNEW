package com.example.shraaboni.bloodbanknew.Adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shraaboni.bloodbanknew.Model.Request;
import com.example.shraaboni.bloodbanknew.R;


import java.util.List;

/**
 * Created by shraaboni on 9/4/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Request> mdata;
    private LayoutInflater miflater;

    public RecyclerAdapter(Context context, List<Request> data) {
        this.mdata=data;
        this.miflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = miflater.inflate(R.layout.row_layout,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Request currentobj = mdata.get(position);
        holder.setData(currentobj , position);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bloodgrpTV , noofbagsTV,areaTV;

        int position;
        Request current;
       public MyViewHolder(View itemView) {
           super(itemView);
           bloodgrpTV = (TextView) itemView.findViewById(R.id.bloodgroup);
           noofbagsTV = (TextView) itemView.findViewById(R.id.noofbags);
           areaTV = (TextView) itemView.findViewById(R.id.area);
       }

        public void setData(Request currentobj, int position) {
            this.bloodgrpTV.setText(currentobj.getRequest());
            this.noofbagsTV.setText(currentobj.getBags());
            this.areaTV.setText(currentobj.getArea());
            this.position=position;
            this.current=currentobj;
        }
    }
}
