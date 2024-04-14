package com.example.ecomersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    ListView listView;

    HashMap<String ,String> hashMap;
    ArrayList< HashMap<String ,String> > arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);


        String uri ="https://dummyjson.com/products";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("products");

                    for (int x=0;x<jsonArray.length();x++){
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String price = jsonObject.getString("price");
                        String brand = jsonObject.getString("brand");

                        //textView.append("\n"+title);
                        String[] images = new String[]{jsonObject.getString("images")};
                        String imagesArry = Arrays.toString(images);

                        hashMap = new HashMap<>();
                        hashMap.put("title",title);
                        hashMap.put("description",description);
                        hashMap.put("price",price);
                        hashMap.put("brand",brand);
                        hashMap.put("images",imagesArry);

                        arrayList.add(hashMap);



                    }

//                    JSONArray imageArray = jsonObject.getJSONArray("images");
//                    for (int i=0;i<imageArray.length();i++) {
//
//
//
//                        String imageUri = imageArray.getString(x);
//
//
//                        hashMap.put("imageUri", imageUri);
//                    }

                    MyAdapter myAdapter= new MyAdapter();
                    listView.setAdapter(myAdapter);


//
//
////                        Picasso.get().load(imageUri).into(imageView);
//                    }






                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

        //-----------------------------------------------------------------------------------------

    }


    //----------------------------------------------------------------------------


    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.item,null);

            ImageView imageView = myView.findViewById(R.id.imageView);
            TextView TvTitle = myView.findViewById(R.id.TvTitle);
            TextView TvDes = myView.findViewById(R.id.TvDes);
            TextView TvPrice = myView.findViewById(R.id.TvPrice);
            TextView TvBrand = myView.findViewById(R.id.TvBrand);
            LinearLayout layItem = myView.findViewById(R.id.layItem);

            HashMap<String,String> hashMap = arrayList.get(position);


            String title = hashMap.get("title");
            String description = hashMap.get("description");
            String price = hashMap.get("price");
            String brand = hashMap.get("brand");
            String images = hashMap.get("images");



            TvTitle.setText(title);
            TvDes.setText(description);
            TvPrice.setText(price);
            TvBrand.setText(brand);


            layItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    imageActivity.DESCRIPSTION = description;
                    imageActivity.imageSliderArray= images;
                    startActivity(new Intent(MainActivity.this,imageActivity.class));
                }
            });

            return myView;
        }
    }
    //----------------------------------------------------------------------------


    //----------------------------------------------------------------------------


    //----------------------------------------------------------------------------
}