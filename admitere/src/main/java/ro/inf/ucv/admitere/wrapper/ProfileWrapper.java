package ro.inf.ucv.admitere.wrapper;

import java.util.List;

import ro.inf.ucv.admitere.entity.Citizenship;
import ro.inf.ucv.admitere.entity.Ethnicity;
import ro.inf.ucv.admitere.entity.FamilySituation;
import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.entity.MaritalStatus;
import ro.inf.ucv.admitere.entity.MedicalCondition;
import ro.inf.ucv.admitere.entity.Religion;
import ro.inf.ucv.admitere.entity.SocialSituation;

public class ProfileWrapper {

	private List<Gender> genders;

	private List<Religion> religions;

	private List<Citizenship> citizenships;

	private List<Ethnicity> ethnicities;

	private List<SocialSituation> socialSituations;

	private List<FamilySituation> familySituations;

	private List<MaritalStatus> maritalStatus;

	private List<MedicalCondition> medicalConditions;

	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public List<Religion> getReligions() {
		return religions;
	}

	public void setReligions(List<Religion> religions) {
		this.religions = religions;
	}

	public List<Citizenship> getCitizenships() {
		return citizenships;
	}

	public void setCitizenships(List<Citizenship> citizenships) {
		this.citizenships = citizenships;
	}

	public List<Ethnicity> getEthnicities() {
		return ethnicities;
	}

	public void setEthnicities(List<Ethnicity> ethnicities) {
		this.ethnicities = ethnicities;
	}

	public List<SocialSituation> getSocialSituations() {
		return socialSituations;
	}

	public void setSocialSituations(List<SocialSituation> socialSituations) {
		this.socialSituations = socialSituations;
	}

	public List<FamilySituation> getFamilySituations() {
		return familySituations;
	}

	public void setFamilySituations(List<FamilySituation> familySituations) {
		this.familySituations = familySituations;
	}

	public List<MaritalStatus> getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(List<MaritalStatus> maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public List<MedicalCondition> getMedicalConditions() {
		return medicalConditions;
	}

	public void setMedicalConditions(List<MedicalCondition> medicalConditions) {
		this.medicalConditions = medicalConditions;
	}

}
