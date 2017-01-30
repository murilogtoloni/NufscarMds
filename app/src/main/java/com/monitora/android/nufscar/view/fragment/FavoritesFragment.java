package com.monitora.android.nufscar.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.monitora.android.nufscar.R;
import com.monitora.android.nufscar.model.News;
import com.monitora.android.nufscar.view.NewsDetailsActivity;
import com.monitora.android.nufscar.view.adapter.ListAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdour on 26/01/2017.
 */

public class FavoritesFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View newsFeedView;
    ArrayList mAndroidMapList;
    String fileName = "FavoriteNews.bak";
//    public static final String URL = "https://ufscar-monitora.firebaseio.com/.json";
//
//    public static final String KEY_AUTOR = "autor";
//    public static final String KEY_DATA = "data";
//    public static final String KEY_FIGCAPTION = "figcapion";
    public static final String KEY_IDNOTICIA = "idNoticia";
//    public static final String KEY_IMG = "image_src";
//    public static final String KEY_TEXTO = "texto";
//    public static final String KEY_TITULO = "titulo";
//    public static final String KEY_URL = "url";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.newsFeedView = inflater.inflate(R.layout.fragment_favorites, container, false);


        try {
            //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                FileInputStream fileInputStream = getActivity().openFileInput(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                mAndroidMapList = (ArrayList) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            //}
        } catch (FileNotFoundException e) {
            mAndroidMapList = new ArrayList<News>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListView newsFeedListView = (ListView) this.newsFeedView.findViewById(R.id.list_view_favorites);
        ListAdapter listAdapter = new ListAdapter(this.mAndroidMapList);
        newsFeedListView.setAdapter(listAdapter);



        //########################
        onLoaded(mAndroidMapList);



        //####################



        return this.newsFeedView;
    }


    public void onError() {
        //Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }



    private ListView  loadListView() {

        /*ListAdapter adapter = new SimpleAdapter(FavoritesFragment.this, mAndroidMapList, R.layout.list_item,
                new String[] {  KEY_TITULO, KEY_IMG, KEY_IDNOTICIA },
                new int[] {R.id.version,R.id.name, R.id.api });*/

        //mListView.setAdapter(adapter);

        ListView newsFeedListView = (ListView) this.newsFeedView.findViewById(R.id.list_view_favorites);
       ListAdapter listAdapter = new ListAdapter(this.mAndroidMapList);
        newsFeedListView.setAdapter(listAdapter);

        return newsFeedListView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

//    @Override
    public void onLoaded(List<News> androidList) {
//        for (News android : androidList) {
//
//
//            mAndroidMapList.add(android);
//        }

        ListView listView = loadListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News news = (News) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), NewsDetailsActivity.class);
                intent.putExtra(KEY_IDNOTICIA,news);
                startActivity(intent);


            }
        });

    }

















}


















//    public void onLoaded(List<News> androidList) {
//        for (News android : androidList) {
//
//
//            mAndroidMapList.add(android);
//        }
//
//        ListView listView = loadListView();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                News news = (News) parent.getItemAtPosition(position);
//                mAndroidMapList.add(news);
//            }
//        });
//
//    }

//        Button saveFavortie = (Button) this.getActivity().findViewById(R.id.events_details_radio);
//        saveFavortie.setOnClickListener(new View.OnClickListener(){
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                String meuOVO;
//
//                News news = null;//(News) v.getParent();
//
//                /////////////////////////////////////
//                try {
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                        FileInputStream fileInputStream = getContext().openFileInput(fileName);
//                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//                        mAndroidMapList = (ArrayList) objectInputStream.readObject();
//                        objectInputStream.close();
//                        fileInputStream.close();
//
//                        mAndroidMapList.indexOf(news);
//                        if(mAndroidMapList.indexOf(news) < 0){
//                            mAndroidMapList.add(news);
//
//                            FileOutputStream fileOutputStream = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
//                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//                            objectOutputStream.writeObject(mAndroidMapList);
//                            fileOutputStream.close();
//                            objectOutputStream.close();
//                        }
//                    }
//                } catch (FileNotFoundException e) {
//                    mAndroidMapList = new ArrayList<News>();
//                    mAndroidMapList.add(news);
//
//                    FileOutputStream fileOutputStream = null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                        try {
//                            fileOutputStream = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
//                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//                            objectOutputStream.writeObject(mAndroidMapList);
//                            fileOutputStream.close();
//                            objectOutputStream.close();
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                } catch (IOException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//
//

///////////////////////////////
//            }
//        });