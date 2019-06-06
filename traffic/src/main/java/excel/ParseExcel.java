package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.cyit.traffic.service.UploadFileService;
import cn.cyit.traffic.util.SpringUtil;

/**
 * 解析excel 工具类
 * 
 * @author PCCW
 * 
 */
public class ParseExcel {

	private UploadFileService attachmentService;
	
	private InputStream fis;
	private Workbook workBook;
	private Sheet sheet;
	private ExcelConfig parseXmlUtil;
	private StringBuffer errorString = new StringBuffer();;
	private Map curEntity;
	/** 当前实体类的code* */
	private String curEntityCode;
	/** 表头map对象：key:entityCode, value:headMap(index,headTitle)* */
	private Map curEntityHeadMap;

	/** 字段的必填：key:entityCode+headTitle, value:true(必填),false(不必填)* */
	private Map curEntityColRequired;

	/** 存放每一行的数据* */
	private Map sheetDatas = new HashMap();
	private int datacount = 0;

	public void inportFiles(File excelFile, File xmlFile) throws Exception {
		if (excelFile == null) {
			throw new FileNotFoundException();
		}
		fis = new FileInputStream(excelFile);
		try{
		workBook = new HSSFWorkbook(fis);
		}catch(Exception e){
		workBook = new XSSFWorkbook(fis);
		}
		parseXmlUtil = new ExcelConfig(xmlFile);

	}

	public void inportFiles(InputStream fileinput, String xmlContent)
			throws Exception {
		if (fileinput == null) {
			throw new FileNotFoundException();
		}
		fis = fileinput;
		try{
			workBook = new HSSFWorkbook(fis);
			}catch(Exception e){
			workBook = new XSSFWorkbook(fis);
			}
		parseXmlUtil = new ExcelConfig(xmlContent);
	}

	/** 开始从excel读取数据* */
	public boolean startParse() {
		for (String sheetName : (Set<String>) parseXmlUtil.getEntityMap()
				.keySet()) {
			sheet = workBook.getSheet(sheetName);
			if (null == sheet) {
				errorString.append("excel未找到sheet " + sheetName);
				return false;
			}
			readSheetData(sheet, sheetName);
		}
		if (this.getErrorString().length() == 0) {// 如果没有任何错误，就保存
			System.out.println("Excel 数据没有问题！");
			return true;
		} else {
			// 清理所有的缓存clearMap();现在暂时未清理
			System.out.println(errorString.toString());
			return false;
		}

	}

	private void readSheetData(Sheet sheet, String sheetName) {

		int rowNumbers = sheet.getPhysicalNumberOfRows();
		curEntity = (Map) parseXmlUtil.getEntityMap().get(sheetName);
		curEntityCode = (String) curEntity.get("code");
		if (rowNumbers == 0) {
			System.out.println("================excel中数据为空！");
			errorString.append(ExcelConstans.ERROR_EXCEL_NULL);
		}
		List colList = (List) parseXmlUtil.getColumnListMap().get(sheetName);
		int xmlRowNum = colList.size();
		Row excelRow = sheet.getRow(0);
		int excelFirstRow = excelRow.getFirstCellNum();
		int excelLastRow = excelRow.getLastCellNum();
		if (xmlRowNum > (excelLastRow - excelFirstRow)) {
			System.out.println("==================xml列数大于excel列数，请检查");
			errorString.append(ExcelConstans.ERROR_EXCEL_COLUMN_NOT_EQUAL);
		}
		readSheetHeadData(sheet);
		readSheetColumnData(sheet, sheetName);

	}

	private void readSheetHeadData(Sheet sheet) {

		Map headMap = new HashMap();
		curEntityHeadMap = new HashMap();
		curEntityColRequired = new HashMap();
		Row excelheadRow = sheet.getRow(0);
		int excelLastRow = excelheadRow.getLastCellNum();
		String headTitle = "";
		for (int i = 0; i < excelLastRow; i++) {
			Cell cell = excelheadRow.getCell(i);
			headTitle = ParseExcel.getStringCellValue(cell).trim();
			if (headTitle.endsWith("*")) {
				curEntityColRequired.put(this.curEntityCode + "_" + headTitle,
						true);
			} else {
				curEntityColRequired.put(this.curEntityCode + "_" + headTitle,
						false);
			}
			headMap.put(i, headTitle);
		}
		curEntityHeadMap.put(this.curEntityCode, headMap);
	}

