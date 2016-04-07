package com.example.tranjaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDaoImpl implements BoardDao
{		
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Board> rowMapper = new RowMapper<Board>()
	{
		@Override
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Board board = new Board( rs.getString("user_id"), rs.getString("user_pw") );			
			return board;
		}		
	};
	
	@Override
	public List<Board> selectAll() 
	{
		String sql = " SELECT * FROM board  ";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@Transactional
	@Override
	public int insert(Board board) throws Exception
	{	
		String sql = " INSERT INTO board(idx, user_id,user_pw) values(?,?,?) ";		
		return jdbcTemplate.update(sql,new Object[]{
														board.getIdx(),
														board.getUser_id(),
														board.getUser_pw()				
													});		
	}		

	@Override
	public int update(Board board) {
		String sql = " UPDATE board SET user_id=?,user_pw=? WHERE idx=" + board.getIdx();	
		System.out.println(sql);
		return	jdbcTemplate.update(sql, new Object[]{
				board.getUser_id(),
				board.getUser_pw()				
		});
	}

	@Override
	public int delete(int idx) {
		String sql = " DELETE FROM board WHERE idx="+idx ;		
		return jdbcTemplate.update(sql);
	}



	
}
