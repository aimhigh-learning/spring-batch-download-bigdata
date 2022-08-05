package org.ranasoftcraft.com.main.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Reader extends JdbcCursorItemReader implements ItemReader {
	
	public Reader(@Autowired DataSource primaryDataSource) {
		setDataSource(primaryDataSource);
		setSql("SELECT id, name, salary FROM users");
		setFetchSize(100);
		setRowMapper(new EmployeeRowMapper());
	}
	
	public class EmployeeRowMapper implements org.springframework.jdbc.core.RowMapper<Users> {
		@Override
		public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
			Users employee  = new Users();
			employee.setId(rs.getInt("id"));
			employee.setName(rs.getString("name"));
			employee.setSalary(rs.getInt("salary"));
			return employee;
		}
	}

}
