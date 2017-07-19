package com.my.land.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//	private static Map<String, String> excelList;
//	private static String[][] excelList = null;
	private static Map<String, String> excelList = new HashMap<String, String>();
	private static String column = "";
	private static boolean columnChecked = false;
	private static int row;

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "fileUpload.do")
	public String fileUpload(@ModelAttribute("fileUpload")FileVO fileUpload, HttpServletRequest request) throws Exception{
		
		this.processOneSheet(fileUpload);
		System.out.println("Column : " + column + " , Row : " + row);
		for(int i = 2 ; i <= row ; i++){	// 1 은 Head
			String tempColumn[] = column.split(";");
			for(int j = 0; j < tempColumn.length; j++){
				System.out.print(tempColumn[j] + i + " : " + excelList.get(tempColumn[j] + i) + " / ");
			}
			System.out.println();
		}
		return "main";
	}
	
	// 문자 제외. 숫자만 남기기.
	public static String getOnlyDigit1(String str){
		String ret = "";
		if(str != null && str.length() != 0){
			String tmpStr = str;
			StringBuffer sb = new StringBuffer();
			if(tmpStr.length() != 0){
				Pattern p = Pattern.compile("[^\\d]");
				Matcher m = p.matcher(str);
				while(m.find()){
					m.appendReplacement(sb, "");
				}
				m.appendTail(sb);
			}else{
				sb.append("");
			}
			ret = sb.toString();
		}
		return ret;
	}
	// 여기까지
	
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
		private String cellField;

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
				cellField = attributes.getValue("r");
				excelList.put(cellField, "");
				
				// 헤드가 사용한 column 지정 로직.
				String[] tempChar = cellField.split("[0-9]");		// split 숫자 하나씩 배열로 저장. column
				String tempColumn = "";
				if(!columnChecked){
					for(int i = 0 ; i < tempChar.length; i++){
						tempColumn += tempChar[i];
					}
					if(column.indexOf(tempColumn) < 0){
						if(column.equals("")){
							column += tempColumn;
						}else{
							column += ";" + tempColumn;
						}
					}else{
						columnChecked = true;
					}
				}
				// 헤드가 사용한 column 지정 로직.

				// 마지막 row 지정 로직.
				String[] tempNum = cellField.split("[a-zA-Z]");		// split 문자 하나씩 배열로 저장. row
				String tempRow = "";
				if(cellField.indexOf(row) < 0){
					for(int i = 0 ; i < tempNum.length; i++){
						tempRow += tempNum[i];
					}
					row = Integer.parseInt(tempRow);
				}
				// 마지막 row 지정 로직.
				
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
				excelList.put(cellField, lastContents);
				cellField = "";
			}
		}

		@Override
        public void characters(char[] ch, int start, int length) throws SAXException { // NOSONAR
			lastContents += new String(ch, start, length);
		}
	}
}
