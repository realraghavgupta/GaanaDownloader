package in.pathri.gaana.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVExporterImport implements ExporterImportInterface {
	static final Logger logger = LogManager.getLogger();
	private CSVWriter writer;
	private CSVReader reader;

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
		if (null != writer) {
			logger.trace("Into addColumnHeader null check");
			writer.writeNext(headers);
		}
		logger.traceExit();
	}

	@Override
	public void addRecordValues(String[] values) {
		logger.entry(String.join(";", values));
		if (null != writer) {
			logger.trace("Into addRecordValues null check");
			writer.writeNext(values);
		}
		logger.traceExit();
	}

	@Override
	public void addAllValues(List<String[]> values) {
		logger.entry(values);
		if (null != writer) {
			logger.trace("Into addAllValues null check");
			writer.writeAll(values);
		}
		logger.traceExit();
	}

	@Override
	public void doExport() {
		logger.traceEntry();
		if (null != writer) {
			logger.trace("Into doExport null check");
			try {
				writer.close();
			} catch (IOException e) {
				logger.catching(e);
			}
		}
		logger.traceExit();
	}

	@Override
	public void initImporter(String filePath) {
		try {
			reader = new CSVReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			logger.catching(e);
		}
	}

	@Override
	public String[] getColumnHeader() {
		try {
			return reader.readNext();
		} catch (IOException e) {
			logger.catching(e);
		}
		return null;
	}

	@Override
	public String[] getNextRecord() {
		try {
			return reader.readNext();
		} catch (IOException e) {
			logger.catching(e);
		}
		return null;
	}

	@Override
	public List<String[]> getAllValues() {
		try {
			List<String[]> allValues = reader.readAll();
			allValues.remove(0);
			return allValues;
		} catch (IOException e) {
			logger.catching(e);
		}
		return null;
	}

	@Override
	public List<String[]> getAll() {
		try {
			return reader.readAll();
		} catch (IOException e) {
			logger.catching(e);
		}
		return null;
	}

}
