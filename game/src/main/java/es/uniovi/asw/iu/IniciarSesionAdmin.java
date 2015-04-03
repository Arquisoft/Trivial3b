package es.uniovi.asw.iu;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class IniciarSesionAdmin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txLogin;
	private JPasswordField pFpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			IniciarSesionAdmin dialog = new IniciarSesionAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public IniciarSesionAdmin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				("src/main/resources/images/icono.jpg")));
		setTitle("Iniciar sesion administrador");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbUsuario = new JLabel("Login de administrador");
		lbUsuario.setBounds(155, 11, 144, 32);
		contentPanel.add(lbUsuario);
		
		txLogin = new JTextField();
		txLogin.setBounds(97, 42, 226, 20);
		contentPanel.add(txLogin);
		txLogin.setColumns(10);
		
		JLabel lbPassword = new JLabel("Password administrador");
		lbPassword.setBounds(151, 81, 144, 32);
		contentPanel.add(lbPassword);
		
		JButton btAceptar = new JButton("Iniciar sesion");
		btAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarVentanaEstadisticas();
			}
		});
		btAceptar.setBounds(131, 160, 164, 20);
		contentPanel.add(btAceptar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarVentanaPrincipal();
			}
		});
		btnAtras.setBounds(131, 203, 164, 20);
		contentPanel.add(btnAtras);
		
		pFpassword = new JPasswordField();
		pFpassword.setBounds(97, 113, 226, 20);
		contentPanel.add(pFpassword);
	}
	
	
	private void mostrarVentanaPrincipal(){
		Principal prin = new Principal();
		prin.setLocationRelativeTo(null);
		prin.setVisible(true);
		dispose();
	
	}
	
	private void mostrarVentanaEstadisticas(){
		TablaEstadistica est = new TablaEstadistica();
		est.setLocationRelativeTo(null);
		est.setVisible(true);
		dispose();
	
	}
	
}
