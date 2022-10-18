package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FilterOfFlights {

    FilterOfFlights filterForIncorrectDataDeparture();

    FilterOfFlights filterForArrivalBeforeDeparture();

    FilterOfFlights filterIntervalOnGround();

    List<Flight> toBuild();
}
