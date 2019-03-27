import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.URLEncoder.encode;

public class MapBoxService {

    private final String _baseUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/";

    public String MakeCall(String searchRequest, String token) throws IOException {

        String encodedSearch = encode(searchRequest, "UTF-8");

        URL requestUrl = new URL(_baseUrl + encodedSearch + ".json?access_token=" + token);

        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();

        if (status == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return content.toString();
        }

        return connection.getResponseMessage();
    }
}
