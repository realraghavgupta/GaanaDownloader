package in.pathri.gaana.enums;

public enum Language {
	HINDI("hindi", 1),
	ENGLISH("english", 2),
	TAMIL("tamil", 3),
	TELUGU("telugu", 4),
	PUNJABI("punjabi", 5),
	MARATHI("marathi", 6),
	BENGALI("bengali", 7),
	BHOJPURI("bhojpuri", 8),
	KANNADA("kannada", 9),
	MALAYALAM("malayalam", 10),
	OTHERS("others", 11);
	
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
