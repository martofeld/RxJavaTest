package com.mfeldsztejn.rxjavatest.detail.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.detail.ImageAdapter;
import com.mfeldsztejn.rxjavatest.dto.Image;
import com.mfeldsztejn.rxjavatest.dto.Images;
import com.mfeldsztejn.rxjavatest.dto.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mfeldsztejn on 9/10/16.
 */

public class PersonDetailActivity extends DetailActivity {
    private static final String KEY_PERSON = "PERSON";
    private ViewPager viewPager;
    private Person person;
    private TextView nameTv, ageTv, heightTv, massTv, genderTv, hairTv, eyesTv, skinTv;

    public static Intent getIntent(Context context, Person person) {
        Intent i = new Intent(context, PersonDetailActivity.class);
        i.putExtra(KEY_PERSON, person);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        extractPerson(savedInstanceState);

        getData(person.getName());
        initViews();
    }

    @Override
    void onSuccess(Images images) {
        List<Image> imagesList = new ArrayList<>();
        for (int i = 0; i < 5 && i < images.getResultCount(); i++) {
            int index = new Random().nextInt(images.getImages().size());
            Image image = images.getImages().get(index);
            if (imagesList.contains(image)) {
                i--;
            } else {
                imagesList.add(image);
            }
        }
        ImageAdapter adapter = new ImageAdapter(imagesList);
        viewPager.setAdapter(adapter);
    }

    private void extractPerson(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (getIntent() != null && getIntent().getExtras() != null) {
                person = (Person) getIntent().getExtras().getSerializable(KEY_PERSON);
            }
        } else {
            person = (Person) savedInstanceState.getSerializable(KEY_PERSON);
        }
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        nameTv = (TextView) findViewById(R.id.detail_name);
        ageTv = (TextView) findViewById(R.id.detail_age);
        heightTv = (TextView) findViewById(R.id.detail_height);
        massTv = (TextView) findViewById(R.id.detail_mass);
        genderTv = (TextView) findViewById(R.id.detail_gender);
        hairTv = (TextView) findViewById(R.id.detail_hair);
        eyesTv = (TextView) findViewById(R.id.detail_eyes);
        skinTv = (TextView) findViewById(R.id.detail_skin);

        nameTv.setText("Name: " + person.getName());
        ageTv.setText("Birth Year: " + person.getBirthYear());
        heightTv.setText("Height: " + person.getHeight() + "cm");
        massTv.setText("Mass: " + person.getMass() + "kg");
        genderTv.setText("Gender: " + person.getGender());
        hairTv.setText("Hair Color: " + person.getHairColor());
        eyesTv.setText("Eye Color: " + person.getEyeColor());
        skinTv.setText("Skin Color: " + person.getSkinColor());

        LinearLayout filmsContainer = (LinearLayout) findViewById(R.id.films_container);
        TextView textView = new TextView(this);
        textView.setText("Films:");
        filmsContainer.addView(textView);
        for (final String film : person.getFilms()) {
            textView = new TextView(this);
            textView.setText(film);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PersonDetailActivity.this, "Film " + film, Toast.LENGTH_SHORT).show();
                }
            });
            filmsContainer.addView(textView);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_PERSON, person);
        super.onSaveInstanceState(outState);
    }
}
