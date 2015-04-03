package es.uniovi.asw.iu;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.GameService;


public class EleccionTablero extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private GameService service;

	/**
	 * Launch the application.
	 */
	

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}

	/**
	 * Create the dialog.
	 */
	public EleccionTablero(final GameService service) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				("src/main/resources/images/icono.jpg")));
		this.service=service;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnCircular = new JButton("Foto circulo");
		btnCircular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardGame board=new BoardGame(1,service);
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
				BoardGame board=new BoardGame(2,service);
				board.setVisible(true);
				board.setLocationRelativeTo(getParent());
				dispose();
			}
		});
		btnCuadrado.setBounds(227, 45, 184, 151);
		contentPanel.add(btnCuadrado);
	}
}
