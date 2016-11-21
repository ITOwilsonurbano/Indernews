package itosoft.wilson.com.indernews.Models;

import java.util.ArrayList;

/**
 * Created by wilsonurbano on 21/11/16.
 */

public class NewsRespuesta {

    private ArrayList<News> items;

    public ArrayList<News> getItems(){
        return items;
    }

    public void setItems(ArrayList<News> items) {
        this.items = items;
    }
}
