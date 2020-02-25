package com.example.randomuserapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.randomuserapp.POJO.User;

import java.util.List;

/**
 * Carga una lista de usuarios usando un AsyncTaskLoader
 * para realizar la solicitud de red a la URL dada.
 */
public class UserLoader extends AsyncTaskLoader<List<User>> {

    private static final String LOG_TAG = UserLoader.class.getName();

    /**
     * URL que cargará el API
     */
    private String mUrl;

    /**
     * Lista de Usuarios actual
     */
    private List<User> mUsers;

    /**
     * Constructor de {@link UserLoader}.
     *
     * @param context de la actividad
     * @param url que cargará la data del API
     */
    public UserLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * Aquí es donde se realiza la mayor parte de nuestro trabajo. Esta función es
     * llamado en un hilo con el nombre de "background thread" y debería generar un
     * nuevo conjunto de datos a ser publicados por el "loader".
     */
    @Override
    public List<User> loadInBackground() {
        if (mUrl == null) {
            Log.d(LOG_TAG, "mUrl is null in loadInBackground() method");
            return null;
        }
        // Realice la solicitud de red, analice la respuesta
        // y extraiga una lista de usuarios.
        return QueryUtils.fetchUserData(mUrl);
    }

    /**
     * Se llama cuando hay nuevos datos para entregar al cliente.
     * La super class se encargará de entregarlo; la implementación
     * aquí solo agrega un poco más de lógica.
     */
    @Override
    public void deliverResult(List<User> users) {
        mUsers = users;
        if (isStarted()) {
            // Si el "loader" se inicia actualmente,
            // podemos entregar sus resultados de inmediato.
            super.deliverResult(users);
        }
    }

    /**
     * Maneja una solicitud para iniciar el "loader".
     */
    @Override
    protected void onStartLoading() {
        if (mUsers != null) {
            // If we currently have a result available,
            // deliver it immediately.
            deliverResult(mUsers);
        }

        if (takeContentChanged() || mUsers == null) {
            // Si los datos han cambiado desde la última vez que se cargaron
            // o no están disponibles actualmente, inicie una carga.
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    /**
     * Maneja una solicitud para restablecer completamente el cargador.
     */
    @Override
    protected void onReset() {
        super.onReset();
        // Asegúrese de que el cargador esté detenido
        onStopLoading();
    }
}



