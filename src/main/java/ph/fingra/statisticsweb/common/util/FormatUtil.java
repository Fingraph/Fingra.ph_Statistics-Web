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

package ph.fingra.statisticsweb.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class FormatUtil {
    
    /**
    * Make file size format
    * <br>
    * @param filesize ex) "1234567"<br>
    * @return result  ex) "1,234,567"
    */
    public static String FormatFileSize(String filesize) {
        int len = 0;
        int mok = 0;
        int mod = 0;
        String result = "";
        String reverseResult = "";
        String stringMok = "";
        String reverseMok = "";
        len = filesize.length();
        if (len < 4)
            return "1";
        mok = Integer.parseInt(filesize) / 1024;
        mod = Integer.parseInt(filesize) % 1024;
        if (Integer.parseInt(String.valueOf(mod).substring(0, 1)) >= 5)
            mok++;
        stringMok = String.valueOf(mok);
        reverseMok = reverse(stringMok);
        for (int i = 0; i < reverseMok.length(); i++) {
            reverseResult = reverseResult + reverseMok.charAt(i);
            if ((i + 1) % 3 == 0) {
                if (i == reverseMok.length() - 1)
                    continue;
                reverseResult = reverseResult + ",";
            }
        }
        result = reverse(reverseResult);
        return result;
    }
    /**
    * relation function to formatFileSize
    * @param String
    * @return String
    */
    private static String reverse(String str) {
        int i, len;
        len = str.length();
        String result = "";
        for (i = (len - 1); i >= 0; i--)
            result = result + str.charAt(i);
        return result;
    }
    
    /**
     * Insert comma to String
     * @param String
     * @return String
     */
    public static String getNumberComma(String number) {
        NumberFormat fmt = NumberFormat.getInstance();
        String strNumber = fmt.format(Double.valueOf(number));
        return strNumber;
    }
    /**
     * Insert comma to Double
     * @param Double
     * @return String
     */
    public static String getNumberComma(Double number) {
        NumberFormat fmt = NumberFormat.getInstance();
        String strNumber = fmt.format(number);
        return strNumber;
    }
    /**
     * Insert comma to int
     * @param int
     * @return String
     */
    public static String getNumberComma(int number) {
        NumberFormat fmt = NumberFormat.getInstance();
        String strNumber = fmt.format(number);
        return strNumber;
    }
    
    /**
    * relation fuction to Currency format<br>
    * <font color="#0000ff">String formatted = Util.format("1234.67789", 2);<br>
    * formatted ==> "1234.68"
    * </font>
    * @param String<br>
    * @param int<br>
    * @return formatted double
    */
    public static double format(String str_number, int digits) {
        String pattern = "###.";
        double value = -1.;
        for (int i = 0; i < digits; i++) {
            pattern += "#";
        }
        try {
            value = Double.parseDouble(str_number);
        } catch (NumberFormatException e) {
            System.err.println("[Util] Bad Number String!!");
        }
        return customFormat(pattern, value);
    }
    
    /**
    * Use this method like this:<br>
    * <font color="#0000ff">String formatted = Util.format(1234.67789, 2);<br>
    * formatted ==> "1234.68"
    * </font>
    * @param numer<br>
    * @param digits<br>
    * @return formatted double
    */
    public static double format(double number, int digits) {
        return format("" + number, digits);
    }
    
    /**
    * This method actually does all for number formatting...<br>
    * @param pattern pattern to apply to the given double value<br>
    * @param value number to be formatted<br>
    * @return formatted double
    */
    private static double customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        try {
            String output = myFormatter.format(value);
            return Double.parseDouble(output);
        } catch (NumberFormatException e) {
            System.err.println("[Util] at customFormat(..): NumberFormatException");
            return -1.0;
        }
    }
    
    /**
    * Use this method like this:<br>
    * <font color="#0000ff">String formatted = Util.formatIntoCurr("123456789.123", 2);<br>
    * formatted ==> "123,456,789.12"
    * </font>
    * @param str_numer<br>
    * @param digits<br>
    * @return formatted String
    */
    public static String formatIntoCurr(String str_number, int digits) {
        String pattern = "###,###";
        double value = -1.;
        if (digits>0) pattern += ".";
        for (int i = 0; i < digits; i++) {
            pattern += "#";
        }
        try {
            value = Double.parseDouble(str_number);
        } catch (NumberFormatException e) {
            System.err.println("[Util] Bad Number String!!");
        }
        return toCurrencyFormat(pattern, value);
    }
    
    /**
    * This method actually does all for number formatting into Currency <br>
    * @param formatted<br>
    * @return : double type
    */
    public static double currencyToNumber(String formatted) {
        StringTokenizer st = new StringTokenizer(formatted, ",");
        StringBuffer sb = new StringBuffer();
        double ret = -1;
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
        }
        try {
            ret = Double.parseDouble(sb.toString());
        } catch (NumberFormatException e) {
            System.err.println("[Util]: Bad Number Format ...." + sb);
        } finally {}
        return ret;
    }
    
    /**
     * Use this method like this:<br>
    * <font color="#0000ff">String formatted = Util.formatIntoCurr(123456789.123, 2);<br>
    * formatted ==> "123,456,789.12"
    * </font>
     * @param num <br>
     * @param digits <br>
     * @return formatted String
     */
    public static String formatIntoCurr(double num, int digits) {
        return formatIntoCurr("" + num, digits);
    }
    
    /**
     * This method actually does all for number formatting into Currency <br>
     * @param pattern <br>
     * @param value <br>
     * @return formatted currency String
     */
    private static String toCurrencyFormat(String pattern, double value) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(value);
    }
    
    /**
     * Date format ex)2001-01-01 13:00
     * @param date
     * @return
     */
    public static String DateForm(String date) {
        if (date == null)
            return "";
        if (date.length() == 8)
            return new String(date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8));
        if (date.length() == 12)
            return new String(
                date.substring(0, 4)
                    + "-"
                    + date.substring(4, 6)
                    + "-"
                    + date.substring(6, 8)
                    + " "
                    + date.substring(8, 10)
                    + ":"
                    + date.substring(10, 12));
        return date;
    }

    public static String numstring(String currency) {
        String num1 = "만억조경해자양";
        String strTemp = currency;
        String strTemp2 = "";
        String strReturn = "";
        int i = 0, j = 0;

        strTemp = strTemp.replaceAll("0","영");
        strTemp = strTemp.replaceAll("1","일");
        strTemp = strTemp.replaceAll("2","이");
        strTemp = strTemp.replaceAll("3","삼");
        strTemp = strTemp.replaceAll("4","사");
        strTemp = strTemp.replaceAll("5","오");
        strTemp = strTemp.replaceAll("6","육");
        strTemp = strTemp.replaceAll("7","칠");
        strTemp = strTemp.replaceAll("8","팔");
        strTemp = strTemp.replaceAll("9","구");

        if(strTemp.length()%4 != 0) {
            j = 4-(strTemp.length()%4);
            for(i=0;i<j;i++) {
                strTemp = "영" + strTemp;
            }
        }

        for(i=1;i<10;i++) {
            strTemp2 = strTemp.substring(strTemp.length()-(4*i),strTemp.length()-(4*i)+4);
            if(i > 1 && numstring2(strTemp2)!="") {
                strReturn = numstring2(strTemp2) + num1.substring(i-2,i-1) + strReturn;
            } else {
                strReturn = numstring2(strTemp2) + strReturn;
            }

            if(strTemp.length()/4==i) break;
        }

        return strReturn;
    }

    public static String numstring2(String strNum) {
        String num1 = "천백십";
        int i = 0;
        String strReturn = "";

        for(i=0;i<3;i++) {
            if(strNum.substring(i,i+1).compareTo("영")!=0 ) {
                strReturn = strReturn + strNum.substring(i,i+1) + num1.substring(i,i+1);
            }
        }

        if(strNum.substring(3,4).compareTo("영")!=0) {
            strReturn = strReturn + strNum.substring(i,i+1);
        }

        return strReturn;
    }

    private static final String INT_RE = "^\\d*$";
    private static final String FLOAT_RE = "[-+]?([0-9]*\\.)?[0-9]+([eE][-+]?[0-9]+)?";
    private static final String IP_RE = "([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})";

    private static final String INT_FLAG = "(0|1)";     // 0|1
    private static final String INT_20 = "^\\d{1,2}$";  // 99
    private static final String FLOAT_13 = "^[-+]?[0-9]{1,1}(\\.[0-9]{1,3})?$"; // +-9.999
    private static final String FLOAT_23 = "^[-+]?[0-9]{1,2}(\\.[0-9]{1,3})?$"; // +-99.999
    private static final String FLOAT_33 = "^[-+]?[0-9]{1,3}(\\.[0-9]{1,3})?$"; // +-999.999
    private static final String FLOAT_43 = "^[-+]?[0-9]{1,4}(\\.[0-9]{1,3})?$"; // +-9999.999
    private static final String FLOAT_54 = "^[-+]?[0-9]{1,5}(\\.[0-9]{1,4})?$"; // +-99999.9999

    public static boolean validInteger(String canidate) {
        return Pattern.matches(INT_RE, canidate);
    }
    public static boolean validFloat(String canidate) {
        return Pattern.matches(FLOAT_RE, canidate);
    }
    public static boolean validIP(String canidate) {
        return Pattern.matches(IP_RE, canidate);
    }

    public static boolean validIntegerFlag(String canidate) {
        return Pattern.matches(INT_FLAG, canidate);
    }
    public static boolean validInteger20(String canidate) {
        return Pattern.matches(INT_20, canidate);
    }
    public static boolean validFloat13(String canidate) {
        return Pattern.matches(FLOAT_13, canidate);
    }
    public static boolean validFloat23(String canidate) {
        return Pattern.matches(FLOAT_23, canidate);
    }
    public static boolean validFloat33(String canidate) {
        return Pattern.matches(FLOAT_33, canidate);
    }
    public static boolean validFloat43(String canidate) {
        return Pattern.matches(FLOAT_43, canidate);
    }
    public static boolean validFloat54(String canidate) {
        return Pattern.matches(FLOAT_54, canidate);
    }

    public static String getCurrencyunitByCountrycode(String countrycode) {
        String currencyunit = "USD";
        
        if (countrycode==null)
            return currencyunit;
        
        switch(countrycode) {
        case "KR": currencyunit = "KRW"; break;     // Korea, Republic of
        case "DE":                                  // Germany
        case "FR":                                  // France
        case "BE":                                  // Belgium
        case "FI":                                  // Finland
        case "ES":                                  // Spain
        case "IT":                                  // Italy
        case "IE":                                  // Ireland
        case "NL":                                  // Netherlands
        case "PT":                                  // Portugal
        case "AT":                                  // Austria
        case "GR":                                  // Greece
            currencyunit = "EUR"; break;
        case "US": currencyunit = "USD"; break;     // United States of America
        case "JP": currencyunit = "JPY"; break;     // Japan
        case "CN": currencyunit = "CNY"; break;     // China
        case "HK": currencyunit = "HKD"; break;     // Hong Kong, Special Administrative Region of China
        case "TW": currencyunit = "TWD"; break;     // Taiwan, Republic of China
        case "GB": currencyunit = "GBP"; break;     // United Kingdom
        case "CA": currencyunit = "CAD"; break;     // Canada
        case "CH": currencyunit = "CHF"; break;     // Switzerland
        case "SE": currencyunit = "SEK"; break;     // Sweden
        case "AU": currencyunit = "AUD"; break;     // Australia
        case "NZ": currencyunit = "NZD"; break;     // New Zealand
        case "CZ": currencyunit = "CZK"; break;     // Czech Republic
        case "TR": currencyunit = "TRY"; break;     // Turkey
        case "MN": currencyunit = "MNT"; break;     // Mongolia
        case "IL": currencyunit = "ILS"; break;     // Israel
        case "DK": currencyunit = "DKK"; break;     // Denmark
        case "NO": currencyunit = "NOK"; break;     // Norway
        case "SA": currencyunit = "SAR"; break;     // Saudi Arabia
        case "KW": currencyunit = "KWD"; break;     // Kuwait
        case "BH": currencyunit = "BHD"; break;     // Bahrain
        case "AE": currencyunit = "AED"; break;     // United Arab Emirates
        case "JO": currencyunit = "JOD"; break;     // Jordan
        case "EG": currencyunit = "EGP"; break;     // Egypt
        case "TH": currencyunit = "THB"; break;     // Thailand
        case "SG": currencyunit = "SGD"; break;     // Singapore
        case "MY": currencyunit = "MYR"; break;     // Malaysia
        case "ID": currencyunit = "IDR"; break;     // Indonesia
        case "QA": currencyunit = "QAR"; break;     // Qatar
        case "KZ": currencyunit = "KZT"; break;     // Kazakhstan
        case "BN": currencyunit = "BND"; break;     // Brunei Darussalam
        case "IN": currencyunit = "INR"; break;     // India
        case "PK": currencyunit = "PKR"; break;     // Pakistan
        case "BD": currencyunit = "BDT"; break;     // Bangladesh
        case "PH": currencyunit = "PHP"; break;     // Philippines
        case "MX": currencyunit = "MXN"; break;     // Mexico
        case "BR": currencyunit = "BRL"; break;     // Brazil
        case "VN": currencyunit = "VND"; break;     // Viet Nam
        case "ZA": currencyunit = "ZAR"; break;     // South Africa
        case "RU": currencyunit = "RUB"; break;     // Russian Federation
        case "HU": currencyunit = "HUF"; break;     // Hungary
        case "PL": currencyunit = "PLN"; break;     // Poland
        default:
            currencyunit = "USD";
        }
        
        return currencyunit;
    }

    public static void main(String[] args) {

        System.out.println("validIntegerFlag(0) - " + validIntegerFlag("0"));
        System.out.println("validIntegerFlag(1) - " + validIntegerFlag("1"));
        System.out.println("validIntegerFlag(2) - " + validIntegerFlag("2"));

        System.out.println("validInteger20(-1) - " + validInteger20("-1"));
        System.out.println("validInteger20(0) - " + validInteger20("0"));
        System.out.println("validInteger20(30) - " + validInteger20("30"));
        System.out.println("validInteger20(2.5) - " + validInteger20("2.5"));
        System.out.println("validInteger20(100) - " + validInteger20("100"));

        System.out.println("validFloat13(0) - " + validFloat13("0"));
        System.out.println("validFloat13(1) - " + validFloat13("1"));
        System.out.println("validFloat13(-1) - " + validFloat13("-1"));
        System.out.println("validFloat13(0.123) - " + validFloat13("0.123"));
        System.out.println("validFloat13(-0.123) - " + validFloat13("-0.123"));
        System.out.println("validFloat13(9.999) - " + validFloat13("9.999"));
        System.out.println("validFloat13(-9.999) - " + validFloat13("-9.999"));
        System.out.println("validFloat13(10.999) - " + validFloat13("10.999"));
        System.out.println("validFloat13(-9.9999) - " + validFloat13("-9.9999"));

        System.out.println("validFloat23(0) - " + validFloat23("0"));
        System.out.println("validFloat23(1) - " + validFloat23("1"));
        System.out.println("validFloat23(-1) - " + validFloat23("-1"));
        System.out.println("validFloat23(0.123) - " + validFloat23("0.123"));
        System.out.println("validFloat23(-0.123) - " + validFloat23("-0.123"));
        System.out.println("validFloat23(10) - " + validFloat23("10"));
        System.out.println("validFloat23(10.020) - " + validFloat23("10.020"));
        System.out.println("validFloat23(99.999) - " + validFloat23("99.999"));
        System.out.println("validFloat23(-99.999) - " + validFloat23("-99.999"));
        System.out.println("validFloat23(100) - " + validFloat23("100"));
        System.out.println("validFloat23(0.1234) - " + validFloat23("0.1234"));
        System.out.println("validFloat23(100.0) - " + validFloat23("100.0"));
        System.out.println("validFloat23(90.0100) - " + validFloat23("90.0100"));

        System.out.println("validFloat33(0) - " + validFloat33("0"));
        System.out.println("validFloat33(1) - " + validFloat33("1"));
        System.out.println("validFloat33(-1) - " + validFloat33("-1"));
        System.out.println("validFloat33(0.123) - " + validFloat33("0.123"));
        System.out.println("validFloat33(-0.123) - " + validFloat33("-0.123"));
        System.out.println("validFloat33(10) - " + validFloat33("10"));
        System.out.println("validFloat33(10.020) - " + validFloat33("10.020"));
        System.out.println("validFloat33(99.999) - " + validFloat33("99.999"));
        System.out.println("validFloat33(-99.999) - " + validFloat33("-99.999"));
        System.out.println("validFloat33(100) - " + validFloat33("100"));
        System.out.println("validFloat33(100.0) - " + validFloat33("100.0"));
        System.out.println("validFloat33(999.999) - " + validFloat33("999.999"));
        System.out.println("validFloat33(-999.999) - " + validFloat33("-999.999"));
        System.out.println("validFloat33(1234.12) - " + validFloat33("1234.12"));
        System.out.println("validFloat33(0.1234) - " + validFloat33("0.1234"));
        System.out.println("validFloat33(90.0100) - " + validFloat33("90.0100"));

        System.out.println("validFloat54(99999.9999) - " + validFloat54("9999.999"));
        System.out.println("validFloat54(999999.999) - " + validFloat54("999999.999"));
        
        System.out.println(formatIntoCurr(1234.567, 0));
        System.out.println(formatIntoCurr(1234.567, 1));
        System.out.println(formatIntoCurr(1234.567, 2));
    }
}
