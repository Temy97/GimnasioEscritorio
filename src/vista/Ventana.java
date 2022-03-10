package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controlador.objetos.Administrativo;
import modelo.objetosDAO.AdministrativoDAO;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	
	//CONSTRUCTOR:
	public Ventana() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) (screenSize.getWidth() / 6) * 4, (int) (screenSize.getHeight() / 6) * 4);
		this.setLocationRelativeTo(null);
		
		this.setJMenuBar(barraCliente());
		//this.add(new JPanel() {
			
		//}, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	//METODOS:
	public static void main(String[] args) {
		//new Ventana();
		System.out.println(AdministrativoDAO.insert_admin(new Administrativo("71709391Z", "dam2t")));
		
	}
	
	
	private JMenuBar barraCliente() {
		JMenuBar menu_bar = new JMenuBar();
		JMenu menu = new JMenu("Inicio");
		JMenu menu_clases = new JMenu("Clases");
		JMenu menu_opciones = new JMenu("Opciones");
		
		JMenuItem inicio = new JMenuItem("Inicio");
		inicio.setToolTipText("Volver a la forma de inicio con la imagen de fondo");
		
		JMenuItem ver_clases_apuntadas = new JMenuItem("Ver clases Apuntadas");
		ver_clases_apuntadas.setToolTipText("Ver el historial de las clases a las que se ha asistido");
		JMenuItem apuntarse = new JMenuItem("Apuntarse");
		apuntarse.setToolTipText("Apuntarse a proximas clases");
		JMenuItem ver_clases = new JMenuItem("Ver clases");
		ver_clases.setToolTipText("Ver los horarios semanales");
		
		JMenuItem cambiar_datos = new JMenuItem("Cambiar datos");
		cambiar_datos.setToolTipText("Acceder al menu para cambiar datos personales");
		
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
}
