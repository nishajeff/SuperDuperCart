package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ADMINCART database table.
 * 
 */
@Entity
@NamedQuery(name="Admincart.findAll", query="SELECT a FROM Admincart a")
public class Admincart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long aid;

	private BigDecimal pid;

	private BigDecimal qty;

	private BigDecimal total;

	//bi-directional many-to-one association to Shopper
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Shopper shopper;

	public Admincart() {
	}

	public long getAid() {
		return this.aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public BigDecimal getPid() {
		return this.pid;
	}

	public void setPid(BigDecimal pid) {
		this.pid = pid;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Shopper getShopper() {
		return this.shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

}