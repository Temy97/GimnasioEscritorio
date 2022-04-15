package vista_administrativo;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelCliente extends JPanel {
	
	//Atributos:
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	//CONSTRUCTOR:
	public PanelCliente(JFrame ventana) {
		this.setLayout(null);
		
		/* Parametros y boton para buscar o borrar
		 * al cliente, en caso de no encontrar
		 * resultados mostrat mensaje de error,
		 * en caso de SÍ encontrar clientes mostrar
		 * resultado/s en una tabla para su
		 * posterior modificación.
		 * Parametros y boton situados en
		 * la parte superior de la aplicacion.
		 */
		JLabel nombre_buscar = new JLabel("Nombre:");
		nombre_buscar.setBounds(200, 20, 80, 30);
		JTextField text_nombre_buscar = new JTextField();
		text_nombre_buscar.setBounds(300, 20, 100, 30);
		
		JLabel apellido_buscar = new JLabel("Apellido [1º]:");
		apellido_buscar.setBounds(450, 20, 80, 30);
		JTextField text_apellido_buscar = new JTextField();
		text_apellido_buscar.setBounds(550, 20, 100, 30);
		
		JLabel dni_buscar = new JLabel("DNI:");
		dni_buscar.setBounds(200, 65, 80, 30);
		JTextField text_dni_buscar = new JTextField();
		text_dni_buscar.setBounds(300, 65, 100, 30);
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(700, 40, 80, 40);
		JButton borrar = new JButton("Borrar");
		borrar.setBounds(840, 40, 80, 40);
		
		
		JSeparator separador1 = new JSeparator();
		separador1.setOrientation(JSeparator.HORIZONTAL);
		separador1.setBounds(180, 120, (int) (screenSize.getWidth() / 6) * 4, 5);
		
		/* TODOS los parametros para rellenar
		 * con los datos para crear un nuevo
		 * cliente. Situado todo en el margen
		 * izquierdo de la ventana.
		 */
		JLabel dni_nuevo = new JLabel("DNI:");
		dni_nuevo.setBounds(20, 5, 80, 30);
		JTextField text_dni_nuevo = new JTextField();
		text_dni_nuevo.setBounds(20, 30, 100, 30);
		
		JLabel pwd_nuevo = new JLabel("Contraseña:");
		pwd_nuevo.setBounds(20, 60, 80, 30);
		JTextField text_pwd_nuevo = new JTextField();
		text_pwd_nuevo.setBounds(20, 85, 100, 30);
		
		JLabel nombre_nuevo = new JLabel("Nombre:");
		nombre_nuevo.setBounds(20, 115, 80, 30);
		JTextField text_nombre_nuevo = new JTextField();
		text_nombre_nuevo.setBounds(20, 140, 100, 30);
		
		JLabel apellido_nuevo = new JLabel("Apellido:");
		apellido_nuevo.setBounds(20, 170, 80, 30);
		JTextField text_apellido_nuevo = new JTextField();
		text_apellido_nuevo.setBounds(20, 195, 100, 30);
		
		JLabel edad_nuevo = new JLabel("Edad:");
		edad_nuevo.setBounds(20, 225, 80, 30);
		JTextField text_edad_nuevo = new JTextField();
		text_edad_nuevo.setBounds(20, 250, 100, 30);
		
		JLabel desempleado_nuevo = new JLabel("Desempleado?:");
		desempleado_nuevo.setBounds(20, 280, 120, 30);
		JTextField text_desempleado_nuevo = new JTextField();
		text_desempleado_nuevo.setBounds(20, 305, 100, 30);
		
		JLabel cuota_nuevo = new JLabel("Cuota:");
		cuota_nuevo.setBounds(20, 335, 80, 30);
		JTextField text_cuota_nuevo = new JTextField();
		text_cuota_nuevo.setBounds(20, 360, 100, 30);
		
		JButton crear = new JButton("Crear Nuevo");
		crear.setBounds(30, 420, 120, 60);
		
		
		JSeparator separador2 = new JSeparator();
		separador2.setOrientation(JSeparator.VERTICAL);
		separador2.setBounds(180, 0, 5, (int) (screenSize.getHeight() / 6) * 4);
		
		/* Tabla con los datos del resultado
		 * de la búsqueda con los parametros
		 * de los campos situados en la parte
		 * superior de la aplicacion, más un
		 * boton que guarde/actualice los datos
		 * modificados en la tabla.
		 */
		
		JTable tabla = new JTable();
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(220, 140, 700, 300);
		
		JButton modificar = new JButton("Modificar Datos");
		modificar.setBounds(750, 455, 150, 40);
		
		
		this.add(nombre_buscar);
		this.add(text_nombre_buscar);
		this.add(apellido_buscar);
		this.add(text_apellido_buscar);
		this.add(dni_buscar);
		this.add(text_dni_buscar);
		this.add(buscar);
		this.add(borrar);
		
		this.add(separador1);
		
		this.add(dni_nuevo);
		this.add(text_dni_nuevo);
		this.add(pwd_nuevo);
		this.add(text_pwd_nuevo);
		this.add(nombre_nuevo);
		this.add(text_nombre_nuevo);
		this.add(apellido_nuevo);
		this.add(text_apellido_nuevo);
		this.add(edad_nuevo);
		this.add(text_edad_nuevo);
		this.add(desempleado_nuevo);
		this.add(text_desempleado_nuevo);
		this.add(cuota_nuevo);
		this.add(text_cuota_nuevo);
		this.add(crear);
		
		this.add(separador2);
		
		this.add(scroll);
		this.add(modificar);
	}
	
}
