package com.example.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USERS_ID = "user_id";
    private static final String PARAM_VERSION = "v";
    private static final String ACCESS_TOKEN = "access_token";


    public static URL generalURL (String userId) {
        Uri bultUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USERS_ID, userId)
                .appendQueryParameter(PARAM_VERSION, "5.52")
                .appendQueryParameter(ACCESS_TOKEN, "d98c32c3614865751f6827b23d29367fbfa87984ec3b60783b6162548fbc8930d53e417a1bf0bbcd32f21")
                .build();
        //d98c32c3614865751f6827b23d29367fbfa87984ec3b60783b6162548fbc8930d53e417a1bf0bbcd32f21
                URL url = null;
        try {
            url = new URL(bultUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (UnknownHostException e){
            return null;

        } finally {
            urlConnection.disconnect();
        }
    }
}
