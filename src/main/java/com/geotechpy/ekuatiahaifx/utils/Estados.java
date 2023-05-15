package com.geotechpy.ekuatiahaifx.utils;

import java.util.HashMap;
import java.util.Map;

public enum Estados {
    A("Activo"),
    I("Inactivo");

    private static final Map<String, Estados> POR_DESCRIPCION = new HashMap<>();
    static {
        for (Estados e : values()) {
            POR_DESCRIPCION.put(e.descripcion, e);
        }
    }

    public final String descripcion;

    private Estados(String descripcion){
        this.descripcion = descripcion;
    };

    public static Estados valorDeLaDescripcion(String descripcion) {
        return POR_DESCRIPCION.get(descripcion);
    }
}