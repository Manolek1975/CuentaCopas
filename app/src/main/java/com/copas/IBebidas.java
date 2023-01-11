package com.copas;

import android.content.Context;

import java.util.List;

public interface IBebidas {

    void LoadBebidasDB(Context context);

    void createBebida(Context context, Bebidas bebida);

    void updateBebida(Context context, Bebidas bebida);

    void deleteBebida(Context context, int id);

    List<Bebidas> readBebidas(Context context);

    Bebidas getBebida(Context context, int id);


}
