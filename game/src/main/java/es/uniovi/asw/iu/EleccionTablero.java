package es.uniovi.asw.iu;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EleccionTablero extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EleccionTablero dialog = new EleccionTablero();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EleccionTablero() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				("src/main/resources/images/icono.jpg")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnCircular = new JButton("Foto circulo");
		btnCircular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardGame board=new BoardGame(1);
				board.setVisible(true);
				board.setLocationRelativeTo(getParent());
				dispose();
			}
		});
		btnCircular.setBounds(10, 45, 184, 151);
		contentPanel.add(btnCircular);
		
		JButton btnCuadrado = new JButton("Foto Cuadrado");
		btnCuadrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardGame board=new BoardGame(2);
				board.setVisible(true);
				board.setLocationRelativeTo(getParent());
				dispose();
			}
		});
		btnCuadrado.setBounds(227, 45, 184, 151);
		contentPanel.add(btnCuadrado);
	}
}
