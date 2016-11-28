package in.pathri.gaana.constants;

public enum Language {
	TAMIL("tamil");
	private String value;

	Language(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
