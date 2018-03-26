package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "specialization_sample")
public class SpecializationSample implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@Min(1)
	private int percent;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SampleNomenclature sampleNomenclature;

	public SpecializationSample() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public SampleNomenclature getSampleNomenclature() {
		return sampleNomenclature;
	}

	public void setSampleNomenclature(SampleNomenclature sampleNomenclature) {
		this.sampleNomenclature = sampleNomenclature;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "SpecializationSample [id=" + id + ", creationDate=" + creationDate + ", percent=" + percent
				+ ", sampleNomenclature=" + sampleNomenclature + "]";
	}

}
