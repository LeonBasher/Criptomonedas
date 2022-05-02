package com.example.criptomonedas.interfaces;

import com.example.criptomonedas.models.Monedas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoAPI {


    @GET("markets?vs_currency=mxn&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=1h")
    Call<List<Monedas>> getCripto();
}
