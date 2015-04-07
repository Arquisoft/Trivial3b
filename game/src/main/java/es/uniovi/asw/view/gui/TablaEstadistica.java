package es.uniovi.asw.view.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.modelo.persistence.impl.EstadisticasJugadorJdbcDao;
import es.uniovi.asw.util.FileUtil;
import es.uniovi.asw.util.Jdbc;
import es.uniovi.asw.view.components.ModeloNoEditable;

public class TablaEstadistica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ModeloNoEditable modeloTabla;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public TablaEstadistica() {
		setTitle("Estadisticas de jugadores");
		setResizable(false);
		setIconImage(FileUtil.getImage("images/icono.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 267);
		contentPane.add(scrollPane);
		modeloTabla = new ModeloNoEditable(new String[] { "Id jugador",
				"Id pregunta", "Aciertos", "Fallos" }, 0);
		meterTablas();

		table = new JTable();
		table.setModel(modeloTabla);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		contentPane.add(getBtnNewButton());
	}

	public void meterTablas() {
		EstadisticasJugadorJdbcDao dao = new EstadisticasJugadorJdbcDao();
		try {
			dao.setConexion(Jdbc.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String, Object>> estadisticas = dao.findAll();
		Object[] line = new Object[4];

		for (int i = 0; i < estadisticas.size(); i++) {
			line[0] = estadisticas.get(i).get("IDJUGADOR");
			line[1] = estadisticas.get(i).get("IDPREGUNTA");
			line[2] = estadisticas.get(i).get("ACIERTOS");
			line[3] = estadisticas.get(i).get("FALLOS");

			modeloTabla.addRow(line);

		}
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Volver");
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Principal principal = new Principal();
					principal.setVisible(true);
					principal.setLocationRelativeTo(getParent());
					setBounds(100, 100, 581, 371);
					dispose();
				}
			});
			btnNewButton.setBounds(335, 289, 89, 23);
		}
		return btnNewButton;
	}
}
