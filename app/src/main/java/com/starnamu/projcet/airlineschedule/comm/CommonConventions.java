package com.starnamu.projcet.airlineschedule.comm;

/**
 * Created by starnamu on 2015-05-08.
 */
public interface CommonConventions {

    String URLHADE = "http://openapi.airport.kr/openapi/service/StatusOfPassengerFlights";
    String SERVICEKEY = "?ServiceKey=RN5il12RYM%2FXFWaIm8otCbez%2B5W1YxN91ZzBtYx4u" +
            "3hh24IgLuMAr5LEvByuM62KPv7l8Y4qbNUy0AgE2YtWHw%3D%3D";
    String PARRIVALS = "/getPassengerArrivals";
    String PDEPARTURES = "/getPassengerDepartures";

    String[] AIRLINENAME = {"아시아나항공", "중국동방항공", "사천항공"};

    String[] PARSERITEMGROUP = {"airline", "airport", "airportCode", "flightId", "scheduleDateTime",
            "estimatedDateTime", "chkinrange", "gatenumber", "remark", "carousel"};

    String[] ARRIVALPARSERITEMGROUP = {"airline", "airport", "airportCode", "flightId", "scheduleDateTime",
            "estimatedDateTime", "chkinrange", "gatenumber", "remark", "carousel"};
}
