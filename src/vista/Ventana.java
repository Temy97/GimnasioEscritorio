package vista;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controlador.objetos.Administrativo;
import controlador.objetos.Cliente;
import modelo.Singletone;
import modelo.objetosDAO.AdministrativoDAO;
import modelo.objetosDAO.ClienteDAO;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	
	//ATRIBUTOS:
	private static final JPanel PANEL_IMAGEN_FONDO = new JPanel() {
		@Override
		public void paint(Graphics g) {
			Image imagen = new ImageIcon("img\\fondo_pantalla.jpg").getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	};
	private Ventana yo = this;
	private JPanel panel = PANEL_IMAGEN_FONDO;
	private CambiarDatos panelCambiarDatos = new CambiarDatos(this);
	
	public static final String DNI_EJEMPLO = "12345678D";
	
	//CONSTRUCTOR:
	public Ventana() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) (screenSize.getWidth() / 6) * 4, (int) (screenSize.getHeight() / 6) * 4);
		this.setLocationRelativeTo(null);
		
		this.setJMenuBar(barraCliente());
		this.setContentPane(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	//METODOS:
	public static void main(String[] args) {
		new Ventana();
	}
	
	
	private JMenuBar barraCliente() {
		JMenuBar menu_bar = new JMenuBar();
		JMenu menu = new JMenu("Inicio");
		JMenu menu_clases = new JMenu("Clases");
		JMenu menu_opciones = new JMenu("Opciones");
		
		JMenuItem inicio = new JMenuItem("Inicio");
		inicio.setToolTipText("Volver a la forma de inicio con la imagen de fondo");
		inicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				llamarInicio();
			}
		});
		
		JMenuItem ver_clases_apuntadas = new JMenuItem("Ver clases Apuntadas");
		ver_clases_apuntadas.setToolTipText("Ver el historial de las clases a las que se ha asistido");
		JMenuItem apuntarse = new JMenuItem("Apuntarse");
		apuntarse.setToolTipText("Apuntarse a proximas clases");
		JMenuItem ver_clases = new JMenuItem("Ver clases");
		ver_clases.setToolTipText("Ver los horarios semanales");
		
		JMenuItem cambiar_datos = new JMenuItem("Cambiar datos");
		cambiar_datos.setToolTipText("Acceder al menu para cambiar datos personales");
		cambiar_datos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarCambiarDatos();
			}
		});
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setToolTipText("Cerrar la aplicacion");
		
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.Singletone.cierre();
				System.exit(0);
			}});
		
		menu.add(inicio);
		
		menu_clases.add(ver_clases_apuntadas);
		menu_clases.add(apuntarse);
		menu_clases.add(ver_clases);
		menu.add(menu_clases);
		
		menu_opciones.add(cambiar_datos);
		menu.add(menu_opciones);
		
		menu.add(salir);
		menu_bar.add(menu);
		
		return menu_bar;
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel
	 * correspondiente al atributo 'inicio'.
	 */
	public void llamarInicio() {
		limpiarContenido(panel);
		panel = PANEL_IMAGEN_FONDO;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel
	 * correspondiente al atributo 'panelCambiarDatos'.
	 */
	public void llamarCambiarDatos() {
		limpiarContenido(panel);
		limpiarContenido(panelCambiarDatos);
		panelCambiarDatos = new CambiarDatos(this);
		panel = panelCambiarDatos;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Limpia y revalida el componente que
	 * recibe por parametro.
	 * @param contenido
	 */
	public static void limpiarContenido(JComponent contenido) {
		contenido.removeAll();
		contenido.revalidate();
		contenido.repaint();
	}
}
