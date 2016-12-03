package com.gangofseven.labs.app.demo.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Administrador on 03/12/2016.
 */

@IgnoreExtraProperties
public class JuanModel {
    public String nombre;
    public String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
