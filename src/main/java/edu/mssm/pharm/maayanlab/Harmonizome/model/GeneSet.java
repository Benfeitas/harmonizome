package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;

@Entity
@Table(name = "gene_set")
public class GeneSet implements BioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/* From datasets
	 * ------------- */
	@Column(name = "name_from_dataset")
	private String nameFromDataset;
	
	@Column(name = "id_from_dataset")
	private String idFromDataset;
	
	@Column(name = "description_from_dataset")
	@Type(type = "text")
	private String descriptionFromDataset;

	@Column(name = "url_from_dataset", length = 2083)
	private String urlFromDataset;
	
	/* Back references
	 * --------------- */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_fk")
	private Attribute attribute;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dataset_fk")
	private Dataset dataset;
	
	@OneToMany(mappedBy = "geneSet")
	Set<Feature> features;

	public static final String ENDPOINT = "gene_set";

	public GeneSet() {
	}

	/* Getters & Setters 
	 * ----------------- */	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameFromDataset() {
		return nameFromDataset;
	}

	public void setNameFromDataset(String nameFromDataset) {
		this.nameFromDataset = nameFromDataset;
	}

	public String getIdFromDataset() {
		return idFromDataset;
	}

	public void setIdFromDataset(String idFromDataset) {
		this.idFromDataset = idFromDataset;
	}

	public String getDescriptionFromDataset() {
		return descriptionFromDataset;
	}

	public void setDescriptionFromDataset(String descriptionFromDataset) {
		this.descriptionFromDataset = descriptionFromDataset;
	}

	public String getUrlFromDataset() {
		return urlFromDataset;
	}

	public void setUrlFromDataset(String urlFromDataset) {
		this.urlFromDataset = urlFromDataset;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	/* Utility functions
	 * ----------------- */
	@Transient
	public String getKey() {
		return "name";
	}
	
	@Transient
	public String getValue() {
		return getNameFromDataset() + "/" + getDataset().getName();
	}
	
	@Transient
	public String getUrlEncodedValue() {
		String attribute = UrlCodec.encode(getNameFromDataset());
		String dataset = UrlCodec.encode(getDataset().getName());
		return attribute + "/" + dataset;
	}

	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}
}