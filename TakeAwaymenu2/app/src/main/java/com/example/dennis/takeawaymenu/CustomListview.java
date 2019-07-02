package com.example.dennis.takeawaymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dennis.takeawaymenu.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomListview extends BaseAdapter{
    Context context;
    List<Food> foodList;
    ArrayList<Food> arrayList;
    LayoutInflater layoutInflater;


    public CustomListview(Context context, List<Food> foodList){
        this.context = context;
        this.foodList = foodList;
        layoutInflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Food>();
        this.arrayList.addAll(foodList);
    }

    public class ViewHolder{
        TextView txtFoodName, txtFoodPrice;
        ImageView foodImage;
    }
    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.menu_list_row, null);

            //here the  convertView in the menu list row
            holder.txtFoodName = convertView.findViewById(R.id.foodname);
            holder.txtFoodPrice = convertView.findViewById(R.id.price);
            holder.foodImage = convertView.findViewById(R.id.foodImage);

            convertView.setTag(holder); //loading Ui layout
        }
        else {
            holder =(ViewHolder) convertView.getTag();
        }

        //set the result toto the textviews
        holder.txtFoodName.setText(foodList.get(position).getName());
        holder.txtFoodPrice.setText(foodList.get(position).getPrice());
        //set the result toto the imageview
        holder.foodImage.setImageResource(foodList.get(position).getImageId());

        //listview food item to set clicks listener
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    //search to using filter
    public void Search(String charWord){
        charWord = charWord.toLowerCase(Locale.getDefault());
        foodList.clear();;
        if(charWord.length()==0){
            foodList.addAll(arrayList);
        }else {
            for(Food foods:arrayList){
                if ( foods.getName().toLowerCase(Locale.getDefault()).contains(charWord)) {
                    foodList.add(foods);
                }
            }
        }
        notifyDataSetChanged();  //listview Data to update
    }
}
