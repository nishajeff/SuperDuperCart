package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the STORECREDIT database table.
 * 
 */
@Entity
@NamedQuery(name="Storecredit.findAll", query="SELECT s FROM Storecredit s")
public class Storecredit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CR_ID")
	private long crId;

	private String status;

	@Column(name="USER_ID")
	private BigDecimal userId;

	public Storecredit() {
	}

	public long getCrId() {
		return this.crId;
	}

	public void setCrId(long crId) {
		this.crId = crId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}