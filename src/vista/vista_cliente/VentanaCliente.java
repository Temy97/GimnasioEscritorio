package vista.vista_cliente;

import java.awt.Font;
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
import javax.swing.UIManager;

import controlador.servicios.Singletone;
import modelo.objetos.Administrativo;
import modelo.objetos.Cliente;
import modelo.objetosDAO.AdministrativoDAO;
import modelo.objetosDAO.ClienteDAO;

@SuppressWarnings("serial")
public class VentanaCliente extends JFrame {
	
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
	
	private Font fuenteGeneral = new Font("SansSerif", Font.BOLD, 12);
	private Font fuenteTablas = new Font("SansSerif", Font.PLAIN, 12);
	private Font fuenteAreas = new Font("SansSerif", Font.PLAIN, 12);
	
	private VentanaCliente yo = this;
	private JPanel panel = PANEL_IMAGEN_FONDO;
	private VerClasesApuntadas panelVerClasesApuntadas = new VerClasesApuntadas(this, datos_cliente);
	private ApuntarClase panelApuntarse = new ApuntarClase(this, datos_cliente);
	private VerClases panelVerClases = new VerClases(this);
	private CambiarDatos panelCambiarDatos = new CambiarDatos(this, datos_cliente);
	private Opciones panelOpciones = new Opciones(this);
	
	private static Cliente datos_cliente;
	//public static final String DNI_EJEMPLO = "12345678D";
	
	//CONSTRUCTOR:
	public VentanaCliente(Cliente datos_cliente) {
		this.setDatos_cliente(datos_cliente);
		
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.synth.SynthStyleFactory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) (screenSize.getWidth() / 6) * 4, (int) (screenSize.getHeight() / 6) * 4);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setJMenuBar(barraCliente());
		this.setContentPane(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	//METODOS:
	/*public static void main(String[] args) {
		new VentanaCliente();
	}*/
	
	
	/**
	 * Se genera una barra de menu con
	 * las diversas opciones disponibles
	 * para los clientes (inicio, apuntarse a clases... etc).
	 * @return
	 */
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
				llamarInicio();
			}
		});
		
		JMenuItem ver_clases_apuntadas = new JMenuItem("Ver clases Apuntadas");
		ver_clases_apuntadas.setToolTipText("Ver el historial de las clases a las que se ha asistido");
		ver_clases_apuntadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarVerClasesApuntadas();
			}
		});
		
		JMenuItem apuntarse = new JMenuItem("Apuntarse");
		apuntarse.setToolTipText("Apuntarse a proximas clases");
		apuntarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarApuntarse();
			}
		});
		
		JMenuItem ver_clases = new JMenuItem("Ver clases");
		ver_clases.setToolTipText("Ver los horarios semanales");
		ver_clases.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarVerClases();
			}
		});
		
		JMenuItem cambiar_datos = new JMenuItem("Cambiar datos");
		cambiar_datos.setToolTipText("Acceder al menu para cambiar datos personales");
		cambiar_datos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarCambiarDatos();
			}
		});
		
		JMenuItem options = new JMenuItem("Opciones Generales");
		options.setToolTipText("Opciones genéricas de la app, como editar el tamaño de la letra");
		options.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarOpciones();
			}
		});
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setToolTipText("Cerrar la aplicacion");
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.servicios.Singletone.cierre();
				System.exit(0);
			}
		});
		
		menu.add(inicio);
		
		menu_clases.add(ver_clases_apuntadas);
		menu_clases.add(apuntarse);
		menu_clases.add(ver_clases);
		menu.add(menu_clases);
		
		menu_opciones.add(cambiar_datos);
		menu_opciones.add(options);
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
	 * correspondiente al atributo 'ver_clases_apuntadas'.
	 */
	public void llamarVerClasesApuntadas() {
		limpiarContenido(panel);
		limpiarContenido(panelVerClasesApuntadas);
		panelVerClasesApuntadas = new VerClasesApuntadas(this, datos_cliente);
		panel = panelVerClasesApuntadas;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel
	 * correspondiente al atributo 'apuntarse'.
	 */
	public void llamarApuntarse() {
		limpiarContenido(panel);
		limpiarContenido(panelApuntarse);
		panelApuntarse = new ApuntarClase(this, datos_cliente);
		panel = panelApuntarse;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel
	 * correspondiente al atributo 'VerClases'.
	 */
	public void llamarVerClases() {
		limpiarContenido(panel);
		limpiarContenido(panelVerClases);
		panelVerClases = new VerClases(this);
		panel = panelVerClases;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel
	 * correspondiente al atributo 'panelCambiarDatos'.
	 */
	public void llamarCambiarDatos() {
		limpiarContenido(panel);
		limpiarContenido(panelCambiarDatos);
		panelCambiarDatos = new CambiarDatos(this, datos_cliente);
		panel = panelCambiarDatos;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel
	 * correspondiente al atributo 'panelOpciones'.
	 */
	public void llamarOpciones() {
		limpiarContenido(panel);
		limpiarContenido(panelOpciones);
		panelOpciones = new Opciones(this);
		panel = panelOpciones;
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
	
	
	//GET-SET:
	public Font getFuenteGeneral() {
		return fuenteGeneral;
	}
	public void setFuenteGeneral(Font fuente) {
		this.fuenteGeneral = fuente;
	}
	public Font getFuenteTablas() {
		return fuenteTablas;
	}
	public void setFuenteTablas(Font fuente) {
		this.fuenteTablas = fuente;
	}
	public Font getFuenteAreas() {
		return fuenteAreas;
	}
	public void setFuenteAreas(Font fuente) {
		this.fuenteAreas = fuente;
	}



	public static Cliente getDatos_cliente() {
		return datos_cliente;
	}



	public static void setDatos_cliente(Cliente datos_cliente) {
		VentanaCliente.datos_cliente = datos_cliente;
	}
}
