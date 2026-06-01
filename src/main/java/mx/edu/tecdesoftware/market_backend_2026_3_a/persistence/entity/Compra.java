package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name="compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //llave primaria
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column (name= "id_cliente")
    private String idCliente;

    private LocalDateTime fecha;

    @Column (name = "medio_pago")
    private String medioPago;
    private String comentario;
    private String estado;


    public String getEstado() {
        return estado;
    }
    //  Relacion con cliente: muchas compras para un cliente
    @ManyToOne
    @JoinColumn(name="id_cliente", insertable = false, updatable = false) //llave foranea
    private Cliente cliente;

    //una compra tiene muchos productos
    @OneToMany(mappedBy = "id_compra")
    private List<CompraProducto> producto;


    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }
}
