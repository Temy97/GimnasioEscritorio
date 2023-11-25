package vista.vista_administrativo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.Utiles;
import modelo.objetos.Administrativo;
import modelo.objetos.Cliente;
import modelo.objetosDAO.AdministrativoDAO;
import modelo.objetosDAO.ClienteDAO;

public class PanelCliente extends JPanel {
	
	//Atributos:
	private final String cabecera[] = {"DNI", "Pasword", "Nombre", "Apellidos", "edad", "Empleado/En paro", "Cuota"};
	
	private Cliente cliente = new Cliente();
	private PanelCliente yo = this;
	private JScrollPane scroll = new JScrollPane(crearTabla("", "", ""));
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	//CONSTRUCTOR:
	/**
	 * Constructor por defecto, genera la ventana
	 * junto con los botones y los inputs pero sin
	 * una tabla.
	 * @param ventana
	 */
	public PanelCliente(JFrame ventana) {
		this.setLayout(null);
		
		rellenar(ventana, false);
	}
	
	
	/**
	 * Genera la ventana en funcion de los campos
	 * de busqueda recibidos para rellenar la tabla
	 * @param ventana
	 * @param camposBusqueda
	 */
	public PanelCliente(JFrame ventana, String... camposBusqueda) {
		this.setLayout(null);
		
		scroll = new JScrollPane(crearTabla(camposBusqueda[0], camposBusqueda[1], camposBusqueda[2]));
		
		rellenar(ventana, true);
	}


