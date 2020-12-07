package com.juansancho.marvelftcolba.Activities.Master;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.Global.APIHelper;
import com.juansancho.marvelftcolba.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.juansancho.marvelftcolba.Global.PaginationListener.PAGE_SIZE;

public class MasterModel {

    private Context context;
    private MasterPresenter presenter;

    private APIHelper apiHelper;

    private ArrayList<comicDTO> comics;

    public MasterModel(Context context, MasterPresenter presenter){
        this.context = context;
        this.presenter = presenter;
        apiHelper = new APIHelper(context);
    }

    public void getComics(){
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.d("COUNT", presenter.comicCount + "");

        String url = apiHelper.BASE + apiHelper.FETCH_CHARACTERS + apiHelper.authParams(PAGE_SIZE, presenter.comicCount);
        Log.d("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONArray results = data.getJSONArray("results");
                            if(results.length() > 0){
                                comics = new ArrayList<>();
                                for(int i=0; i<results.length(); i++){
                                    JSONObject current = results.getJSONObject(i);
                                    String title = current.getString("title");
                                    JSONObject thumbnail = current.getJSONObject("thumbnail");
                                    String thumbnailPath = thumbnail.getString("path");
                                    String thumbnailExt = thumbnail.getString("extension");

                                    comicDTO comicDTO = new comicDTO();
                                    comicDTO.title = title;
                                    comicDTO.thumbPath = thumbnailPath + "/" + context.getResources().getString(R.string.thumbnailSize) + "." + thumbnailExt;

                                    comics.add(comicDTO);
                                }
                                presenter.loadComics(comics);
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        });
        queue.add(stringRequest);
    }
}
