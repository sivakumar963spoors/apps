package com.spoors.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spoors.beans.FormSpecContainer;
import com.spoors.beans.Media;
import com.spoors.beans.workSpecs.WorkActionSpec;
import com.spoors.beans.workSpecs.WorkSpec;
import com.spoors.beans.workSpecs.WorkSpecContainer;
import com.spoors.beans.workSpecs.WorkSpecFormSpecMap;
import com.spoors.config.AppConfig;
import com.spoors.manager.ServiceManager;
import com.spoors.manager.SqliteManager;
import com.spoors.util.Api;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ApiController.class);
    
    public AppConfig appConfig;
    public ServiceManager serviceManager;
    
    @Autowired
    public SqliteManager sqliteManager;
	
	public ApiController(AppConfig appConfig,ServiceManager serviceManager) {
		this.appConfig = appConfig;
		this.serviceManager = serviceManager;
	}

	@PostMapping("/export")
    public ResponseEntity<String> getApplicationForExport(@RequestBody String jsonString,HttpSession httpSession,
			HttpServletResponse servletResponse) {
       String logtext = "applicationSpecsExport";
		Map<String,Object> response = new HashMap<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(jsonString);
	       
	        List<WorkSpecContainer> workSpecContainerList = new ArrayList<>();
	        JsonNode worksNode = jsonNode.get("works");
	        Set<String> formSpecUniqueIds = new HashSet<>();
	        if(worksNode!= null && worksNode.isArray()) {
	        	for(JsonNode node : worksNode) {
	        		WorkSpecContainer workSpecContainer = new WorkSpecContainer();
	        		String workSpecId = node.asText();
	        		LOGGER.info(logtext+" --> Works:- workSpecId : "+workSpecId+" starts.");
	        		serviceManager.getExportWorkSpecData(workSpecId,workSpecContainer);
	        		
	        		List<WorkSpec> workSpecs = workSpecContainer.getWorkSpecs();
	        		if(workSpecs != null && workSpecs.size() > 0) {
	        			for(WorkSpec workSpec : workSpecs) {
	        				formSpecUniqueIds.add(workSpec.getFormSpecUniqueId());
	        			}
	        		}
	        		List<WorkActionSpec>  workActionSpecs = workSpecContainer.getWorkActionSpecs();
	        		if(workActionSpecs != null && workActionSpecs.size() > 0) {
	        			for(WorkActionSpec workActionSpec : workActionSpecs) {
	        				if(!Api.isEmptyString(workActionSpec.getFormSpecUniqueId())) {
	        					formSpecUniqueIds.add(workActionSpec.getFormSpecUniqueId());
	        				}
	        			}
	        		}
	        		
	        		List<WorkSpecFormSpecMap>  workSpecFormSpecMaps = workSpecContainer.getWorkSpecFormSpecMaps();
	        		if(workSpecFormSpecMaps != null && workSpecFormSpecMaps.size() > 0) {
	        			for(WorkSpecFormSpecMap workActionSpec : workSpecFormSpecMaps) {
	        				if(!Api.isEmptyString(workActionSpec.getFormSpecUniqueId())) {
	        					formSpecUniqueIds.add(workActionSpec.getFormSpecUniqueId());
	        				}
	        			}
	        		}
	        		workSpecContainerList.add(workSpecContainer);
	        	}
	        }else {
	        	System.out.println("works is not an array");
	        	LOGGER.info("works is not an array");
	        }
	        
	        FormSpecContainer formSpecContainer = new FormSpecContainer();
	        JsonNode formsNode = jsonNode.get("forms");
	        if (formsNode != null && formsNode.isArray()) {
	        	for (JsonNode node : formsNode) {
	        		
	            	String formSpecUniqueId = node.asText();
	            	LOGGER.info(logtext+" --> Forms:- formSpecUniqueId : "+formSpecUniqueId+" starts.");
	            	formSpecUniqueIds.add(formSpecUniqueId);
	            }
	        } else {
	            System.out.println("forms is not an array");
	            LOGGER.info("forms is not an array");
	        }
	        
	        serviceManager.getExportFormSpecData(formSpecUniqueIds,formSpecContainer);
	        
	        boolean exportData = true;
	        
	        if(jsonNode.has("cloneEntityData") && jsonNode.get("cloneEntityData") != null) {
	        	String cloneEntityData = jsonNode.get("cloneEntityData").asText();
	        	if(!Api.isEmptyString(cloneEntityData) && cloneEntityData.equalsIgnoreCase("true")) {
	        		exportData = true;
	        	}
	        }
	        
	        serviceManager.exportEntityAndCustomEntitySpecs(formSpecContainer,exportData);
	        
	        String mediaId = sqliteManager.saveFormSpecDataToSqlite(formSpecContainer,workSpecContainerList,servletResponse);
	        
	        response.put("mediaId", mediaId);
	        response.put("Status", "Success");
	        
		}catch(Exception e) {
			 LOGGER.info("Got Exception in getApplicationForExport : "+e);
			 LOGGER.error("Got Exception in getApplicationForExport : "+e);
			 e.printStackTrace();
		}
		
		String responseString = Api.getJsonFromGivenObject(response);
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }
	
	@PostMapping("/export/test")
    public ResponseEntity<String> getApplicationForExportTest(@RequestBody String jsonString,HttpSession httpSession,
			HttpServletResponse servletResponse) {
       String logtext = "applicationSpecsExport";
       LOGGER.info(logtext);
		Map<String,Object> response = new HashMap<>();
		try {	        response.put("sqliteName", "test");
	        response.put("Status", "Success");
	        
		}catch(Exception e) {
			 LOGGER.info("Got Exception in getApplicationForExport : "+e);
			 LOGGER.error("Got Exception in getApplicationForExport : "+e);
			 e.printStackTrace();
		}
		
		String responseString = Api.getJsonFromGivenObject(response);
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }
	
	@PostMapping("/import")
    public ResponseEntity<String> getImportApplication(@RequestBody String jsonString) {
       String logtext = "applicationSpecsImport";
		Map<String,Object> response = new HashMap<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(jsonString);
	        Integer companyId = 8748;
	        JsonNode formsNode = jsonNode.get("forms");
	        if (formsNode.isArray()) {
	            List<FormSpecContainer> formSpecContainerList = new ArrayList<>();
	        	for (JsonNode node : formsNode) {
	            	String formSpecUniqueId = node.asText();
	            	LOGGER.info(logtext+" --> Forms:- formSpecUniqueId : "+formSpecUniqueId+" starts.");
	            	FormSpecContainer formSpecContainer = sqliteManager.importFormSpec(formSpecUniqueId);
	            	formSpecContainerList.add(formSpecContainer);
	            }
	        	serviceManager.createFormSpecs(formSpecContainerList,companyId);
	        	
	        } else {
	            System.out.println("\"forms\" is not an array");
	            LOGGER.info("forms is not an array");
	        }
		}catch(Exception e) {
			 LOGGER.info("Got Exception in getApplicationForExport : "+e);
			 LOGGER.error("Got Exception in getApplicationForExport : "+e);
			 e.printStackTrace();
		}
		String responseString = Api.getJsonFromGivenObject(response);
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }
	
	@PostMapping("/import/bySqliteName")
    public ResponseEntity<String> getImportApplicationBySqliteName(@RequestBody String jsonString) {
       String logtext = "getImportApplicationBySqliteName";
		Map<String,Object> response = new HashMap<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(jsonString);
	        Integer companyId = jsonNode.get("companyId").asInt();
	        String sqliteName = jsonNode.get("sqliteName").asText();
	        String cloneEntityData = "";
	        if(jsonNode.has("cloneEntityData") && jsonNode.get("cloneEntityData") != null) {
	        	cloneEntityData = jsonNode.get("cloneEntityData").asText();
	        }
	        sqliteManager.importDataFromSqlite(sqliteName,companyId,cloneEntityData);
	        response.put("StatusMessage", " Import Successfull ");
	        response.put("Status", "Success");
		}catch(Exception e) {
			 LOGGER.info("Got Exception in getApplicationForExport : "+e);
			 LOGGER.error("Got Exception in getApplicationForExport : "+e);
			 e.printStackTrace();
		}
		String responseString = Api.getJsonFromGivenObject(response);
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }
	
	@PostMapping("/import/byMediaId")
    public ResponseEntity<String> getImportApplicationByMediaId(@RequestBody String jsonString) {
       String logtext = "getImportApplicationBySqliteName";
        Map<String,Object> response = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            Integer companyId = jsonNode.get("companyId").asInt();
            String mediaId = jsonNode.get("mediaId").asText();
            String cloneEntityData = "";
            if(jsonNode.has("cloneEntityData") && jsonNode.get("cloneEntityData") != null) {
                cloneEntityData = jsonNode.get("cloneEntityData").asText();
            }
            Media media = serviceManager.getMedia(mediaId);
            sqliteManager.importDataFromSqlite(media.getLocalPath(),companyId,cloneEntityData);
            response.put("StatusMessage", " Import Successfull ");
            response.put("Status", "Success");
        }catch(Exception e) {
             LOGGER.info("Got Exception in getApplicationForExport : "+e);
             LOGGER.error("Got Exception in getApplicationForExport : "+e);
             e.printStackTrace();
        }
        String responseString = Api.getJsonFromGivenObject(response);
        return new ResponseEntity<>(responseString, HttpStatus.OK);
    }
	
	@PostMapping("/upload/app")
	public ResponseEntity<?> handleUpload(@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "cloneEntityData", required = true,defaultValue="false") String cloneEntityData,
			@RequestParam("file") MultipartFile file) throws ClassNotFoundException {

		LOGGER.info("handleUpload import starts...");

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Uploaded file is empty.");
		}

		// Save MultipartFile to a temporary file
		File tempFile;
		try {
			tempFile = File.createTempFile("uploaded_", ".sqlite");
			file.transferTo(tempFile);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save file.");
		}

		FormSpecContainer formSpecContainer = new FormSpecContainer();
		WorkSpecContainer workSpecContainer = new WorkSpecContainer();
		Class.forName("org.sqlite.JDBC");
		// Read SQLite DB and list all tables
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + tempFile.getAbsolutePath())) {
			Statement stmt = conn.createStatement();

			sqliteManager.fetchDataFromSqliteFile(formSpecContainer, workSpecContainer, stmt);

			serviceManager.fetchDataAndInsertIntoDB(formSpecContainer, workSpecContainer, companyId, cloneEntityData);

			LOGGER.info(" importDataFromSqlite done ... successfully ");

			Map<String, Object> response = new HashMap<>();
			response.put("StatusMessage", " Import Successfull ");
			response.put("Status", "Success");
			String responseString = Api.getJsonFromGivenObject(response);
			LOGGER.info("handleUpload import Successfull");
			return new ResponseEntity<>(responseString, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.info("Got Exception in handleUpload : " + e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to read SQLite file.");
		}

	}
	
}