	//Metodos:
	private void rellenar(JFrame ventana, boolean sacarTabla) {
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
		
		//botones buscar y borrar
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(700, 40, 80, 40);
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!text_dni_buscar.getText().isBlank() || !text_nombre_buscar.getText().isBlank() || !text_apellido_buscar.getText().isBlank()) {
					if(text_dni_buscar.getText().length() == 9) {
						((VentanaAdministrativo) ventana).llamarPanelClientes(text_dni_buscar.getText(), "", "");
					} else {
						if(text_nombre_buscar.getText().isBlank() == false && text_apellido_buscar.getText().isBlank() == false) {
							((VentanaAdministrativo) ventana).llamarPanelClientes("", text_nombre_buscar.getText(), text_apellido_buscar.getText());
						}else if(text_nombre_buscar.getText().isBlank() == false && text_apellido_buscar.getText().isBlank() == true) {
							((VentanaAdministrativo) ventana).llamarPanelClientes("", text_nombre_buscar.getText(), "");
						}else if(text_nombre_buscar.getText().isBlank() == true && text_apellido_buscar.getText().isBlank() == false) {
							((VentanaAdministrativo) ventana).llamarPanelClientes("", "", text_apellido_buscar.getText());
						}
					}
				}
			}
		});
		
		JButton borrar = new JButton("Borrar");
		borrar.setBounds(840, 40, 80, 40);
		borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cliente.getDni().isBlank() == false) {
					if(JOptionPane.showOptionDialog(null, "Realmente desea borrar a: " + cliente.getNombre() + " [dni: " + cliente.getDni() + "]", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"si", "no"}, "si") == 0){
						if(ClienteDAO.borrar_cliente_por_dni(cliente.getDni())){
							JOptionPane.showMessageDialog(null, "El cliente ha sido eliminado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE, null);
						}else {
							JOptionPane.showMessageDialog(null, "Se ha producido un error al borrar al cliente.", "", JOptionPane.WARNING_MESSAGE, null);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "No hay ningun cliente seleccionado correctamente.", "", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		
		
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
		
		//boton crear nuevo cliente
		JButton crear = new JButton("Crear Nuevo");
		crear.setBounds(30, 420, 120, 60);
		crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!text_dni_nuevo.getText().isBlank() && Utiles.comprobar_dni_sin_coincidencia(text_dni_nuevo.getText())) {
					if(!text_pwd_nuevo.getText().isBlank()) {
						if(!text_nombre_nuevo.getText().isBlank()) {
							if(!text_apellido_nuevo.getText().isBlank()) {
								if(!text_edad_nuevo.getText().isBlank() && Utiles.isInt(text_edad_nuevo.getText())) {
									if(!text_desempleado_nuevo.getText().isBlank() && Cliente.estadoValido(text_desempleado_nuevo.getText())) {
										if(!text_cuota_nuevo.getText().isBlank() && Utiles.isDouble(text_cuota_nuevo.getText())) {
											ClienteDAO.insert_cliente(new Cliente(text_dni_nuevo.getText(), text_pwd_nuevo.getText(), text_nombre_nuevo.getText(), text_apellido_nuevo.getText(), Integer.parseInt(text_edad_nuevo.getText()), Cliente.generarEstado(text_desempleado_nuevo.getText()), Double.parseDouble(text_cuota_nuevo.getText())));
											JOptionPane.showMessageDialog(null, "Se ha guardado correctamente el cliente.", "", JOptionPane.WARNING_MESSAGE, null);
										}else {
											JOptionPane.showMessageDialog(null, "El valor indicado en el campo cuota es inválido.", "", JOptionPane.WARNING_MESSAGE, null);
										}
									}else {
										JOptionPane.showMessageDialog(null, "El valor indicado en el campo desempleado es diferente de \"S\" o \"N\".", "", JOptionPane.WARNING_MESSAGE, null);
									}
								}else {
									JOptionPane.showMessageDialog(null, "El valor introducido en edad es inválido.", "", JOptionPane.WARNING_MESSAGE, null);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Debe escribir apellido.", "", JOptionPane.WARNING_MESSAGE, null);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe escribir nombre.", "", JOptionPane.WARNING_MESSAGE, null);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe escribir una contraseña.", "", JOptionPane.WARNING_MESSAGE, null);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe escribir un DNI válido, 8 dígitos y una letra [en mayúsculas].", "", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		
		
		
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
		if(sacarTabla) {
			colocarScroll();
		}
		JTable tabla = new JTable();
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(220, 140, 700, 300);
		
		
		//boton para modificar los datos de un cliente con los datados editados de la tabla
		JButton modificar = new JButton("Modificar Datos");
		modificar.setBounds(750, 455, 150, 40);
		modificar.setToolTipText("Se modificará la ultima fila seleccionada; los datos correspondientes a -> " + cliente.getNombre() + " [dni: " + cliente.getDni() + "]");

		modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cliente.getDni().isBlank() || !cliente.getNombre().isBlank()) {
					if(JOptionPane.showOptionDialog(null, "Realmente desea actualizar los datos de: " + cliente.getNombre() + " [dni: " + cliente.getDni() + "]", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"si", "no"}, "si") == 0) {
						if(ClienteDAO.editar_datos_cliente(cliente.getDni(), cliente)) {
							JOptionPane.showMessageDialog(null, "Se ha actualizado el cliente correctamente.", "", JOptionPane.ERROR_MESSAGE, null);
						}else {
							JOptionPane.showMessageDialog(null, "No pudo actualizarse el cliente seleccionado.", "", JOptionPane.ERROR_MESSAGE, null);
						}
					}
				}
			}
		});
		
		
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
	
	
	/**
	 * Tabla a rellenar en funcion de los campos recibidos.
	 * @param dni
	 * @param pasword
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param estadoEmpleado
	 * @param cuota
	 * @return
	 */
	private JTable crearTabla(String dni, String nombre, String apellido) {
		ArrayList<Cliente> clientes = ClienteDAO.todos_los_clientes();
		
		String[][] tuplas = new String[clientes.size()][cabecera.length];
		
		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < cabecera.length; j++) {
				tuplas[i][j] = " ";
			}
		}
		
		int cont = 0;
		
		for (int i = 0; i < clientes.size(); i++) {
			if(dni.length() == 9 && dni.equalsIgnoreCase(clientes.get(i).getDni())) {
				tuplas = new String[clientes.size()][cabecera.length];
				
				tuplas[0][0] = clientes.get(i).getDni();
				tuplas[0][1] = clientes.get(i).getPaswordAsterisco();
				tuplas[0][2] = clientes.get(i).getNombre();
				tuplas[0][3] = clientes.get(i).getApellidos();
				tuplas[0][4] = Integer.toString(clientes.get(i).getEdad());
				tuplas[0][5] = clientes.get(i).isEstadoChar();
				tuplas[0][6] = Double.toString(clientes.get(i).getCuota());
				
				i = clientes.size() +1;
				
			}else {
				if(nombre.isBlank() == false && apellido.isBlank() == false) {
					if(nombre.equalsIgnoreCase(clientes.get(i).getNombre()) && apellido.equalsIgnoreCase(clientes.get(i).getApellidos())) {
						tuplas[cont][0] = clientes.get(i).getDni();
						tuplas[cont][1] = clientes.get(i).getPaswordAsterisco();
						tuplas[cont][2] = clientes.get(i).getNombre();
						tuplas[cont][3] = clientes.get(i).getApellidos();
						tuplas[cont][4] = Integer.toString(clientes.get(i).getEdad());
						tuplas[cont][5] = clientes.get(i).isEstadoChar();
						tuplas[cont][6] = Double.toString(clientes.get(i).getCuota());
						
						cont++;
					}
				}else if(nombre.isBlank() == false && apellido.isBlank() == true) {
					if(nombre.equalsIgnoreCase(clientes.get(i).getNombre())) {
						tuplas[cont][0] = clientes.get(i).getDni();
						tuplas[cont][1] = clientes.get(i).getPaswordAsterisco();
						tuplas[cont][2] = clientes.get(i).getNombre();
						tuplas[cont][3] = clientes.get(i).getApellidos();
						tuplas[cont][4] = Integer.toString(clientes.get(i).getEdad());
						tuplas[cont][5] = clientes.get(i).isEstadoChar();
						tuplas[cont][6] = Double.toString(clientes.get(i).getCuota());
						
						cont++;
					}
				}else if(nombre.isBlank() == true && apellido.isBlank() == false) {
					if(apellido.equalsIgnoreCase(clientes.get(i).getApellidos())) {
						tuplas[cont][0] = clientes.get(i).getDni();
						tuplas[cont][1] = clientes.get(i).getPaswordAsterisco();
						tuplas[cont][2] = clientes.get(i).getNombre();
						tuplas[cont][3] = clientes.get(i).getApellidos();
						tuplas[cont][4] = Integer.toString(clientes.get(i).getEdad());
						tuplas[cont][5] = clientes.get(i).isEstadoChar();
						tuplas[cont][6] = Double.toString(clientes.get(i).getCuota());
						
						cont++;
					}
				}
			}
		}
		
		JTable tabla = new JTable(tuplas, cabecera);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent evt) {
				int row = tabla.rowAtPoint(evt.getPoint());
				
				try {
					cliente = new Cliente(tabla.getValueAt(row, 0).toString(), tabla.getValueAt(row, 1).toString(), tabla.getValueAt(row, 2).toString(), tabla.getValueAt(row, 3).toString(), Integer.parseInt(tabla.getValueAt(row, 4).toString()), Utiles.esEstadoChar(tabla.getValueAt(row, 5).toString()), Double.parseDouble(tabla.getValueAt(row, 6).toString()));
				
				}catch(NullPointerException e) {
					cliente = new Cliente();
				}
			}
		});
		tabla.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {}

			@Override
			public void focusLost(FocusEvent e) {
				tabla.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						int row = tabla.rowAtPoint(evt.getPoint());

						try {
							cliente = new Cliente(tabla.getValueAt(row, 0).toString(), tabla.getValueAt(row, 1).toString(), tabla.getValueAt(row, 2).toString(), tabla.getValueAt(row, 3).toString(), Integer.parseInt(tabla.getValueAt(row, 4).toString()), Utiles.esEstadoChar(tabla.getValueAt(row, 5).toString()), Double.parseDouble(tabla.getValueAt(row, 6).toString()));

						}catch(NullPointerException e) {
							cliente = new Cliente();
						}
					}
				});
			}
		});
		
		return tabla;
	}
	
	
	/**
	 * Coloca el JScrollPane.
	 */
	private void colocarScroll() {
		yo.remove(scroll);
		scroll.setBounds(220, 140, 700, 300);
		yo.add(scroll);
	}
}
