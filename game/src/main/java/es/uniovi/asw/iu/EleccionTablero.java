package es.uniovi.asw.iu;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.GameService;
import es.uniovi.asw.game.impl.GameServiceImpl;
import es.uniovi.asw.model.Player;

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
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                ("src/main/resources/images/icono.jpg")));
        this.service = service;
        setBounds(100, 100, 440, 250);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
		Icon circulo = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("src/main/resources/images/trivialCirculo.jpg")
				.getScaledInstance(160, 160, Image.SCALE_SMOOTH));

		JButton btnCircular = new JButton(circulo);
		btnCircular.addActionListener(new ActionListener() {
			@Override
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
		btnCircular.setBounds(20, 20, 180, 180);
		contentPanel.add(btnCircular);

		Icon cuadrado = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("src/main/resources/images/trivialCuadrado.jpg")
				.getScaledInstance(160, 160, Image.SCALE_SMOOTH));

		JButton btnCuadrado = new JButton(cuadrado);
		btnCuadrado.addActionListener(new ActionListener() {
			@Override
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
		btnCuadrado.setBounds(235, 20, 180, 180);
		contentPanel.add(btnCuadrado);
    }
}
