package itosoft.wilson.com.indernews.Newsapi;

import itosoft.wilson.com.indernews.Models.NewsRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wilsonurbano on 21/11/16.
 */

public interface NewsapiService {
    @GET("noticias")
    Call<NewsRespuesta> obtenerListaNews(/*@Query("limit") int limit, @Query("offset") int offset*/);

}
