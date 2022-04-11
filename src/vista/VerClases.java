package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
//import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
//import javax.swing.border.SoftBevelBorder;
//import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class VerClases extends JPanel {
	
	public static final String INICIO_JLABEL_NOMBRE = "<html><body>Nombre de la clase:<br>";
	public static final String FINAL_JLABEL_NOMBRE = "</body></html>";
	
	public static final String INICIO_JLABEL_PROFESOR = "<html><body>Profesor:<br>";
	public static final String FINAL_JLABEL_PROFESOR = "</body></html>";
	
	public VerClases(JFrame ventana) {
		this.setLayout(null);
		
		JTable tabla = new JTable();
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(50, 50, 200, 400);
		
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
		
		this.add(scroll);
		this.add(nombre);
		this.add(n_profesor);
		this.add(area_descripcion);
		this.add(ir_apuntarse);
		this.add(limpiar);
		this.add(inicio);
	}
}
