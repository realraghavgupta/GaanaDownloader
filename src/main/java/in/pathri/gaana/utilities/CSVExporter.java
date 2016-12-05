package in.pathri.gaana.utilities;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVWriter;

public class CSVExporter implements ExporterInterface{
	static final Logger logger = LogManager.getLogger();
	private CSVWriter writer;

@Override
public void initExporter(String filePath) {
	logger.entry(filePath);
	try {
		writer = new CSVWriter(new FileWriter(filePath));	
	} catch (Exception e) {
		logger.catching(e);
	}
	logger.traceExit();
}

@Override
public void addColumnHeader(String[] headers) {
	logger.entry(String.join(";", headers));
if(null != writer){
	logger.trace("Into addColumnHeader null check");
	writer.writeNext(headers);
}
logger.traceExit();
	
}
@Override
public void addRecordValues(String[] values) {
	logger.entry(String.join(";", values));
	if(null != writer){
		logger.trace("Into addRecordValues null check");
		writer.writeNext(values);
	}
	logger.traceExit();
	
}
@Override
public void doExport() {
	logger.traceEntry();
if(null != writer){
	logger.trace("Into doExport null check");
	try {
		writer.close();
	} catch (IOException e) {
		logger.catching(e);
	
	}
}
logger.traceExit();
	
}

}
