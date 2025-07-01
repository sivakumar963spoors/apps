package com.spoors.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spoors.config.AppConfig;
import com.spoors.context.AppContext;
import com.spoors.manager.ServiceManager;

import ch.qos.logback.classic.Logger;

public class Api {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Api.class);
	
	public static enum CsvOptions{FILTER_NULL_OR_EMPTY,NONE};
	public static enum DateConversionType{STADARD_TO_XSD,XSD_TO_STADARD,
											DATETIME_TO_UTC_DATETIME_WITH_TZO,
											UTC_DATETIME_TO_DATETIME_WITH_TZO,
											UTC_DATETIME_TO_DATETIME_DISPLAY_FORMAT,
											UTC_DATETIME_TO_IST};
	
	public static AppConfig getAppConfig() {
        ApplicationContext context = AppContext.getApplicationContext();
        return context.getBean(AppConfig.class);
    }

	public static String getJsonFromGivenObject(Object object) {

		try {
			return Api.toJson(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "[]";
	}

	public static String toJson(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		String disableInternFieldNames =  getAppConfig().getDisableInternFieldNamesForJsonObjectMapper();
		if ("true".equalsIgnoreCase(disableInternFieldNames)) {
			JsonFactory f = new JsonFactory();
			f.disable(Feature.INTERN_FIELD_NAMES);
			ObjectMapper mapper = new ObjectMapper(f);
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, object);
			return strWriter.toString();
		} else {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, object);
			return strWriter.toString();
		}
	}
	
	public static boolean isEmptyString(String string1) {
		if ((string1 != null && string1.trim().length() > 0 && !string1.equals("null"))) {
			return false;
		} else {
		}
		return true;
	}

	public static boolean isEmptyObj(Object object) {
		if (object != null && !object.equals("null") && !object.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isEqualString(String string1, String string2) {
		if (string1 == null) {
			if (string2 == null || Api.isEmptyString(string2)) {
				return true;
			} else {
				return false;
			}
		} else {
			return string1.equals(string2);
		}
	}
	
	public static String getTimeZoneDatesInLTZ(String date, String myTimeZone,
			String employeeTimeZone) {

		try {
			if ((employeeTimeZone != null && !employeeTimeZone.equalsIgnoreCase("null")) && (myTimeZone != null && !myTimeZone.equalsIgnoreCase("null") )) {

				String myTimeZoneDate = Api.getDateTimeInTz(Api.getCalendar(Api.getDateTimeInUTC(date)), myTimeZone, 1);

				
				if(!myTimeZone.equalsIgnoreCase(employeeTimeZone)) {
					String employeeTimeZoneDate =Api.getDateTimeInTz(Api.getCalendar(Api.getDateTimeInUTC(date)), employeeTimeZone, 1);
							
					myTimeZoneDate = myTimeZoneDate + " (" + employeeTimeZoneDate + ")";
				}
				date = myTimeZoneDate;

			} else if (myTimeZone != null) {
				String myTimeZoneDate = Api.getDateTimeInTz(Api.getCalendar(Api.getDateTimeInUTC(date)), myTimeZone, 1);
				date = myTimeZoneDate;

			}else if (employeeTimeZone != null && !employeeTimeZone.equalsIgnoreCase("null")){
				String myTimeZoneDate = Api.getDateTimeInTz(Api.getCalendar(Api.getDateTimeInUTC(date)), employeeTimeZone, 1);
				date = myTimeZoneDate;
			}
		} catch (Exception e) {
		//	e.printStackTrace();
		}

		return date;
	}
	
	public static String getDateTimeInTz(Calendar calendar, String offset, int type) {
		calendar.add(Calendar.MINUTE, -Integer.parseInt(offset));
		SimpleDateFormat format = null;
		if(type == 1)
			format =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		else if(type == 2)
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(calendar.getTime());
	}
	
	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		calendar.setTime(date);
		return calendar;
	}

	public static Date getDateTimeInUTC(String date) throws ParseException {
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
			return format.parse(date);
		}catch(Exception e){
			String value = date.replace("T", " ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
			return format.parse(value);
		}
	}
	
	public static String toCSV(List<?> list, String fieldName,CsvOptions csvOption){
		try{
			if (list != null) {
				StringBuilder csv = new StringBuilder("");
				for (Object obj : list) {
					String value=BeanUtils.getProperty(obj, fieldName);
						
					if(CsvOptions.FILTER_NULL_OR_EMPTY==csvOption && Api.isEmptyString(value)){
						continue;
					}
					
					if (csv.length() > 0) {
						csv .append(",");
					}
					
						csv .append(value == null ? "" : value);
					}
				return csv.toString();
			}
			 else {
				return null;
			}
		}catch(Exception ex){
			LOGGER.debug("in toCSV", ex);
			LOGGER.info("in toCSV", ex);
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	public static Object fromJson(String json, TypeReference type)
			throws JsonParseException, JsonMappingException, IOException 
	{
		String disableInternFieldNames = "true";
		
		if("true".equalsIgnoreCase(disableInternFieldNames))
		{
			JsonFactory f = new JsonFactory();
	        f.disable(Feature.INTERN_FIELD_NAMES);
	        ObjectMapper mapper = new ObjectMapper(f);
	        mapper. configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, type);
		}
		else
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper. configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, type);
		}
	}
	
	public static String processStringValuesList(List<String> givenvalues) {
		if (givenvalues != null) {
			List<String> tempList = new ArrayList<String>();
			for (String string : givenvalues) {
				if(!Api.isEmptyString(string))
				{
					tempList.add(new StringBuilder("'").append(string.replaceAll("'", "")).append("'").toString());
				}
			}

			return Api.toCSV(tempList);
		}
		return null;
	}
	
	public static String toCSV(List<?> list) {
		if (list != null) {
			StringBuilder csv = new StringBuilder("");
			for (Object obj : list) {
				if(obj != null)
				{
					if (csv.length() > 0) {
						csv.append(", ");
					}
	
					csv.append(obj.toString());
				}
			}

			return csv.toString();
		} else {
			return null;
		}
	}
	
	public static String toCSV(Set<?> list) {
		if (list != null) {
			StringBuilder csv = new StringBuilder("");
			for (Object obj : list) {
				if(obj != null)
				{
					if (csv.length() > 0) {
						csv.append(", ");
					}
	
					csv.append(obj.toString());
				}
			}

			return csv.toString();
		} else {
			return null;
		}
	}
	
	public static String getDateTimeFromXsd(String datetimeXsd) {
		String syncDateFormat = Api.getConvertDateTimesToGivenType(datetimeXsd,DateConversionType.STADARD_TO_XSD,"");
		Calendar calendar = DatatypeConverter.parseDateTime(syncDateFormat);
		String datetime = Api.getDateTimeInUTC(calendar);
		datetime = datetime.substring(0, 19);
		return datetime;
	}
	
	public  static String getConvertDateTimesToGivenType(String value,DateConversionType dateConversionType,String tzo){
		try {
			
			if(value!=null && !Api.isEmptyString(value.toString())){
			
				if(dateConversionType==DateConversionType.STADARD_TO_XSD){
					value=toUtcXsd(value.toString());
			    	
			    }else if(dateConversionType==DateConversionType.XSD_TO_STADARD){
			    	value=getDateTimeFromXsd(value.toString());
			    }else if(dateConversionType==DateConversionType.DATETIME_TO_UTC_DATETIME_WITH_TZO){
			    	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			    	calendar.setTimeInMillis(getDateTimeInUTC(value.toString()).getTime());
			    	value=getDateTimeInTzToUtc(calendar, tzo) ;
			    }else if(dateConversionType==DateConversionType.UTC_DATETIME_TO_DATETIME_WITH_TZO){
			    	value =getDateTimeInTz24(value.toString(), tzo);
			    }
			    else if(dateConversionType==DateConversionType.UTC_DATETIME_TO_DATETIME_DISPLAY_FORMAT){
			    	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			    	calendar.setTimeInMillis(getDateTimeInUTC(value.toString()).getTime());
			    	value =getDateTimeInTz(calendar, tzo, 1);
			    }
			    else if(dateConversionType==DateConversionType.UTC_DATETIME_TO_IST){
			    	Calendar calendar = Calendar.getInstance();
			    	calendar.setTimeInMillis(getDateTimeInUTC(value.toString()).getTime());
			    	value =getDateTimeInTzIst(calendar, tzo);
			    }
	    	  }
			  
			} catch (Exception e) {
				e.printStackTrace();
			}
		  return value;
		}
	
	public static String toUtcXsd(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		try {
			calendar.setTimeInMillis(getDateTimeInUTC(date).getTime());
		} catch (ParseException e) {
			try {
				calendar = DatatypeConverter.parseDateTime(date);
			} catch (IllegalArgumentException ee) {
				throw e;
			}
		}
		return DatatypeConverter.printDateTime(calendar);
	}
	
	public static String getDateTimeInTzToUtc(Calendar calendar, String offset) {
		calendar.add(Calendar.MINUTE, Integer.parseInt(offset));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(calendar.getTime());
	}
	
	public static String getDateTimeInTz24(String dateTime, String offset) {
		String dateTime1 = removeTrailingZeroFromDateTime(dateTime);
		Date date =getDateFromString(dateTime1);
		Calendar calendar= getCalendar(date);
		return getDateTimeWithGivenTime(calendar, offset);
	}
	
