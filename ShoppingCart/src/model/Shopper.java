package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SHOPPER database table.
 * 
 */
@Entity
@NamedQuery(name="Shopper.findAll", query="SELECT s FROM Shopper s")
public class Shopper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private long userId;

	private String address;

	@Column(name="CREDIT_NUM")
	private BigDecimal creditNum;

	private String name;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="shopper")
	private List<Cart> carts;

	public Shopper() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getCreditNum() {
		return this.creditNum;
	}

	public void setCreditNum(BigDecimal creditNum) {
		this.creditNum = creditNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setShopper(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setShopper(null);

		return cart;
	}

}