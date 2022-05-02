package com.example.criptomonedas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.criptomonedas.interfaces.ProductoAPI;
import com.example.criptomonedas.models.Monedas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    TextView Currentbtc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Currentbtc = findViewById(R.id.tvCurrentBitcoin);


        setMonedas();

    }


    public void setMonedas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/coins/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);

        Call<List<Monedas>> call = productoAPI.getCripto();

        call.enqueue(new Callback<List<Monedas>>() {
            @Override
            public void onResponse(Call<List<Monedas>> call, Response<List<Monedas>> response) {

                if(!response.isSuccessful()){
                    Currentbtc.setText("CODIGO " +response.code());
                    return;
                }

                List<Monedas> listMonedas = response.body();

                    for(Monedas moneda: listMonedas){



                        String content = "";
                        content += moneda.getSymbol() + "\n";
                        content += "                             $" +  moneda.getCurrent_price() + "mxn" + "\n";
                        content +=  moneda.getName() + "\n" + "\n" + "\n" + "\n";
                        Currentbtc.append(content);

                    }
            }

            @Override
            public void onFailure(Call<List<Monedas>> call, Throwable t) {
                Currentbtc.setText(t.getMessage());
                Toast.makeText(MainActivity.this, "ERROR EN SISTEMA", Toast.LENGTH_SHORT).show();

            }
        });

    }
}