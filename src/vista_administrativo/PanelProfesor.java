package vista_administrativo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelProfesor extends JPanel {
	
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
		
		JTable tabla = new JTable();
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(300, 50, 600, 200);
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(50, 300, 80, 40);
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
		
		this.add(scroll);
		
		this.add(buscar);
		this.add(nuevo);
		this.add(borrar);
	}
	
}
