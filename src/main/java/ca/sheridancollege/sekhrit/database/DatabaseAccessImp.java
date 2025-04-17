package ca.sheridancollege.sekhrit.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public interface DatabaseAccessImp<T>  {
	
	
    public T getOne(T t);
	public List<T> getAll ();
	
	public void postOne (T t);
    public void postCollection(List<T> t);

	public void putOne (T t);
    public void putCollection(List<T> t);

	public void patchOne (T t);
    public void patchCollection(List<T> t);

	public void deleteOne (T t);
    public void deleteCollection();

}
