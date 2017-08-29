package com.example.shraaboni.bloodbanknew;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shraaboni on 8/7/2017.
 */

public class RequestAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Request> requests;
    public RequestAdapter(@NonNull Context context, ArrayList<Request> requests) {
        super(context, R.layout.row_layout,requests);
        this.context=context;
        this.requests= requests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_layout, parent, false);


        TextView bloodgrpTV =(TextView) convertView.findViewById(R.id.bloodgroup);
        TextView noofbagsTV =(TextView) convertView.findViewById(R.id.noofbags);
        TextView areaTV =(TextView) convertView.findViewById(R.id.area);

        String txt1= requests.get(position).getRequest();
        bloodgrpTV.setText(requests.get(position).getRequest());
        noofbagsTV.setText(requests.get(position).getBags()+" bags");
        areaTV.setText(requests.get(position).getArea());
        return  convertView;
    }
}
