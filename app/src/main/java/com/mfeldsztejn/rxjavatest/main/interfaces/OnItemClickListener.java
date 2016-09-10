package com.mfeldsztejn.rxjavatest.main.interfaces;

import com.mfeldsztejn.rxjavatest.dto.Person;
import com.mfeldsztejn.rxjavatest.dto.StarShip;

/**
 * Created by mfeldsztejn on 9/6/16.
 */

public interface OnItemClickListener {
    void onPersonSelected(Person person);

    void onStarShipSelected(StarShip starShip);
}
