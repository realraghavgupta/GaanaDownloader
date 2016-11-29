package in.pathri.gaana.constants;

import in.pathri.gaana.utilities.MiscUtilities;

public enum NewSearchPromt {
	NEW_SEARCH(Global.DO_NEW_SEARCH_PROMPT_NEW_SEARCH,1),
	DOWNLOAD(Global.DO_NEW_SEARCH_PROMPT_DOWNLOAD,2);
	private String name;
	private int value;
	

	NewSearchPromt(final String name, final int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public String getName(){
		return name;
	}

	public static NewSearchPromt getEnum(String value){
	    for(NewSearchPromt v : values())
            if(v.getValue() == MiscUtilities.parseInt(value)) return v;
        throw new IllegalArgumentException();		
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
}
