package com.example.dennis.takeawaymenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.btmenu:
                intent = new Intent(MainActivity.this,FoodMenu.class);
                startActivity( intent );
                break;
            case R.id.btcomment:
                intent = new Intent(MainActivity.this,Comments.class);
                startActivity( intent );
                break;
            case R.id.btgps:
                intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity( intent );
                break;
            case R.id.btfavorite:
                intent = new Intent(MainActivity.this,AboutUs.class);
                startActivity( intent );
                break;

            default:
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu:
                Intent intent = new Intent(MainActivity.this, FoodMenu.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Menu is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.comment:
                intent = new Intent(MainActivity.this, Comments.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Comment is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.gps:
                intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Search is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.aboutus:
                intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "About Us is Selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //cardview menu botton
//    private void setSingleEvent(GridLayout mainGrid) {
//
//        for(int i =0; i<mainGrid.getChildCount(); i++){
//            CardView cardView = (CardView)mainGrid.getChildAt(i);
//            final int finalI = i;
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(finalI == 0)
//                    {
//                        Intent intent = new Intent(MainActivity.this, FoodMenu.class);
//
//                        startActivity(intent);
//                    }
//                    else if(finalI == 1)
//                    {
//                        Intent intent = new Intent(MainActivity.this, Comments.class);
//
//                        startActivity(intent);
//                    }
//
//                    else if (finalI == 2)
//                    {
//                        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//
//                        startActivity(intent);
//                    }
//
//
//                    else if (finalI == 3)
//                    {
//                        Intent intent = new Intent(MainActivity.this, Favorite.class);
//
//                        startActivity(intent);
//                    }
//
//                    else{
//                        Toast.makeText(MainActivity.this, "Please click the button" , Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//
//
//
//
//            });
//        }
//    }


}