	private void readSheetColumnData(Sheet sheet, String entityName) {
		int headstart = (Integer) curEntity.get("head");
		int datastart = (Integer) curEntity.get("data");
		Row excelheadRow = sheet.getRow(headstart);
		int excelLastcell = excelheadRow.getLastCellNum(); // excel总列数
		int excelRowNum = sheet.getLastRowNum(); // excel总行数
		Map headMap = (Map) curEntityHeadMap.get(curEntityCode);
		Map colMap = parseXmlUtil.getColumnMap();
		List listDatas = new ArrayList();
		for (int i = datastart; i < excelRowNum + 1; i++) {// 行循环
			Row columnRow = sheet.getRow(i);
			if (columnRow != null) {
				Map curRowCellMap = new LinkedHashMap();
				for (int j = 0; j < excelLastcell; j++) { // 列循环
					int cout = headMap.get(j).toString().indexOf("*");
					String headTitle = "";
					if (cout == -1) {
						headTitle = headMap.get(j).toString();
					} else {
						headTitle = headMap.get(j).toString()
								.substring(0, cout);
					}
					Map curColMap = (Map) colMap.get(entityName + "_"
							+ headTitle);
					if (null != curColMap) {
						String curColCode = (String) curColMap.get("code");
						String curColType = (String) curColMap.get("type");
						Cell colCell = columnRow.getCell(j);
						String value = ParseExcel.getStringCellValue(colCell);
						if (value != null&&value.length()>0) {
							value = value.trim();
							String xmlColType = (String) curColMap.get("type");
							if (xmlColType.equals("int")) {
								//excel可能会带小数点过来
								if(value.contains(".")){
									int index = value.indexOf(".");
									value=value.substring(0, index);
								}
								int intVal = Integer.valueOf(value);
								curRowCellMap.put(curColCode, intVal); // 将这一行的数据以code-value的形式存入map
							} else if (xmlColType.equals("double")) {
								double douVal = Double.parseDouble(value);
								curRowCellMap.put(curColCode, douVal);
							} else {
								if(value.contains(".0")){
									int index = value.indexOf(".0");
									value=value.substring(0, index);
								}
								curRowCellMap.put(curColCode, value);
							}
						}else {
							curRowCellMap.put(curColCode, value);
						}
					
						/** 验证cell数据* */
						validateCellData(i + 1, j + 1, colCell, entityName,
								headTitle, curColType);
					}
				}
				listDatas.add(curRowCellMap);
			}
		}
		sheetDatas.put(curEntity.get("name"), listDatas);
	}

