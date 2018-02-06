package ro.inf.ucv.admitere.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table
@Entity(name = "sample_result")
public class SampleResult {

	@Id
	@GeneratedValue
	private int id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private SampleNomenclature sampleNomenclature;

	@NotBlank
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SampleNomenclature getSampleNomenclature() {
		return sampleNomenclature;
	}

	public void setSampleNomenclature(SampleNomenclature sampleNomenclature) {
		this.sampleNomenclature = sampleNomenclature;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
