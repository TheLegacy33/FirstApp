package net.devatom.firstapp.tools;

import android.util.Log;

public final class LogApp {
    private static final String app_name = "FirstApp";
    public static void i(String message) {
        Log.i(app_name, message);
    }
}
