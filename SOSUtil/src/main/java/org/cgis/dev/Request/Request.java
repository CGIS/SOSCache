package org.cgis.dev.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zil on 2016/10/27.
 */
public class Request {

    private final static String USER_AGENT = "Mozilla/5.0";
    private URL url;
    private HttpURLConnection connection;

    public Request(String url) throws IOException {
        this.url = new URL( url );
    }

    public Request(URL url) throws IOException {
        this.url = url;
    }

    public String get() throws IOException {
        setMethod("GET");
        return getResponse();
    }

    public String post(String body) throws IOException {
        setMethod("POST");
        write(body);
        return getResponse();
    }

    private void write(String body) throws IOException {
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        wr.write( body );
        wr.flush();
        wr.close();
    }

    private String getResponse() throws IOException{
        InputStreamReader connecionReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader in = new BufferedReader( connecionReader );
        return readFrom(in);
    }

    private String readFrom(BufferedReader in) throws IOException {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    protected void setMethod(String method) throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        if (method.equals("POST")) {
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
        }
        connection.setRequestMethod(method);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Charset", "UTF-8");
    }

}