package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the ORDERHIST database table.
 * 
 */
@Entity
@NamedQuery(name="Orderhist.findAll", query="SELECT o FROM Orderhist o")
public class Orderhist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="O_ID")
	private long oId;

	private BigDecimal amount;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="orderhist",cascade = {CascadeType.PERSIST},fetch=FetchType.LAZY)
	//private List someOrAllHeaderFields = new ArrayList<>();
	private List<Cart> carts;

	//bi-directional many-to-one association to Shopper
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Shopper shopper;

	public Orderhist() {
	}

	public long getOId() {
		return this.oId;
	}

	public void setOId(long oId) {
		this.oId = oId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setOrderhist(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setOrderhist(null);

		return cart;
	}

	public Shopper getShopper() {
		return this.shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

}