package org.cgis.dev.SOSUtility;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zil on 2016/9/21.
 */
public class SOSRequest {

    public static String post(String url, String body) throws IOException {
        URL _url = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) _url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        OutputStream output = new BufferedOutputStream(urlConnection.getOutputStream());
        output.write(body.getBytes());
        output.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            builder.append(inputLine);
        in.close();
        return builder.toString();
    }

    public static String get(String url){
        return null;
    }
}
