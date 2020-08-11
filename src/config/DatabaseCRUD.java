package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

public class DatabaseCRUD {
	private static final String createTableSQL = "CREATE TABLE TEST ( \r\n" + " ID_TEST NUMBER(6) NOT NULL,\r\n"
			+ " NOMBRE_TEST VARCHAR2(20),\r\n" + "  CONSTRAINT TEST_PK PRIMARY KEY\r\n" + " (\r\n" + "   ID_TEST\r\n"
			+ " )\r\n" + " ENABLE\r\n" + ") TABLESPACE \"DB_ECOMMERCE\"";

	private static final String INSERT_USERS_SQL = "INSERT INTO TEST" + "  (ID_TEST, NOMBRE_TEST) VALUES " + " (?, ?)";

	private static final String QUERY = "SELECT * FROM TEST";

	private static final String UPDATE_USERS_SQL = "update TEST set NOMBRE_TEST = ? where ID_TEST = ?";

	private static final String DELETE_USERS_SQL = "delete from TEST where ID_TEST = 1";

	public void createTable() throws SQLException {

		System.out.println("\n\n" + createTableSQL);
		try (Connection connection = DriverManager
				.getConnection("jdbc:oracle:thin:ADMIN_DB/ecommerce@localhost:1521:XE");

				// Step 2:Create a statement using connection object
				Statement statement = connection.createStatement();) {

			// Step 3: Execute the query or update query
			statement.execute(createTableSQL);
		} catch (SQLException e) {

			// print SQL exception information
			printSQLException(e);
		}

		// Step 4: try-with-resource statement will auto close the connection.
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("\n\nSQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public void insertRecord(JSONObject values, int count_values) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager
				.getConnection("jdbc:oracle:thin:ADMIN_DB/ecommerce@localhost:1521:XE");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "Tony");

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			// print SQL exception information
			printSQLException(e);
		}

		// Step 4: try-with-resource statement will auto close the connection.
	}

	public String makeQueryDML(JSONObject values, String typeDML, String table) {
		switch (typeDML) {
		case "INSERT": {
			String DMLresult = typeDML + " INTO " + table + " (";
			for (int count = 0; count < values.length(); count++) {
				DMLresult = DMLresult + JSONObject.getNames(values)[count];

				if ((count + 1) != values.length()) {
					DMLresult = DMLresult + ",";
				} else {
					DMLresult = DMLresult + ")";
					break;
				}
			}
			// FIN DEL CICLO QUE PONE LOS NOMBRES DE LAS COLUMNAS. ESTO ME OBLIGA A
			// COLOCARLOS EN EL JSON.
			DMLresult = DMLresult + " VALUES (";
			for (int count = 0; count < values.length(); count++) {
				DMLresult = DMLresult + "?";
				if ((count + 1) != values.length()) {
					DMLresult = DMLresult + ",";
				} else {
					DMLresult = DMLresult +")";
					break;
				}
			}
			// ESTE CICLO RECORRE Y EVALUA CADA DATA PARA INSERTAR.
			return DMLresult;
		}
		}
		return null;
	}// FIN DEL METODO DE CREACION DEL QUERY DML

	public void selectRecord() throws SQLException {
		// using try-with-resources to avoid closing resources (boiler plate code)

		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager
				.getConnection("jdbc:oracle:thin:ADMIN_DB/ecommerce@localhost:1521:XE");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("ID_TEST");
				String nombre = rs.getString("NOMBRE_TEST");

				System.out.println(id + "," + nombre);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
	}

	public void updateRecord() throws SQLException {
		System.out.println(UPDATE_USERS_SQL);
		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager
				.getConnection("jdbc:oracle:thin:ADMIN_DB/ecommerce@localhost:1521:XE");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			preparedStatement.setInt(2, 2);
			preparedStatement.setString(1, "Betty");

			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			// print SQL exception information
			printSQLException(e);
		}

		// Step 4: try-with-resource statement will auto close the connection.
	}

	public void deleteRecord() throws SQLException {
		System.out.println(DELETE_USERS_SQL);

		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager
				.getConnection("jdbc:oracle:thin:ADMIN_DB/ecommerce@localhost:1521:XE");

				// Step 2:Create a statement using connection object
				Statement statement = connection.createStatement();) {

			// Step 3: Execute the query or update query
			int result = statement.executeUpdate(DELETE_USERS_SQL);
			System.out.println("Number of records affected :: " + result);
		} catch (SQLException e) {

			// print SQL exception information
			printSQLException(e);
		}

		// Step 4: try-with-resource statement will auto close the connection.
	}

	public static void main(String[] argv) throws SQLException {
		DatabaseCRUD test = new DatabaseCRUD();
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.putOnce("edad", 24);
		json.put("nombre", "Faustino");
		System.out.println(test.makeQueryDML(json, "INSERT", "USER"));
	}

}
