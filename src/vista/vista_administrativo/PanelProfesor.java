package vista.vista_administrativo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.Utiles;
import modelo.objetos.Administrativo;
import modelo.objetos.Profesor;
import modelo.objetosDAO.ProfesorDAO;

@SuppressWarnings("serial")
public class PanelProfesor extends JPanel {
	
	//Atributos:
	private static final String[] CABECERA = {"DNI", "Nombre", "Salario"};
	private final String[] opciones = {"Sí", "No"};
	private static JScrollPane scroll = new JScrollPane(crearTabla("", ""));
	private PanelProfesor yo = this;
	private static Profesor profesor= new Profesor("", "", 0.0);
	
	
	//CONSTRUCTOR:
	/**
	 * Constructor por defecto.
	 * @param ventana
	 */
	public PanelProfesor(JFrame ventana) {
		this.setLayout(null);
		
		rellenar(ventana, this);
	}
	
	
	/**
	 * Constructor que genera el panel con los campos rellenos
	 * segun el dni especificado.
	 * @param ventana
	 * @param dni
	 */
	public PanelProfesor(JFrame ventana, String dni) {
		this.setLayout(null);
		
		scroll = new JScrollPane(crearTabla(dni, ""));
		
		rellenar(ventana, this);
	}
	
	
	/**
	 * Cosntructor que genera el panel con los campos rellenos
	 * segun el nombre y/o apellido especificado.
	 * @param ventana
	 * @param nombre
	 * @param apellido
	 */
	public PanelProfesor(JFrame ventana, String nombre, String apellido) {
		this.setLayout(null);
		
		scroll = new JScrollPane(crearTabla("", nombre));
		
		rellenar(ventana, this);
	}
	
	
	/**
	 * Metodo interno que pinta y ubica cada
	 * componente del panel.
	 * @param ventana
	 * @param panelProfesor
	 */
	private void rellenar(JFrame ventana, PanelProfesor panelProfesor) {
		JLabel dni = new JLabel("DNI:");
		dni.setBounds(50, 50, 80, 30);
		JTextField dni_txt = new JTextField();
		dni_txt.setBounds(150, 50, 100, 30);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(50, 150, 80, 30);
		JTextField nombre_txt = new JTextField();
		nombre_txt.setBounds(150, 150, 100, 30);
		
		JLabel salario = new JLabel("Salario:");
		salario.setBounds(50, 200, 80, 30);
		JTextField salario_txt = new JTextField();
		salario_txt.setBounds(150, 200, 100, 30);
		
		scroll.setBounds(300, 50, 600, 200);
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(50, 300, 80, 40);
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!dni_txt.getText().isBlank() || !nombre_txt.getText().isBlank()) {
					if(dni_txt.getText().length() == 9) {
						((VentanaAdministrativo) ventana).llamarPanelProfesor(dni_txt.getText());
					} else {
						((VentanaAdministrativo) ventana).llamarPanelProfesor(nombre_txt.getText(), "");
					}
					
					scroll  = new JScrollPane(crearTabla(dni_txt.getText(), nombre_txt.getText()));
					colocarScroll();
				}
			}
		});
		
		JButton nuevo = new JButton("Crear nuevo");
		nuevo.setBounds(150, 300, 120, 40);
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!dni_txt.getText().isBlank() || !nombre_txt.getText().isBlank() || !salario_txt.getText().isBlank()) {
					try {
						if (!Utiles.comprobar_dni_sin_coincidencia(dni_txt.getText())) {
							JOptionPane.showMessageDialog(null, "El dni es inválido.", "", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							Double salario = Double.parseDouble(salario_txt.getText());
							profesor = new Profesor(dni_txt.getText(), nombre_txt.getText(), salario);
							ProfesorDAO.insert_profesor(profesor);
							
							JOptionPane.showMessageDialog(null, "El profesor [" + profesor.getNombre() + ", " + profesor.getDni() + "] ha sido añadido", "", JOptionPane.PLAIN_MESSAGE, null);
						}
						
					}catch(NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "El salario introducido es erroneo.", "", JOptionPane.INFORMATION_MESSAGE, null);
					}
				}
			}
		});
		
		JButton borrar = new JButton("Borrar");//TODO hacer mensaje error para seleccion de tabla en null o vacio
		borrar.setBounds(760, 300, 120, 40);
		borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!profesor.getDni().isBlank()) {
					if(JOptionPane.showOptionDialog(null, "Realmente desea borrar a: " + profesor.getNombre() + " [dni: " + profesor.getDni() + "]", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]) == 0) {
						if(ProfesorDAO.borrar_profesor(profesor.getDni())) {
							JOptionPane.showMessageDialog(null, "El profesor ha sido eliminado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE, null);
						}else {
							JOptionPane.showMessageDialog(null, "Se ha producido un error al borrar al profesor.", "", JOptionPane.WARNING_MESSAGE, null);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "No hay ningun profesor seleccionado correctamente.", "", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		
		
		this.add(dni);
		this.add(dni_txt);
		this.add(nombre);
		this.add(nombre_txt);
		this.add(salario);
		this.add(salario_txt);
		
		colocarScroll();
		
		this.add(buscar);
		this.add(nuevo);
		this.add(borrar);
	}
	
	
	//METODOS:
	/**
	 * Crea una tabla con la cabecera de los datos de los
	 * profesores y se crean filas en correspondencia con
	 * los profesores a mostrar en la tabla.
	 * @param dni
	 * @param nombre
	 * @return
	 */
	private static JTable crearTabla(String dni, String nombre) {
		ArrayList<Profesor> profesores = ProfesorDAO.todos_los_profesores();
		String[][] tuplas = new String[profesores.size()][CABECERA.length];
		
		for (int i = 0; i < profesores.size(); i++) {
			for (int j = 0; j < CABECERA.length; j++) {
				tuplas[i][j] = "";
			}
		}
		
		int cont = 0;
		
		//recorrer array, si coincide dni solo una tupla en la tabla,
		//si se busca por nombre se muestran todas las coincidencias.
		for (int i = 0; i < profesores.size(); i++) {
			if(dni.length() == 9 && dni.equalsIgnoreCase(profesores.get(i).getDni())) {
				tuplas = new String[1][CABECERA.length];

				tuplas[cont][0] = profesores.get(i).getDni();
				tuplas[cont][1] = profesores.get(i).getNombre();
				tuplas[cont][2] = Double.toString(profesores.get(i).getSalario());

				//i = profesores.size() +1;
				cont++;

			}else if(nombre.isBlank() == false){
				if(nombre.equalsIgnoreCase(profesores.get(i).getNombre())) {
					tuplas[cont][0] = profesores.get(i).getDni();
					tuplas[cont][1] = profesores.get(i).getNombre();
					tuplas[cont][2] = Double.toString(profesores.get(i).getSalario());
					
					cont++;
				}
			}
		}
		
		JTable tabla = new JTable(tuplas, CABECERA);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent evt) {
				int row = tabla.rowAtPoint(evt.getPoint());
				
				try {
					profesor = new Profesor(tabla.getValueAt(row, 0).toString(), tabla.getValueAt(row, 1).toString(), Double.parseDouble(tabla.getValueAt(row, 2).toString()));
				
				}catch(NullPointerException | NumberFormatException e) {
					profesor = new Profesor("", "", 0.0);
				}
			}
		});
		
		return tabla;
	}
	
	
	/**
	 * Coloca el JScrollPane dentro del JPanel,
	 * se usa para actualizar el existente.
	 */
	private void colocarScroll() {
		yo.remove(scroll);
		scroll.setBounds(300, 50, 600, 200);
		yo.add(scroll);
	}
}
