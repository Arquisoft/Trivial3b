package es.uniovi.asw.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import es.uniovi.asw.controller.game.GameService;
import es.uniovi.asw.controller.game.impl.GameServiceImpl;
import es.uniovi.asw.modelo.model.Player;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

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
		setTitle("Tablero de juego");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				("src/main/resources/images/icono.jpg")));
		this.service = service;
		setBounds(100, 100, 524, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		final JButton btnCircular = new JButton("");
		btnCircular.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));

		btnCircular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCircular.setBorder(new LineBorder(new Color(255, 0, 0), 3,
						true));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnCircular.setBorder(new LineBorder(new Color(0, 0, 0), 3,
						true));
			}
		});

		btnCircular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameService servicio = new GameServiceImpl(1);
				for (Player p : service.getPlayers()) {
					servicio.addPlayer(p);
				}
				BoardGame board = new BoardGame(1, servicio);
				board.setVisible(true);
				board.setLocationRelativeTo(getParent());
				dispose();
			}
		});
		btnCircular.setBounds(21, 103, 221, 185);
		ImageIcon fot = new ImageIcon(
				"src/main/resources/images/trivialCirculo.jpg");
		ImageIcon imagen = new ImageIcon(fot.getImage().getScaledInstance(
				btnCircular.getWidth(), btnCircular.getHeight(),
				Image.SCALE_DEFAULT));
		btnCircular.setIcon(imagen);
		contentPanel.add(btnCircular);

		final JButton btnCuadrado = new JButton("");
		btnCuadrado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCuadrado.setBorder(new LineBorder(new Color(255, 0, 0), 3,
						true));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnCuadrado.setBorder(new LineBorder(new Color(0, 0, 0), 3,
						true));
			}
		});
		btnCuadrado.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));

		btnCuadrado.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnCuadrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameService servicio = new GameServiceImpl(2);
				for (Player p : service.getPlayers()) {
					servicio.addPlayer(p);
				}
				BoardGame board = new BoardGame(2, servicio);
				board.setVisible(true);
				board.setLocationRelativeTo(getParent());
				dispose();
			}
		});
		btnCuadrado.setBounds(273, 103, 235, 183);
		fot = new ImageIcon("src/main/resources/images/trivialCuadrado.jpg");
		imagen = new ImageIcon(fot.getImage().getScaledInstance(
				btnCircular.getWidth(), btnCircular.getHeight(),
				Image.SCALE_DEFAULT));
		btnCuadrado.setIcon(imagen);
		contentPanel.add(btnCuadrado);

		JLabel lbTitulo = new JLabel("Elige el tablero con el que desees jugar");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbTitulo.setForeground(new Color(0, 0, 0));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(21, 55, 487, 32);
		contentPanel.add(lbTitulo);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaJugadores();
			}
		});
		btnAtras.setBounds(364, 312, 144, 45);
		contentPanel.add(btnAtras);
	}

	private void mostrarVentanaJugadores() {
		Login login = new Login();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		dispose();

	}
}
