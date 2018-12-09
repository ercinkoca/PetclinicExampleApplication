package com.ercin.petclinic.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ercin.petclinic.dao.OwnerRepository;
import com.ercin.petclinic.model.Owner;

@Repository
public class OwnerRepositoryJdbcImpl implements OwnerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Owner> rowMapper=new RowMapper<Owner>() {
		
		@Override
		public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
			Owner owner=new Owner();//mapRow metodu direk tekrar tekrar kod yazılmasın diye veritabanından dönen değerlerin owner classına set edilmesini sağlıyor.
			owner.setId(rs.getLong("id"));
			owner.setFirstName(rs.getString("first_name"));
			owner.setLastName(rs.getString("last_name"));
			return owner;
		}
	};
	
	@Override
	public List<Owner> findAll() {//jdbcTemplate sayesinde sadece connection açmamıza vs. gerek kalmıyor.
		String sql="select id,first_name,last_name from t_owner";
		return jdbcTemplate.query(sql, rowMapper);//query metodu sql sorgusunun çalıştırılmasına ve dönecek olan değerin rowMapper metoduyla dönmesine olanak sağlıyor.
	}

	@Override
	public Owner findById(Long id) {
		String sql="select id,first_name,last_name from t_owner where id = ?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper,id));//tek eleman döndüreceği için bunu kullandık.
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
		String sql="select id,first_name,last_name from t_owner where last_name = ?";
		return jdbcTemplate.query(sql, rowMapper,lastName);
	}

	@Override
	public void create(Owner owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		String sql="delete from t_owner where id = ?";
		jdbcTemplate.update(sql,id);

	}

}
