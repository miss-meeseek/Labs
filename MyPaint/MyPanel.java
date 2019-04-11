import static java.lang.Math.*;
import java.awt.* ;
import java.awt.geom.*;
import java.awt.event.* ;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;

/** Klasa w ktorej odbywa sie rysowanie 
* 
* @author Volha Hutouskaya
*/
public class MyPanel extends JPanel implements MouseListener, MouseWheelListener, MouseMotionListener {
	/**
	* Wyskakujące menu. Sluzy do zmiany kolorow.
	*/
	public JPopupMenu menu;
	/**
	* Lista, w ktorej przechowuja sie punkty nacisniete w trybie "Polygon"
	*/		
	ArrayList<Point2D.Float> points = new ArrayList<Point2D.Float>();
	/**
	* Lista do przechowywania objektow typu Poly, czyli wielokatow.
	*/
	ArrayList<Poly> polygons = new ArrayList<Poly>();
	/**
	* Lista do przechowywania objektow typu ZEllipse, czyli okragow.
	*/
	ArrayList<ZEllipse> circle = new ArrayList<ZEllipse>();
	/**
	* Lista do przechowywania objektow typu ZRectangle, czyli prostokatow.
	*/
	ArrayList<ZRectangle> rectangle = new ArrayList<ZRectangle>();
	/**
	* Wspolrzedne klikniec.
	*/
	float xPressed, yPressed, xCenter, yCenter;
	/**
	* Zmienna odpowiadajaca za liczbe dotychczas wykonanych klilniec.
	*/
	boolean firstClick = true;
	/**
	* Zmienna sygnalizujaca ze nastapil koniec wielokata
	*/
	boolean polygonEnd = false;
	/**
	* Zmienna sygnalizujaca ze na planszy moze istniec aktywna figura, ktora trzeba zgasic przy wybraniu innej opcji w menu.
	*/
	boolean activeFigureFlag = false;
	/**
	* Zmienna przechowujaca liczbe podanych wierzcholkow wielokata.
	*/
	int number = 0;
	/**
	* Zmienne szerokosci i wysokosci do dodawania okragow i prostokatow do odpowiednich list
	*/
	float hight, width;
	
