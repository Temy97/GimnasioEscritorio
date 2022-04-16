package vista_administrativo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelClase extends JPanel {
	
	public PanelClase(JFrame ventana) {
		this.setLayout(null);
		
		JTable tabla_profes = new JTable();
		JScrollPane scroll_profes = new JScrollPane(tabla_profes);
		scroll_profes.setBounds(50, 50, 250, 300);
		
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
		
		JTable tabla_clases = new JTable();
		JScrollPane scroll_clases = new JScrollPane(tabla_clases);
		scroll_clases.setBounds(600, 50, 350, 200);
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(350, 300, 80, 40);
		JButton nuevo = new JButton("Crear nuevo");
		nuevo.setBounds(500, 300, 120, 40);
		JButton borrar = new JButton("Borrar");
		borrar.setBounds(700, 300, 120, 40);
		
		
		this.add(scroll_profes);
		
		this.add(id);
		this.add(id_txt);
		this.add(nombre);
		this.add(nombre_txt);
		this.add(descripcion);
		this.add(descripcion_txt);
		this.add(dni_prof);
		this.add(dni_prof_txt);
		
		this.add(scroll_clases);
		
		this.add(buscar);
		this.add(nuevo);
		this.add(borrar);
	}
	
}
