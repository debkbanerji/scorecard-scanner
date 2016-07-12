package com.example.scorecardscanner;

/**
 * Created by Deb Banerji on 12-Jul-16.
 */
public class TimeManager {

    //    Note: Times are in milliseconds
    int[] times;

    public TimeManager(int[] times) {
        setTimes(times);
    }

    public int[] getTimes() {
        return times;
    }

    public void setTimes(int[] times) {
        if (!(times.length == 3 || times.length == 5)) {
            throw new RuntimeException("Length of 'times' array must be 3 or 5");
        }
        for (int i = 0; i < times.length; i++) {
            times[i] = (times[i] / 10) * 10;
        }
        this.times = times;
    }

    private static String getTimeString(int time) {
        String result;
        int milliseconds = time % 1000;
        int seconds = (time / 1000) % 60;
        int minutes = (time / 60000) % 60;
        result = "";
        result = result + milliseconds;
        if (time > 1000) {
            result = seconds + "." + result;
        }
        if (time > 60000) {
            result = minutes + ":" + result;
        }
        result = result.substring(0, result.length() - 1);
        return result;

    }

    public String[] getTimeStrings() {
        String[] result = new String[times.length];
        for (int i = 0; i < times.length; i++) {
            result[i] = getTimeString(times[i]);
        }
        return result;
    }

    public String getAverage() {
        return getAverage(times);
    }

    public static String getAverage(int[] times) {
        int smallIndex = 0;
        int bigIndex = 0;
        int total = 0;
        if (times.length == 5) {
            for (int i = 0; i < times.length; i++) {
                if (times[i] < times[smallIndex]) {
                    smallIndex = i;
                }
                if (times[i] > times[bigIndex]) {
                    bigIndex = i;
                }
            }
        }

        if (times.length == 5) {
            for (int i = 0; i < times.length; i++) {
                if (i != smallIndex && i != bigIndex) {
                    total += times[i];
                }
            }
        } else {
            for (int time : times) {
                total += time;
            }
        }

        int result = total / 3;
        result += 5;
        result = (result / 10) * 10;
        return getTimeString(result);
    }
}