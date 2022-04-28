package vista_administrativo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import controlador.Utiles;
import controlador.objetos.Profesor;
import modelo.objetosDAO.ProfesorDAO;

public class PanelProfesor extends JPanel {
	
	private Profesor profesor = null;
	
	public PanelProfesor(JFrame ventana, String dni) {
			rellenarConDNI(ventana, dni);
	}
	
	
	public PanelProfesor(JFrame ventana) {
			rellenarSinDNI(ventana);
	}

	
	private void rellenarSinDNI(JFrame ventana) {
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
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dni_txt.getText().isBlank() || Utiles.comprobar_dni_sin_coincidencia(dni_txt.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Dni incorrecto", "", JOptionPane.WARNING_MESSAGE, null);
				}else {
					((VentanaAdministrativo) ventana).llamarPanelProfesor(dni_txt.getText());
				}
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
		
		this.add(scroll);
		
		this.add(buscar);
		this.add(nuevo);
		this.add(borrar);
	}
	
	
	private void rellenarConDNI(JFrame ventana, String dni_) {
		this.setLayout(null);
		
		JLabel dni = new JLabel("DNI:");
		dni.setBounds(50, 50, 80, 30);
		JTextField dni_txt = new JTextField(dni_);
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
		try {
			tabla = crearTabla(dni_);
		}catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "El dni no existe en la base de datos de profesores.", "", JOptionPane.WARNING_MESSAGE, null);
			((VentanaAdministrativo) ventana).llamarPanelProfesor();
		}
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(300, 50, 600, 200);
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(50, 300, 80, 40);
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dni_txt.getText().isBlank() || Utiles.comprobar_dni_sin_coincidencia(dni_txt.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Dni incorrecto", "", JOptionPane.WARNING_MESSAGE, null);
				}else {
					((VentanaAdministrativo) ventana).llamarPanelProfesor(dni_txt.getText());
				}
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
		
		this.add(scroll);
		
		this.add(buscar);
		this.add(nuevo);
		this.add(borrar);
	}
	
	
	private JTable crearTabla(String dni) {
		profesor = ProfesorDAO.buscar_profesor(dni);
		
		if(profesor == null) {
			throw new IllegalArgumentException();
		}
		
		String tuplas[][] = new String[1][3];
		tuplas[0][0] = profesor.getDni();
		tuplas[0][1] = profesor.getNombre();
		tuplas[0][2] = Double.toString(profesor.getSalario());
		
		String[] cabecera = {"DNI", "Nombre", "Salario"};
		
		return new JTable(tuplas, cabecera);
	}
}
