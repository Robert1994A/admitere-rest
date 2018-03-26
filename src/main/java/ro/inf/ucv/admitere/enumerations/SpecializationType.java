package ro.inf.ucv.admitere.enumerations;

import java.util.Random;

public enum SpecializationType {
	BUDGET, TAXA, ID, IFR;

	public static SpecializationType getRandomSpecializationType() {
		int number = new Random().nextInt(4);
		switch (number) {
		case 0:
			return BUDGET;

		case 1:
			return TAXA;

		case 2:
			return IFR;
		case 3:
			return ID;

		default:
			return BUDGET;
		}
	}
}