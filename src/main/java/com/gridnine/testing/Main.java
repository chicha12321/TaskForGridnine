package com.gridnine.testing;

import com.gridnine.testing.dao.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.FilterOfFlightsImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();

        List<Flight> flightsForIncorrectDataDeparture = new FilterOfFlightsImpl(flightList)
                .filterForIncorrectDataDeparture()
                .toBuild();
        System.out.println("Incorrect data departure:\n" + flightsForIncorrectDataDeparture);

        List<Flight> flightsForArrivalBeforeDeparture = new FilterOfFlightsImpl(flightList)
                .filterForArrivalBeforeDeparture()
                .toBuild();
        System.out.println("Arrival before departure:\n" + flightsForArrivalBeforeDeparture);

        List<Flight> flightsIntervalOnGround = new FilterOfFlightsImpl(flightList)
                .filterIntervalOnGround()
                .toBuild();
        System.out.println("Interval on ground is more than 2 hours:\n" + flightsIntervalOnGround);

    }
}
