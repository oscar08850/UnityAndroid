package com.upc.eetac.dsa.androidapp;

import java.util.List;

import models.Credentials;
import models.CredentialsCompra;
import models.CredentialsPartida;
import models.Inventario;
import models.RecordUsuario;
import models.User;
import models.Object;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("auth/registrarUsuario")
    Call<User> addUser(@Body User user);

    @POST("auth/iniciarSesion")
    Call<Credentials> loginUser(@Body Credentials credentials);

    @GET ("user/obtenerUsuario/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET ("user/listaUsuarios")
    Call<User> getUserList();

    @DELETE("user/borrarUsuario/{username}/{password}")
    Call<User> deleteUser(@Path("username") String username, @Path("password") String password);

    @GET ("tienda/catalogo")
    Call<List<Object>> getObjetosTienda();
    @GET ("estadisticas/records")
    Call<List<RecordUsuario>> getRecordsTotales();

    @GET ("estadisticas/partidasUsuario/{username}")
    Call<List<RecordUsuario>> getRecordsIndividual(@Path("username") String username);

    @POST("tienda/comprarObjeto")
    Call<CredentialsCompra> addObjetoTienda(@Body CredentialsCompra credentials);

    @GET ("tienda/obtenerInventarioUsuario/{username}")
    Call<Inventario> getObjetosUser(@Path("username") String username);
    /*
    @PATCH("/dsaApp/{userID}/UpdateMonedas")
    Call<User> updateCoins(@Path("userID") String userID,@Body User user);

     */

    @POST("estadisticas/añadirPartida")
    Call<CredentialsPartida> addPartida(@Body CredentialsPartida credentials);

    @POST("tienda/consumirObjeto")
    Call<CredentialsCompra> consumir(@Body CredentialsCompra credentials);

    @PUT("")
    Call<Void> updateUser(@Body User usuario);

}
