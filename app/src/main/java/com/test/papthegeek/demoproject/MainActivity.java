package com.test.papthegeek.demoproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;

import com.test.papthegeek.demoproject.senproCloud.CloudinaryClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response ;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://inventory-188619.appspot.com/inventory/inventorylist/";

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyData> data_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this,data_list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size()-1){
                    load_data_from_server(data_list.get(data_list.size()-1).getId());
                }
            }
        });



        //Papa Adding his logic


    }

    private void load_data_from_server(int id) {
        final String  URL = String.format(URL_DATA.concat("/%s"), Integer.toString(id));
        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {


            String username = "papa";
            String password = "abc123";
            String credentials = username + ":" + password;

            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization" , basic)
                        .addHeader("Accept" , "application/json")
                        .url(URL).build();
                try {
                    Response response = client.newCall(request).execute();

                    System.out.println(response.toString());
                   /* System.out.println(response.body().toString());*/
                    JSONObject jsonObj = new JSONObject(response.body().string());
                    /*System.out.println(jsonObj);*/
                    JSONArray array = jsonObj.getJSONArray("events");
                   /* System.out.println(array);*/

                    for (int i =0 ; i < array.length(); ++i){
                        JSONObject object = array.getJSONObject(i);
                        System.out.println(object);
                        MyData data = new MyData(object.getInt("eventId"),object.getString("description"),object.getString("imagePath"),
                                object.getString("eventTitle"),object.getString("eventDate"),object.getString("eventStartTime"),
                                object.getString("eventEndTime"),object.getString("ticketPrice"),object.getString("nbrOfTicketsLeft"));

                        data.setImage_link(CloudinaryClient.getURL(data.getImage_link()));
                        System.out.println(data.getImage_link() );
                        data_list.add(data);
                    }

                }catch (IOException e){
                    System.out.println("IOException occured while invoking Inventory");
                    System.out.println("cause: " + e.getCause() + "/nmessage: " + e.getMessage());
                } catch (JSONException e) {
                    System.out.println("JSONException occured while invoking Inventory");
                    System.out.println("cause: " + e.getCause() + "/nmessage: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);

    }
}
