package in.pathri.gaana.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.enums.ExportType;
import in.pathri.gaana.utilities.CSVExporterImport;
import in.pathri.gaana.utilities.ExporterImportInterface;
import in.pathri.gaana.utilities.UserPromptStubber;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class SearchExporter {
	static final Logger logger = LogManager.getLogger();
	private static ExporterImportInterface exporter;

	public static void init(ExportType type, String filePath) {
		logger.entry(type, filePath);
		switch (type) {
		case CSV:
			exporter = new CSVExporterImport();
			break;
		default:
			break;
		}
		exporter.initExporter(filePath);
		exporter.addColumnHeader(Global.EXPORT_COLUMNS);
	}

	public static void appendAlbumResults(JSONArray results) {
		JSONObject record;
		String[] values;
		for (Object jsonObject : results) {
			record = (JSONObject) jsonObject;
			values = new String[4];
			values[0] = record.getAsString("album_id");
			values[1] = record.getAsString("trackids");
			values[2] = record.getAsString("title");
			if(UserPromptStubber.isStubbed() && UserPromptStubber.stubSearchSelection()){
				values[3] = "yes";
			}
			exporter.addRecordValues(values);
		}
	}

	public static void appendTrackResults(JSONArray results) {
		JSONObject record;
		String[] values;
		for (Object jsonObject : results) {
			record = (JSONObject) jsonObject;
			values = new String[4];
			values[0] = record.getAsString("album_id");
			values[1] = record.getAsString("track_id");
			values[2] = record.getAsString("album_title");
			if(UserPromptStubber.isStubbed() && UserPromptStubber.stubSearchSelection()){
				values[3] = "yes";
			}			
			exporter.addRecordValues(values);
		}
	}

	public static void end() {
		exporter.doExport();
		logger.traceExit();
	}
}
