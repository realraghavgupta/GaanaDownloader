package in.pathri.gaana.constants;

import in.pathri.gaana.utilities.MiscUtilities;

public enum NewSearchPromt {
	NEW_SEARCH(Global.DO_NEW_SEARCH_PROMPT_NEW_SEARCH, 1), 
	DOWNLOAD(Global.DO_NEW_SEARCH_PROMPT_DOWNLOAD, 2);
	
	private String name;
	private int value;

	NewSearchPromt(final String value, final int option) {
		this.name = value;
		this.value = option;
	}

	public int getOption() {
		return value;
	}

	public String getValue() {
		return name;
	}

	public static NewSearchPromt getEnum(String value) {
		for (NewSearchPromt v : values())
			if (v.getOption() == MiscUtilities.parseInt(value))
				return v;
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return this.getValue();
	}

}
