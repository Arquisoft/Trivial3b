package es.uniovi.asw.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaDeLogueo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager
							.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel"); // En
																											// este
																											// caso
																											// se
																											// utilizó
																											// el
																											// tema
																											// "Blue Light",
																											// reemplazar
																											// por
																											// el
																											// tema
																											// deseado.
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					VentanaDeLogueo frame = new VentanaDeLogueo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaDeLogueo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 748, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		panelBotones.setPreferredSize(new Dimension(10, 35));
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Nuevo Usuario");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaDeRegistro iU = new VentanaDeRegistro();
				iU.setLocationRelativeTo(contentPane);
				iU.setModal(true);
				iU.setVisible(true);
				}
			});
		panelBotones.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Iniciar  Sesion");
		panelBotones.add(btnNewButton);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setPreferredSize(new Dimension(100, 100));
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		
		JPanel panelLogueo = new JPanel();
		panelLogueo.setBorder(new TitledBorder(null, "Iniciar Sesion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelLogueo, BorderLayout.CENTER);
		panelLogueo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setPreferredSize(new Dimension(10, 142));
		panelLogueo.add(panelUsuario, BorderLayout.NORTH);
		panelUsuario.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 50));
		lblNewLabel.setBounds(128, 41, 196, 48);
		panelUsuario.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		textField.setBounds(423, 49, 287, 48);
		panelUsuario.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VentanaDeLogueo.class.getResource("/es/uniovi/asw/resources/hombre-negro-de-un-usuario-icono-7176-48.png")));
		lblNewLabel_1.setBounds(49, 41, 46, 46);
		panelUsuario.add(lblNewLabel_1);
		
		JPanel panelContraseña = new JPanel();
		panelContraseña.setPreferredSize(new Dimension(10, 148));
		panelLogueo.add(panelContraseña, BorderLayout.SOUTH);
		panelContraseña.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		textField_1.setBounds(444, 50, 266, 49);
		panelContraseña.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setFont(new Font("Lucida Sans", Font.PLAIN, 50));
		lblNewLabel_2.setBounds(125, 50, 309, 60);
		panelContraseña.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VentanaDeLogueo.class.getResource("/es/uniovi/asw/resources/palabra-clave-de-contrasena-pasar-icono-5906-48.png")));
		lblNewLabel_3.setBounds(46, 33, 53, 90);
		panelContraseña.add(lblNewLabel_3);
	}
}

