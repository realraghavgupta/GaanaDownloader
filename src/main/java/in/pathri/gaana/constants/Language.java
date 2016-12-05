package in.pathri.gaana.constants;

import in.pathri.gaana.utilities.MiscUtilities;

public enum Language {
	TAMIL("tamil",1);
	private String value;
	private int option;
	
	Language(final String value, final int option) {
		this.value = value;
		this.option = option;
	}

	public String getValue() {
		return value;
	}
	
	public int getOption(){
		return option;
	}

	public Language getDefault(){
		return TAMIL;
	}
	
	public static Language getEnum(int option){
	    for(Language v : values())
            if(v.getOption() == option) return v;
        return null;		
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
}
