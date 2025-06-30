package dao;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<E> {
	void insert(E e)throws SQLException;
	void delete(E e)throws SQLException;
	void update(E e)throws SQLException;
	E getById(int id)throws SQLException;
	List<E>  getByName(String nom)throws SQLException;
	List<E> getAll()throws SQLException;
	
}