	/**
	* Tworzy nowy objekt.
	*/
	public MyPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		menu = new JPopupMenu();
		ActionListener menuListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("red")) {
					changeColor(Color.RED);
				}
				if(e.getActionCommand().equals("blue")) {
					changeColor(Color.BLUE);
				}
				if(e.getActionCommand().equals("green")) {
					changeColor(Color.GREEN);
				}
			}
		};
		JMenuItem item;
		menu.add(item = new JMenuItem("red"));
		item.addActionListener(menuListener);
		menu.add(item = new JMenuItem("blue"));
		item.addActionListener(menuListener);
		menu.add(item = new JMenuItem("green"));
		item.addActionListener(menuListener);
	}
	
	private void checkMenu(MouseEvent e) {
		if (e.isPopupTrigger()) {
			menu.show(MyPaint.this, e.getX(), e.getY());
		}
	}
	
	/** 
	* Metoda rysujaca figury i punkty
	*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
	
		for (ZEllipse c : circle) {
			if(!c.isActive()) {
				g2d.setColor(c.getColor());
				g2d.fill(c);
			}
			else {
				g2d.setColor(Color.GRAY);
				g2d.fill(c);
			}
		}
		
		for (ZRectangle r : rectangle) {
			if(!r.isActive()) {
				g2d.setColor(r.getColor());
				g2d.fill(r);
			}
			else {
				g2d.setColor(Color.GRAY);
				g2d.fill(r);
			}
		}
		
		g2d.setColor(Color.RED);
		for(Point2D.Float p: points) {
			g2d.fillOval((int)p.getX(), (int)p.getY(), 5, 5);
			g2d.setColor(Color.GRAY);
		}
		
		for(Poly p : polygons) {
			if(!p.isActive()) {
				g2d.setColor(p.getColor());
				g2d.fill(p);
			}
			else {
				g2d.setColor(Color.GRAY);
				g2d.fill(p);
			}
		}
		repaint();
	} 
	
	/** 
	* Metoda zmieniajca kolor aktywnej figury
	* @param co kolor
	*/
	public void changeColor(Color co) {
		for(ZRectangle r : rectangle) {
			if (r.isActive()) {
				r.changeColor(co);
				r.changeActive();
				repaint();
			}
		}
		for(ZEllipse c : circle) {
			if (c.isActive()) {
				c.changeColor(co);
				c.changeActive();
				repaint();
			}
		}
		for(Poly p : polygons) {
			if (p.isActive()) {
				p.changeColor(co);
				p.changeActive();
				repaint();
			}
		}
	}
	
	/** 
	* Metoda zmieniajca aktywnosc figury
	*/
	private void changeActiveFigures(int x, int y) {
		for (ZEllipse c : circle) {
			if(c.isActive()) {
				c.changeActive();
			}
			if(c.isHit(x, y)) {
				c.changeActive();
			}
		}
		for (ZRectangle r : rectangle) {
			if(r.isActive()) {
				r.changeActive();
			}
			if(r.isHit(x, y)) {
				r.changeActive();
			}
		}
		for (Poly p : polygons) {
			if(p.isActive()) {
				p.changeActive();
			}
			if(p.isHit((int)x, (int)y)) {
				p.changeActive();
			}
		}
		repaint();
	}
	
	/** 
	* Metoda zmieniajca aktywnosc figury przy wybraniu innej opcji w menu.
	*/
	private void getRidOfActiveFigures() {
		for (ZEllipse c : circle) {
			if(c.isActive()) {
				c.changeActive();
				c.changeColor(c.getColor());
				repaint();
			}
		}
		for (ZRectangle r : rectangle) {
			if(r.isActive()) {
				r.changeActive();
				r.changeColor(r.getColor());
				repaint();
			}
		}
		for (Poly p : polygons) {
			if(p.isActive()) {
				p.changeActive();
				p.changeColor(p.getColor());
				repaint();
			}
		}
	}
 
	@Override
	public void mousePressed(MouseEvent e) {
		checkMenu(e);
		if(action == 1) {
			if(activeFigureFlag) {
				getRidOfActiveFigures();
				activeFigureFlag = false;
			}
			if(!firstClick) {
				xCenter = e.getX();
				yCenter = e.getY();
				
				float dx = (xCenter - xPressed);
				float dy = (yCenter - yPressed);
				float R = (float)Math.sqrt(dx*dx + dy*dy);
				addToList(xCenter - R, yCenter - R, 2*R, 2*R);
				firstClick = true;
			} 
			else if(firstClick) {
					xPressed = e.getX();
					yPressed = e.getY();
					firstClick = false;
			}
		}
		
		if(action == 2) {
			if(activeFigureFlag) {
				getRidOfActiveFigures();
				activeFigureFlag = false;
			}
			if(!firstClick) {
				xCenter = e.getX();
				yCenter = e.getY();
				float dx = (xCenter - xPressed);
				float dy = (yCenter - yPressed);
				if(dx < 0) { //rysowanie wlewo
					dx *= (-1);
				}
				if(dy < 0) { //rysowanie wgore
					dy *= (-1);
				}
				addToList(xCenter - dx, yCenter - dy, 2*dx, 2*dy);
				firstClick = true;
			}
			else if(firstClick) {
					xPressed = e.getX();
					yPressed = e.getY();
					firstClick = false;
			}
		}
		
		if(action == 3) {
			if(activeFigureFlag) {
				getRidOfActiveFigures();
				activeFigureFlag = false;
			}
			
			xPressed = e.getX();
			yPressed = e.getY();
			
			points.add(new Point2D.Float(xPressed, yPressed));	//rysuja sie kropeczki
			double dx = points.get(0).getX() - points.get(number).getX();
			double dy = points.get(0).getY() - points.get(number).getY();
			
			if(number > 0) {
				if((dx * dx + dy * dy) <= 200) {
					polyFunction(points);
					
					number = -1;
					polygonEnd = false;
					for(int i = points.size() - 1; i >= 0; i--) {
						points.remove(i);                          
					}
				}
			}
			number++;
		}
		
		if(action == 0) {
			activeFigureFlag = true;
			xPressed = e.getX();
			yPressed = e.getY();
			changeActiveFigures((int)xPressed, (int)yPressed);
		}
	}
	
	/** 
	* Metoda dodajaca okragi i prostokaty do list.
	* @param xPressed wspolrzedna x klikniecia
	* @param yPressed wspolrzedna y klikniecia
	* @param width szerokosc figury {@link width}
	* @param hight wysokosc figury {@link hight}
	*/
	private void addToList(float xPressed, float yPressed, float width, float hight) {
		if(action == 1) {
			ZEllipse c = new ZEllipse(xPressed, yPressed , width, hight);
			circle.add(c);
		}
		if(action == 2) {
			ZRectangle r = new ZRectangle(xPressed, yPressed, width, hight);
			rectangle.add(r);
		}
	}
	
	/** 
	* Metoda tworzaca i dodajaca do listy wielokaty
	* @param points lista nacisnietych punktow jednego wielokata w trybie wielokata
	*/
	public void polyFunction(ArrayList<Point2D.Float> points) {
		int n = points.size() - 1;
		int[] xPoints = new int[n];
		int[] yPoints = new int[n];
		
		for(int i = 0; i < n; i++) {
			Point2D.Float tmp = points.get(i);
			xPoints[i] = (int)tmp.getX();
			yPoints[i] = (int)tmp.getY();
		}
		Poly new_poly = new Poly(xPoints, yPoints, n);
		polygons.add(new_poly);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
			doMove(e);
			for(Poly p : polygons){
			for(int i = 0; i < p.npoints; i++) {
			}
		}
	}
	
	/** 
	* Metoda do przemieszczania figur
	*/
	private void doMove(MouseEvent e) {
		int dx = (int)e.getX() - (int)xPressed;
		int dy = (int)e.getY() - (int)yPressed;
		for (ZEllipse c : circle) {
			if(c.isActive()){
				c.addX(dx);
				c.addY(dy);		
				repaint();
			}
		}
		for (ZRectangle r : rectangle) {
			if(r.isActive()) {
				r.addX(dx);
				r.addY(dy);		
				repaint();
			}
		}
		dx = (int)(e.getX() - xPressed);
		dy = (int)(e.getY() - yPressed);
		for (Poly p : polygons) {
			if(p.isActive()) {
				p.addX(dx);
				p.addY(dy);		
				repaint();
			}
		}
		xPressed += dx;
		yPressed += dy;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		doScale(e);
	}
	
	/** 
	* Metoda do skalowania figur
	*/
	private void doScale(MouseWheelEvent e) {
		xPressed = e.getX();
		yPressed = e.getY();
		if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
			for (ZEllipse c : circle) {
				if(c.isActive()) {
					float amount =  e.getWheelRotation() * 9f;
					c.addWidth(amount);
					c.addHeight(amount);		
					repaint();
				}
			}
			for (ZRectangle r : rectangle) {
				if(r.isActive()) {
					float amount =  e.getWheelRotation() * 9f;
					r.addWidth(amount);
					r.addHeight(amount);		
					repaint();
				}
			}
			for (Poly p : polygons) {
				if(p.isActive()) {
					float amount =  e.getWheelRotation() * 1.2f;	
					int CenterX = (int)p.getBounds2D().getCenterX();
					int CenterY = (int)p.getBounds2D().getCenterY();
					if(amount < 0) {
						amount = (-1)*(1/amount);
					}
					p.scaling(amount);
					int Center2X = p.findCenterX();
					int Center2Y = p.findCenterY();
					int dx = CenterX - Center2X;
					int dy = CenterY - Center2Y;
					p.addX(dx);
					p.addY(dy);		
					repaint();
				}
			}
		}            
	}
 
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		checkMenu(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		checkMenu(e);
	}
}

