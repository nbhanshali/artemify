package com.presenters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**The translator class is adapted from StackExchange author Maksym,
 * whose original post can be found here:
 * https://stackoverflow.com/questions/8147284/how-to-use-google-translate-api-in-my-java-application
 * The google script is largely adapted from the same author, although slight modifications were
 * needed to get the script running on our end. These modifications have since been added to the
 * original author's post coincidentally.
 */

public class translator {

    static String translate(String langFrom, String langTo, String text)  {
        try{
            String urlStr = "https://script.google.com/macros/s/AKfycbw_wAAny4t8GEQSvbNM8q_SfpXnqJ3Kc9GQih-KtmcnRgCmZSo/exec" +
                    "?q=" + URLEncoder.encode(text, "UTF-8") +
                    "&target=" + langTo +
                    "&source=" + langFrom;
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            return "";
        }

    }

}