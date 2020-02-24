package com.example.randomuserapp;

import android.text.TextUtils;
import android.util.Log;

import com.example.randomuserapp.POJO.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Métodos "Helpers" relacionados con la solicitud y recepción de datos
 * de Usuarios de "RANDOM USER GENERATOR API".
 *
 * @see <a href="https://randomuser.me/">RANDOM USER GENERATOR</a>.
 */
public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Cree un constructor privado porque nadie debería crear un objeto {@link QueryUtils}.
     * Esta clase solo pretende contener variables y métodos estáticos, a los que se puede acceder
     * directamente desde el nombre de la clase QueryUtils y no se necesita una instancia de
     * objeto de QueryUtils.
     */
    private QueryUtils() {
    }

    /**
     * Consulta el conjunto de datos del API y devuelve una lista de objetos {@link User}.
     *
     * @param requestUrl es el link del API (ejemplo: <a href="https://randomuser.me/api">https://randomuser.me/api</a>)
     * @return Una lista de {@link User} del parámetro requestUrl
     */
    public static List<User> fetchUserData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            //Realice la solicitud HTTP a la URL y reciba una respuesta JSON.
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problema al hacer la solicitud HTTP.", e);
        }

        // Extraiga campos relevantes de la respuesta JSON y cree una lista de {@link User}s
        return extractFeatureFromJson(jsonResponse);
    }

    /**
     * Forma una url de tipo {@link String} a tipo {@link URL}.
     *
     * @param stringUrl es el link del API (ejemplo: <a href="https://randomuser.me/api">https://randomuser.me/api</a>)
     * @return La url formada ya en formato {@link URL}
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Realice una solicitud HTTP a la URL dada y devuelva una {@link String} como respuesta.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milisegundos */);
            urlConnection.setConnectTimeout(15000 /* milisegundos */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Si la solicitud fue exitosa (código de respuesta 200),
            //luego lea la secuencia de entrada y analice la respuesta.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Código de error: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problema al formar los resultados del de JSON.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Cerrar el flujo de entrada podría generar un IOException, razón por la cual
                // el método makeHttpRequest (URL url) especifica que un IOException podría
                // ser arrojado.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convierta el {@link InputStream} en una {@link String} que contenga la
     * respuesta completa del servidor.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Devuelve una lista de objetos {@link User} que se
     * ha creado al analizar la respuesta JSON.
     */
    private static List<User> extractFeatureFromJson(String earthquakeJSON) {
        // Si la String JSON está vacía o es nula, regrese antes.
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        List<User> users = new ArrayList<>();

        // Intenta analizar la cadena de respuesta JSON. Si hay un problema con la forma en que JSON
        // está organizado, se lanzará un objeto de excepción (JSONException).
        // Captura la excepción para que la aplicación no se bloquee e imprime el mensaje de error
        // en los registros.
        try {

            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

            // Extraiga el JSONArray asociado con la clave llamada "results",
            //que representa una lista de resultados de usuarios.
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("results");
            // Para cada usuario en la matriz de results, cree un objeto {@link User}
            for (int i = 0; i < earthquakeArray.length(); i++) {

                // Obtenga un solo terremoto en la posición i dentro de la lista de terremotos
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                ///////////////////////////////////////////////////////////////////////////
                // En el orden que están las variables en ese mismo orden está en el API.
                ///////////////////////////////////////////////////////////////////////////

                String gender = currentEarthquake.getString("gender");

                // Para un Usuario dado, extraiga el objeto JSON asociado con la clave
                // llamada "name", que representa una lista de todas las propiedades
                // del nombre del Usuario.
                JSONObject name = currentEarthquake.getJSONObject("name");
                String first = name.getString("first");
                String last = name.getString("last");

                // Para un Usuario dado, extraiga el objeto JSON asociado con la clave
                // llamada "location", que representa una lista de todas las propiedades
                // del la localización del Usuario.
                JSONObject location = currentEarthquake.getJSONObject("location");
                String city = location.getString("city");
                String country = location.getString("country");

                String email = currentEarthquake.getString("email");

                // Para un Usuario dado, extraiga el objeto JSON asociado con la clave
                // llamada "dob", que representa una lista de todas las propiedades
                // de la fecha de nacimiento del Usuario.
                JSONObject dob = currentEarthquake.getJSONObject("dob");
                int age = dob.getInt("age");

                String phone = currentEarthquake.getString("phone");

                String cell = currentEarthquake.getString("cell");

                // Para un Usuario dado, extraiga el objeto JSON asociado con la clave
                // llamada "picture", que representa una lista de todas las propiedades
                // de la foto de perfil del Usuario en tamaños diferentes.
                JSONObject picture = currentEarthquake.getJSONObject("picture");
                String medium = picture.getString("medium");
                String thumbnail = picture.getString("thumbnail");

                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                User user = new User(first, last, gender, email, thumbnail, medium);
                user.setAdditionalInformation(age, city, country, phone, cell);

                //Agregue el nuevo {@link User} a la lista de usuarios.
                users.add(user);
            }

        } catch (JSONException e) {
            // Si se produce un error al ejecutar cualquiera de las declaraciones anteriores
            // en el bloque "try". Capture la excepción aquí, para que la aplicación no se
            // bloquee. Imprima un mensaje de registro con el mensaje de la excepción.
            Log.e("QueryUtils", "Problema al analizar los resultados de JSON del terremoto", e);
        }
        return users;
    }

}

