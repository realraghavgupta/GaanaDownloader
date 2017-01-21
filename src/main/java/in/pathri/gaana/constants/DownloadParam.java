package in.pathri.gaana.constants;

import in.pathri.gaana.enums.SearchType;

public final class DownloadParam {
	private static Quality quality;
	
	public static void setQuality(Quality quality){
		DownloadParam.quality = quality;
	}
	
	public static Quality getQuality(){
		return quality;
	}	
	
	public enum ConnectionType {
		WIFI("wifi");
		private String name;

		ConnectionType(final String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

	}

	public enum Quality {
		EXTREME("extreme",1),
		HIGH("high",2);
		private String value;
		private int option;

		Quality(final String value, final int option) {
			this.value = value;
			this.option = option;
		}

		public String getValue() {
			return value;
		}

		public int getOption() {
			return option;
		}

		public Quality getDefault() {
			return HIGH;
		}

		public static Quality getEnum(int option) {
			for (Quality v : values())
				if (v.getOption() == option)
					return v;
			return null;
		}

		@Override
		public String toString() {
			return this.getValue();
		}
	}

	public enum DeliveryType {
		DOWNLOAD("download");
		private String name;

		DeliveryType(final String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

}
