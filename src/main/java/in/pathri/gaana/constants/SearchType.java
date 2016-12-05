package in.pathri.gaana.constants;

public enum SearchType {
	ALL_ALBUMS("All Albums",1);
	private String value;
	private int option;
	
	SearchType(final String value, final int option) {
		this.value = value;
		this.option = option;
	}

	public String getValue() {
		return value;
	}
	
	public int getOption(){
		return option;
	}

	public SearchType getDefault(){
		return ALL_ALBUMS;
	}
	
	public static SearchType getEnum(int option){
	    for(SearchType v : values())
            if(v.getOption() == option) return v;
        return null;		
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
}
