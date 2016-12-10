package in.pathri.gaana.constants;

public enum UsageOptions {
	NEW_SEARCH(Global.USAGE_OPTION_NEW_SEARCH, 1), 
	GENERATE_DOWNLOAD_LINKS(Global.USAGE_OPTION_GENERATE_DOWNLOAD_LINKS, 2),
	DOWNLOAD_FROM_GENERATED_LINKS(Global.USAGE_OPTION_DOWNLOAD_FROM_GENERATED_LINKS, 3),
	COVERT_DOWNLOADED_SONGS(Global.USAGE_OPTION_COVERT_DOWNLOADED_SONGS, 4);
	
	private String value;
	private int option;

	UsageOptions(final String value, final int option) {
		this.value = value;
		this.option = option;
	}

	public int getOption() {
		return option;
	}

	public String getValue() {
		return value;
	}

	public static UsageOptions getEnum(int value) {
		for (UsageOptions v : values())
			if (v.getOption() == value)
				return v;
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return this.getValue();
	}

}
