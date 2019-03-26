package ru.job4j.samples;

public class Driver {
	private char license = 'C';

	public void passExamOn(char category) {
		this.license = category;
	}

	public boolean hasLicense() {
		return this.license == 'A'||this.license == 'B'||this.license == 'C';
	}

	public boolean canDrive(char category) {
		return this.license == category;
	}
}