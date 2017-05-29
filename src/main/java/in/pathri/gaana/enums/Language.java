package in.pathri.gaana.enums;

public enum Language {
	HINDI("hindi", 1),
	ENGLISH("english", 2),
	TAMIL("tamil", 3),
	TELUGU("telugu", 4),
	MARATHI("marathi", 5),
	PUNJABI("punjabi", 6),
	GUJARATI("gujarati", 7),
	BENGALI("bengali", 8),
	KANNADA("kannada", 9),
	MALAYALAM("malayalam", 10),
	BHOJPURI("bhojpuri", 11),
	ODIA("odia", 12);

	private String value;
	private int option;

	Language(final String value, final int option) {
		this.value = value;
		this.option = option;
	}

	public String getValue() {
		return value;
	}

	public int getOption() {
		return option;
	}

	public Language getDefault() {
		return TAMIL;
	}

	public static Language getEnum(int option) {
		for (Language v : values())
			if (v.getOption() == option)
				return v;
		return null;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
