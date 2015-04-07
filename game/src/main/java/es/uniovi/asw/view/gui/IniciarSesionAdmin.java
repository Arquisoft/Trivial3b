package es.uniovi.asw.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.controller.game.GameFactory;
import es.uniovi.asw.util.FileUtil;

public class IniciarSesionAdmin extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txLogin;
	private JPasswordField pFpassword;

	/**
	 * Create the dialog.
	 */
	public IniciarSesionAdmin() {
		setResizable(false);
		setIconImage(FileUtil.getImage("images/icono.jpg"));
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comprobarEsAdministrador()) {
					mostrarVentanaEstadisticas();
				} else {
					JOptionPane
							.showMessageDialog(null,
									"El usuario no existe o no tiene permisos de administracion");
				}
			}
		});
		btAceptar.setBounds(131, 160, 164, 20);
		contentPanel.add(btAceptar);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			@Override
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

	@SuppressWarnings("deprecation")
	private boolean comprobarEsAdministrador() {
		if (GameFactory.newGameService().isAdmin(txLogin.getText(),
				pFpassword.getText())) {
			return true;
		} else {
			return false;
		}
	}

	private void mostrarVentanaPrincipal() {
		Principal prin = new Principal();
		prin.setLocationRelativeTo(null);
		prin.setVisible(true);
		dispose();

	}

	private void mostrarVentanaEstadisticas() {
		TablaEstadistica est = new TablaEstadistica();
		est.setLocationRelativeTo(null);
		est.setVisible(true);
		dispose();

	}

}
