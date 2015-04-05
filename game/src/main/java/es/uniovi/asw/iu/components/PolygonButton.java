package es.uniovi.asw.iu.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class PolygonButton extends JComponent implements ActionListener,
        MouseListener, MouseMotionListener {
    boolean hasPlayer;
    int jugador;

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public boolean isHasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub

        if (isActive && !hasPlayer) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            int x = (int) (getSize().width / 4.5);
            int y = (int) (getSize().height / 4.5);
            g2d.setComposite(makeComposite((float) 0.8));
            g.fillOval(x, y, 25, 25);
        }
        if (!isActive && hasPlayer) {
            int x = (int) (getSize().width / 4.5);
            int y = (int) (getSize().height / 4.5);
            g.drawImage(getImage(), x, y, null);
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Color color;
    public int contador;
    protected String text;

    protected Polygon polygon;

    protected Rectangle rectangle;

    protected boolean isActive;

    public PolygonButton(Polygon p, Integer id, Color color) {
        polygon = p;
        this.color = color;
        super.setName(String.valueOf(id));
        setText(text);
        setOpaque(false);

        addMouseListener(this);
        addMouseMotionListener(this);
        rectangle = new Rectangle(polygon.getBounds()); // Bug alert!
        rectangle.grow(-1, -1);
        setBounds(rectangle);
    }

    public void setText(String t) {
        text = t;
    }

    public String getText() {
        return text;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!rectangle.contains(e.getX(), e.getY()) || e.isConsumed()) {
            if (isActive) {
                isActive = false;
            }
            return;
        }

        int x = e.getX() - rectangle.x;
        int y = e.getY() - rectangle.y;
        boolean active = polygon.contains(x, y);

        if (isActive != active) {
            setState(active);
        }
        if (active) {
            e.consume();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    protected void setState(boolean active) {
        isActive = active;
        if (active) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            polygon.translate(-rectangle.x, -rectangle.y);
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void setEnabledButton(boolean active) {
        setState(active);
        super.setEnabled(active);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public Image getImage() {
        // BufferedImage img = ImageIO.read(new
        // File("/es/uniovi/asw/resources/espect.png"));
        switch (jugador) {
            case 0:
                ImageIcon icon = new ImageIcon(
                        ("src/main/resources/images/literatura.png"));
                return icon.getImage();
            case 1:
                ImageIcon icon2 = new ImageIcon(
                        ("src/main/resources/images/historia.png"));
                return icon2.getImage();
            case 2:
                ImageIcon icon3 = new ImageIcon(
                        ("src/main/resources/images/geografia.png"));
                return icon3.getImage();
            case 3:
                ImageIcon icon4 = new ImageIcon(
                        ("src/main/resources/images/entretenimiento.png"));
                return icon4.getImage();
            case 4:
                ImageIcon icon5 = new ImageIcon(
                        ("src/main/resources/images/deportes.png"));
                return icon5.getImage();
            case 5:
                ImageIcon icon6 = new ImageIcon(
                        ("src/main/resources/images/ciencias.png"));
                return icon6.getImage();
            default:
                ImageIcon icon7 = new ImageIcon(
                        ("src/main/resources/images/literatura.png"));
                return icon7.getImage();
        }
    }
}
