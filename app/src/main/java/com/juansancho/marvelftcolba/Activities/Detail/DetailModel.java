package com.juansancho.marvelftcolba.Activities.Detail;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.juansancho.marvelftcolba.Activities.Master.MasterPresenter;
import com.juansancho.marvelftcolba.DTO.comicDTO;
import com.juansancho.marvelftcolba.Global.APIHelper;
import com.juansancho.marvelftcolba.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.juansancho.marvelftcolba.Global.PaginationListener.PAGE_SIZE;

public class DetailModel {

    private Context context;
    private DetailPresenter presenter;

    private APIHelper apiHelper;

    public DetailModel(Context context, DetailPresenter presenter){
        this.context = context;
        this.presenter = presenter;
        apiHelper = new APIHelper(context);
    }
}
