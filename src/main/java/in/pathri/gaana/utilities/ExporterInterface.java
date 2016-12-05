package in.pathri.gaana.utilities;

public interface ExporterInterface {
	public void initExporter(String filePath);
	public void addColumnHeader(String[] headers);
	public void addRecordValues(String[] values);
	public void doExport();

}
