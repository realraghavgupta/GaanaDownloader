package in.pathri.gaana.utilities;

import java.util.List;

public interface ExporterImportInterface {
	public void initExporter(String filePath);
	public void addColumnHeader(String[] headers);
	public void addRecordValues(String[] values);
	public void addAllValues(List<String[]> values);
	public void doExport();
	
	public void initImporter(String filePath);
	public String[] getColumnHeader();
	public String[] getNextRecord();
	public List<String[]> getAllValues();
	public List<String[]> getAll();
	

}
