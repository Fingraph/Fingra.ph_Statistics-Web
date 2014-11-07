/**
 * Copyright 2014 tgrape Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ph.fingra.statisticsweb.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ph.fingra.statisticsweb.common.util.DateTimeUtil;

public class ExcelExportServlet extends HttpServlet{

	private static final long serialVersionUID = -1364893065606590175L;
	public static String defaultUploadPath = "/upload/fileroot/";
	public static String uploadTempPath = "/Temp/";

	// constructor
    public ExcelExportServlet() {
        super();
    }

    /**
     * It calls doPost directly.
     *
     * @author minikim
     * @param request http servlet request.
     * @param response http servlet response.
     * @exception ServletException when it has servlet error.
     * @exception IOException when it has IO error.
     *
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    /**
     * It creates excel file and download it.
     *
     * @author minikim
     * @param request http servlet request.
     * @param response http servlet response.
     * @exception ServletException when it has servlet error.
     * @exception IOException when it has IO error.
     *
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] htmldata = request.getParameterValues("htmldata");
		String[] sheetName = request.getParameterValues("sheetname");

		String currentMenu = request.getParameter("currentMenu");
		String sectionMenu = request.getParameter("sectionMenu");
		String appkey =	request.getParameter("appkey");

		if (sheetName == null) {
			sheetName = new String[1];
			sheetName[0] = currentMenu;
		}

		// variable that create excel files
		WritableWorkbook workbook = null;

		// set filename.
		String filename = "Fingraph_" + appkey + "_" +sectionMenu + "_" +currentMenu + "_" + DateTimeUtil.getTodayFormatString("yyyyMMdd_HHmmss") + ".xls";

		try {
			// set filepath.
			workbook = Workbook.createWorkbook(new File(uploadTempPath + filename));

			for(int k=0; k<htmldata.length;k++){
			// create first excel's sheet.
			WritableSheet sheet = workbook.createSheet(sheetName[k], k);
			sheet = workbook.getSheet(k);
			// 엑셀 스타일 설정
			WritableFont TitleFont = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD, false);
			// 행과 열에 사용할 스타일 객체를 생성
			jxl.write.WritableCellFormat ColFormat        = new WritableCellFormat(TitleFont); // Column Style
			jxl.write.WritableCellFormat ColFormatTop     = new WritableCellFormat(TitleFont); // Column Style 2
			jxl.write.WritableCellFormat RowFormat        = new WritableCellFormat();  // Row Style
			jxl.write.WritableCellFormat RowFormatCenter  = new WritableCellFormat();  // Row Style Center
			//정수형 ( 1000단위 마다 , 찍기 X )
			//jxl.write.WritableCellFormat format_integer1 = new WritableCellFormat(NumberFormats.INTEGER);
			//정수형 ( 1000단위 마다 , 찍기 O )
			jxl.write.NumberFormat moneytype1 = new NumberFormat("###,##0");
			jxl.write.NumberFormat moneytype2 = new NumberFormat("###,##0.00");
			jxl.write.WritableCellFormat format_integer1 = new WritableCellFormat(moneytype1);
			jxl.write.WritableCellFormat format_integer2 = new WritableCellFormat(moneytype2);


			// 행과 열의 스타일 상세 설정 (Border,Align,Background...)
			ColFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			ColFormat.setAlignment(jxl.format.Alignment.CENTRE);
			ColFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			ColFormat.setBackground(jxl.format.Colour.ICE_BLUE);

			ColFormatTop.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			ColFormatTop.setAlignment(jxl.format.Alignment.CENTRE);
			ColFormatTop.setVerticalAlignment(VerticalAlignment.CENTRE);
			ColFormatTop.setBackground(jxl.format.Colour.ORANGE);

			RowFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			RowFormat.setAlignment(jxl.format.Alignment.LEFT);
			RowFormat.isNumber();

			format_integer1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			format_integer1.setAlignment(jxl.format.Alignment.RIGHT);
			format_integer2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			format_integer2.setAlignment(jxl.format.Alignment.RIGHT);

			RowFormatCenter.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			RowFormatCenter.setAlignment(jxl.format.Alignment.CENTRE);

			NumberFormat nf = new NumberFormat("#,###.00");
			WritableCellFormat numberFormat = new WritableCellFormat(nf);


/*
			jxl.write.Label label = new jxl.write.Label(0, 2, "Column 1",ColFormat);
			sheet.addCell(label);
			label = new jxl.write.Label(1, 2, "Column 2",ColFormat);
			sheet.addCell(label);
			label = new jxl.write.Label(2, 2, "Column 3",ColFormat);
			sheet.addCell(label);
			label = new jxl.write.Label(3, 2, "Column 4",ColFormat);
			sheet.addCell(label);
			label = new jxl.write.Label(4, 2, "Column 5",ColFormat);
			sheet.addCell(label);
			label = new jxl.write.Label(5, 2, "Column 6",ColFormat);
			sheet.addCell(label);
			label = new jxl.write.Label(6, 2, "Column 7",ColFormat);
			sheet.addCell(label);

			// 셀 넓이 적용 (행, 열)
			sheet.setColumnView(0, 40);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 40);
			sheet.setColumnView(3, 30);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 40);
			sheet.setColumnView(6, 15);

			sheet.setRowView(2, 500);

*/

			// get the data from html string.
			//System.out.println(URLDecoder.decode(htmldata[k], "UTF-8"));
			Document doc = Jsoup.parse(URLDecoder.decode(htmldata[k], "UTF-8"));
			Elements trs = doc.getElementsByTag("tr");
			jxl.write.Number num = null;
			int first = 1;
			int i = 0;
			int j = 0;

			for (Element tr : trs) {
				Elements tds = null;
				if (first == 1) {
					first = 0;
					tds = tr.getElementsByTag("th");
					if (tds != null) {
						for (Element td : tds) {
							sheet.addCell(new Label(j, i, td.text(),ColFormat));
							sheet.setColumnView(j, Integer.parseInt(td.attr("width"))/2);
							j++;
						}
					}
				} else {
					tds = tr.getElementsByTag("td");
					if (tds != null) {
						for (Element td : tds) {
							if("numFormat".equals(td.attr("class"))){
								num = new jxl.write.Number(j, i, Double.parseDouble(td.text()),format_integer1);
								sheet.addCell(num);
							}else if("doubleFormat".equals(td.attr("class"))){
								num = new jxl.write.Number(j, i, Double.parseDouble(td.text()),format_integer2);
								sheet.addCell(num);
							}else{
								sheet.addCell(new Label(j, i, td.text(),RowFormat));
							}

							j++;
						}
					}
				}


				i++;
				j=0;
			}
			}
			// write to excel file.
			workbook.write();

        } catch(Exception e) {
			e.printStackTrace();
    	} finally {
    		try{
    			if (workbook!=null) workbook.close();
    		}catch(Exception ignored){}
    	}

		// create file.
		File f = new File(uploadTempPath + filename);

		// set for download excel file.
		String contentType = request.getContentType();
		response.setContentType("x-msdownload");

	    if (contentType == null) {
	        if (request.getHeader("user-agent").indexOf("MSIE 5.5") != -1)
	            response.setContentType("doesn/matter;");
	        else
	            response.setContentType("application/octet-stream");
	    } else {
	        response.setContentType(contentType);
	    }

	    response.setHeader("Content-Transfer-Encoding:", "binary");
	    response.setHeader("Content-Disposition", "attachment;filename=" + filename + ";");
	    response.setHeader("Content-Length", "" + f.length());
	    response.setHeader("Pragma", "no-cache;");
	    response.setHeader("Expires", "-1;");

	    byte b[] = new byte[1024];
		BufferedInputStream fin = new BufferedInputStream(new FileInputStream(f));
		BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());

		try{
			int read = 0;
			while ((read = fin.read(b)) != -1)
			{
				outs.write(b,0,read);
			}

		}catch (Exception e){
		}finally{
			if (outs!=null) outs.close();
			if (fin!=null) fin.close();
		}

		try{
			if (f.exists()) f.delete();
		}catch(Exception e){}

	}
}
