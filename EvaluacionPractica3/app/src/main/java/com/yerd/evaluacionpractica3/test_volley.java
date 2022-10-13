package com.yerd.evaluacionpractica3;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class test_volley {

    //Estructura base
    private void baseRequest(final Context context){
        String url = "";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", "1");
                return map;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    private void pruevaVolley(final  Context context){
        String url = "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Result handling
                        System.out.println(response.substring(0, 16));
                        //textView.setText(response.substring(0, 16));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Error handling
                System.out.println("Something went wrong!");
                Toast.makeText(context, "Sin conexion a internet", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void peticionGson(final Context context) {
        String url = "";

        JsonObjectRequest jsonObjectRequest = new  JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        //texView.setText("Response: " + response.toString());
                        Toast.makeText(context, "" + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    private void recibirJson(final  Context context){
        String url = "";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuestaJSON = new JSONObject(response.toString());

                    String var1 = respuestaJSON.getString("id");
                    String var2 = respuestaJSON.getString("nombre");
                    //textView.setText("Response: " + response.toString());
                    Toast.makeText(context, "Id: " + var1 + " \nnombre: " + var2,
                            Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
