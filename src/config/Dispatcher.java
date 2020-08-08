package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JLabel;

import org.json.JSONObject;

import at.favre.lib.crypto.bcrypt.BCrypt;

public abstract class Dispatcher {
	protected static int MAXIMO_TAREAS_A_PROCESAR = 3; // BASICAMENTE UN MAXIMO DE 3 TAREAS CONCURRENTES.
	public String TipoDeTarea;
	
	public static Thread accionesSecundarias[] = new Thread[MAXIMO_TAREAS_A_PROCESAR];
	public int countFile = 0;
	public Dispatcher(String TipoDeTarea){
		this.TipoDeTarea=TipoDeTarea;
	}
	
	public static void encapsulatesSessionData(JSONObject sessionData) {
		//BCrypt.withDefaults().hashToString(12,jpfPass.getPassword() )
		JSONObject finalSessionData	= new JSONObject();	
//		accionesSecundarias[0].
		
	} //---=== METODO QUE ENCAPSULA Y CIFRA LOS DATOS DE SESION
	
	public static void controlVersions(String archivo, JLabel label){
		try {
		      File ruta = new File (archivo);
		      FileReader f = new FileReader(ruta);
		      BufferedReader b = new BufferedReader(f);
		      String lines;
		      while((lines=b.readLine())!=null){
		             label.setText(lines+"\n");
		        }
		      b.close();
		}catch(Exception e) {System.out.println("ERROR File! \n"+e);}
	}//---=== METODO EN EL CUAL CONTROLA LAS VERSIONES DEL PROGRAMA
	
	public static boolean outFileData(String TipoDeTarea) {
		ArrayList <Object> lista = new ArrayList<Object>();

//		try {
//			ObjectInputStream input = new ObjectInputStream(new FileInputStream(""+TipoDeTarea+"-"+countFile+"-"+LocalDate.now()+"-"+LocalTime.now()+"-0"+countFile));
//			
//			ArrayList<Object[]> data_recuperada = (ArrayList<Object[]>) input.readObject();
//			
//			input.close();	
//			
//			Object []listaNueva = new Object[data_recuperada.size()];
//			data_recuperada.toArray(listaNueva);
//			for(Object e: listaNueva)
//				lista.add(e);
//			
//		}catch (Exception e1) {System.out.println("ERROR1\n"+e1);  }
//		
		lista.add("nuevos datos");
		
		try{	
//			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(""+TipoDeTarea+"-"+countFile+"-"+LocalDate.now()+"-"+LocalTime.now()+"-0"+countFile));
//			out.writeObject(lista);
//			System.out.println("Los datos se han cargados con EXITO!");
//				out.close();
			return true;
		}catch(Exception e){System.out.println("ERROR2\n"+e); }
		
		return false;		
	} //---=== METODO PARA GENERAR ARCHIVOS DE SALIDA EN EL SISTEMA
	
	
	public static void queryDatabaseConnect() {
		
	} //---=== ENCARGADO DE EJECUTAR EL HILO DE CONECCION Y CRUD DE LA BASE DE  DATOS
	
	
}
