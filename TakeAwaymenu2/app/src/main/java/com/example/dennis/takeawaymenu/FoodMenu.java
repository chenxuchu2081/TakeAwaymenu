package com.example.dennis.takeawaymenu;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.dennis.takeawaymenu.model.Food;
import com.example.dennis.takeawaymenu.myHelper.MyDbAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class FoodMenu extends AppCompatActivity {

    ListView lst;
    CustomListview adapter;
    String[] name;
    String[] price;
    int[] image;
    ArrayList<Food> arrayList = new ArrayList<Food>();
    private MyDbAdapter myDbAdapter;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        mySwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh) ;

        //this.myDbAdapter = new MyDbAdapter(this);
        image = new int[]{
                R.drawable.creamsoup,
                R.drawable.salads,
                R.drawable.pepperonipizza,
                R.drawable.carbonara,
                R.drawable.angusburdock,
                R.drawable.tunafishsandwiches,
                R.drawable.seafoodpaella,
                R.drawable.hainanchickenrice,
                R.drawable.roastedchicken};

        String jsonStr = readAssetsFile("foodmenu.json");
        try {
            JSONObject root = new JSONObject(jsonStr);
            JSONArray jsonArray = root.getJSONArray("foodmenu");
            for(int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String foodname = jsonObject.getString("foodname");
                String foodprice = jsonObject.getString("foodprice");
                Food foods = new Food(foodname,foodprice, image[i]);
                arrayList.add(foods);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Listview food Data
//        name = new String[]{"Cream Soup", "Salads", "PepperOni pizza",
//                "Carbonara", "Angus Burdock","Tuna Fish Sandwiches",
//                "Seafood Paella","hainan Chicken Rice","Roasted Chicken"};
//        price = new String[]{"$102", "$120","$110",
//                "$130","$110","$120",
//                "$110","$110","$100","$100"};



//        for (int i = 0; i < name.length; i++) {
//            Food foods = new Food(name[i], price[i], image[i]);
//            //myDbAdapter.addFood(foods);
//            arrayList.add(foods);
//        }


        //by calling result to customListview class
        lst = (ListView) findViewById(R.id.listview);
        adapter = new CustomListview(this, arrayList);
        lst.setAdapter(adapter);

        //refresh foodmenu listview layout
        RefreshFoodMenu();



    }

    private String readAssetsFile(String fileName){
        try{
            InputStream inputStream = getAssets().open(fileName);
            int fileSize = inputStream.available();
            byte[] bytesBuffer = new byte[fileSize];

            inputStream.read(bytesBuffer);
            inputStream.close();

            String text = new String(bytesBuffer);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return " ";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.food_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView)menuItem.getActionView();
        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String Text) {
                if(TextUtils.isEmpty(Text)){
                    adapter.Search("");
                    lst.clearTextFilter();
                }else{
                    adapter.Search(Text);
                }
                return true;
            }
        });
        return true;
    }

    private void RefreshFoodMenu(){
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
         @Override
         public void onRefresh() {
           // This method performs the actual data-refresh operation.
           // The method calls setRefreshing(false) when it's finished.
            mySwipeRefreshLayout.setRefreshing(false);
              lst.setAdapter(adapter);
              }
           }
        );
    }

}
