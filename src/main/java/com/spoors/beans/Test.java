package com.spoors.beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.spoors.util.Api;

public class Test {

    public void main(String[] args) {
        // Path to the SQLite database file (can be .sqlite, .db, etc.)
        String url = "jdbc:sqlite:/home/spoors/Desktop/SqliteExport/5ee800cb-18f2-428a-b38c-7cf3f94adab0.sqlite";

        // SQL query to be executed
        String query = "SELECT * FROM forms_specs_fields";

        // Load SQLite JDBC driver (Optional for newer JDBC versions)
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
            return;
        }
        
        List<FormFieldSpec> formFieldSpecs = new ArrayList<FormFieldSpec>();

        // Establish connection and execute query
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
        	
        	extracted(query, formFieldSpecs, stmt);

		} catch (SQLException e) {
            System.err.println("SQL Exception occurred:");
            e.printStackTrace();
        }
    }

	public static void extracted(String query, List<FormFieldSpec> formFieldSpecs, Statement stmt)
			throws SQLException {
		ResultSet rs = stmt.executeQuery(query);

		// Process the result set
		while (rs.next()) {
			// Retrieve data from the result set
			String json_data = rs.getString("json_data"); // Replace "name" with your column name
			System.out.println(" json_data " + json_data);

			try {
				FormFieldSpec formFieldSpec = (FormFieldSpec) Api.fromJson(json_data,
						new TypeReference<FormFieldSpec>() {
						});
				formFieldSpecs.add(formFieldSpec);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" import done successfully");
	}
}
