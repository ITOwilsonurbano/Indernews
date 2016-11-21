package itosoft.wilson.com.indernews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import itosoft.wilson.com.indernews.Models.News;
import itosoft.wilson.com.indernews.Models.NewsRespuesta;
import itosoft.wilson.com.indernews.Newsapi.NewsapiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "NEWSDEX";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaNewsAdapter listaNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        listaNewsAdapter = new ListaNewsAdapter(this);
        recyclerView.setAdapter(listaNewsAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this , 1);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.inder.gov.co/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerdatos();
    }

    private void obtenerdatos() {

        NewsapiService service = retrofit.create(NewsapiService.class);
        Call<NewsRespuesta> newsRespuestaCall = service.obtenerListaNews();

        newsRespuestaCall.enqueue(new Callback<NewsRespuesta>() {
            @Override
            public void onResponse(Call<NewsRespuesta> call, Response<NewsRespuesta> response) {

                if (response.isSuccessful()){

                    NewsRespuesta newsRespuesta = response.body();
                    ArrayList<News> listaNews = newsRespuesta.getItems();

                    for (int i = 0 ; i < listaNews.size();i ++){
                        News n = listaNews.get(i);
                        Log.i(TAG,"Noticias: " + n.getTexto());
                    }

                    listaNewsAdapter.adicionarListaNews(listaNews);

                }else {
                    Log.e(TAG,"onResponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<NewsRespuesta> call, Throwable t) {
                Log.e(TAG," onFailure : " + t.getMessage());
            }
        });
    }
}
