package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CART database table.
 * 
 */
@Entity
@NamedQuery(name="Cart.findAll", query="SELECT c FROM Cart c")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CART_ID")
	private long cartId;

	private BigDecimal qty;

	private String status;

	private BigDecimal total;

	//bi-directional many-to-one association to Orderhist
	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="ORDER_ID")
	private Orderhist orderhist;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PID")
	private Product product;

	//bi-directional many-to-one association to Shopper
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Shopper shopper;

	public Cart() {
	}

	public long getCartId() {
		return this.cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Orderhist getOrderhist() {
		return this.orderhist;
	}

	public void setOrderhist(Orderhist orderhist) {
		this.orderhist = orderhist;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shopper getShopper() {
		return this.shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

}