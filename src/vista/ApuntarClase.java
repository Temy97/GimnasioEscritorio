package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ApuntarClase extends JPanel {
	
	//ATRIBUTOS:
	public static final String[] CABECERA = {"Hora", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
	public static final String[] HORAS = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "", "16:00", "17:00", "18:00", "19:00", "20:00"};
	private JTable tabla;
	
	
	//CONSTRUCTOR:
	public ApuntarClase(JFrame ventana) {
		
	}
}
