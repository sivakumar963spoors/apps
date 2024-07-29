package com.spoors.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.JsonGenerationException;
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

}
