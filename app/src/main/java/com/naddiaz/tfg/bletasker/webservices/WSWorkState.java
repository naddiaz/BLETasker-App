package com.naddiaz.tfg.bletasker.webservices;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.naddiaz.tfg.bletasker.R;
import com.naddiaz.tfg.bletasker.utils.CustomRequest;
import com.naddiaz.tfg.bletasker.utils.UserPrefecences;
import com.naddiaz.tfg.bletasker.widget.MainActivity;
import com.naddiaz.tfg.bletasker.widget.RestoreUserActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nad on 12/02/15.
 */
public class WSWorkState {


    private static String URL;
    Context ctx;
    String hash;

    public WSWorkState(Context ctx, String hash){
        this.ctx = ctx;
        this.hash = hash;
        this.URL = ctx.getString(R.string.ws_url_state_work);
    }

    public void setWorkState(String id_task,String state){
        RequestQueue queue = Volley.newRequestQueue(ctx);

        Map<String, String>  params = new HashMap<String, String>();
        params.put("hash", hash);
        params.put("id_task",id_task);
        params.put("state",state);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response.has("status")){
                            try {
                                if(response.getString("status") == "false") {
                                    Toast.makeText(ctx, ctx.getString(R.string.ws_error_change_work_state), Toast.LENGTH_LONG).show();
                                }
                                else{

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx,ctx.getString(R.string.ws_error_timeout_sync),Toast.LENGTH_LONG).show();
                Log.d("Error.Response", String.valueOf(error));
            }
        });
        queue.add(jsObjRequest);
    }
}
