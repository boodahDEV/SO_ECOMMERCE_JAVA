package layouts;
import javax.swing.JPanel;

public class ChangeLayout {
	
	public ChangeLayout(JPanel contenedor, JPanel contenido) {		
		contenedor.removeAll();
		contenedor.revalidate();
		contenedor.repaint();
		
		contenedor.add(contenido);
		contenedor.revalidate();
		contenedor.repaint();
	}
}
