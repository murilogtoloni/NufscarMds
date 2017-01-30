package com.monitora.android.nufscar.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.monitora.android.nufscar.R;
import com.monitora.android.nufscar.model.News;
import com.monitora.android.nufscar.view.fragment.FavoritesFragment;
import com.monitora.android.nufscar.view.fragment.NewsFeedFragment;
import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewsDetailsActivity extends AppCompatActivity {


    ArrayList<News> mAndroidMapList;
    String fileName = "FavoriteNews.bak";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final News news = (News) extras.getSerializable(NewsFeedFragment.KEY_IDNOTICIA);

            final TextView newsTitle = (TextView) findViewById(R.id.news_details_title);
            TextView newsAuthor = (TextView) findViewById(R.id.news_details_author);
            TextView newsText = (TextView) findViewById(R.id.news_details_text);
            ImageView targetImageView = (ImageView) findViewById(R.id.news_details_image);
            String imageUrl = news.getImg_src();

            Picasso
                    .with(this)
                    .load(imageUrl)
                    .into(targetImageView);


            newsTitle.setText(news.getTitulo());
            newsAuthor.setText(news.getAutor() + " - " + news.getData());
            newsText.setText(news.getTexto());


            Button button = (Button) findViewById(R.id.adicionar_favoritos);

//            private void setOnClick(final Button btn, final News news){
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


//     newsTitle.setText(news.getTitulo()+" TESTE "+news.getAutor()+" TESTE "+news.getData());





                    //########################
//                                              RelativeLayout rl = (RelativeLayout) v.getParent();
//                                              rl.findViewById()

                    /////////////////////////////////////
                    try {
                        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        FileInputStream fileInputStream = openFileInput(fileName);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        mAndroidMapList = (ArrayList) objectInputStream.readObject();
                        objectInputStream.close();
                        fileInputStream.close();

                        mAndroidMapList.indexOf(news);
                        if (mAndroidMapList.indexOf(news) < 0) {
                            mAndroidMapList.add(news);

                            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                            objectOutputStream.writeObject(mAndroidMapList);
                            fileOutputStream.close();
                            objectOutputStream.close();
                        }
                        // }
                    } catch (FileNotFoundException e) {
                        mAndroidMapList = new ArrayList<News>();
                        mAndroidMapList.add(news);

                        FileOutputStream fileOutputStream = null;
                        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            try {
                                fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                                objectOutputStream.writeObject(mAndroidMapList);
                                fileOutputStream.close();
                                objectOutputStream.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        //}
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }









                    //##########
                }
            });
        }
    }
}