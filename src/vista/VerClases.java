package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
//import javax.swing.border.SoftBevelBorder;
//import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import controlador.objetos.Clase;
import modelo.objetosDAO.ClaseDAO;
import modelo.objetosDAO.ProfesorDAO;

@SuppressWarnings("serial")
public class VerClases extends JPanel {
	
	//ATRIBUTOS:
	public static final String INICIO_JLABEL_NOMBRE = "<html><body>Nombre de la clase:<br>";
	public static final String FINAL_JLABEL_NOMBRE = "</body></html>";
	
	public static final String INICIO_JLABEL_PROFESOR = "<html><body>Profesor:<br>";
	public static final String FINAL_JLABEL_PROFESOR = "</body></html>";
	
	
	//CONSTRUCTOR:
	public VerClases(JFrame ventana) {
		this.setLayout(null);
		
		JLabel nombre = new JLabel(INICIO_JLABEL_NOMBRE + FINAL_JLABEL_NOMBRE);
		nombre.setBounds(320, 80, 120, 40);
		nombre.setBorder(/*new TitledBorder("")*/ new EtchedBorder() /*new SoftBevelBorder(BevelBorder.LOWERED)*/);
		
		JLabel n_profesor = new JLabel(INICIO_JLABEL_PROFESOR + FINAL_JLABEL_PROFESOR);
		n_profesor.setBounds(320, 120, 120, 40);
		n_profesor.setBorder(new EtchedBorder());
		
		JTextArea area_descripcion = new JTextArea();
		area_descripcion.setBounds(550, 50, 400, 200);
		
		JButton ir_apuntarse = new JButton("Ir a apuntarse");
		ir_apuntarse.setBounds(300, 400, 120, 60);
		JButton limpiar = new JButton("Limpiar");
		limpiar.setBounds(550, 400, 120, 60);
		JButton inicio = new JButton("Inicio");
		inicio.setBounds(800, 400, 120, 60);
		
		JTable tabla = generarTabla(nombre, n_profesor, area_descripcion);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(50, 50, 200, 400);
		
		this.add(scroll);
		this.add(nombre);
		this.add(n_profesor);
		this.add(area_descripcion);
		this.add(ir_apuntarse);
		this.add(limpiar);
		this.add(inicio);
	}
	
	
	//METODOS:
	public static JTable generarTabla(JLabel nombre, JLabel n_profesor, JTextArea descripcion) {
		ArrayList<Clase> lista = ClaseDAO.todasLasClases();
		System.out.println(lista.size());
		String[] cabecera = {"CLASES"};
		String[][] tuplas = new String[lista.size()][1];
		
		for (int i = 0; i < lista.size(); i++) {
			tuplas[i][0] = lista.get(i).getNombre();
		}
		
		JTable tabla = new JTable(tuplas, cabecera);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(JLabel.CENTER);
		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent evt) {
				int row = tabla.rowAtPoint(evt.getPoint());
		        
				nombre.setText(lista.get(row).getNombre());
				n_profesor.setText(ProfesorDAO.buscar_profesor(lista.get(row).getDni_profesor()).getNombre());
				descripcion.setText(lista.get(row).getDescripcion());
		    }
		});
		
		return tabla;
	}
}
