package config;

import java.util.ArrayList;

public interface InterfaceMainCRUD {
	public String create(ArrayList<Object> load_data) throws Exception;
	public String read(ArrayList<Object> identified) throws Exception;
	public String update(ArrayList<Object> identified) throws Exception;
	public String delete(ArrayList<Object> idendified) throws Exception;
}
