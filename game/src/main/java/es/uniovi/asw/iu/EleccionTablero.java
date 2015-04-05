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
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                ("src/main/resources/images/icono.jpg")));
        this.service = service;
        setBounds(100, 100, 524, 410);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JButton btnCircular = new JButton("Foto circulo");
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
        btnCircular.setBounds(10, 103, 221, 185);
        contentPanel.add(btnCircular);

        JButton btnCuadrado = new JButton("Foto Cuadrado");
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
        btnCuadrado.setBounds(263, 105, 235, 183);
        contentPanel.add(btnCuadrado);
    }
}