public static String removeTrailingZeroFromDateTime(String datetimeXsd) {
		
		if(datetimeXsd == null || datetimeXsd.trim().length() == 0){
			return datetimeXsd;
		}
		if(datetimeXsd.trim().length() <= 19){
			return datetimeXsd;
		}
		if(!datetimeXsd.trim().endsWith(".0"))
			return datetimeXsd;
		String datetime = datetimeXsd.substring(0, 19);
		return datetime;
	}

	public static Date getDateFromString(String dateTime) {
		Date date;
		try {
			date = DateUtils.parseDate(dateTime,
					new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd hh:mm:ss a", "yyyy-MM-dd HH:mm:ss.s",
							"yyyy-MM-dd", "dd-MM-yyyy", "yyyy-MM-dd hh:mm a", "yyyy-MM-dd hh:mm", "MM/dd/yyyy",
							"dd/MM/yyyy h:mm:ss a", "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy", "yyyy/MM/dd HH:mm:ss.SSS",
							"EEE MMM dd HH:mm:ss zzz yyyy" });
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDateTimeWithGivenTime(Calendar calendar, String offset) {
		calendar.add(Calendar.MINUTE, -Integer.parseInt(offset));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(calendar.getTime());
	}

	public static String getDateTimeInTzIst(Calendar calendar, String offset) {
		calendar.add(Calendar.MINUTE, -Integer.parseInt(offset));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		format.setTimeZone(TimeZone.getTimeZone("IST"));
		return format.format(calendar.getTime());
	}
	
	public static String getDateTimeInUTC(Calendar calendar) {
		Date date = calendar.getTime();
		return getDateTimeInUTC(date);
	}
	
	public static String getDateTimeInUTC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(date);
	}
	public static Map<Object,Object> getMapFromList(List<?> list, String requiredFieldKeyName) {
		Map<Object,Object> map=new HashMap<Object,Object>();
		try{
			if (requiredFieldKeyName != null) {
				for (Object object : list) {
					// String valueKey =BeanUtils.getProperty(object, requiredFieldKeyName);
					Object valueKey = PropertyUtils.getProperty(object, requiredFieldKeyName);
					map.put(valueKey, object);
				}
			}
			}catch(Exception ex){
			 
		 }		
		 return map;
		}
	public static double round(double unrounded, int precision) {
		return round(unrounded, precision, BigDecimal.ROUND_HALF_DOWN);
	}
	public static double round(double unrounded, int precision, int roundingMode) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal roundedBig = bd.setScale(precision, roundingMode);
		double rounded = roundedBig.doubleValue();
		return rounded;
	}
	
	public static String roundToString(double unrounded, int precision) {
		return roundToString(unrounded, precision, BigDecimal.ROUND_HALF_DOWN);
	}

	public static String roundToString(double unrounded, int precision, int roundingMode) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal roundedBig = bd.setScale(precision, roundingMode);
		String rounded = roundedBig.toString();
		return rounded;
	}
	
	public static String makeNullIfNull(String value) {
		if (value == null) {
			return null;
		}

		if (value.trim().length() == 0) {
			return null;
		} else {
			if (value.equals("null") || value.equals("Null")
					|| value.equals("NULL")) {
				return null;
			} else {
				return value;
			}
		}
	}

	public static Map<Object,List<Object>> getResolvedMapFromList(List<?> list, String requiredFieldKeyName) 
	 {
		Map<Object, List<Object>> map = new HashMap<Object, List<Object>>();
		try 
		{
			if (requiredFieldKeyName != null) 
			{
				for (Object object : list) 
				{
					List<Object> objects = new ArrayList<Object>();
					Object valueKey = PropertyUtils.getProperty(object,
							requiredFieldKeyName);
					if(map.containsKey(valueKey))
					{
						objects = map.get(valueKey);
					}
					objects.add(object);
					map.put(valueKey, objects);
				}
			}
		} 
		catch (Exception ex) 
		{
	
		}
		return map;
	}
	public static List<String> csvToList(String csv) {
		ArrayList<String> list = new ArrayList<String>();

		if (!Api.isEmptyString(csv)) {
			String[] parts = csv.split(",");
			for (String part : parts) {
				list.add(part.trim());
			}
		}

		return list;
	}
	 public static String processStringValuesList1(List<String> givenvalues) {
 		if (givenvalues != null) {
 			List<String> tempList = new ArrayList<String>();
 			for (String string : givenvalues) {
 				if(string!=null){
 				string = string.replaceAll("'", "");
 				string = "'" + string + "'";
 				tempList.add(string);
  				}
 			}
 			return Api.toCSV(tempList);
 		}
 		return null;
 	}
	 public static String makeEmptyIfNull(String value) {
			if (value == null) {
				return "";
			} 
			else if (value.equals("null") || value.equals("Null")
					|| value.equals("NULL")) {
				return "";
			}
			else {
				return value;
			}
		}
	 public static Object makeEmptyIfNull(Object value) {
			if (value == null) {
				return "";
			} 
			else if (value.equals("null") || value.equals("Null")
					|| value.equals("NULL")) {
				return "";
			}
			else {
				return value;
			}
		}	

	
	public static String[] csvToArray(String csv) {
		String[] parts = null;

		if (!Api.isEmptyString(csv)) {
			parts = csv.split(",");
		}

		return parts;
	}
	
	public static boolean isEqualString(String string1, String string2,
			boolean makeNullIfEmpty) {
		if (makeNullIfEmpty) {
			return isEqualString(makeNullIfEmpty(string1),
					makeNullIfEmpty(string2));
		} else {
			return isEqualString(string1, string2);
		}
	}

	public static boolean isEqualStringIgnoreCase(String string1, String string2) {
		if (string1 == null) {
			if (string2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return string1.equalsIgnoreCase(string2);
		}
	}
	
	public static String makeNullIfEmpty(String value) {
		if (value != null && value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}
	
	public static boolean isEqualLong(Long long1, Long long2) {
		if (long1 == null) {
			if (long2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (long2 == null) {
				return false;
			} else {
				return long1.longValue() == long2.longValue();
			}
		}
	}
	
	public static String toCSVFromList(List<?> list, String fieldName){
		try{
			return toCSV(list, fieldName);
		}catch(Exception ex){
			
		}
		
		return null;
	}
	
	public static String toCSV(List<?> list, String fieldName)
			throws IllegalArgumentException, IllegalAccessException {
		if (list != null) {
			StringBuilder csv =  new StringBuilder("");
			for (Object obj : list) {
				if (csv.length() > 0) {
					csv.append(",");
				}

				if (fieldName == null || fieldName.trim().length() == 0) {
					csv.append(obj.toString());
				} else {
					Object val = getFieldValue(obj, fieldName);
					csv.append(val == null ? "" : val.toString());
				}
			}

			return csv.toString();
		} else {
			return null;
		}
	}
	
	public static Object getFieldValue(Object obj, String fieldName)
			throws IllegalArgumentException, IllegalAccessException {
		if (fieldName != null && fieldName.trim().length() >= 0) {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				String name = field.getName();
				if (name.equalsIgnoreCase(fieldName.trim())) {
					field.setAccessible(true);
					return field.get(obj);
				}
			}
		}

		return null;
	}
	
	public static List<Long> listToLongList(List<?> list, String fieldName) {
		
		String csv = "";
		try {
			csv = toCSV(list, fieldName);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Long> resultStringList = new ArrayList<Long>();
		
		if(!isEmptyString(csv)){
			resultStringList = csvToLongList(csv);
			return resultStringList;
		}
		
		//return null;
		return new ArrayList<Long>();
	}
	
	public static List<Long> csvToLongList(String csv) {
		ArrayList<Long> list = new ArrayList<Long>();

		if (!Api.isEmptyString(csv)) {
			String[] parts = csv.split(",");
			for (String part : parts) {
				if(!Api.isEmptyString(part)) {
					list.add(Long.parseLong(part.trim()));
				}
			}
		}

		return list;
	}
	public static boolean isNumber(String number){
		try {
			Double.parseDouble(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isNumber(Object number){
		try {
			Double.parseDouble(number.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
