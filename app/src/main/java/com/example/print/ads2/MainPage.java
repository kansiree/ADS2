package com.example.print.ads2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.print.ads2.ContentPage.PageFive;
import com.example.print.ads2.ContentPage.PageFour;
import com.example.print.ads2.ContentPage.PageOne;
import com.example.print.ads2.ContentPage.PageSix;
import com.example.print.ads2.ContentPage.PageTree;
import com.example.print.ads2.ContentPage.PageTwo;
import com.google.android.gms.ads.AdListener;

import java.util.ArrayList;

import static com.example.print.ads2.MainActivity.adRequest1;
import static com.example.print.ads2.MainActivity.interstitialAd;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainPage extends Fragment {
    RecyclerView RecyclerView_;
    int position_ =0;
    ProgressBar progressBar;

    public MainPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        RecyclerView_ = view.findViewById(R.id.RecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> list = new ArrayList<>();
        list.add("1 Gameplay");
        list.add("2 Weapons");
        list.add("3 Battle Royal");
        list.add("4 How to play");
        list.add("5  How to Get V-Bucks");
        list.add("6  HOW TO CHANGE YOUR CHARACTER");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView_.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(getContext(),list);
        cardAdapter.setItemClick(new CardAdapter.onClickAdapter() {
            @Override
            public void onClickItem(int position, View view) {
                interstitialAd.loadAd(adRequest1);
                progressBar.setVisibility(View.VISIBLE);
                position_=position;
                ControPage(position);
            }
        });
        RecyclerView_.setAdapter(cardAdapter);
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                ControPage(position_);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void ControPage(int position){
        switch (position){
            case 0:
                    System.out.println("interstitialAd.isLoaded :"+interstitialAd.isLoaded());
                    changPage(new PageOne());
                break;
            case 1:
                changPage(new PageTwo());
                break;
            case 2:
                changPage(new PageTree());
                break;
            case 3:
                changPage(new PageFour());
                break;
            case 4:
                changPage(new PageFive());
                break;
            case 5:
                changPage(new PageSix());
                break;
        }

    }

    private void changPage(Fragment fragment){
        if(interstitialAd.isLoaded()){
            getFragmentManager().beginTransaction().addToBackStack("").replace(R.id.main_page,fragment).commit();
            interstitialAd.show();
        }
        else {

            System.out.println("LoadFail");
        }

    }

}

