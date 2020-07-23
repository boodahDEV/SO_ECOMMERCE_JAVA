import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import org.json.JSONObject;

import Animacion.Animacion;
import at.favre.lib.crypto.bcrypt.BCrypt;
import config.Dispatcher;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import helper.MaterialButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MainApp extends JFrame {

	private JPanel contentPane;
	private JTextField jtfEmail;
	private JPasswordField jpfPass;
	protected int x, y;
	private JPasswordField jpfConfirmar;
	private JTextField jtfusername;
	public boolean switchIsActive = true;
	public static int RETARDO = 1, SALTO = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainApp frame = new MainApp();
		frame.setTitle("SO! ECOMMERCE");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */

	public MainApp() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 523);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(10, 152, 42)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jpForLogo = new JPanel();
		jpForLogo.setBackground(new Color(255, 255, 255));
		jpForLogo.setBounds(3, 3, 367, 517);
		contentPane.add(jpForLogo);
		jpForLogo.setLayout(null);

		JLabel jlLogo = new JLabel("");
		jlLogo.setIcon(new ImageIcon(MainApp.class.getResource("/assets/WhatsApp Image 2020-07-09 at 22.11.31.jpeg")));
		jlLogo.setBounds(0, 100, 313, 307);
		jpForLogo.add(jlLogo);

		JLabel jlTitle = new JLabel("Inicio de sesion");
		jlTitle.setForeground(new Color(10, 152, 42));
		jlTitle.setFont(new Font("Arial", Font.BOLD, 35));
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setBounds(460, 80, 277, 60);
		contentPane.add(jlTitle);

		jtfEmail = new JTextField();
		jtfEmail.setForeground(new Color(128, 128, 128));
		jtfEmail.setText("test@gmail.com");
		jtfEmail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(10, 152, 42)));
		jtfEmail.setBounds(517, 178, 223, 30);
		jtfEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfEmail.setColumns(10);
		contentPane.add(jtfEmail);

		jpfPass = new JPasswordField();
		jpfPass.setForeground(new Color(128, 128, 128));
		jpfPass.setFont(new Font("Arial", Font.PLAIN, 20));
		jpfPass.setText("passprueba");
		jpfPass.setEchoChar('*');
		jpfPass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(10, 152, 42)));
		jpfPass.setBounds(517, 252, 223, 30);
		contentPane.add(jpfPass);

		JLabel jlicon_email = new JLabel("");
		jlicon_email.setIcon(new ImageIcon(MainApp.class.getResource("/assets/email.png")));
		jlicon_email.setBounds(457, 165, 60, 45);
		contentPane.add(jlicon_email);

		JLabel jlicon_pass = new JLabel("");
		jlicon_pass.setIcon(new ImageIcon(MainApp.class.getResource("/assets/pass.png")));
		jlicon_pass.setBounds(457, 230, 55, 50);
		contentPane.add(jlicon_pass);

		jpfConfirmar = new JPasswordField();
		jpfConfirmar.setVisible(false);
		jpfConfirmar.setForeground(Color.GRAY);
		jpfConfirmar.setFont(new Font("Arial", Font.PLAIN, 20));
		jpfConfirmar.setEchoChar('*');
		jpfConfirmar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(10, 152, 42)));
		jpfConfirmar.setBounds(517, 330, 223, 30);
		contentPane.add(jpfConfirmar);

		JLabel jlExample = new JLabel("Ejm: Antonio Ramirez");
		jlExample.setVisible(false);
		jlExample.setForeground(new Color(169, 169, 169));
		jlExample.setFont(new Font("Arial", Font.BOLD, 11));
		jlExample.setBounds(514, 231, 119, 14);
		contentPane.add(jlExample);

		jtfusername = new JTextField();
		jtfusername.setVisible(false);
		jtfusername.setForeground(Color.GRAY);
		jtfusername.setFont(new Font("Arial", Font.PLAIN, 20));
		jtfusername.setColumns(10);
		jtfusername.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(10, 152, 42)));
		jtfusername.setBounds(517, 200, 223, 30);
		contentPane.add(jtfusername);

		JLabel jlicon_user = new JLabel("");
		jlicon_user.setFocusable(false);
		jlicon_user.setIcon(new ImageIcon(MainApp.class.getResource("/assets/user.png")));
		jlicon_user.setVisible(false);
		jlicon_user.setBounds(457, 187, 55, 50);
		contentPane.add(jlicon_user);

		MaterialButton jbinit = new MaterialButton();
		jbinit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (a.getActionCommand().equalsIgnoreCase("Iniciar")) {
					// =--- ACCION DEL BOTON PARA EL CASO LOGIN

					// VALIDAR CON EL PATTER Y MATCHER SI ES UN CORREO DE VERDAD CON EXPRESION
					// REGULAR
					
					JSONObject sessionData = new JSONObject();
					sessionData.put("email", jtfEmail.getText().trim());
					sessionData.put("pass", String.valueOf(jpfPass.getPassword()));
					sessionData.put("sessionDate",
							DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
					Dispatcher.encapsulatesSessionData(sessionData);
				} else {
					// =--- ACCION DEL BOTON PARA EL CASO REGISTRO

				}

			}
		});
		jbinit.setColorPressed(new Color(10, 152, 42));
		jbinit.setColorHover(new Color(123, 203, 131));
		jbinit.setColorNormal(new Color(10, 152, 42));
		jbinit.setText("Iniciar");
		jbinit.setFocusable(false);
		jbinit.setBounds(535, 390, 130, 50);
		contentPane.add(jbinit);

		JButton jbNavigate = new JButton("");
		jbNavigate.setBorder(null);
		jbNavigate.setBorderPainted(false);
		jbNavigate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				App app = new App();
				app.setLocationRelativeTo(null);
				app.setVisible(true);

				try {
					dispose();
					Thread.sleep(1500);
				} catch (Exception e) {
				}

			}
		});
		jbNavigate.setIcon(new ImageIcon(MainApp.class.getResource("/assets/navigate.png")));
		jbNavigate.setFocusable(false);
		jbNavigate.setContentAreaFilled(false);
		jbNavigate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbNavigate.setBounds(745, 453, 45, 45);
		contentPane.add(jbNavigate);

		JLabel jlTextNavigation = new JLabel("Explorar");
		jlTextNavigation.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		jlTextNavigation.setForeground(new Color(128, 128, 128));
		jlTextNavigation.setBounds(738, 495, 65, 18);
		contentPane.add(jlTextNavigation);

		JLabel jlswitch = new JLabel("No tienes cuenta? Registrate aqui!");
		jlswitch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jlswitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jlswitch.setForeground(new Color(10, 152, 42)); // SIMPLE HOVER AL LABEL
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jlswitch.setForeground(new Color(123, 203, 131)); // SIMPLE HOVER AL LABEL
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/* AJUSTES AL PANEL PARA EL REGISTRO! */

				if (switchIsActive) {
					Animacion.subir(80, 45, 1, 1, jlTitle);
					jlTitle.setText("Regístrate");

					Animacion.subir(178, 150, RETARDO, SALTO, jtfEmail);
					Animacion.subir(165, 135, RETARDO, SALTO, jlicon_email);
					Animacion.bajar(252, 280, RETARDO, SALTO, jpfPass);
					Animacion.bajar(230, 260, RETARDO, SALTO, jlicon_pass);

					jlicon_user.setVisible(true);
					jtfusername.setVisible(true);
					jlExample.setVisible(true);
					jpfConfirmar.setVisible(true);
					Animacion.subir(200, 205, RETARDO, SALTO, jtfusername);
					Animacion.subir(187, 192, RETARDO, SALTO, jlicon_user);
					Animacion.subir(231, 236, RETARDO, SALTO, jlExample);
					Animacion.subir(330, 335, RETARDO, SALTO, jpfConfirmar);

					Animacion.bajar(390, 425, RETARDO, SALTO, jbinit);
					jbinit.setText("Continuar");
					Animacion.bajar(340, 380, RETARDO, SALTO, jlswitch);
					jlswitch.setText("Ya tienes cuenta? Inicia aqui!");

					jbNavigate.setVisible(false);
					jlTextNavigation.setVisible(false);
					switchIsActive = false;
				} else {
					Animacion.bajar(45, 80, RETARDO, SALTO, jlTitle);
					jlTitle.setText("Iniciar sesion");

					Animacion.bajar(150, 178, RETARDO, SALTO, jtfEmail);
					Animacion.bajar(135, 165, RETARDO, SALTO, jlicon_email);
					Animacion.subir(280, 252, RETARDO, SALTO, jpfPass);
					Animacion.subir(260, 230, RETARDO, SALTO, jlicon_pass);

					Animacion.bajar(205, 200, RETARDO, SALTO, jtfusername);
					Animacion.bajar(192, 187, RETARDO, SALTO, jlicon_user);
					Animacion.bajar(236, 231, RETARDO, SALTO, jlExample);
					Animacion.bajar(335, 330, RETARDO, SALTO, jpfConfirmar);
					jlicon_user.setVisible(false);
					jtfusername.setVisible(false);
					jlExample.setVisible(false);
					jpfConfirmar.setVisible(false);

					Animacion.subir(425, 390, 1, 1, jbinit);
					jbinit.setText("Iniciar");
					Animacion.subir(380, 340, 1, 1, jlswitch);
					jlswitch.setText("No tienes cuenta? Registrate aqui!");

					jbNavigate.setVisible(true);
					jlTextNavigation.setVisible(true);
					switchIsActive = true;
				}

			}
		});
		jlswitch.setForeground(new Color(123, 203, 131));
		jlswitch.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		jlswitch.setBounds(440, 340, 326, 30);
		contentPane.add(jlswitch);

		JButton jbexit = new JButton("");
		jbexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		jbexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbexit.setBorderPainted(false);
		jbexit.setContentAreaFilled(false);
		jbexit.setIcon(new ImageIcon(MainApp.class.getResource("/assets/exitv2.png")));
		jbexit.setFocusable(false);
		jbexit.setBorder(null);
		jbexit.setBounds(775, 5, 30, 25);
		contentPane.add(jbexit);

		JLabel jlmouseMotion = new JLabel("New label");
		jlmouseMotion.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
		jlmouseMotion.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen(), yy = e.getYOnScreen();
				setLocation(xx - x, yy - y);
			}
		});
		jlmouseMotion.setBounds(3, 3, 802, 77);
		contentPane.add(jlmouseMotion);

	}
}
