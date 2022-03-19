package vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ApuntarClase extends JPanel {
	
	//ATRIBUTOS:
	public static final String[] CABECERA = {"Hora", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
	public static final String[] HORAS = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "", "16:00", "17:00", "18:00", "19:00", "20:00"};
	private JTable tabla;
	
	
	//CONSTRUCTOR:
	public ApuntarClase(JFrame ventana) {
		this.setLayout(null);
		
		JScrollPane tabla = new JScrollPane(generarTabla());
		tabla.setBounds(50, 50, 900, 215);
		
		JLabel n_clase = new JLabel("Nombre de la clase:");
		n_clase.setBounds(50, 300, 200, 40);
		
		JTextField n_text = new JTextField();
		n_text.setBounds(50, 350, 150, 30);
		
		JLabel s_hora = new JLabel("Seleccionar hora:");
		s_hora.setBounds(250, 300, 100, 40);
		
		JComboBox<String> c_horas = new JComboBox<String>(HORAS);
		c_horas.setBounds(250, 350, 100, 30);
		
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.setBounds(700, 320, 120, 40);
		
		JButton imprimir = new JButton("Imprimir horario");
		imprimir.setBounds(680, 400, 160, 40);
		
		
		this.add(tabla);
		this.add(n_clase);
		this.add(n_text);
		this.add(s_hora);
		this.add(c_horas);
		this.add(confirmar);
		this.add(imprimir);
	}
	
	
	//METODOS:
	public static JTable generarTabla() {
		String[][] tuplas = new String[HORAS.length][CABECERA.length];
		
		for (int i = 0; i < HORAS.length; i++) {
			tuplas[i][0] = HORAS[i];
		}
		
		return new JTable(tuplas, CABECERA);
	}
}
