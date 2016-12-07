package in.pathri.gaana.utilities;

import java.util.HashMap;
import java.util.Map;

import in.pathri.gaana.constants.DownloadParam.ConnectionType;
import in.pathri.gaana.constants.DownloadParam.DeliveryType;
import in.pathri.gaana.constants.DownloadParam.Quality;

public class DownloadParamHelper {
	private static String token;
	private ConnectionType connectionType;
	private Quality quality;
	private DeliveryType deliveryType;
	private String track_id;
	private String hashCode;
	private Map<String, String> params = new HashMap<String, String>();

	public static void setUserToken(String token) {
		DownloadParamHelper.token = token;
	}

	public void initParams(ConnectionType connectionType, Quality quality, DeliveryType deliveryType) {
		this.connectionType = connectionType;
		this.quality = quality;
		this.deliveryType = deliveryType;
	}

	public void setTrackId(String track_id) {
		this.track_id = track_id;
		this.hashCode = GaanaUtilities.getHashCode(track_id, token);
		if (params.isEmpty()) {
			generateParams();
		} else {
			updateParams();
		}
	}

	public Map<String, String> getParams() {
		if (params.isEmpty()) {
			generateParams();
		}
		return params;
	}

	private void generateParams() {
		if (null != track_id && !track_id.isEmpty())
			params.put("track_id", track_id);
		if (null != hashCode && !hashCode.isEmpty())
			params.put("hashcode", hashCode);
		params.put("connection_type", connectionType.getName());
		params.put("token", token);
		params.put("quality", quality.getName());
		params.put("delivery_type", deliveryType.getName());
	}

	private void updateParams() {
		params.put("track_id", track_id);
		params.put("hashcode", hashCode);
	}

}
