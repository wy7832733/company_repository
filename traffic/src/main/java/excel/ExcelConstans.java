package excel;


public class ExcelConstans {
	
	/**xml中验证规则的名称name**/
	private static String RULE_NAME_NULLABLE = "nullable";
	private static String RULE_NAME_UNIQUE = "checkUnique";
	private static String RULE_NAME_REGEX = "checkRegex";
	private static String RULE_NAME_INDIC = "checkDIC";
	
	/**excel 中的模板数据错误**/
	public static String ERROR_EXCEL_NULL="excel中数据为空!<br>";
	public static String ERROR_EXCEL_COLUMN_NOT_EQUAL="xml列数大于excel列数，请检查!<br>";
	public static String ERROR_EXCEL_DATA_TYPE = "数据类型错误";

}
