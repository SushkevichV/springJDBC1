package by.sva.springCRUD.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.sva.springCRUD.models.Person;

// используется, если стандартный RowMapper не подходит и нужен свой RowMapper

public class PersonMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(resultSet.getInt("id"));
		person.setName(resultSet.getString("name"));
		person.setAge(resultSet.getInt("age"));
		person.setEmail(resultSet.getString("email"));
		return person;
	}

}
