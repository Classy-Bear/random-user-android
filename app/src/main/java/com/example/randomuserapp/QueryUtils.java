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
 * de Usuarios de "JSONPlaceholder API".
 *
 * @see <a href="https://jsonplaceholder.typicode.com/users">JSONPlaceholder</a>.
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
     * @param requestUrl es el link del API (ejemplo: <a href="https://jsonplaceholder.typicode.com/users">https://jsonplaceholder.typicode.com/users</a>)
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
     * @param stringUrl es el link del API (ejemplo: <a href="https://jsonplaceholder.typicode.com/users">https://jsonplaceholder.typicode.com/users</a>)
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
    private static List<User> extractFeatureFromJson(String jsonResponse) {
        // Si la String JSON está vacía o es nula, regrese antes.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<User> users = new ArrayList<>();

        // Intenta analizar la cadena de respuesta JSON. Si hay un problema con la forma en que JSON
        // está organizado, se lanzará un objeto de excepción (JSONException).
        // Captura la excepción para que la aplicación no se bloquee e imprime el mensaje de error
        // en los registros.
        try {

            JSONArray baseJsonResponse = new JSONArray(jsonResponse);

            // Para cada usuario en la matriz de results, cree un objeto {@link User}
            for (int i = 0; i < baseJsonResponse.length(); i++) {

                // Obtenga un solo usuario en la posición i dentro de la lista de usuarios
                JSONObject currentUser = baseJsonResponse.getJSONObject(i);

                ///////////////////////////////////////////////////////////////////////////
                // Información esencial del usuario
                ///////////////////////////////////////////////////////////////////////////

                String name = currentUser.getString("name");
                String userName = currentUser.getString("username");
                String email = currentUser.getString("email");

                // Para un usuario dado, extraiga el objeto JSON asociado con la clave
                // llamada "address", que representa una lista de todas las propiedades
                // del la dirrección donde vive el Usuario.
                JSONObject address = currentUser.getJSONObject("address");
                String street = address.getString("street");
                String suite = address.getString("suite");
                String city = address.getString("city");
                String zipCode = address.getString("zipcode");

                ///////////////////////////////////////////////////////////////////////////
                // Más información esencial del usuario
                ///////////////////////////////////////////////////////////////////////////
                String phone = currentUser.getString("phone");
                String website = currentUser.getString("website");

                // Para un usuario dado, extraiga el objeto JSON asociado con la clave
                // llamada "company", que representa una lista de todas las propiedades
                // del la compañia que trabaja el Usuario.
                JSONObject company = currentUser.getJSONObject("company");
                String companyName = company.getString("name");
                String catchPhrase = company.getString("catchPhrase");
                String bs = company.getString("bs");

                // Cree un nuevo objeto {@link User} de la respuesta JSON.
                User user = new User(name,userName,email,phone,website);

                // Data del Usuario
                user.setStreet(street);
                user.setSuite(suite);
                user.setCity(city);
                user.setZipCode(zipCode);

                // Data de la compañia del Usuario
                user.setCompanyName(companyName);
                user.setCatchPhrase(catchPhrase);
                user.setBS(bs);

                //Agregue el nuevo {@link User} a la lista de usuarios.
                users.add(user);
            }

        } catch (JSONException e) {
            // Si se produce un error al ejecutar cualquiera de las declaraciones anteriores
            // en el bloque "try". Capture la excepción aquí, para que la aplicación no se
            // bloquee. Imprima un mensaje de registro con el mensaje de la excepción.
            Log.e("QueryUtils", "Problema al analizar los resultados de JSON del usuario", e);
        }
        return users;
    }

}

