import java.awt.BorderLayout;
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
import javax.swing.SpinnerDateModel;

import java.util.Date;
import java.util.Calendar;


public class Login extends JFrame {

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


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
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

		} else if(jugadores == 2){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, false);
			habilitarComponentes(panel_4, false);

		} else if(jugadores == 3){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, true);
			habilitarComponentes(panel_4, false);

		}
		
		else if(jugadores == 4){
			habilitarComponentes(panel_1, true);
			habilitarComponentes(panel_2, true);
			habilitarComponentes(panel_3, true);
			habilitarComponentes(panel_4, true);

		}
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 517);
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
				int jugadores = (int) spinner.getValue();
				actualizarNumeroJugadores(jugadores);
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		spinner.setBounds(23, 397, 170, 59);
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
		txU2.setEnabled(false);
		txU2.setColumns(10);
		txU2.setBounds(259, 18, 247, 20);
		panel_2.add(txU2);
		
		txP2 = new JPasswordField();
		txP2.setEnabled(false);
		txP2.setColumns(10);
		txP2.setBounds(259, 49, 247, 20);
		panel_2.add(txP2);
		
		JLabel label = new JLabel("Introduzca su usuario");
		label.setEnabled(false);
		label.setBounds(101, 21, 148, 14);
		panel_2.add(label);
		
		JLabel lblPasswordUsuario = new JLabel("Password usuario 2");
		lblPasswordUsuario.setEnabled(false);
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
		btSalir.setBounds(321, 423, 97, 33);
		contentPane.add(btSalir);
		
		JButton btJugar = new JButton("Jugar");
		btJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btJugar.setBounds(442, 423, 97, 33);
		contentPane.add(btJugar);
	}
}
