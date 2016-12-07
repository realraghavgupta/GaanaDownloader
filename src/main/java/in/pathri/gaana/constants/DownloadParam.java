package in.pathri.gaana.constants;

public final class DownloadParam {

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
		HIGH("high");
		private String name;

		Quality(final String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
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
