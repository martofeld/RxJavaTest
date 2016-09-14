package com.mfeldsztejn.rxjavatest.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Person;

/**
 * Created by mfeldsztejn on 9/10/16.
 */

public class PersonDetailActivity extends AppCompatActivity {
    private static final String KEY_PERSON = "PERSON";

    private Person person;

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
}
