package com.example.randomuserapp.POJO;

import com.example.randomuserapp.R;

/**
 * Un objeto {@link User} contiene información esencial y extra del
 * usuario, generado del "RANDOM USER GENERATOR API".
 *
 * @see <a href="https://randomuser.me/">RANDOM USER GENERATOR</a>.
 */
public class User {

    ///////////////////////////////////////////////////////////////////////////
    // Información esencial del usuario
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Primer nombre del usuario
     */
    private String mFirstName;

    /**
     * Primer apellido del usuario
     */
    private String mLastName;

    /**
     * Género del usuario
     */
    private String mGender;

    /**
     * Correo del usuario
     */
    private String mEmail;

    /**
     * Correo del usuario
     */
    private String mPictureThumbnail;

    /**
     * Correo del usuario
     */
    private String mPictureMedium;


    /**
     * Este constructor establece los valores esencial del usuario que se verán en {@link R.layout#activity_user_list}
     *
     * @param firstName o primer nombre del usuario (ejem. John, Max, Pedro)
     * @param lastName o apellido de usuario (ejem. Doe, Acevedo, Cabral)
     * @param gender o género del usuario (ejm. femenino, masculino, no-binario)
     * @param email o correo del usuario (ejm. johdoe@example.com, john892@gmail.com, pedro@outlook.com)
     * @param pictureThumbnail o imagen en miniatura del usuario (<a href="https://randomuser.me/api/portraits/thumb/men/38.jpg/">imagen 48 x 48</a>)
     * @param pictureMedium o imagen mediana del usuario (<a href="https://randomuser.me/api/portraits/med/men/38.jpg">imagen tipo 72 x 72</a>)
     */
    public User(String firstName, String lastName, String gender, String email, String pictureThumbnail, String pictureMedium) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mGender = gender;
        this.mEmail = email;
        this.mPictureThumbnail = pictureThumbnail;
        this.mPictureMedium = pictureMedium;
    }

    /**
     * Retorna el primer nombre del usuario
     */
    public String getmFirstName() {
        return mFirstName;
    }

    /**
     * Retorna el último nombre del usuario
     */
    public String getmLastName() {
        return mLastName;
    }

    /**
     * Retorna el genéro del usuario
     */
    public String getmGender() {
        return mGender;
    }

    /**
     * Retorna el correo del usuario
     */
    public String getmEmail() {
        return mEmail;
    }

    /**
     * Retorna la imágen del usuario que se usará
     * en el {@link com.example.randomuserapp.R.layout#activity_user_list}
     */
    public String getmPictureThumbnail() {
        return mPictureThumbnail;
    }

    /**
     * Retorna la imágen del usuario que se usará
     * en el {@link com.example.randomuserapp.R.layout#activity_user_list}
     */
    public String getmPictureMedium() {
        return mPictureMedium;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Información extra del Usuario
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Edad del usuario
     */
    private int mAge;
    /**
     * Ciudad del usuario
     */
    private String mCity;
    /**
     * País del usuario
     */
    private String mCountry;
    /**
     * Teléfono del usuario
     */
    private String mPhone;
    /**
     * Celular del usuario
     */
    private String mCell;

    /**
     * Este función establece los valores adicionales del usuario que se verán en {@link R.layout#activity_user_details}
     *
     * @param age o edad del usuario (ejem. 12, 46, 23)
     * @param city o ciudad en la que reside el usuario (ejem. Tokyo, New York, Londres)
     * @param country o país en la que reside el usuario (ejem. Japón, Estados Unidos, Reino Unido)
     * @param phone o teléfono del usuario (ejem. "079 964 34 81", "074 649 31 51", "072 321 45 81")
     * @param cell o celular del usuario (ejem. "077 194 94 43", "087 344 24 49", "072 362 69 29")
     */
    public void setAdditionalInformation(int age, String city, String country, String phone, String cell) {
        this.mAge = age;
        this.mCity = city;
        this.mCountry = country;
        this.mPhone = phone;
        this.mCell = cell;
    }

    /**
     * Retorna la edad del usuario
     */
    public int getmAge() {
        return mAge;
    }

    /**
     * Retorna la ciudad del usuario
     */
    public String getmCity() {
        return mCity;
    }

    /**
     * Retorna el país del usuario
     */
    public String getmCountry() {
        return mCountry;
    }

    /**
     * Retorna el teléfono del usuario
     */
    public String getmPhone() {
        return mPhone;
    }

    /**
     * Retorna el celular del usuario
     */
    public String getmCell() {
        return mCell;
    }
}
