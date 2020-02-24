package com.example.randomuserapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.randomuserapp.POJO.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * El {@link UserAdapter} sabe como crear una lista de items para cada usuario
 * en la fuente de datos (una lista de objetos {@link User}).
 * <p>
 * Estos elementos de la lista se proporcionarán a un adaptador de vista
 * como ListView para que se muestre al usuario.
 */
public class UserAdapter extends ArrayAdapter<User> {

    private static final String LOG_TAG = UserAdapter.class.getName();

    /**
     * Construye un nuevo {@link UserAdapter}.
     *
     * @param context de la application
     * @param users   es la lista de usuarios, que es la fuente de datos del adaptador
     */
    public UserAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    /**
     * Devuelve una vista de elemento lista que muestra información sobre el usuario
     * en la posición dada en la lista de usuarios.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Compruebe si hay una vista del elemento de la lista existente (llamada convertView)
        // que podemos reutilizar, de lo contrario, si convertView es nulo,
        // infle un nuevo diseño de elemento de lista.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.user_list_item, parent, false);
        }

        // Encuentra el usuario en la posición dada en la lista de usuarios
        User currentUser = getItem(position);

        if (currentUser == null){
            Log.e(LOG_TAG, "currentUser is null");
        }else {
            TextView fullNameTextView = listItemView.findViewById(R.id.full_name);
            /*
             * El formato fullName consiste en la combinación de
             * el primer nombre del usuario y el apellido del usuario
             * separados por una coma.
             *
             * Ejemplo : John, Doe
             * */
            StringBuilder fullName = new StringBuilder();
            fullName.append(currentUser.getmFirstName());
            fullName.append(" ");
            fullName.append(currentUser.getmLastName());

            fullNameTextView.setText(fullName);

            TextView emailTextView = listItemView.findViewById(R.id.email);
            emailTextView.setText(currentUser.getmEmail());

            ImageView pictureThumbnail = listItemView.findViewById(R.id.picture_thumbnail);

            Picasso.get().load(currentUser.getmPictureThumbnail()).into(pictureThumbnail);

        }
        return listItemView;
    }
}
