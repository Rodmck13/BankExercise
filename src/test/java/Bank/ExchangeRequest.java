package Bank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class ExchangeRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    String valorPesoString = "";
    String valorEuroString = "";

    double valorEuroDouble;
    double valorPesoDouble;

    public static void main(String[] args) throws Exception {

        ExchangeRequest http = new ExchangeRequest();
        http.sendGet();
    }

    // HTTP GET request
    public void sendGet() throws Exception {

        String url = "https://api.exchangeratesapi.io/latest?base=USD";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String responseString = response.toString();

        int a= responseString.indexOf("EUR");
        valorEuroString=responseString.substring(a+5, a+12);

        int b= responseString.indexOf("MXN");
        valorPesoString=responseString.substring(b+5, b+12);

        valorEuroDouble=Double.parseDouble(valorEuroString);
        valorPesoDouble=Double.parseDouble(valorPesoString);



    }
}