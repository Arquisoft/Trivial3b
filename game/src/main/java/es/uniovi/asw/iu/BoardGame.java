package es.uniovi.asw.iu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import es.uniovi.asw.game.GameFactory;
import es.uniovi.asw.game.GameService;
import es.uniovi.asw.iu.components.PolygonButton;
import es.uniovi.asw.model.Casilla;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.util.FileUtil;

public class BoardGame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel lblNewLabel;
    JButton dado;
    private PolygonButton[] botones;
    private JLabel[] quesitos = new JLabel[6];
    private JPanel[] paneles = new JPanel[6];
    private JLabel[] nombres = new JLabel[6];
    static Map<Integer, List<Point>> coordenadas;
    private List<Casilla> activados;
    GameService service;

    public GameService getService() {
        return service;
    }

    public void setService(GameService service) {
        this.service = service;
    }

    private JLabel tirada;
    JPanel contentPane;
    static BoardGame frame;
    private Map<Integer, Integer> posiciones = new HashMap<Integer, Integer>();
    JButton icono;
    int contador = 0;
    Map<Integer, List<JLabel>> figuras = new HashMap<Integer, List<JLabel>>();
    private JPanel panel;
    private JPanel Jugador1;
    private JPanel Jugador2;
    private JPanel Jugador3;
    private JPanel Jugador4;
    private JPanel Jugador5;
    private JPanel Jugador6;
    private JLabel nombre1, nombre2, nombre3, nombre4, nombre5, nombre6;
    private JLabel quesitos1;
    private JLabel quesitos2;
    private JLabel quesitos3;
    private JLabel quesitos4;
    private JLabel quesitos5;
    private JLabel quesitos6;
    private static final int CIRCULAR = 1;
    private static final int CUADRADO = 2;
    private JPanel panelFiguras1;
    private JLabel figura1;
    private JLabel figura2;
    private JLabel figura3;
    private JLabel figura4;
    private JLabel figura5;
    private JLabel figura6;
    private JPanel panelFiguras2;
    private JLabel figura7;
    private JLabel figura8;
    private JLabel figura9;
    private JLabel figura10;
    private JLabel figura11;
    private JLabel figura12;
    private JPanel panelFiguras3;
    private JLabel figura13;
    private JLabel figura14;
    private JLabel figura15;
    private JLabel figura16;
    private JLabel figura17;
    private JLabel figura18;
    private JPanel panelFiguras4;
    private JLabel figura19;
    private JLabel figura20;
    private JLabel figura21;
    private JLabel figura22;
    private JLabel figura23;
    private JLabel figura24;
    private JPanel panelFiguras5;
    private JLabel figura25;
    private JLabel figura26;
    private JLabel figura27;
    private JLabel figura28;
    private JLabel figura29;
    private JLabel figura30;
    private JPanel panelFiguras6;
    private JLabel figura31;
    private JLabel figura32;
    private JLabel figura33;
    private JLabel figura34;
    private JLabel figura35;
    private JLabel figura36;

    /**
     * Create the frame.
     */
    public BoardGame(int tablero, GameService servicio) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 870, 572);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        // CargarCoordenadas();
        contentPane.add(getLblNewLabel());
        contentPane.add(getDado());
        contentPane.add(getTirada());
        cargaTablero(tablero);
        setService(servicio);
        contentPane.add(getPanel());
        cargarPaneles();
    }

    private JLabel getLblNewLabel() {
        if (lblNewLabel == null) {
            lblNewLabel = new JLabel("");
            lblNewLabel.setBounds(0, 0, 540, 539);
        }
        return lblNewLabel;
    }

    private JButton getDado() {
        if (dado == null) {
            dado = new JButton("");
            dado.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer tirar = service.throwDice();
                    tirada.setText(String.valueOf(tirar));
                    dado.setEnabled(false);
                    activarBotones();
                }
            });

            dado.setBounds(548, 30, 59, 59);
        }
        return dado;
    }

    private JLabel getTirada() {
        if (tirada == null) {
            tirada = new JLabel("");
            tirada.setFont(new Font("Tahoma", Font.PLAIN, 65));
            tirada.setBounds(706, 0, 66, 85);
        }
        return tirada;
    }

    public void activarBotones() {
        activados = service.getMoves();
        for (Casilla casilla : activados) {
            botones[casilla.getId()].setEnabledButton(true);
        }
    }

    public void desactivarBotones() {
        for (int i = 1; i < botones.length; i++) {
            botones[i].setEnabledButton(false);
        }
        pintar();
    }

    /*
     * Metodo que pinta los polygonbutton una vez que se activar o se desactivan
     * los botones
     */
    private void pintar() {
        contentPane.removeAll();
        addBotones(coordenadas);
        for (int i = 1; i < botones.length; i++) {
            contentPane.add(botones[i]);
        }
        contentPane.add(getLblNewLabel());
        contentPane.add(getDado());
        contentPane.add(getTirada());
        contentPane.add(getPanel());
        contentPane.invalidate();
        contentPane.validate();
        contentPane.repaint();
    }

    private int numeroJugadorActual() {
        for (int i = 0; i < service.getPlayers().size(); i++) {
            if (service.getPlayers().get(i) == service.getCurrentTurnPlayer()) {
                return i;
            }
        }
        return 0;
    }

    public void pintarPosiciones() {
        for (Integer key : posiciones.keySet()) {
            botones[posiciones.get(key)].setHasPlayer(true);
            botones[posiciones.get(key)].setJugador(key);
        }
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setBounds(540, 91, 314, 459);
            panel.setLayout(new GridLayout(6, 0, 0, 0));
            panel.add(getJugador1());
            panel.add(getJugador2());
            panel.add(getJugador3());
            panel.add(getJugador4());
            panel.add(getJugador5());
            panel.add(getJugador6());
        }
        return panel;
    }

    private JPanel getJugador1() {
        if (Jugador1 == null) {
            Jugador1 = new JPanel();
            Jugador1.setEnabled(false);
            Jugador1.setBorder(new TitledBorder(null, "Jugador1",
                    TitledBorder.LEADING, TitledBorder.TOP, null, null));
            Jugador1.setLayout(null);
            Jugador1.add(getNombre1());
            Jugador1.add(getQuesitos1());
            if (service.getPlayers().size() > 0) {
                Jugador1.add(getPanelFiguras1());
            }
        }
        return Jugador1;
    }

    private JPanel getJugador2() {
        if (Jugador2 == null) {
            Jugador2 = new JPanel();
            Jugador2.setEnabled(false);
            Jugador2.setBorder(new TitledBorder(null, "Jugador2",
                    TitledBorder.LEADING, TitledBorder.TOP, null, null));
            Jugador2.setLayout(null);
            Jugador2.add(getNombre2());
            Jugador2.add(getQuesitos2());
            if (service.getPlayers().size() > 1) {
                Jugador2.add(getPanelFiguras2());
            }
        }
        return Jugador2;
    }

    private JPanel getJugador3() {
        if (Jugador3 == null) {
            Jugador3 = new JPanel();
            Jugador3.setEnabled(false);
            Jugador3.setBorder(new TitledBorder(null, "Jugador3",
                    TitledBorder.LEADING, TitledBorder.TOP, null, null));
            Jugador3.setLayout(null);
            Jugador3.add(getNombre3());
            Jugador3.add(getQuesitos3());
            if (service.getPlayers().size() > 2) {
                Jugador3.add(getPanelFiguras3());
            }
        }
        return Jugador3;
    }

    private JPanel getJugador4() {
        if (Jugador4 == null) {
            Jugador4 = new JPanel();
            Jugador4.setEnabled(false);
            Jugador4.setBorder(new TitledBorder(null, "Jugador4",
                    TitledBorder.LEADING, TitledBorder.TOP, null, null));
            Jugador4.setLayout(null);
            Jugador4.add(getNombre4());
            Jugador4.add(getQuesitos4());
            if (service.getPlayers().size() > 3) {
                Jugador4.add(getPanelFiguras4());
            }
        }
        return Jugador4;
    }

    private JPanel getJugador5() {
        if (Jugador5 == null) {
            Jugador5 = new JPanel();
            Jugador5.setEnabled(false);
            Jugador5.setBorder(new TitledBorder(null, "Jugador5",
                    TitledBorder.LEADING, TitledBorder.TOP, null, null));
            Jugador5.setLayout(null);
            Jugador5.add(getNombre5());
            Jugador5.add(getQuesitos5());
            if (service.getPlayers().size() > 4) {
                Jugador5.add(getPanelFiguras5());
            }
        }
        return Jugador5;
    }

    private JPanel getJugador6() {
        if (Jugador6 == null) {
            Jugador6 = new JPanel();
            Jugador6.setEnabled(false);
            Jugador6.setBorder(new TitledBorder(null, "Jugador6",
                    TitledBorder.LEADING, TitledBorder.TOP, null, null));
            Jugador6.setLayout(null);
            Jugador6.add(getNombre6());
            Jugador6.add(getQuesitos6());
            if (service.getPlayers().size() > 5) {
                Jugador6.add(getPanelFiguras6());
            }
        }
        return Jugador6;
    }

    private JLabel getNombre1() {
        if (nombre1 == null) {
            nombre1 = new JLabel("");
            nombre1.setFont(new Font("Tahoma", Font.PLAIN, 16));
            nombre1.setBounds(10, 22, 95, 31);
        }
        return nombre1;
    }

    /*
     * Metodo que carga los paneles en funcion de los jugadores que haya
     */
    public void cargarPaneles() {
        paneles[0] = Jugador1;
        paneles[1] = Jugador2;
        paneles[2] = Jugador3;
        paneles[3] = Jugador4;
        paneles[4] = Jugador5;
        paneles[5] = Jugador6;
        nombres[0] = nombre1;
        nombres[1] = nombre2;
        nombres[2] = nombre3;
        nombres[3] = nombre4;
        nombres[4] = nombre5;
        nombres[5] = nombre6;
        quesitos[0] = quesitos1;
        quesitos[1] = quesitos2;
        quesitos[2] = quesitos3;
        quesitos[3] = quesitos4;
        quesitos[4] = quesitos5;
        quesitos[5] = quesitos6;
        List<JLabel> figuritas1 = new ArrayList<JLabel>();
        figuritas1.add(figura1);
        figuritas1.add(figura2);
        figuritas1.add(figura3);
        figuritas1.add(figura4);
        figuritas1.add(figura5);
        figuritas1.add(figura6);
        figuras.put(0, figuritas1);
        List<JLabel> figuritas2 = new ArrayList<JLabel>();
        figuritas2.add(figura7);
        figuritas2.add(figura8);
        figuritas2.add(figura9);
        figuritas2.add(figura10);
        figuritas2.add(figura11);
        figuritas2.add(figura12);
        figuras.put(1, figuritas2);
        List<JLabel> figuritas3 = new ArrayList<JLabel>();
        figuritas3.add(figura13);
        figuritas3.add(figura14);
        figuritas3.add(figura15);
        figuritas3.add(figura16);
        figuritas3.add(figura17);
        figuritas3.add(figura18);
        figuras.put(2, figuritas3);
        List<JLabel> figuritas4 = new ArrayList<JLabel>();
        figuritas4.add(figura19);
        figuritas4.add(figura20);
        figuritas4.add(figura21);
        figuritas4.add(figura22);
        figuritas4.add(figura23);
        figuritas4.add(figura24);
        figuras.put(3, figuritas4);
        List<JLabel> figuritas5 = new ArrayList<JLabel>();
        figuritas5.add(figura25);
        figuritas5.add(figura26);
        figuritas5.add(figura27);
        figuritas5.add(figura28);
        figuritas5.add(figura29);
        figuritas5.add(figura30);
        figuras.put(4, figuritas5);
        List<JLabel> figuritas6 = new ArrayList<JLabel>();
        figuritas6.add(figura31);
        figuritas6.add(figura32);
        figuritas6.add(figura33);
        figuritas6.add(figura34);
        figuritas6.add(figura35);
        figuritas6.add(figura36);
        figuras.put(5, figuritas6);
        for (int i = 0; i < service.getPlayers().size(); i++) {
            paneles[i].setEnabled(true);
            nombres[i].setText(service.getPlayers().get(i).getUsername());
            quesitos[i].setText(String.valueOf(service.getPlayers().get(i)
                    .numeroQuesitos()));
        }
    }

    private static void cargaBotones(String location) {
        coordenadas = new HashMap<Integer, List<Point>>();
        String file = FileUtil.getFile(location);
        // Dividimos el archivo en lineas
        String[] lineas = file.split("\r\n");
        for (int i = 0; i < lineas.length; i++) {
            List<Point> temp = new ArrayList<Point>();
            String[] puntos = lineas[i].split(",");
            temp.add(new Point(Integer.parseInt(puntos[0]), Integer
                    .parseInt(puntos[1])));
            temp.add(new Point(Integer.parseInt(puntos[2]), Integer
                    .parseInt(puntos[3])));
            temp.add(new Point(Integer.parseInt(puntos[4]), Integer
                    .parseInt(puntos[5])));
            temp.add(new Point(Integer.parseInt(puntos[6]), Integer
                    .parseInt(puntos[7])));
            coordenadas.put(i + 1, temp);
        }
    }

    private JLabel getNombre2() {
        if (nombre2 == null) {
            nombre2 = new JLabel("");
            nombre2.setFont(new Font("Tahoma", Font.PLAIN, 16));
            nombre2.setBounds(10, 22, 101, 31);
        }
        return nombre2;
    }

    private JLabel getNombre3() {
        if (nombre3 == null) {
            nombre3 = new JLabel("");
            nombre3.setFont(new Font("Tahoma", Font.PLAIN, 16));
            nombre3.setBounds(10, 22, 107, 31);
        }
        return nombre3;
    }

    private JLabel getQuesitos1() {
        if (quesitos1 == null) {
            quesitos1 = new JLabel("");
            quesitos1.setFont(new Font("Tahoma", Font.PLAIN, 28));
            quesitos1.setBounds(115, 22, 32, 31);
        }
        return quesitos1;
    }

    private JLabel getQuesitos2() {
        if (quesitos2 == null) {
            quesitos2 = new JLabel("");
            quesitos2.setFont(new Font("Tahoma", Font.PLAIN, 28));
            quesitos2.setBounds(121, 22, 30, 31);
        }
        return quesitos2;
    }

    private JLabel getQuesitos3() {
        if (quesitos3 == null) {
            quesitos3 = new JLabel("");
            quesitos3.setFont(new Font("Tahoma", Font.PLAIN, 28));
            quesitos3.setBounds(122, 22, 30, 31);
        }
        return quesitos3;
    }

    /*
     * Metodo que recarga los quesitos despues de que aparezca una pregunta y
     * saca un Panel si se ha obtenido un nuevo quesito
     */
    public void recargarQuesitos(Category category) {
        for (int i = 0; i < service.getPlayers().size(); i++) {
            String quesitosAntes = quesitos[i].getText();
            quesitos[i].setText(String.valueOf(service.getPlayers().get(i)
                    .numeroQuesitos()));
            String quesitosDespues = quesitos[i].getText();
            if (!quesitosAntes.equals(quesitosDespues)) {
                JLabel label = figuras.get(i).get(numeroCategoria(category));
                label.setIcon(getImage(numeroCategoria(category)));
                repintarQuesitos();
                JOptionPane.showMessageDialog(null, service.getPlayers().get(i)
                        .getUsername()
                        + " ha conseguido el quesito de " + category);
            }
        }
    }

    public int numeroCategoria(Category category) {
        if (category.equals(Category.CIENCIAYTECNOLOGIA)) {
            return 0;
        }
        if (category.equals(Category.DEPORTES)) {
            return 1;
        }
        if (category.equals(Category.ESPECTACULOSYENTRETENIMIENTO)) {
            return 2;
        }
        if (category.equals(Category.GEOGRAFIA)) {
            return 3;
        }
        if (category.equals(Category.HISTORIA)) {
            return 4;
        }
        if (category.equals(Category.ARTEYLITERATURA)) {
            return 5;
        }
        return 0;
    }

    public void repintarQuesitos() {
        if (panelFiguras1 != null) {
            panelFiguras1.removeAll();
            panelFiguras1.add(figura1);
            panelFiguras1.add(figura2);
            panelFiguras1.add(figura3);
            panelFiguras1.add(figura4);
            panelFiguras1.add(figura5);
            panelFiguras1.add(figura6);
            panelFiguras1.repaint();
        }
        if (panelFiguras2 != null) {
            panelFiguras2.removeAll();
            panelFiguras2.add(figura7);
            panelFiguras2.add(figura8);
            panelFiguras2.add(figura9);
            panelFiguras2.add(figura10);
            panelFiguras2.add(figura11);
            panelFiguras2.add(figura12);
            panelFiguras2.repaint();
        }
        if (panelFiguras3 != null) {
            panelFiguras3.removeAll();
            panelFiguras3.add(figura13);
            panelFiguras3.add(figura14);
            panelFiguras3.add(figura15);
            panelFiguras3.add(figura16);
            panelFiguras3.add(figura17);
            panelFiguras3.add(figura18);
            panelFiguras3.repaint();
        }
        if (panelFiguras4 != null) {
            panelFiguras4.removeAll();
            panelFiguras4.add(figura19);
            panelFiguras4.add(figura20);
            panelFiguras4.add(figura21);
            panelFiguras4.add(figura22);
            panelFiguras4.add(figura23);
            panelFiguras4.add(figura24);
            panelFiguras4.repaint();
        }
        if (panelFiguras5 != null) {
            panelFiguras5.removeAll();
            panelFiguras5.add(figura25);
            panelFiguras5.add(figura26);
            panelFiguras5.add(figura27);
            panelFiguras5.add(figura28);
            panelFiguras5.add(figura29);
            panelFiguras5.add(figura30);
            panelFiguras5.repaint();
        }
        if (panelFiguras6 != null) {
            panelFiguras6.removeAll();
            panelFiguras6.add(figura31);
            panelFiguras6.add(figura32);
            panelFiguras6.add(figura33);
            panelFiguras6.add(figura34);
            panelFiguras6.add(figura35);
            panelFiguras6.add(figura36);
            panelFiguras6.repaint();
        }
    }

    /*
     * Metodo que carga todo lo referente al tablero en funcion de una opcion
     */
    public void cargaTablero(int opcion) {
        switch (opcion) {
            case CIRCULAR:
                cargaBotones("src/main/resources/botonesCircular.txt");
                lblNewLabel.setIcon(new ImageIcon(
                        ("src/main/resources/images/trivialCirculo.jpg")));
                addBotones(coordenadas);
                service = GameFactory.newGameService(1);
                break;
            case CUADRADO:
                cargaBotones("src/main/resources/botonesCuadrado.txt");
                lblNewLabel.setIcon(new ImageIcon(
                        ("src/main/resources/images/trivialCuadrado.jpg")));
                addBotones(coordenadas);
                service = GameFactory.newGameService(2);
                break;
        }
    }

    /*
     * Metodo que crea todos los poligonButton a partir del mapa coordenadas
     */
    public void addBotones(Map<Integer, List<Point>> coordenadas) {
        // Inicializo el array de botones con el tamaño de keys que tenga el map
        botones = new PolygonButton[coordenadas.keySet().size() + 1];
        for (Integer key : coordenadas.keySet()) {
            if (key != 0) {
                List<Point> puntos = coordenadas.get(key);
                Polygon poligono = new Polygon();
                poligono.addPoint(puntos.get(0).x, puntos.get(0).y);
                poligono.addPoint(puntos.get(1).x, puntos.get(1).y);
                poligono.addPoint(puntos.get(2).x, puntos.get(2).y);
                poligono.addPoint(puntos.get(3).x, puntos.get(3).y);
                poligono.addPoint(puntos.get(0).x, puntos.get(0).y);
                PolygonButton pb = new PolygonButton(poligono, key, Color.BLACK);
                pb.setEnabledButton(false);
                // Añado el evento de clickar a todos los botones
                pb.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getComponent().isEnabled()) {
                            // metodo jugar de cada boton
                            jugar(Integer.valueOf(e.getComponent().getName()));
                        }

                    }
                });
                botones[key] = pb;
            }
        }
        // Añade todos los botones creados al content pane
        for (int i = 1; i < botones.length; i++) {
            contentPane.add(botones[i]);
        }
    }

    /*
     * Metodo jugar que sera llamado por cada boton del trivial
     */
    public void jugar(int i) {
        // Mueve la posicion del jugador a dicha casilla
        service.moveTo(service.getCasilla(i));
        // Desactiva los botones
        desactivarBotones();
        // Pone en el mapa la nueva posicion del jugador
        posiciones.put(numeroJugadorActual(), i);
        // Pinta las posiciones de los jugadores en el tablero
        pintarPosiciones();
        if (service.getCasilla(i).getCategoria() == null) {
            // Si no tiene categoria es una pregunta de volver a tirar y
            // volvemos a activar el dado
            dado.setEnabled(true);
        } else {
            dado.setEnabled(true);
            // Sacamos el dialogo con una nueva pregunta
            JDialog dialogo = new QuestionDialog(this);
            this.setEnabled(false);
            dialogo.setDefaultCloseOperation(0);
            dialogo.setSize(800, 200);
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);
            dialogo.setModal(true);
        }

    }

    private JLabel getNombre4() {
        if (nombre4 == null) {
            nombre4 = new JLabel("");
            nombre4.setFont(new Font("Tahoma", Font.PLAIN, 16));
            nombre4.setBounds(10, 23, 110, 31);
        }
        return nombre4;
    }

    private JLabel getNombre5() {
        if (nombre5 == null) {
            nombre5 = new JLabel("");
            nombre5.setFont(new Font("Tahoma", Font.PLAIN, 16));
            nombre5.setBounds(10, 23, 108, 31);
        }
        return nombre5;
    }

    private JLabel getNombre6() {
        if (nombre6 == null) {
            nombre6 = new JLabel("");
            nombre6.setFont(new Font("Tahoma", Font.PLAIN, 16));
            nombre6.setBounds(10, 21, 105, 31);
        }
        return nombre6;
    }

    private JLabel getQuesitos4() {
        if (quesitos4 == null) {
            quesitos4 = new JLabel("");
            quesitos4.setFont(new Font("Tahoma", Font.PLAIN, 28));
            quesitos4.setBounds(125, 23, 31, 31);
        }
        return quesitos4;
    }

    private JLabel getQuesitos5() {
        if (quesitos5 == null) {
            quesitos5 = new JLabel("");
            quesitos5.setFont(new Font("Tahoma", Font.PLAIN, 28));
            quesitos5.setBounds(119, 23, 41, 31);
        }
        return quesitos5;
    }

    private JLabel getQuesitos6() {
        if (quesitos6 == null) {
            quesitos6 = new JLabel("");
            quesitos6.setFont(new Font("Tahoma", Font.PLAIN, 28));
            quesitos6.setBounds(121, 21, 37, 31);
        }
        return quesitos6;
    }

    private JPanel getPanelFiguras1() {
        if (panelFiguras1 == null) {
            panelFiguras1 = new JPanel();
            panelFiguras1.setBounds(168, 11, 146, 65);
            panelFiguras1.setLayout(new GridLayout(0, 3, 0, 0));
            panelFiguras1.add(getFigura1());
            panelFiguras1.add(getFigura2());
            panelFiguras1.add(getFigura3());
            panelFiguras1.add(getFigura4());
            panelFiguras1.add(getFigura5());
            panelFiguras1.add(getFigura6());
        }
        return panelFiguras1;
    }

    private JLabel getFigura1() {
        if (figura1 == null) {
            figura1 = new JLabel("");
            figura1.setIcon(new ImageIcon(
                    ("src/main/resources/images/cienciasbn.png")));
        }
        return figura1;
    }

    private JLabel getFigura2() {
        if (figura2 == null) {
            figura2 = new JLabel("");
            figura2.setIcon(new ImageIcon(
                    ("src/main/resources/images/deportesbn.png")));
        }
        return figura2;
    }

    private JLabel getFigura3() {
        if (figura3 == null) {
            figura3 = new JLabel("");
            figura3.setIcon(new ImageIcon(
                    ("src/main/resources/images/entretenimientobn.png")));
        }
        return figura3;
    }

    private JLabel getFigura4() {
        if (figura4 == null) {
            figura4 = new JLabel("");
            figura4.setIcon(new ImageIcon(
                    ("src/main/resources/images/geografiabn.png")));
        }
        return figura4;
    }

    private JLabel getFigura5() {
        if (figura5 == null) {
            figura5 = new JLabel("");
            figura5.setIcon(new ImageIcon(
                    ("src/main/resources/images/historiabn.png")));
        }
        return figura5;
    }

    private JLabel getFigura6() {
        if (figura6 == null) {
            figura6 = new JLabel("");
            figura6.setIcon(new ImageIcon(
                    ("src/main/resources/images/literaturabn.png")));
        }
        return figura6;
    }

    private JPanel getPanelFiguras2() {
        if (panelFiguras2 == null) {
            panelFiguras2 = new JPanel();
            panelFiguras2.setBounds(168, 11, 146, 65);
            panelFiguras2.setLayout(new GridLayout(0, 3, 0, 0));
            panelFiguras2.add(getLabel_6());
            panelFiguras2.add(getLabel_1_1());
            panelFiguras2.add(getLabel_2_1());
            panelFiguras2.add(getLabel_3_1());
            panelFiguras2.add(getLabel_4_1());
            panelFiguras2.add(getLabel_5_1());
        }
        return panelFiguras2;
    }

    private JLabel getLabel_6() {
        if (figura7 == null) {
            figura7 = new JLabel("");
            figura7.setIcon(new ImageIcon(
                    ("src/main/resources/images/cienciasbn.png")));
        }
        return figura7;
    }

    private JLabel getLabel_1_1() {
        if (figura8 == null) {
            figura8 = new JLabel("");
            figura8.setIcon(new ImageIcon(
                    ("src/main/resources/images/deportesbn.png")));
        }
        return figura8;
    }

    private JLabel getLabel_2_1() {
        if (figura9 == null) {
            figura9 = new JLabel("");
            figura9.setIcon(new ImageIcon(
                    ("src/main/resources/images/entretenimientobn.png")));
        }
        return figura9;
    }

    private JLabel getLabel_3_1() {
        if (figura10 == null) {
            figura10 = new JLabel("");
            figura10.setIcon(new ImageIcon(
                    ("src/main/resources/images/geografiabn.png")));
        }
        return figura10;
    }

    private JLabel getLabel_4_1() {
        if (figura11 == null) {
            figura11 = new JLabel("");
            figura11.setIcon(new ImageIcon(
                    ("src/main/resources/images/historiabn.png")));
        }
        return figura11;
    }

    private JLabel getLabel_5_1() {
        if (figura12 == null) {
            figura12 = new JLabel("");
            figura12.setIcon(new ImageIcon(
                    ("src/main/resources/images/literaturabn.png")));
        }
        return figura12;
    }

    public ImageIcon getImage(int numero) {
        switch (numero) {
            case 0:
                return new ImageIcon(("src/main/resources/images/ciencias.png"));
            case 1:
                return new ImageIcon(("src/main/resources/images/deportes.png"));

            case 2:
                return new ImageIcon(
                        ("src/main/resources/images/entretenimiento.png"));

            case 3:
			return new ImageIcon(("src/main/resources/images/geografia.png"));

            case 4:
                return new ImageIcon(("src/main/resources/images/historia.png"));

            case 5:
			return new ImageIcon(("src/main/resources/images/literatura.png"));

            default:
			return new ImageIcon(("src/main/resources/images/literatura.png"));

        }
    }

    private JPanel getPanelFiguras3() {
        if (panelFiguras3 == null) {
            panelFiguras3 = new JPanel();
            panelFiguras3.setBounds(168, 11, 146, 65);
            panelFiguras3.setLayout(new GridLayout(0, 3, 0, 0));
            panelFiguras3.add(getFigura13());
            panelFiguras3.add(getFigura14());
            panelFiguras3.add(getFigura15());
            panelFiguras3.add(getFigura16());
            panelFiguras3.add(getFigura17());
            panelFiguras3.add(getLabel_6_1());
        }
        return panelFiguras3;
    }

    private JLabel getFigura13() {
        if (figura13 == null) {
            figura13 = new JLabel("");
            figura13.setIcon(new ImageIcon(
                    ("src/main/resources/images/cienciasbn.png")));
        }
        return figura13;
    }

    private JLabel getFigura14() {
        if (figura14 == null) {
            figura14 = new JLabel("");
            figura14.setIcon(new ImageIcon(
                    ("src/main/resources/images/deportesbn.png")));
        }
        return figura14;
    }

    private JLabel getFigura15() {
        if (figura15 == null) {
            figura15 = new JLabel("");
            figura15.setIcon(new ImageIcon(
                    ("src/main/resources/images/entretenimientobn.png")));
        }
        return figura15;
    }

    private JLabel getFigura16() {
        if (figura16 == null) {
            figura16 = new JLabel("");
            figura16.setIcon(new ImageIcon(
                    ("src/main/resources/images/geografiabn.png")));
        }
        return figura16;
    }

    private JLabel getFigura17() {
        if (figura17 == null) {
            figura17 = new JLabel("");
            figura17.setIcon(new ImageIcon(
                    ("src/main/resources/images/historiabn.png")));
        }
        return figura17;
    }

    private JLabel getLabel_6_1() {
        if (figura18 == null) {
            figura18 = new JLabel("");
            figura18.setIcon(new ImageIcon(
                    ("src/main/resources/images/literaturabn.png")));
        }
        return figura18;
    }

    private JPanel getPanelFiguras4() {
        if (panelFiguras4 == null) {
            panelFiguras4 = new JPanel();
            panelFiguras4.setBounds(168, 11, 146, 65);
            panelFiguras4.setLayout(new GridLayout(0, 3, 0, 0));
            panelFiguras4.add(getFigura19());
            panelFiguras4.add(getFigura20());
            panelFiguras4.add(getFigura21());
            panelFiguras4.add(getFigura22());
            panelFiguras4.add(getFigura23());
            panelFiguras4.add(getLabel_6_2());
        }
        return panelFiguras4;
    }

    private JLabel getFigura19() {
        if (figura19 == null) {
            figura19 = new JLabel("");
            figura19.setIcon(new ImageIcon(
                    ("src/main/resources/images/cienciasbn.png")));
        }
        return figura19;
    }

    private JLabel getFigura20() {
        if (figura20 == null) {
            figura20 = new JLabel("");
            figura20.setIcon(new ImageIcon(
                    ("src/main/resources/images/deportesbn.png")));
        }
        return figura20;
    }

    private JLabel getFigura21() {
        if (figura21 == null) {
            figura21 = new JLabel("");
            figura21.setIcon(new ImageIcon(
                    ("src/main/resources/images/entretenimientobn.png")));
        }
        return figura21;
    }

    private JLabel getFigura22() {
        if (figura22 == null) {
            figura22 = new JLabel("");
            figura22.setIcon(new ImageIcon(
                    ("src/main/resources/images/geografiabn.png")));
        }
        return figura22;
    }

    private JLabel getFigura23() {
        if (figura23 == null) {
            figura23 = new JLabel("");
            figura23.setIcon(new ImageIcon(
                    ("src/main/resources/images/historiabn.png")));
        }
        return figura23;
    }

    private JLabel getLabel_6_2() {
        if (figura24 == null) {
            figura24 = new JLabel("");
            figura24.setIcon(new ImageIcon(
                    ("src/main/resources/images/literaturabn.png")));
        }
        return figura24;
    }

    private JPanel getPanelFiguras5() {
        if (panelFiguras5 == null) {
            panelFiguras5 = new JPanel();
            panelFiguras5.setBounds(170, 11, 146, 65);
            panelFiguras5.setLayout(new GridLayout(0, 3, 0, 0));
            panelFiguras5.add(getFigura25());
            panelFiguras5.add(getFigura26());
            panelFiguras5.add(getFigura27());
            panelFiguras5.add(getFigura28());
            panelFiguras5.add(getFigura29());
            panelFiguras5.add(getLabel_6_3());
        }
        return panelFiguras5;
    }

    private JLabel getFigura25() {
        if (figura25 == null) {
            figura25 = new JLabel("");
            figura25.setIcon(new ImageIcon(
                    ("src/main/resources/images/cienciasbn.png")));
        }
        return figura25;
    }

    private JLabel getFigura26() {
        if (figura26 == null) {
            figura26 = new JLabel("");
            figura26.setIcon(new ImageIcon(
                    ("src/main/resources/images/deportesbn.png")));
        }
        return figura26;
    }

    private JLabel getFigura27() {
        if (figura27 == null) {
            figura27 = new JLabel("");
            figura27.setIcon(new ImageIcon(
                    ("src/main/resources/images/entretenimientobn.png")));
        }
        return figura27;
    }

    private JLabel getFigura28() {
        if (figura28 == null) {
            figura28 = new JLabel("");
            figura28.setIcon(new ImageIcon(
                    ("src/main/resources/images/geografiabn.png")));
        }
        return figura28;
    }

    private JLabel getFigura29() {
        if (figura29 == null) {
            figura29 = new JLabel("");
            figura29.setIcon(new ImageIcon(
                    ("src/main/resources/images/historiabn.png")));
        }
        return figura29;
    }

    private JLabel getLabel_6_3() {
        if (figura30 == null) {
            figura30 = new JLabel("");
            figura30.setIcon(new ImageIcon(
                    ("src/main/resources/images/literaturabn.png")));
        }
        return figura30;
    }

    private JPanel getPanelFiguras6() {
        if (panelFiguras6 == null) {
            panelFiguras6 = new JPanel();
            panelFiguras6.setBounds(168, 0, 146, 65);
            panelFiguras6.setLayout(new GridLayout(0, 3, 0, 0));
            panelFiguras6.add(getFigura31());
            panelFiguras6.add(getFigura32());
            panelFiguras6.add(getFigura33());
            panelFiguras6.add(getFigura34());
            panelFiguras6.add(getFigura35());
            panelFiguras6.add(getLabel_6_4());
        }
        return panelFiguras6;
    }

    private JLabel getFigura31() {
        if (figura31 == null) {
            figura31 = new JLabel("");
            figura31.setIcon(new ImageIcon(
                    ("src/main/resources/images/cienciasbn.png")));
        }
        return figura31;
    }

    private JLabel getFigura32() {
        if (figura32 == null) {
            figura32 = new JLabel("");
            figura32.setIcon(new ImageIcon(
                    ("src/main/resources/images/deportesbn.png")));
        }
        return figura32;
    }

    private JLabel getFigura33() {
        if (figura33 == null) {
            figura33 = new JLabel("");
            figura33.setIcon(new ImageIcon(
                    ("src/main/resources/images/entretenimientobn.png")));
        }
        return figura33;
    }

    private JLabel getFigura34() {
        if (figura34 == null) {
            figura34 = new JLabel("");
            figura34.setIcon(new ImageIcon(
                    ("src/main/resources/images/geografiabn.png")));
        }
        return figura34;
    }

    private JLabel getFigura35() {
        if (figura35 == null) {
            figura35 = new JLabel("");
            figura35.setIcon(new ImageIcon(
                    ("src/main/resources/images/historiabn.png")));
        }
        return figura35;
    }

    private JLabel getLabel_6_4() {
        if (figura36 == null) {
            figura36 = new JLabel("");
            figura36.setIcon(new ImageIcon(
                    ("src/main/resources/images/literaturabn.png")));
        }
        return figura36;
    }
}
