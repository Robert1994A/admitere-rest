package ro.inf.ucv.admitere.wrapper;

import java.util.Collection;
import java.util.Set;

public class Statistics {

	private String name;

	private Set<String> labels;

	private Collection<Integer> values;

	public Statistics(String name, Set<String> set, Collection<Integer> collection) {
		super();
		this.name = name;
		this.labels = set;
		this.values = collection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getLabels() {
		return labels;
	}

	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}

	public Collection<Integer> getValues() {
		return values;
	}

	public void setValues(Collection<Integer> values) {
		this.values = values;
	}

}
