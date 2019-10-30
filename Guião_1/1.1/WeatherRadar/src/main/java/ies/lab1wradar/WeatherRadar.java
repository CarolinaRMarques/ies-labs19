package ies.lab1wradar;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author carolina
 */
public class WeatherRadar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IpmaService service = retrofit.create(IpmaService.class);

        int CITY_ID = Integer.parseInt(args[0]);

        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);
        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();
            System.out.println("Max temp for today: " + forecast.getData().
                    listIterator().next().getTMax());
            System.out.println("Min temp for today: " + forecast.getData().
                    listIterator().next().getTMin());
            System.out.println("Probability of Precipitation: " + forecast.getData().
                    listIterator().next().getPrecipitaProb());
            System.out.println("Wind Direction: " + forecast.getData().
                    listIterator().next().getPredWindDir());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
