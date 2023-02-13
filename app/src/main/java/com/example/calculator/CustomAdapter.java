package com.example.calculator;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] buttons;
    private String[] toBeOrange= {"C", "<-", "%", "/", "*", "-", "+", "="};
    private List<String> arrayList = Arrays.asList(toBeOrange);

    public CustomAdapter(Context context, String buttons[]){
        this.context = context;
        this.buttons = buttons;
    }

    @Override
    public int getCount() {
        return buttons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = layoutInflater.inflate(R.layout.grid_item, null);
        }
        TextView textView  = view.findViewById(R.id.text_button);

        if(arrayList.contains(buttons[i])){
            textView.setTextColor(Color.parseColor("#ff8000"));
        }


        textView.setText(buttons[i]);
        return view;
    }
}
