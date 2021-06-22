package models;

public class Object {

    String nombre;
    int coste;
    String avatar;
    String descripcion;



    public Object() {
    }

    public Object(String nombre, int coste) {
        this.nombre = nombre;
        this.coste = coste;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString (){
        return this.avatar + this.nombre;
    }

}
