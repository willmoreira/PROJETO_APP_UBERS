package com.example.williammoreira.ubers_ultimo;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Wander on 04/09/2017.
 */

public class Connection {

    public List<Marker> getData() throws JSONException {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final StringBuilder result = new StringBuilder();

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            //url = new URL("https://api.myjson.com/bins/23k9u");
            url = new URL("https://api.myjson.com/bins/3c2di");
            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);



            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                result.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(result);

        List<Marker> finalResult = generateJSON(new JSONArray(result.toString()));

        return finalResult;
    }


    public List<Marker> generateJSON(JSONArray json){

        List<Marker> found = new LinkedList<Marker>();

        try {


            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                if(obj.getString("id").equals("upa"))
                // found.add(new Marker(obj.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0),obj.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1), obj.getJSONObject("properties").getString("name")));
                //found.add(new Marker(obj.getDouble("lat"), obj.getDouble("long"),obj.getString("id"),obj.getString("unidade")));
                found.add(new Marker(obj.getDouble("lat"), obj.getDouble("long"),obj.getString("id"),obj.getString("unidade"),obj.getString("end"),obj.getString("hfun"),obj.getString("tel"),obj.getString("serv"),obj.getString("prof")));

                if(obj.getString("id").equals("hosp"))
                    found.add(new Marker(obj.getDouble("lat"), obj.getDouble("long"),obj.getString("id"),obj.getString("unidade"),obj.getString("end"),obj.getString("hfun"),obj.getString("tel"),obj.getString("serv"),obj.getString("prof")));

                else
                found.add(new Marker(obj.getDouble("lat"), obj.getDouble("long"),obj.getString("id"),obj.getString("unidade"),obj.getString("end"),obj.getString("hfun"),obj.getString("tel")));


            }

        } catch (JSONException e) {
            // handle exception
        }

        return found;

    }

}
