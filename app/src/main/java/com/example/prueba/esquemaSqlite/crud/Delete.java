package com.example.prueba.esquemaSqlite.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.prueba.data.modelo.VentaDetalle;
import com.example.prueba.esquemaSqlite.ConexionSqliteHelper;
import com.example.prueba.esquemaSqlite.tablas.ClienteTabla;
import com.example.prueba.esquemaSqlite.tablas.ProductoTabla;
import com.example.prueba.esquemaSqlite.tablas.VentaCabeceraTabla;
import com.example.prueba.esquemaSqlite.tablas.VentaDetalleTabla;

import java.util.List;

public class Delete {
    public static void eliminar(Context context, int codigo, String tabla){
        ConexionSqliteHelper con = new ConexionSqliteHelper(context);
        SQLiteDatabase db = con.getWritableDatabase();
        switch (tabla){
            case ClienteTabla.TABLA:
                db.delete(ClienteTabla.TABLA, ClienteTabla.CLIE_ID + " = ?", new String[]{String.valueOf(codigo)});
                break;
            case ProductoTabla.TABLA:
                db.delete(ProductoTabla.TABLA, ProductoTabla.PROD_ID + " = ?", new String[]{String.valueOf(codigo)});
                break;
            case VentaCabeceraTabla.TABLA:
                db.delete(VentaCabeceraTabla.TABLA, VentaCabeceraTabla.VC_ID + " = ?", new String[]{String.valueOf(codigo)});
                break;
            case VentaDetalleTabla.TABLA:
                db.delete(VentaDetalleTabla.TABLA, VentaDetalleTabla.VD_ID + " = ?", new String[]{String.valueOf(codigo)});
                break;
        }
    }

    public static void eliminarVenta(Context context, List<VentaDetalle> lista, int vc_id){
        //eliminar la venta cabecera
        eliminar(context, vc_id, VentaCabeceraTabla.TABLA);
        for (VentaDetalle item : lista){
            eliminar(context, item.getVd_id(), VentaDetalleTabla.TABLA);
        }
    }
}
