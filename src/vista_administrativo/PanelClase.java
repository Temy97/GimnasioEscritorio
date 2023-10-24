package vista_administrativo;

import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.Utiles;
import controlador.objetos.Clase;
import modelo.objetosDAO.ClaseDAO;

public class PanelClase extends JPanel {
	
	//Atributos:
		private final String[] cabecera = {"ID", "Clase", "Profesor"};
		private final String[] opciones = {"Sí", "No"};
		private JScrollPane scroll = new JScrollPane(crearTabla("", "", ""));
		private PanelClase yo = this;
		private Clase clase = new Clase("   ", "", "", "");
		private JTextArea area_descripcion = new JTextArea();
	
	//CONSTRUCTOR:
	/**
	 * Constructor default con tabla vacía.
	 * @param ventana
	 */
	public PanelClase(JFrame ventana) {
		this.setLayout(null);
		
		rellenar(ventana, this);
	}
	
	
	/**
	 * Constructor que genera el panel con los campos rellenos
	 * segun el id especificado.
	 * @param ventana
	 * @param id
	 */
	public PanelClase(JFrame ventana, String id) {
		this.setLayout(null);
		
		scroll = new JScrollPane(crearTabla(id, "", ""));
		
		rellenar(ventana, this);
	}

	
	//METODOS:
	/**
	 * Metodo interno que pinta y ubica cada
	 * componente del panel.
	 * @param ventana
	 * @param panelClase
	 */
	private void rellenar(JFrame ventana, PanelClase panelClase) {
		JLabel tabla_res = new JLabel("Tabla resultados:");
		tabla_res.setBounds(55, 20, 120, 30);
		
		JLabel id = new JLabel("ID:");
		id.setBounds(330, 50, 80, 30);
		JTextField id_txt = new JTextField();
		id_txt.setBounds(430, 50, 100, 30);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(330, 100, 80, 30);
		JTextField nombre_txt = new JTextField();
		nombre_txt.setBounds(430, 100, 100, 30);
		
		JLabel descripcion = new JLabel("Descripcion:");
		descripcion.setBounds(330, 150, 80, 30);
		JTextField descripcion_txt = new JTextField();
		descripcion_txt.setBounds(430, 150, 100, 30);
		
		JLabel dni_prof = new JLabel("DNI profesor:");
		dni_prof.setBounds(330, 200, 80, 30);
		JTextField dni_prof_txt = new JTextField();
		dni_prof_txt.setBounds(430, 200, 100, 30);
		
		JLabel area_desc = new JLabel("Descripción de la clase seleccionada:");
		area_desc.setBounds(605, 20, 220, 30);
		JTextArea area_descripcion = extractedAreaDescripcion();
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(350, 300, 80, 40);
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!id_txt.getText().isBlank()) {
					if(!id_txt.getText().isBlank()) {
						((VentanaAdministrativo) ventana).llamarPanelClases(id_txt.getText());
					}
					colocarScroll();
				}else {
					JOptionPane.showMessageDialog(null, "Debe buscar mediante el id de la clase");
				}
			}
		});
		
		JButton nuevo = new JButton("Crear nuevo");
		nuevo.setBounds(500, 300, 120, 40);
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!id_txt.getText().isBlank()) {
					if(!nombre_txt.getText().isBlank()) {
						if(!descripcion_txt.getText().isBlank()) {
							if(!dni_prof_txt.getText().isBlank() || Utiles.comprobar_dni_sin_coincidencia(dni_prof_txt.getText())){
								
								Clase clase_nueva = new Clase(id_txt.getText(), nombre_txt.getText(), descripcion_txt.getText(), dni_prof_txt.getText());
								 if(ClaseDAO.existe_id(clase_nueva.getId()) == false) {
									if(ClaseDAO.insert_clase(clase_nueva)) {
										JOptionPane.showMessageDialog(null, "Clase añadida correctamente.");
									}else {
										JOptionPane.showMessageDialog(null, "No pudo añadirse la clase, posiblemente haya usado un dni de profesor inválido.");
									}
								 }else {
									 //TODO comprobacion id libre
									 JOptionPane.showMessageDialog(null, "ID ocupado.");
								 }
								
							}else {
								JOptionPane.showMessageDialog(null, "DNI inválido.");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Falta la descripción de la clase");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Falta nombre de la clase.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "El ide debe ser un número a partir del 101,\nten en cuenta que no puede haber id duplicados\nasí que se mostraá el primero libre en caso de duplicidad.");
				}
			}
		});
		
		JButton borrar = new JButton("Borrar");
		borrar.setBounds(700, 300, 120, 40);
		
		
		//this.add(scroll_clases);
		this.add(tabla_res);
		
		this.add(id);
		this.add(id_txt);
		this.add(nombre);
		this.add(nombre_txt);
		this.add(descripcion);
		this.add(descripcion_txt);
		this.add(dni_prof);
		this.add(dni_prof_txt);
		
		this.add(area_descripcion);
		this.add(area_desc);
		
		colocarScroll();
		
		this.add(buscar);
		this.add(nuevo);
		this.add(borrar);
	}


	/**
	 * Metodo privado para la modularización
	 * y encapsulamiento en favor de imprimir
	 * en el JTextArea "area_descripcion" la
	 * descripcion de la clase seleccionada.
	 * @return
	 */
	private JTextArea extractedAreaDescripcion() {
		area_descripcion.setBounds(600, 50, 350, 200);
		area_descripcion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		area_descripcion.setEditable(false);
		return area_descripcion;
	}
	

	/**
	 * Genera un JTable con los datos recibidos, en el caso
	 * de recibir cadenas vacías solo devuelve una tabla
	 * con todos los huecos existentes pero vacíos.
	 * @param id
	 * @param nombre_clase
	 * @param profesor
	 * @return
	 */
	private JTable crearTabla(String id, String nombre_clase, String profesor) {
		ArrayList<Clase> clases = ClaseDAO.todasLasClases();

		String[][] tuplas = new String[clases.size()][cabecera.length];

		for (int i = 0; i < clases.size(); i++) {
			for (int j = 0; j < cabecera.length; j++) {
				tuplas[i][j] = " ";
			}
		}

		int cont = 0;

		for (int i = 0; i < clases.size(); i++) {
			if(id.length() == 3 && id.equalsIgnoreCase(clases.get(i).getId())) {
				tuplas = new String[clases.size()][cabecera.length];

				tuplas[0][0] = clases.get(i).getId();
				tuplas[0][1] = clases.get(i).getNombre();
				tuplas[0][2] = clases.get(i).getDni_profesor();

				i = clases.size() +1;

			}else {
				if(nombre_clase.isBlank() == false && profesor.isBlank() == false) {
					if(nombre_clase.equalsIgnoreCase(clases.get(i).getNombre()) && profesor.equalsIgnoreCase(clases.get(i).getDni_profesor())) {
						tuplas[cont][0] = clases.get(i).getId();
						tuplas[cont][1] = clases.get(i).getNombre();
						tuplas[cont][2] = clases.get(i).getDni_profesor();

						cont++;
					}
				}else if(nombre_clase.isBlank() == false && profesor.isBlank() == true) {
					if(nombre_clase.equalsIgnoreCase(clases.get(i).getNombre())) {
						tuplas[cont][0] = clases.get(i).getId();
						tuplas[cont][1] = clases.get(i).getNombre();
						tuplas[cont][2] = clases.get(i).getDni_profesor();

						cont++;
					}
				}else if(nombre_clase.isBlank() == true && profesor.isBlank() == false) {
					if(profesor.equalsIgnoreCase(clases.get(i).getDni_profesor())) {
						tuplas[cont][0] = clases.get(i).getId();
						tuplas[cont][1] = clases.get(i).getNombre();
						tuplas[cont][2] = clases.get(i).getDni_profesor();

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
					if(!tabla.getValueAt(row, 0).toString().isBlank() || !tabla.getValueAt(row, 1).toString().isBlank() || !tabla.getValueAt(row, 2).toString().isBlank()) {
						clase = new Clase(tabla.getValueAt(row, 0).toString(), tabla.getValueAt(row, 1).toString(), ClaseDAO.buscar_clase(tabla.getValueAt(row, 0).toString()).getDescripcion(), tabla.getValueAt(row, 2).toString());
						area_descripcion.setText(clase.getDescripcion());
					}else {
						clase = new Clase("   ", "", "", "");
					}
						
				}catch(NullPointerException e) {
					clase = new Clase("   ", "", "", "");
				}
			}
		});

		return tabla;
	}


	/**
	 * Coloca el JScrollPane.
	 */
	private void colocarScroll() {
		yo.remove(scroll);
		scroll.setBounds(50, 50, 250, 300);
		yo.add(scroll);
	}
}
