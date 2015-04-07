package es.uniovi.asw.view.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.util.FileUtil;

public class Principal extends JFrame {

	/**
     *
     */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					javax.swing.UIManager
							.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(FileUtil.getImage("images/icono.jpg"));
		setResizable(false);
		setTitle("Trivial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 371);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Estadisticas");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaInicioSesionAdmin();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Opciones");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Acerca de");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
						.showMessageDialog(
								null,
								"Equipo formado por: \n Alcalá Pérez Joel \n "
										+ "Fernández Morán	Raúl \n García García Daniel \n Llana Pérez Sergio \n "
										+ "López Soto Jairo \n Navia Santos Cristian Javier \n García Fernández José Manuel",
								"Trivial 3b", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Salir");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnJugar = new JButton("Partida Rapida");
		btnJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarVentanaJugadores();
			}
		});
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnJugar.setActionCommand("");
		btnJugar.setBounds(90, 219, 200, 50);
		contentPane.add(btnJugar);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Registro registro = new Registro();
				registro.setLocationRelativeTo(null);
				registro.setModal(true);
				registro.setVisible(true);

			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnRegistrarse.setActionCommand("");
		btnRegistrarse.setBounds(327, 219, 200, 50);
		contentPane.add(btnRegistrarse);
		contentPane.add(getPanel_1());
	}

	private void mostrarVentanaJugadores() {
		Login login = new Login();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		dispose();

	}

	private void mostrarVentanaInicioSesionAdmin() {
		IniciarSesionAdmin ini = new IniciarSesionAdmin();
		ini.setLocationRelativeTo(null);
		ini.setVisible(true);
		dispose();

	}

	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 575, 321);

			JLabel lblNewLabel = new JLabel("");
			panel.add(lblNewLabel);
			lblNewLabel.setIcon(new ImageIcon(FileUtil.getImage("images/Trivial-mobile_opt.jpg")));
		}
		return panel;
	}
}
