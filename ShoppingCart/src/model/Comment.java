package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the COMMENTS database table.
 * 
 */
@Entity
@Table(name="COMMENTS")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COM_ID")
	private long comId;

	@Temporal(TemporalType.DATE)
	private Date cdate;

	private String comments;

	private BigDecimal rating;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="P_ID")
	private Product product;

	//bi-directional many-to-one association to Shopper
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Shopper shopper;

	public Comment() {
	}

	public long getComId() {
		return this.comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
	}

	public Date getCdate() {
		return this.cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
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