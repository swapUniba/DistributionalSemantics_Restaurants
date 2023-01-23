package wherexp;
import java.io.Serializable;


public class Locale implements Serializable {


    private int id;
    private String url, nome, indirizzo, telefono, categoria;

    public Locale() {

    }

    public Locale(int id, String url, String nome, String indirizzo, String telefono) {
        this.id = id;
        this.url = url;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
