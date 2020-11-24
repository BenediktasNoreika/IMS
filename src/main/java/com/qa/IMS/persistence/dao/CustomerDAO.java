package com.qa.IMS.persistence.dao;


	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;

    import com.qa.IMS.persistence.domain.Customer;
	import com.qa.IMS.utils.DBUtils;

	public class CustomerDAO implements Dao<Customer> {

		public static final Logger LOGGER = LogManager.getLogger();

		@Override
		public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
			Long id = resultSet.getLong("customer_id");
			String firstName = resultSet.getString("first_name");
			String surname = resultSet.getString("last_name");
			return new Customer(id, firstName, surname);
		}

		/**
		 * Reads all customers from the database
		 * 
		 * @return A list of customers
		 */
		@Override
		public List<Customer> readAll() {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from customer");) {
				List<Customer> customers = new ArrayList<>();
				while (resultSet.next()) {
					customers.add(modelFromResultSet(resultSet));
				}
				return customers;
			} catch (SQLException e) {
				LOGGER.debug(e);
				System.out.println(e.getMessage());
			}
			return new ArrayList<>();
		}

		public Customer readLatest() {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1");) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			} catch (Exception e) {
				LOGGER.debug(e);
				System.out.println(e.getMessage());
			}
			return null;
		}

		/**
		 * Creates a customer in the database
		 * 
		 * @param customer - takes in a customer object. id will be ignored
		 */
		@Override
		public Customer create(Customer customer) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("INSERT INTO customer(first_name, last_name) values('" + customer.getFirstName()
						+ "','" + customer.getSurname() + "')");
				return readLatest();
			} catch (Exception e) {
				LOGGER.debug(e);
				System.out.println(e.getMessage());
			}
			return null;
		}

		public Customer readCustomer(Long id) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM customer where customer_id = " + id);) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			} catch (Exception e) {
				LOGGER.debug(e);
				System.out.println(e.getMessage());
			}
			return null;
		}

		/**
		 * Updates a customer in the database
		 * 
		 * @param customer - takes in a customer object, the id field will be used to
		 *                 update that customer in the database
		 * @return
		 */
		@Override
		public Customer update(Customer customer) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("update customer set first_name ='" + customer.getFirstName() + "', last_name ='"
						+ customer.getSurname() + "' where customer_id =" + customer.getId());
				return readCustomer(customer.getId());
			} catch (Exception e) {
				LOGGER.debug(e);
				System.out.println(e.getMessage());
			}
			return null;
		}

		/**
		 * Deletes a customer in the database
		 * 
		 * @param id - id of the customer
		 */
		@Override
		public int delete(long id) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();) {
				return statement.executeUpdate("delete from customer where customer_id = " + id);
			} catch (Exception e) {
				LOGGER.debug(e);
				System.out.println(e.getMessage());
			}
			return 0;
		}

	}