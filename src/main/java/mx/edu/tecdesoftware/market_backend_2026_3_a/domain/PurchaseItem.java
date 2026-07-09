package mx.edu.tecdesoftware.market_backend_2026_3_a.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.CompraProductoPK;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;

public class PurchaseItem {
    private int productId;
    private Integer amount;
    private Double total;
    private Boolean active;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
