package es.uniovi.asw.gui;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;


public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/es/uniovi/asw/gui/imagenes/icono.jpg")));
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
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaInicioSesionAdmin();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Opciones");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Preferencias");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Acerca de");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Salir");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/es/uniovi/asw/gui/imagenes/Trivial-mobile_opt.jpg")));
		lblNewLabel.setBounds(0, 0, 571, 317);
		contentPane.add(lblNewLabel);
		
		JButton btnJugar = new JButton("Partida Rapida");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarVentanaJugadores();
			}
		});
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnJugar.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		btnJugar.setBackground(new Color(102, 204, 204));
		btnJugar.setActionCommand("");
		btnJugar.setBounds(179, 213, 200, 50);
		contentPane.add(btnJugar);
	}
	
	private void mostrarVentanaJugadores(){
			Login login = new Login();
			login.setLocationRelativeTo(null);
			login.setVisible(true);
			dispose();
		
	}
	
	private void mostrarVentanaInicioSesionAdmin(){
		IniciarSesionAdmin ini = new IniciarSesionAdmin();
		ini.setLocationRelativeTo(null);
		ini.setVisible(true);
		dispose();
	
	}
	
	
}
