package by.sva.springCRUD.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import by.sva.springCRUD.models.Person;

@Component
public class PersonDAO {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDAO (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Person> index(){
		//return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper()); // если используется свой RowMapper
		return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)); // если используется стандартный RowMapper.
																										// для этого имена полей объекта должны совпадать с названиями колонок БД
																										// При этом Class PersonMapper можно не создавать
	}
	
	public Person show(int id) {
		//return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null); // если используется свой RowMapper 
		return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}

	public void save (Person person, int count) {
		jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?)", count, person.getName(), person.getAge(), person.getEmail());
	}
	

	public void update (int id, Person person) {
		jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", person.getName(), person.getAge(), person.getEmail(), person.getId());
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
	}
	
}
