package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity;

import jakarta.persistence.*;

@Entity //entidad
@Table(name="categorias")
public class Categoria {
    @Id
    //genera id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    //solo se pone  @Column(name = "") si el nombre es diferencte al de la tabla
    private String descripcion;
    private boolean estado;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}




