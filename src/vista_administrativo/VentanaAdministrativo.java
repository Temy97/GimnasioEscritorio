package vista_administrativo;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VentanaAdministrativo extends JFrame {

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
	
	private VentanaAdministrativo yo = this;
	private JPanel panel = PANEL_IMAGEN_FONDO;
	private PanelAdministrativo panel_administrativo = new PanelAdministrativo(this);
	private PanelClase panel_clase = new PanelClase(this);
	private PanelCliente panel_cliente = new PanelCliente(this);
	private PanelProfesor panel_profesor = new PanelProfesor(this);
	
	public static final String DNI_EJEMPLO = "12345678D";
	
	//CONSTRUCTOR:
	public VentanaAdministrativo() {
		
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
	public static void main(String[] args) {
		new VentanaAdministrativo();
	}
	
	
	private JMenuBar barraCliente() {
		JMenuBar menu_bar = new JMenuBar();
		
		JMenuItem inicio = new JMenuItem("Inicio");
		inicio.setToolTipText("Volver a la forma de inicio con la imagen de fondo");
		inicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarInicio();
			}
		});
		
		JMenuItem menu_administrativos = new JMenuItem("Administrativos");
		menu_administrativos.setToolTipText("Modificacion de administrativos");
		menu_administrativos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarPanelAdministrativos();
			}
		});
		
		JMenuItem menu_clases = new JMenuItem("Clases");
		menu_clases.setToolTipText("Modificacion de clases");
		menu_clases.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarPanelClases();
			}
		});
		
		JMenuItem menu_clientes = new JMenuItem("Clientes");
		menu_clientes.setToolTipText("Modificacion de clientes");
		menu_clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarPanelClientes();
			}
		});
		
		JMenuItem menu_profesores = new JMenuItem("Profesores");
		menu_profesores.setToolTipText("Modificacion de profesores");
		menu_profesores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				llamarPanelProfesor();
			}
		});
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setToolTipText("Cerrar la aplicacion");
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.Singletone.cierre();
				System.exit(0);
			}
		});
		
		menu_bar.add(inicio);
		menu_bar.add(menu_administrativos);
		menu_bar.add(menu_clases);
		menu_bar.add(menu_clientes);
		menu_bar.add(menu_profesores);
		menu_bar.add(salir);
		
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
	 * Provoca que la ventana muestre el JPanel correspondiente.
	 */
	public void llamarPanelAdministrativos() {
		limpiarContenido(panel);
		limpiarContenido(panel_administrativo);
		panel_administrativo = new PanelAdministrativo(this);
		panel = panel_administrativo;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel correspondiente.
	 */
	public void llamarPanelClases() {
		limpiarContenido(panel);
		limpiarContenido(panel_clase);
		panel_clase = new PanelClase(this);
		panel = panel_clase;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel correspondiente.
	 */
	public void llamarPanelClientes() {
		limpiarContenido(panel);
		limpiarContenido(panel_cliente);
		panel_cliente = new PanelCliente(this);
		panel = panel_cliente;
		yo.setContentPane(panel);
	}
	
	
	/**
	 * Provoca que la ventana muestre el JPanel correspondiente.
	 */
	public void llamarPanelProfesor() {
		limpiarContenido(panel);
		limpiarContenido(panel_profesor);
		panel_profesor = new PanelProfesor(this);
		panel = panel_profesor;
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
