package es.uniovi.asw.iu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.game.GameService;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Pregunta;

public class QuestionDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static Pregunta pregunta;
	private static GameService servicio;
	private static BoardGame game;
	private JPanel panel;
	private List<Integer> usados;
	private JButton[] botones=new JButton[4];
	private JButton Respuesta2;
	private Category category;
	private JButton Respuesta3;
	private JButton Respuesta1;
	private JButton Respuesta4;
	private JProgressBar pB;
	private int contador=0;
	Timer t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			QuestionDialog dialog = new QuestionDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public QuestionDialog(BoardGame game) {
		pregunta=game.service.getPregunta();
		category=pregunta.getCategory();
		servicio=game.service;
		QuestionDialog.game=game;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(75, 75));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel pregun = new JLabel("");
			pregun.setFont(new Font("Tahoma", Font.PLAIN, 18));
			pregun.setText(pregunta.getQuestion());
			contentPanel.add(pregun);
		}
		contentPanel.add(getPB(), BorderLayout.NORTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		// Create a progress bar
		 // Paint the percent complete on progress bar
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}
	public void cargarBotones(){
		botones[0]=getRespuesta1();
		botones[1]=getRespuesta2();
		botones[2]=getRespuesta3();
		botones[3]=getRespuesta4();
	}
	public void preguntasAleatorias(){
		int i=0;
		usados=new ArrayList<Integer>();
		while(i<4){
			panel.add(botones[aleatorio(0,3)]);
			i++;
		}
	}
	public int aleatorio(int min, int max) {
		int num = (int) (Math.floor(Math.random()*(max-min+1))+min);
		while(usados.contains(num)){
			num = (int) (Math.floor(Math.random()*(max-min+1))+min);
		}
		usados.add(num);
		return num;
		}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			cargarBotones();
			preguntasAleatorias();
		}
		return panel;
	}
	private JButton getRespuesta2() {
		if (Respuesta2 == null) {
			Respuesta2 = new JButton("");
			Respuesta2.setText(pregunta.getWrongAnswers()[0]);
			Respuesta2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					servicio.respuestaIncorrecta();
					t.stop();
					game.setEnabled(true);
					dispose();
					game.recargarQuesitos(category);
				}
			});
		}
		return Respuesta2;
	}
	private JButton getRespuesta3() {
		if (Respuesta3 == null) {
			Respuesta3 = new JButton("");
			Respuesta3.setText(pregunta.getWrongAnswers()[1]);
			Respuesta3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					servicio.respuestaIncorrecta();
					t.stop();
					game.setEnabled(true);
					dispose();
					game.recargarQuesitos(category);
				}
			});
		}
		return Respuesta3;
	}
	private JButton getRespuesta1() {
		if (Respuesta1 == null) {
			Respuesta1 = new JButton("");
			Respuesta1.setText(pregunta.getCorrectAnswer());
			Respuesta1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					servicio.respuestaCorrecta();
					t.stop();
					game.setEnabled(true);
					dispose();
					game.recargarQuesitos(category);				
				}
			});
		}
		return Respuesta1;
	}
	private JButton getRespuesta4() {
		if (Respuesta4 == null) {
			Respuesta4 = new JButton("");
			Respuesta4.setText(pregunta.getWrongAnswers()[2]);
			Respuesta4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					servicio.respuestaIncorrecta();
					t.stop();
					game.setEnabled(true);
					dispose();
					game.recargarQuesitos(category);
				}
			});
		}
		return Respuesta4;
	}
	private JProgressBar getPB() {
		if (pB == null) {
			pB = new JProgressBar();
			pB.setStringPainted(true);
			 // Set a size (optional)
			pB.setStringPainted(false);
			 pB.setPreferredSize(new Dimension(500,30));
			 // Start at 0
			 pB.setMinimum(0);
			 // End at 1000
			 pB.setMaximum(15);
			  // Create a timer that executes for every 2 millisec
			 t=new Timer(1000,new ActionListener(){
			  public void actionPerformed(ActionEvent ae)
			  {
				  contador=pB.getValue();
				  if(contador==15){
					  contador=0;
					  game.setEnabled(true);
					  game.service.respuestaIncorrecta();
					  t.stop();
					  dispose();
				  }
			
			   pB.setValue(contador+1);
			  }
			 });
			 // Start the timer
			 t.start();
			 add(pB);
			 pack();
			 }
		return pB;
	}
}
