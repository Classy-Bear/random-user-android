package com.example.randomuserapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.randomuserapp.POJO.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity implements LoaderCallbacks<List<User>> {

    private static final String LOG_TAG = UserListActivity.class.getName();

    /**
     * URL para datos de Usuarios del conjunto de datos de RANDOM USER GENERATOR
     */
    private static final String URL_API = "https://jsonplaceholder.typicode.com/users";

    /**
     * Valor constante para la identificación del "loader" de usuarios.
     * Podemos elegir cualquier número entero. Esto realmente solo entra
     * en juego si está utilizando múltiples cargadores.
     */
    private static final int USER_LOADER_ID = 1;

    /**
     * Adapter para la lista de usuarios
     */
    private UserAdapter mAdapter;

    /**
     * TextView que se muestra cuando la lista está vacía
     */
    private TextView mEmptyStateTextView;

    /**
     * Lista de usuarios que se pasará a la otra actividad.
     */
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        //Establezca la lista vacía por si no hay usuarios.
        //Establezca el adaptador para llenarlo más tarde.
        ListView userListView = findViewById(R.id.list);
        mEmptyStateTextView = findViewById(R.id.empty_view);
        userListView.setEmptyView(mEmptyStateTextView);
        mAdapter = new UserAdapter(this, new ArrayList<User>());
        userListView.setAdapter(mAdapter);

        // Establezca un elemento OnItemClickListener en ListView, que envía los argumentos
        // que se presentará en el {@link UserDetailsActivity}
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent myIntent = new Intent(UserListActivity.this, UserDetailsActivity.class);
                myIntent.putExtra("name", userList.get(position).getName());
                myIntent.putExtra("username", userList.get(position).getUsername());
                myIntent.putExtra("email", userList.get(position).getEmail());
                myIntent.putExtra("phone", userList.get(position).getPhone());
                myIntent.putExtra("website", userList.get(position).getWebsite());
                myIntent.putExtra("street", userList.get(position).getStreet());
                myIntent.putExtra("city", userList.get(position).getCity());
                myIntent.putExtra("suite", userList.get(position).getSuite());
                myIntent.putExtra("zipCode", userList.get(position).getZipCode());
                myIntent.putExtra("companyName", String.valueOf(userList.get(position).getCompanyName()));
                myIntent.putExtra("catchPhrase", userList.get(position).getCatchPhrase());
                startActivity(myIntent);
            }
        });

        // Obtenga una referencia al ConnectivityManager para verificar
        // el estado de la conectividad de red.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtenga detalles sobre la red de datos predeterminada actualmente activa
        NetworkInfo networkInfo = connMgr != null ? connMgr.getActiveNetworkInfo() : null;

        // Si hay una conexión de red, busque datos
        if (networkInfo != null && networkInfo.isConnected()) {
            // Obtenga una referencia al {@link LoaderManager} para interactuar con los "loaders".
            LoaderManager loaderManager = getLoaderManager();

            // Inicializa el "Loader". Pase la constante int {@value ID} definida anteriormente y
            // pase nulo para el "bundle". Pase esta actividad para el parámetro LoaderCallbacks
            // (que es válido porque esta actividad implementa la interfaz {@link LoaderCallbacks}).
            loaderManager.initLoader(USER_LOADER_ID, null, this);
        } else {
            // De lo contrario oculte el indicador de carga y luego
            // establezca el mensaje de error para que sea visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Actualizar el TextView que tiene el mensaje de error de conexión
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<User>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "onCreateLoader method was called");
        return new UserLoader(this, URL_API);
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> userList) {
        // Oculta el indicador de carga porque los datos se han cargado
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        // Establece el mensaje sino se encontraron datos, si el adapter
        // tiene datos este texto no se mostrará
        mEmptyStateTextView.setText(R.string.no_users);
        // Limpia el adaptador de la data previa del usuario
        mAdapter.clear();
        //Si hay una lista válida de {@link User}s, agréguelos a los adaptadores
        //Esto hará que ListView se actualice y ya no esté vacío.
        if (userList != null && !userList.isEmpty()) {
            this.userList = userList;
            mAdapter.addAll(userList);
            Log.i(LOG_TAG, "User list has been updated");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {
        // Restablecimiento del "Loader", para que podamos borrar nuestros datos existentes.
        mAdapter.clear();
        Log.i(LOG_TAG, "Loader has been reset");
    }
}
