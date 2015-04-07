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
import es.uniovi.asw.modelo.persistence.impl.PreguntaJdbcDao;
import es.uniovi.asw.util.FileUtil;
import es.uniovi.asw.util.Jdbc;
import es.uniovi.asw.view.components.ModeloNoEditable;

import javax.swing.JTabbedPane;

public class TablaEstadistica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ModeloNoEditable tablaTodo;
	private ModeloNoEditable tablaMasAcertadas;
	private ModeloNoEditable tablaMasFalladas;
	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTable table_1;
	private JTable table_2;

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
		tablaTodo = new ModeloNoEditable(new String[] { "Id jugador",
				"Id pregunta", "Aciertos", "Fallos" }, 0);
		tablaMasAcertadas = new ModeloNoEditable(new String[] { "Id pregunta",
				"Aciertos", "Fallos" }, 0);
		tablaMasFalladas = new ModeloNoEditable(new String[] { "Id pregunta",
				"Aciertos", "Fallos" }, 0);
		contentPane.add(getBtnNewButton());

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 278);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Estadisticas totales", null, scrollPane, null);

		table = new JTable();
		table.setModel(tablaTodo);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Preguntas mas acertadas", null, scrollPane_1, null);
		
		table_1 = new JTable();
		table_1.setModel(tablaMasAcertadas);
		table_1.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);

		scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Preguntas mas falladas", null, scrollPane_2, null);
		
		table_2 = new JTable();
		table_2.setModel(tablaMasFalladas);
		table_2.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(table_2);

		meterTablasTodo();
		meterTablasAcertadas();
		meterTablasFalladas();
	}

	public void meterTablasTodo() {
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

			tablaTodo.addRow(line);

		}
	}
	
	public void meterTablasAcertadas() {
		PreguntaJdbcDao dao = new PreguntaJdbcDao();
		try {
			dao.setConnection(Jdbc.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> estadisticas = dao.getMasAcertada();
		Object[] line = new Object[3];
			if(estadisticas!=null){
			line[0] = estadisticas.get("IDPREGUNTA");
			line[1] = estadisticas.get("ACIERTOS");
			line[2] = estadisticas.get("FALLOS");
			}
			tablaMasAcertadas.addRow(line);

		
	}
	
	public void meterTablasFalladas() {
		PreguntaJdbcDao dao = new PreguntaJdbcDao();
		try {
			dao.setConnection(Jdbc.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> estadisticas = dao.getMasFallada();
		Object[] line = new Object[3];
		if(estadisticas!=null){
			line[0] = estadisticas.get("IDPREGUNTA");
			line[1] = estadisticas.get("ACIERTOS");
			line[2] = estadisticas.get("FALLOS");
		}
			tablaMasFalladas.addRow(line);
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
