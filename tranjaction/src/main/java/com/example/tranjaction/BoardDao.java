package com.example.tranjaction;

import java.util.List;

public interface BoardDao 
{
	public List<Board> selectAll();
	public int insert(Board board) throws Exception;
	public int update(Board board);
	public int delete(int idx);
}
