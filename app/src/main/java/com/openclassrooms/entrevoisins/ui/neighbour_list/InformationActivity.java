package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class InformationActivity extends AppCompatActivity {


    private  FloatingActionButton favoriteButton;
    boolean favoriteNeighbour = false;
    private List<Neighbour> mNeighbours;
    private NeighbourApiService mApiService;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mApiService = DI.getNeighbourApiService();
        Neighbour neighbour = (Neighbour) getIntent().getSerializableExtra("neighbour");

        ImageView backGroundId = findViewById(R.id.backgroundId);
        CardView cardInfo = findViewById(R.id.info);
        TextView cardTextName = findViewById(R.id.name);
        TextView logoPosition = findViewById(R.id.logo_position);
        TextView logoPhone = findViewById(R.id.logo_phone);
        TextView logoWorld = findViewById(R.id.logo_world);
        CardView cardAbout = findViewById(R.id.cardAboutMe);
        TextView textAboutTitle = findViewById(R.id.textAboutTitle);
        TextView textAboutDescription = findViewById(R.id.textAboutDescription);
        FloatingActionButton favoriteButton = findViewById(R.id.favoriteButton);
        TextView backGroundIdName = findViewById(R.id.backGroundIdName);

        cardTextName.setText(neighbour.getName());
        logoPosition.setText(neighbour.getAddress());
        logoPhone.setText(neighbour.getPhoneNumber());
        logoWorld.setText(neighbour.toString());
        textAboutDescription.setText(neighbour.getAboutMe());
        backGroundIdName.setText(neighbour.getName());

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(backGroundId);

        if (neighbour.isFavorite()) {
            favoriteButton.setColorFilter(ContextCompat.getColor(this, R.color.yellow));
        } else {
            favoriteButton.setColorFilter(ContextCompat.getColor(this, R.color.grey));
        }
        favoriteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mApiService.invertNeighbourFavoriteStatus(neighbour);


                if (neighbour.isFavorite()) {
                    favoriteButton.setColorFilter(ContextCompat.getColor(InformationActivity.this, R.color.yellow));
                } else {
                    favoriteButton.setColorFilter(ContextCompat.getColor(InformationActivity.this, R.color.grey));
                }
            }
        });
    }
}




