import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

/************************************************/
//LIBRERIAS DEDICADAS A LA CONEXIÓN
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/************************************************/

public class App extends JFrame {
	
	/************************************************/
	//VARIABLES DEDICADAS A LA CONEXIÓN CON LA BBDD
	private static Connection connection = null;
	private static ResultSet result = null;
	private static Statement statement = null; 
	/************************************************/
	private JPanel contentPane;
	protected int x, y;
	
	public App() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1332, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(60, 179, 113)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		/*PANEL DEL CENTRO, EN ESTE IRA PRACTOCAMENTE TODO, ESTE ES EL PANEL QUE ESTA SIEMPRE EN MOVIMIENTO, OSEA CAMBIA CRECUENTEMENTE.*/
		
		JPanel jpCentro = new JPanel(null);
		jpCentro.setBounds(76, 77, 1253, 720);
		contentPane.add(jpCentro);
		
		/**********************************************************************/

		
		/* PANEL DE DASHBOARD ENCARGADO DE MANTENER LOS ICONOS Y FUNCIONES PRINCIPALES DEL APP*/
		
		JPanel jpdashboard = new JPanel();
		jpdashboard.setBackground(new Color(0,150,36));
		jpdashboard.setBounds(2, 2, 72, 796);
		contentPane.add(jpdashboard);
		jpdashboard.setLayout(null);
		
			JButton jbexit = new JButton("");
			jbexit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						dispose();
						Thread.sleep(1500);
					} catch (Exception e) {}
					System.exit(0);
					
					 new Thread(new Runnable() {
						 @Override
						public void run() {
							// TODO Auto-generated method stub
							App.conexion();
						}
					 });
					
				}
			});
			
			JLabel jlLogo = new JLabel("");
			jlLogo.setIcon(new ImageIcon(App.class.getResource("/assets/log_ajustado.png")));
			jlLogo.setBounds(5, 5, 62, 62);
			jpdashboard.add(jlLogo);
			jbexit.setToolTipText("Cerrar app");
			jbexit.setIcon(new ImageIcon(App.class.getResource("/assets/exit.png")));
			jbexit.setFocusable(false);
			jbexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbexit.setBorderPainted(false);
			jbexit.setContentAreaFilled(false);
			jbexit.setBorder(null);
			jbexit.setBounds(14, 745, 43, 42);
			jpdashboard.add(jbexit);
			
		/**********************************************************************/

			
		/*PANEL DEL NORTE, TIENE DRAWNER ON SCREEN Y ES DONDE IRA LA INFO DE USUARIO*/
			
		JPanel jpHeader = new JPanel(null);								
		jpHeader.addMouseListener(new MouseAdapter() {
			@Override
		public void mousePressed(MouseEvent a) {
				x=a.getX(); y=a.getY();
			}
		});
		jpHeader.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent a) {
				int xx=a.getXOnScreen(), yy=a.getYOnScreen();
				setLocation(xx-x,yy-y);
			}
		});
		jpHeader.setBounds(2, 3, 1327, 73);
		contentPane.add(jpHeader);
		
		JLabel jlNameUser = new JLabel("AB");
		jlNameUser.setBounds(1241, 25, 62, 34);
		jpHeader.add(jlNameUser);
		jlNameUser.setToolTipText("Informacion de usuario");
		jlNameUser.setForeground(new Color(169, 169, 169));
		jlNameUser.setHorizontalAlignment(SwingConstants.CENTER);
		jlNameUser.setFont(new Font("Arial", Font.BOLD, 20));
		jlNameUser.setFocusable(false);
		jlNameUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton jbinfoUser = new JButton("");
		jbinfoUser.setBounds(1242, 12, 62, 61);
		jpHeader.add(jbinfoUser);
		jbinfoUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbinfoUser.setIcon(new ImageIcon(App.class.getResource("/assets/circle.png")));
		jbinfoUser.setToolTipText("Informacion de usuario");
		jbinfoUser.setFocusable(false);
		jbinfoUser.setContentAreaFilled(false);
		jbinfoUser.setBorderPainted(false);
		jbinfoUser.setBorder(null);
			
		
		
		/**********************************************************************/
			
	}
	
	//MÉTODO DEDICADO A LA CONEXIÓN DE LA BBDD
	private static void conexion() {
		//VARIABLES QUE INDICARÁN LA DIRECCIÓN DE LA BBDD, LA CONTRASEÑA Y EL ESQUEMA
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String password = "ecommerce";
		String user = "ADMIN_DB";
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(url,user,password);
			
			if (connection != null) {
				JOptionPane.showMessageDialog(null,"Conexion Establecida" ); 
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problemas de conexión");
		}
	}
}
