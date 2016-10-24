package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by FOUED BRAHIM on 09/11/2015.
 */
public class JsonUtils {


    public static StringBuffer request(String urlString) {
        // TODO Auto-generated method stub

        StringBuffer chaine = new StringBuffer("");
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         //   connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        return chaine;
    }


}
