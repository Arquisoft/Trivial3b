package es.uniovi.asw.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class Login extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField txU1;
	private JTextField txP1;
	private JTextField txU2;
	private JTextField txP2;
	private JTextField txU3;
	private JTextField txP3;
	private JTextField txU4;
	private JTextField txP4;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JTextField txU5;
	private JPasswordField txP5;
	private JTextField txU6;
	private JPasswordField txP6;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void habilitarComponentes(JPanel panel, boolean habilitar) {		
		for(int i=0;i<panel.getComponents().length;i++) {
			panel.getComponent(i).setEnabled(habilitar);
			}	
	}	
	
	public void actualizarNumeroJugadores(int jugadores) {
		if(jugadores == 1){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, false);
			habilitarComponentes(panel_3, false);
			habilitarComponentes(panel_4, false);
			habilitarComponentes(panel_5, false);
			habilitarComponentes(panel_6, false);


		} else if(jugadores == 2){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, false);
			habilitarComponentes(panel_4, false);
			habilitarComponentes(panel_5, false);
			habilitarComponentes(panel_6, false);

		} else if(jugadores == 3){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, true);
			habilitarComponentes(panel_4, false);
			habilitarComponentes(panel_5, false);
			habilitarComponentes(panel_6, false);

		} else if(jugadores == 4){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, true);
			habilitarComponentes(panel_4, true);
			habilitarComponentes(panel_5, false);
			habilitarComponentes(panel_6, false);

		} else if(jugadores == 5){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, true);
			habilitarComponentes(panel_4, true);
			habilitarComponentes(panel_5, true);
			habilitarComponentes(panel_6, false);

		} else if(jugadores == 6){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, true);
			habilitarComponentes(panel_4, true);
			habilitarComponentes(panel_5, true);
			habilitarComponentes(panel_6, true);

		}
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JSpinner spinner = new JSpinner();
		spinner.setForeground(new Color(240, 240, 240));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinner.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3, true), "Numero de jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int jugadores = (Integer) spinner.getValue();
				actualizarNumeroJugadores(jugadores);
			}
		});
		spinner.setModel(new SpinnerNumberModel(2, 2, 6, 1));
		spinner.setBounds(23, 595, 170, 59);
		contentPane.add(spinner);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "Jugador 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(23, 21, 516, 80);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txU1 = new JTextField();
		txU1.setBounds(259, 18, 247, 20);
		panel_1.add(txU1);
		txU1.setColumns(10);
		
		txP1 = new JPasswordField();
		txP1.setBounds(259, 49, 247, 20);
		panel_1.add(txP1);
		txP1.setColumns(10);
		
		JLabel lbUsuario1 = new JLabel("Introduzca su usuario");
		lbUsuario1.setBounds(101, 21, 148, 14);
		panel_1.add(lbUsuario1);
		
		JLabel lblPassword1 = new JLabel("Password usuario 1");
		lblPassword1.setBounds(101, 52, 129, 14);
		panel_1.add(lblPassword1);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "Jugador 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(23, 118, 516, 80);
		contentPane.add(panel_2);
		
		txU2 = new JTextField();
		txU2.setColumns(10);
		txU2.setBounds(259, 18, 247, 20);
		panel_2.add(txU2);
		
		txP2 = new JPasswordField();
		txP2.setColumns(10);
		txP2.setBounds(259, 49, 247, 20);
		panel_2.add(txP2);
		
		JLabel label = new JLabel("Introduzca su usuario");
		label.setBounds(101, 21, 148, 14);
		panel_2.add(label);
		
		JLabel lblPasswordUsuario = new JLabel("Password usuario 2");
		lblPasswordUsuario.setBounds(101, 52, 129, 14);
		panel_2.add(lblPasswordUsuario);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "Jugador 3", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(23, 209, 516, 80);
		contentPane.add(panel_3);
		
		txU3 = new JTextField();
		txU3.setEnabled(false);
		txU3.setColumns(10);
		txU3.setBounds(259, 18, 247, 20);
		panel_3.add(txU3);
		
		txP3 = new JPasswordField();
		txP3.setEnabled(false);
		txP3.setColumns(10);
		txP3.setBounds(259, 49, 247, 20);
		panel_3.add(txP3);
		
		JLabel label_2 = new JLabel("Introduzca su usuario");
		label_2.setEnabled(false);
		label_2.setBounds(100, 21, 149, 14);
		panel_3.add(label_2);
		
		JLabel lblPasswordUsuario_1 = new JLabel("Password usuario 3");
		lblPasswordUsuario_1.setEnabled(false);
		lblPasswordUsuario_1.setBounds(100, 52, 130, 14);
		panel_3.add(lblPasswordUsuario_1);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "Jugador 4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(23, 306, 516, 80);
		contentPane.add(panel_4);
		
		txU4 = new JTextField();
		txU4.setEnabled(false);
		txU4.setColumns(10);
		txU4.setBounds(259, 18, 247, 20);
		panel_4.add(txU4);
		
		txP4 = new JPasswordField();
		txP4.setEnabled(false);
		txP4.setColumns(10);
		txP4.setBounds(259, 49, 247, 20);
		panel_4.add(txP4);
		
		JLabel label_4 = new JLabel("Introduzca su usuario");
		label_4.setEnabled(false);
		label_4.setBounds(97, 21, 152, 14);
		panel_4.add(label_4);
		
		JLabel lblPasswordUsuario_2 = new JLabel("Password usuario 4");
		lblPasswordUsuario_2.setEnabled(false);
		lblPasswordUsuario_2.setBounds(97, 52, 134, 14);
		panel_4.add(lblPasswordUsuario_2);
		
		JButton btSalir = new JButton("Salir");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btSalir.setBounds(321, 616, 97, 33);
		contentPane.add(btSalir);
		
		JButton btJugar = new JButton("Jugar");
		btJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btJugar.setBounds(442, 616, 97, 33);
		contentPane.add(btJugar);
		
		panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "Jugador 5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(23, 397, 516, 80);
		contentPane.add(panel_5);
		
		txU5 = new JTextField();
		txU5.setEnabled(false);
		txU5.setColumns(10);
		txU5.setBounds(259, 18, 247, 20);
		panel_5.add(txU5);
		
		txP5 = new JPasswordField();
		txP5.setEnabled(false);
		txP5.setColumns(10);
		txP5.setBounds(259, 49, 247, 20);
		panel_5.add(txP5);
		
		JLabel label_1 = new JLabel("Introduzca su usuario");
		label_1.setEnabled(false);
		label_1.setBounds(100, 21, 149, 14);
		panel_5.add(label_1);
		
		JLabel lblPasswordUsuario_3 = new JLabel("Password usuario 5");
		lblPasswordUsuario_3.setEnabled(false);
		lblPasswordUsuario_3.setBounds(100, 52, 130, 14);
		panel_5.add(lblPasswordUsuario_3);
		
		panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 4, true), "Jugador 6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(23, 494, 516, 80);
		contentPane.add(panel_6);
		
		txU6 = new JTextField();
		txU6.setEnabled(false);
		txU6.setColumns(10);
		txU6.setBounds(259, 18, 247, 20);
		panel_6.add(txU6);
		
		txP6 = new JPasswordField();
		txP6.setEnabled(false);
		txP6.setColumns(10);
		txP6.setBounds(259, 49, 247, 20);
		panel_6.add(txP6);
		
		JLabel label_5 = new JLabel("Introduzca su usuario");
		label_5.setEnabled(false);
		label_5.setBounds(97, 21, 152, 14);
		panel_6.add(label_5);
		
		JLabel lblPasswordUsuario_4 = new JLabel("Password usuario 6");
		lblPasswordUsuario_4.setEnabled(false);
		lblPasswordUsuario_4.setBounds(97, 52, 134, 14);
		panel_6.add(lblPasswordUsuario_4);
	}
}
