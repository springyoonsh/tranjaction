package com.example.tranjaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService 
{		
	BoardDaoImpl dao;
	
	@Autowired
	public BoardService(BoardDaoImpl dao) 
	{
		this.dao = dao;
	}	
	
	@Transactional
	public void execute() 
	{
		Board board1  = new Board(2,"tran2","tran2");
		Board board2  = new Board(2,"tran2","tran2");
		
		try {
			dao.insert(board1);
			dao.insert(board2);
			System.out.println("OK");
		} catch (Exception e) {
			new RuntimeException();
			System.out.println("ERROR");
			//e.printStackTrace();
		}
	}

}