class Dialog extends JDialog {	
	public Dialog() {
		
		add(new JLabel("<html><i> Autor: Volha Hutouskaya.<br>" +
		" Program podobny do Paint. Pozwala na rysowanie wybranych figur.</i><hr>"+ 
		" Instrukcja obslugi: <br>" +
		"1. Mozna wybrac w Menu figury do rysowania: okrag, prostokat, wielokat. <br>" +
		"2. Okrag i prostokat narysuja sie po dwoch kliknieciach na ekranie(wyznaczajacych promien albo polowe srednicy), <br>" +
		"3. Wielokat zostanie narysowany po wskazaniu kilku punktow i kliknieciu w poblizu pierwszego punktu(czerwona kropka) <br>" +
		"4. Wybierajac opcje Empty uzytkownik przestanie tworzyc nowe figury i bedzie mogl dzialac na juz istnujacych. <br>" +
		"5. Klikajac prawym przyciskiem myszy w polu figury wyswietlamy menu, zmieniajace kolory wybranej figury. <br>" +
		"6. Klikajac lewym przyciskiem myszy w polu figury aktywujemy figyre. Mozemy ja przeciagac i skalowac. </html>"));
		
		JButton myButton = new JButton(" OK ");
		myButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		JPanel panel = new JPanel();
		panel.add(myButton);
		add(panel, BorderLayout.SOUTH);
	}
}

class menuAppletWindowAdapter extends WindowAdapter { 
	public void windowClosing(WindowEvent e) { System.exit(0); } 
}