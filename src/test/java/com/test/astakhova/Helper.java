package com.test.astakhova;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Helper {
    public static String getFormattedTodayDateWithOffset(int offset){
        LocalDate today = LocalDate.now().plusDays(offset);
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(today);
    }

    public static String getCurrentMonthName(){
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public static String getFavoriteMovies(){
        List<String> favorite = Arrays.asList("Good Omens", "Doctor Who", "Black Mirror", "Sherlock Holmes", "Girlboss", "Anastasia");
        Collections.shuffle(favorite);
        List<String> randomMovies = favorite.subList(0, 3);
        StringBuilder randomMoviesStr = new StringBuilder();
        int moviesSize = randomMovies.size();
        for (int i = 0; i < moviesSize; i++){
            randomMoviesStr.append(randomMovies.get(i));
            if (i != moviesSize - 1) {
                randomMoviesStr.append("\n");
            }
        }
        return randomMoviesStr.toString();
    }
}