	public void datasToDB(String lsh) {
		String sql = null;
		String vsql = null;
		List<String> keylist = new ArrayList();
		try {
			//conn = dao.getDataSource().getConnection();
			for (Map.Entry entry : (Set<Map.Entry>) sheetDatas.entrySet()) {
				String shetname = (String) entry.getKey();
				Map ent = (Map) parseXmlUtil.getEntityMap().get(shetname);
				String tablecode = (String) ent.get("code");
				String drlsname = (String) ent.get("drlsname");
				boolean needls = null != drlsname && null != lsh&& drlsname.length() > 0 
						&& lsh.length() > 0;
				List<Map> listdatas = (List) entry.getValue();
				datacount += listdatas.size();
				
				//组装SQL
				//sql = "insert into " + tablecode + "( id,";
				sql = "insert into " + tablecode + "(";
				vsql = "?,";
				Map row = listdatas.get(0);
				if (null != row) {
					Iterator keyit = row.keySet().iterator();
					while (keyit.hasNext()) {
						String key = (String) keyit.next();
						sql += key + ",";
						vsql+="?,";
						keylist.add(key);
					}
					if (vsql.length() > 1) {
						if (needls) {
							sql += drlsname;
							vsql+="?";
						} else {
							sql = sql.substring(0, sql.length() - 1);
							vsql = vsql.substring(0, vsql.length() - 1);
						}
						//sql = sql + ") values ("+vsql+")";
						sql = sql + ") values (";
						
						Map<String, Object> ResultMap = new HashMap<String, Object>();
						//塞入value()值
						int ss = 0;
						for (Map arow : listdatas) {
							if(null ==arow.get(keylist.get(0))){
								break;
							}
							//String valueSql = "member.NEXTVAL,";
							//String valueSql = "1,";
							String valueSql = "";
							int i = 0;
							for (; i < keylist.size(); i++) {
								Object obj = arow.get(keylist.get(i));
								if(null ==obj){
									break;
								}else{
									valueSql += "'"+obj.toString() + "',";
								}
							}
							if (needls) {
								valueSql += "'"+lsh+"'";
							}else{
								valueSql = valueSql.substring(0, valueSql.length()-1);
							}
							String reSql=sql + valueSql + ")";
							
							ResultMap.put(String.valueOf(ss), reSql);
							ss++;
						}
						this.insertSql(ResultMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 创建JDBC链接批量插入
	 */
	public void insertSql(Map<String, Object> map){
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		
		  try
		    {
				//ResourceBundle bundle = ResourceBundle.getBundle("properties.jdbc.properties");			  
			  //标准jdbc连数据库语句
		        //Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
		        Class.forName("com.mysql.jdbc.Driver");// 加载Oracle驱动程序
		        System.out.println("开始尝试连接数据库！");
		        //String url = "jdbc:oracle:" + "thin:@119.10.55.181:1521:jdks";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
		        /*
		        String url=bundle.getString("jdbc.url");
		        String user =bundle.getString("jdbc.username");// 用户名,系统默认的账户名
		        String password = bundle.getString("jdbc.password");// 你安装时选设置的密码
		        con = DriverManager.getConnection(url, user, password);// 获取连接
		        **/
		        String url="jdbc:mysql://localhost:3306/yzlhzs?characterEncoding=UTF-8";
		        String user ="root";// 用户名,系统默认的账户名
		        String password = "root";// 你安装时选设置的密码
		        con = DriverManager.getConnection(url, user, password);// 获取连接		        
		        System.out.println("连接成功！");
		        
		        //String sql = (String)map.get("sql");// 预编译语句，“？”代表参数
		        for(int i=0;i<map.size();i++){
		        	String sql = map.get(String.valueOf(i)).toString();
		        	pre = con.prepareStatement(sql);// 实例化预编译语句
		        	//result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
		        	 pre.execute();// 执行查询，注意括号中不需要再加参数
		        }
		        
		        
		        /* Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
		        System.out.println("开始尝试连接数据库！");
		        String url = "jdbc:oracle:" + "thin:@192.168.1.202:1521:jdks";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
		        String user = "jdks";// 用户名,系统默认的账户名
		        String password = "jdks";// 你安装时选设置的密码
		        con = DriverManager.getConnection(url, user, password);// 获取连接
			    con.setAutoCommit(false);     
		        PreparedStatement prest = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);     
		        for (Map arow : listdatas) {
		        	 if(null == arow.get("name")){
		        		 break;
		        	 }
			         prest.setString(1, "member.NEXTVAL");     
			         prest.setString(2, arow.get("name").toString());     
			         prest.setString(3, arow.get("turnover").toString());     
			         prest.setString(4, arow.get("year").toString());     
			         prest.setString(5, arow.get("month").toString());   
			         prest.setString(6, lsh);  
			         prest.addBatch();     
			      }     
		       prest.executeBatch();     
		       con.commit();*/    
		        
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
		    finally
		    {
		        try
		        {
		            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
		            // 注意关闭的顺序，最后使用的最先关闭
		            if (result != null)
		                result.close();
		            if (pre != null)
		                pre.close();
		            if (con != null)
		                con.close();
		            System.out.println("数据库连接已关闭！");
		        }
		        catch (Exception e)
		        {
		            e.printStackTrace();
		        }
		    }
		
	}
	
	/** 验证单元格数据* */
	public void validateCellData(int curRow, int curCol, Cell colCell,
			String entityName, String headName, String curColType) {

		List rulList = (List) parseXmlUtil.getColumnRulesMap().get(
				entityName + "_" + headName);
		if (rulList != null && rulList.size() > 0) {
			for (int i = 0; i < rulList.size(); i++) {
				Map rulM = (Map) rulList.get(i);
				String rulName = (String) rulM.get("name");
				String rulMsg = (String) rulM.get("message");
				String rulValue = (String) rulM.get("value");
				String cellValue = ParseExcel.getStringCellValue(colCell).trim();
				RulePlugin rulup = (RulePlugin) SpringUtil.getBean(rulName);
				if (rulup.validate(cellValue, rulValue)) {
					errorString.append("第" + curRow + "行,第" + curCol + "列:"
							+ rulMsg + "<br>");
				}
			}
		}
	}

	/**
	 * 获得单元格字符串
	 * 
	 * @throws UnSupportedCellTypeException
	 */
	public static String getStringCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}

		String result = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				java.text.SimpleDateFormat TIME_FORMATTER = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				result = TIME_FORMATTER.format(cell.getDateCellValue());
			} else {
				double doubleValue = cell.getNumericCellValue();
				result = "" + doubleValue;
			}
			break;
		case Cell.CELL_TYPE_STRING:
			if (cell.getRichStringCellValue() == null) {
				result = null;
			} else {
				result = cell.getRichStringCellValue().getString();
			}
			break;
		case Cell.CELL_TYPE_BLANK:
			result = null;
			break;
		case Cell.CELL_TYPE_FORMULA:
			try {
				result = String.valueOf(cell.getNumericCellValue());
			} catch (Exception e) {
				result = cell.getRichStringCellValue().getString();
			}
			break;
		default:
			result = "";
		}

		return result;
	}

	/***************************************************************************
	 * 主方法
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		File excelFile = new File("src/user.xls");
		File xmlFile = new File("src/user.xml");
		ParseExcel ppp = new ParseExcel();
		ppp.inportFiles(excelFile, xmlFile);

	}



	public UploadFileService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(UploadFileService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public String getErrorString() {
		return errorString.toString();
	}

	public int getDatacount() {
		return datacount;
	}
	
	public Map getSheetDatas() {
		return sheetDatas;
	}

	public void setSheetDatas(Map sheetDatas) {
		this.sheetDatas = sheetDatas;
	}
	
	

}
