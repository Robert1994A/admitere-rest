package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ro.inf.ucv.admitere.enumerations.NomenclatureType;

@Entity
@Table(name = "sample_nomenclature")
public class SampleNomenclature implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	@Column(name = "creation_date")
	private Date creationDate = new Date();

	@NotNull
	private NomenclatureType nomenclatureType;

	public SampleNomenclature() {
	}

	public SampleNomenclature(String name, NomenclatureType nomenclatureType) {
		this.name = name;
		this.nomenclatureType = nomenclatureType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public NomenclatureType getNomenclatureType() {
		return nomenclatureType;
	}

	public void setNomenclatureType(NomenclatureType nomenclatureType) {
		this.nomenclatureType = nomenclatureType;
	}

	@Override
	public String toString() {
		return "SampleNomenclature [id=" + id + ", name=" + name + ", creationDate=" + creationDate
				+ ", nomenclatureType=" + nomenclatureType + "]";
	}

}
