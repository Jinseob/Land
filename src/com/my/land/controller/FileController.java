package com.my.land.controller;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.my.land.dao.CmmnDao;
import com.my.land.model.FileVO;

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "fileUpload.do")
	public String fileUpload(@ModelAttribute("fileUpload")FileVO fileUpload, HttpServletRequest request) throws Exception{
		
		this.processOneSheet(fileUpload);
		
//		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)request;
//		Iterator<String> fileNames = null;
//		fileNames = mpRequest.getFileNames();
		
		
		
//		while(fileNames.hasNext()){
//			FileInputStream fis = new FileInputStream(fileUpload.getFileData().getInputStream());
			
//		POIFSFileSystem fileSystem = new POIFSFileSystem(fileUpload.getFileData().getInputStream());
		
//		OPCPackage pkg = OPCPackage.open(fileUpload.getFileData().getInputStream());
//		try{
//			XSSFReader r = new XSSFReader(pkg);
//			SharedStringsTable sst = r.getSharedStringsTable();
//			XmlReader parser = 
//			
//		}finally{
//			pkg.close();
//		}
//		SXSSFWorkbook wb = new SXSSFWorkbook(-1);
		
//		Workbook wb = null;
//		if(fileUpload.getFileData().getOriginalFilename().endsWith("xlsx")){
//			OPCPackage pkg = OPCPackage.open(fileUpload.getFileData().getInputStream());
//			XSSFReader xr = new XSSFReader(pkg);
//			wb = new XSSFWorkbook(pkg);
//		}else if(fileUpload.getFileData().getOriginalFilename().endsWith("xls")){
//			wb = new HSSFWorkbook(fileUpload.getFileData().getInputStream());
//		}else{
//			throw new Exception("Invalid file name, should be xls or xlsx");
//		}
		
//		try{
//			for(int i = 0; i < wb.getNumberOfSheets(); i++){
//				Sheet sheet = wb.getSheetAt(i);
//				int rows = sheet.getPhysicalNumberOfRows();
//				
//				for(int j = 0; j < rows; j++){
//					Row row = sheet.getRow(j);
//					if(row == null){
//						continue;
//					}
//					
//					for(int a = 0; a < row.getLastCellNum(); a++){
//						Cell cell = row.getCell(a);
//						String value;
//						
//						if(cell != null){
//							switch(cell.getCellTypeEnum()){
//							case FORMULA:
//								value = "FORMULA value=" + cell.getCellFormula();
//								break;
//							case NUMERIC:
//								value = "NUMERIC value=" + cell.getNumericCellValue();
//								break;
//							case STRING:
//								value = "STRING value=" + cell.getStringCellValue();
//								break;
//							case BLANK:
//								value = "<BLANK>";
//								break;
//							case BOOLEAN:
//								value = "BOOLEAN value=" + cell.getBooleanCellValue();
//								break;
//							case ERROR:
//								value = "ERROR value=" + cell.getErrorCellValue();
//								break;
//							default:
//								value = "UNKNOWN value of type " + cell.getCellTypeEnum();
//							}
//						}
//					}
//				}
//			}
//		}finally{
//			wb.close();
//		}
			
//		}
		
		
		return "main";
	}
	
	public void processOneSheet(FileVO fileUpload) throws Exception {
		OPCPackage pkg = OPCPackage.open(fileUpload.getFileData().getInputStream());
		try {
			XSSFReader r = new XSSFReader(pkg);
			SharedStringsTable sst = r.getSharedStringsTable();

			XMLReader parser = fetchSheetParser(sst);

			// process the first sheet
			InputStream sheet2 = r.getSheetsData().next();
			InputSource sheetSource = new InputSource(sheet2);
			parser.parse(sheetSource);
			sheet2.close();
		} finally {
			pkg.close();
		}
	}
	
	public void processAllSheets(FileVO fileUpload) throws Exception {
		OPCPackage pkg = OPCPackage.open(fileUpload.getFileData().getInputStream());
		try {
			XSSFReader r = new XSSFReader(pkg);
			SharedStringsTable sst = r.getSharedStringsTable();

			XMLReader parser = fetchSheetParser(sst);

			Iterator<InputStream> sheets = r.getSheetsData();
			while (sheets.hasNext()) {
				System.out.println("Processing new sheet:\n");
				InputStream sheet = sheets.next();
				InputSource sheetSource = new InputSource(sheet);
				parser.parse(sheetSource);
				sheet.close();
				System.out.println("");
			}
		} finally {
			pkg.close();
		}
	}
	
	public XMLReader fetchSheetParser(SharedStringsTable sst)throws SAXException{
		XMLReader parser = XMLReaderFactory.createXMLReader();
		ContentHandler handler = new SheetHandler(sst);
		parser.setContentHandler(handler);
		return parser;
	}
	
	private static class SheetHandler extends DefaultHandler{
		private final SharedStringsTable sst;
		private String lastContents;
		private boolean nextIsString;
		private boolean inlineStr;
		private final LruCache<Integer,String> lruCache = new LruCache<Integer,String>(50);

		private static class LruCache<A,B> extends LinkedHashMap<A, B> {
		    private final int maxEntries;

		    public LruCache(final int maxEntries) {
		        super(maxEntries + 1, 1.0f, true);
		        this.maxEntries = maxEntries;
		    }

		    @Override
		    protected boolean removeEldestEntry(final Map.Entry<A, B> eldest) {
		        return super.size() > maxEntries;
		    }
		}
		
		private SheetHandler(SharedStringsTable sst) {
			this.sst = sst;
		}
		
		@Override
        public void startElement(String uri, String localName, String name,
								 Attributes attributes) throws SAXException {
			// c => cell
			if(name.equals("c")) {
				// Print the cell reference
				System.out.print(attributes.getValue("r") + " - ");
				// Figure out if the value is an index in the SST
				String cellType = attributes.getValue("t");
				nextIsString = cellType != null && cellType.equals("s");
				inlineStr = cellType != null && cellType.equals("inlineStr");
			}
			// Clear contents cache
			lastContents = "";
		}

		@Override
        public void endElement(String uri, String localName, String name)
				throws SAXException {
			// Process the last contents as required.
			// Do now, as characters() may be called more than once
			if(nextIsString) {
				Integer idx = Integer.valueOf(lastContents);
				lastContents = lruCache.get(idx);
				if (lastContents == null && !lruCache.containsKey(idx)) {
				    lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
				    lruCache.put(idx, lastContents);
				}
				nextIsString = false;
			}

			// v => contents of a cell
			// Output after we've seen the string contents
			if(name.equals("v") || (inlineStr && name.equals("c"))) {
				System.out.println(lastContents);
			}
		}

		@Override
        public void characters(char[] ch, int start, int length) throws SAXException { // NOSONAR
			lastContents += new String(ch, start, length);
		}
	}
}
