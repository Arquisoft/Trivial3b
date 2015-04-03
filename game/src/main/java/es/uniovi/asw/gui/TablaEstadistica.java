package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import es.uniovi.asw.persistence.impl.EstadisticasJugadorJdbcDao;
import es.uniovi.asw.util.Jdbc;

public class TablaEstadistica extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ModeloNoEditable modeloTabla;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaEstadistica frame = new TablaEstadistica();
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
	public TablaEstadistica() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaEstadistica.class.getResource("/es/uniovi/asw/gui/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		modeloTabla = new ModeloNoEditable(new String[] {"Id jugador", "Id pregunta",   "Aciertos",  "Fallos"}, 0);
		meterTablas();
		
		table = new JTable();
		table.setModel(modeloTabla);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
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
		
		for(int i = 0; i < estadisticas.size(); i++){
			line[0] = estadisticas.get(i).get("IDJUGADOR");
			line[1] = estadisticas.get(i).get("IDPREGUNTA");
			line[2] = estadisticas.get(i).get("ACIERTOS");
			line[3] = estadisticas.get(i).get("FALLOS");

			modeloTabla.addRow(line);
			
		}
	}
}
