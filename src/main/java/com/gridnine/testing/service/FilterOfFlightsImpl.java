package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterOfFlightsImpl implements FilterOfFlights {
    private final List<Flight> flightList;

    public FilterOfFlightsImpl(List<Flight> flightList) {
        this.flightList = new ArrayList<>(flightList);
    }

    @Override
    public List<Flight> toBuild() {
        return flightList;
    }

    @Override
    public FilterOfFlights filterForIncorrectDataDeparture() {
        flightList.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())));
        return this;
    }

    @Override
    public FilterOfFlights filterForArrivalBeforeDeparture() {
        flightList.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));
        return this;

    }

    @Override
    public FilterOfFlights filterIntervalOnGround() {
        flightList.removeIf(flight -> {
            List<Segment> segments = flight.getSegments();
            LocalDateTime curDeparture;
            LocalDateTime lastArrival;
            Duration duration = Duration.ZERO;

            for (int i = 1; i < segments.size(); i++) {
                curDeparture = segments.get(i).getDepartureDate();
                lastArrival = segments.get(i - 1).getArrivalDate();
                duration = duration.plus(Duration.between(curDeparture, lastArrival).abs());
            }
            return duration.toHours() >= 2;
        });
        return this;
    }
}
