package in.pathri.gaana.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.ExportType;
import in.pathri.gaana.constants.Global;
import in.pathri.gaana.dao.SearchResults;
import in.pathri.gaana.utilities.CSVExporterImport;
import in.pathri.gaana.utilities.ExporterImportInterface;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class SearchExporter {
	static final Logger logger = LogManager.getLogger();
	private static ExporterImportInterface exporter;
public static void exportSearchResults(ExportType type, String filePath){
	logger.entry(type,filePath);
	JSONObject record;
	String[] values;
	switch (type) {
	case CSV:
		exporter = new CSVExporterImport();
		break;
	default:
		break;
	}
	exporter.initExporter(filePath);
	exporter.addColumnHeader(Global.EXPORT_COLUMNS);
	JSONArray results = SearchResults.getResultArray();
	for (Object jsonObject : results) {
		record = (JSONObject)jsonObject;
		values = new String[3];
		values[0] = record.getAsString("album_id");
		values[1] = record.getAsString("trackids");
		values[2] = record.getAsString("title");
		exporter.addRecordValues(values);
	}
	exporter.doExport();
	logger.traceExit();
}
}
