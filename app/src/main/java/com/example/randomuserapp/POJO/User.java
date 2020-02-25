package com.example.randomuserapp.POJO;

import com.example.randomuserapp.R;

import java.io.Serializable;

/**
 * Un objeto {@link User} contiene información esencial y extra del
 * usuario, generado del "JSONPlaceholder API".
 *
 * @see <a href="https://jsonplaceholder.typicode.com/users">JSONPlaceholder</a>.
 */
public class User implements Serializable {

    ///////////////////////////////////////////////////////////////////////////
    // Información esencial del usuario
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Nombre completo del usuario
     */
    private String mName;

    /**
     * Nombre personalizado del usuario o "username"
     */
    private String mUsername;

    /**
     * Correo del usuario
     */
    private String mEmail;

    /**
     * Teléfono del usuario
     */
    private String mPhone;

    /**
     * Sitio web del usuario
     */
    private String mWebsite;

    /**
     * Este constructor establece los valores esencial del usuario que se verán en {@link R.layout#activity_user_list}
     *
     * @param name     o nombre (ejem. John, Max, Pedro)
     * @param userName o nombre personalizado (ejem. john_doe, max78, p3dr0)
     * @param email    o correo (ejm. johdoe@example.com, maX892@gmail.com, pedro@outlook.com)
     * @param phone    o teléfono (ejem. "079 964 34 81", "074 649 31 51", "072 321 45 81")
     * @param website  o sitio web (ejem. <a href="https://JohnDoe.com">www.JohnDoe.com</a>, <a href="https://max.com">https://max.com</a>, <a href="https://pedro.com">https://pedro.com</a>)
     */
    public User(String name, String userName, String email, String phone, String website) {
        this.mName = name;
        this.mUsername = userName;
        this.mEmail = email;
        this.mPhone = phone;
        this.mWebsite = website;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Datos de la dirección del usuario
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Calle en la que vive el usuario
     */
    private String mStreet;

    /**
     * Apartamento donde reside el usuario
     */
    private String mSuite;

    /**
     * Ciudad en la que vive el usuario
     */
    private String mCity;

    /**
     * Código postal que posee el usuario
     */
    private String mZipCode;

    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String street) {
        this.mStreet = street;
    }

    public String getSuite() {
        return mSuite;
    }

    public void setSuite(String suite) {
        this.mSuite = suite;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        this.mZipCode = zipCode;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Datos de la compañia del usuario
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Compañia que trabaja el usuario
     */
    private String mCompanyName;

    /**
     * Cita o eslogan del usaurio
     */
    private String mCatchPhrase;

    /**
     * Título universitario del usuario
     */
    private String mBS;

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        this.mCompanyName = companyName;
    }

    public String getCatchPhrase() {
        return mCatchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.mCatchPhrase = catchPhrase;
    }

    public String getBS() {
        return mBS;
    }

    public void setBS(String mBS) {
        this.mBS = mBS;
    }
}

