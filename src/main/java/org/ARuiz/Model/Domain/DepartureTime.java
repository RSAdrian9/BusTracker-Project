package org.ARuiz.Model.Domain;

import java.util.List;

public enum DepartureTime {
    SIX_AM("06:00"), SEVEN_AM("07:00"), EIGHT_AM("08:00"), NINE_AM("09:00"), TEN_AM("10:00"), ELEVEN_AM("11:00"),
    TWELVE_PM("12:00"), ONE_PM("13:00"), TWO_PM("14:00"), THREE_PM("15:00"), FOUR_PM("16:00"), FIVE_PM("17:00"),
    SIX_PM("18:00"), SEVEN_PM("19:00"), EIGHT_PM("20:00"), NINE_PM("21:00"), TEN_PM("22:00"), ELEVEN_PM("23:00"),
    TWELVE_AM("00:00");

    private String Dtime;

    DepartureTime(String Dtime) {
        this.Dtime = Dtime;
    }

    public String getDTime() {
        return Dtime;
    }
}
