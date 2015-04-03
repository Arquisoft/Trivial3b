package es.uniovi.asw.iu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.google.common.util.concurrent.Service;

import es.uniovi.asw.game.GameService;
import es.uniovi.asw.game.impl.GameServiceImpl;

public class Registro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField usuario;
	private JPasswordField contraseña;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPanel label;
	private GameService servicio;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 412);
		servicio=new GameServiceImpl();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getLabel());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Registrar Nuevo Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(53, 94, 391, 136);
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getLblNewLabel_1());
			panel.add(getUsuario());
			panel.add(getContraseña());
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Usuario:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblNewLabel.setBounds(34, 37, 93, 26);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Password:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblNewLabel_1.setBounds(34, 80, 103, 26);
		}
		return lblNewLabel_1;
	}
	private JTextField getUsuario() {
		if (usuario == null) {
			usuario = new JTextField();
			usuario.setBounds(137, 44, 213, 20);
			usuario.setColumns(10);
		}
		return usuario;
	}
	private JPasswordField getContraseña() {
		if (contraseña == null) {
			contraseña = new JPasswordField();
			contraseña.setBounds(137, 87, 213, 20);
		}
		return contraseña;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Registrar");
			btnNewButton.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
				if(!servicio.registerUser(usuario.getText(), contraseña.getText())){
					JOptionPane.showMessageDialog(getParent(), "El usuario que se intenta registrar ya existe en el sistema");
				}
				else{
					JOptionPane.showMessageDialog(getParent(), "Se ha registrado correctamente en el sistema");
				}
				}
			});
			btnNewButton.setBounds(365, 323, 123, 39);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Volver");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Principal principal=new Principal();
					principal.setVisible(true);
					principal.setLocationRelativeTo(getParent());
					dispose();
				}
			});
			btnNewButton_1.setBounds(251, 323, 104, 39);
		}
		return btnNewButton_1;
	}
	private JPanel getLabel() {
		if (label == null) {
			label = new JPanel();
			label.setBounds(0, 0, 498, 373);
			label.add(getLblNewLabel_2());
		}
		return label;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(
					("src/main/resources/images/Trivial-mobile_opt.jpg")));
		}
		return lblNewLabel_2;
	}
}
