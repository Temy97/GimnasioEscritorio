package vista_administrativo;

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

import controlador.objetos.Administrativo;
import controlador.objetos.Profesor;
import modelo.objetosDAO.ProfesorDAO;

@SuppressWarnings("serial")
public class PanelProfesor extends JPanel {
	
	//Atributos:
	private static final String[] CABECERA = {"DNI", "Nombre", "Salario"};
	private static JScrollPane scroll = new JScrollPane(crearTabla("", ""));
	private PanelProfesor yo = this;
	private static Profesor profesor= new Profesor("", "", 0.0);
	
	
	//CONSTRUCTOR:
	public PanelProfesor(JFrame ventana) {
		this.setLayout(null);
		
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
				if(dni_txt.getText().isBlank() == false && dni_txt.getText().length() == 9) {
					scroll = new JScrollPane(crearTabla(dni_txt.getText(), ""));
				}else if(nombre_txt.getText().isBlank() == false) {
					scroll = new JScrollPane(crearTabla("", nombre_txt.getText()));
				}
				
				colocarScroll();
				/*dni_txt.setText("");
				nombre_txt.setText("");
				salario_txt.setText("");*/
			}
		});
		JButton nuevo = new JButton("Crear nuevo");
		nuevo.setBounds(150, 300, 120, 40);
		JButton borrar = new JButton("Borrar");
		borrar.setBounds(760, 300, 120, 40);
		
		
		this.add(dni);
		this.add(dni_txt);
		this.add(nombre);
		this.add(nombre_txt);
		this.add(salario);
		this.add(salario_txt);
		
		//this.add(scroll);
		
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
		
		//recorrer array, si coincide dni solo una tupla en la tabla,
		//si se busca por nombre se muestran todas las coincidencias.
		for (int i = 0; i < profesores.size(); i++) {
			if(dni.length() == 9 && dni.equalsIgnoreCase(profesores.get(i).getDni())) {
				tuplas = new String[1][CABECERA.length];

				tuplas[0][0] = profesores.get(i).getDni();
				tuplas[0][1] = profesores.get(i).getNombre();
				tuplas[0][2] = Double.toString(profesores.get(i).getSalario());

				i = profesores.size() +1;

			}else if(nombre.isBlank() == false){
				if(nombre.equalsIgnoreCase(profesores.get(i).getNombre())) {
					tuplas[i][0] = profesores.get(i).getDni();
					tuplas[i][1] = profesores.get(i).getNombre();
					tuplas[i][2] = Double.toString(profesores.get(i).getSalario());
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
				
				}catch(NullPointerException e) {
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
