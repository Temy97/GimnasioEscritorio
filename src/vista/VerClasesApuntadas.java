package vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.SecuencialClasesApuntadas;
import modelo.objetosDAO.ClaseDAO;
import modelo.objetosDAO.ClienteDAO;

@SuppressWarnings("serial")
public class VerClasesApuntadas extends JPanel{
	
	//CONSTRUCTOR:
	public VerClasesApuntadas(JFrame ventana) {
		this.setLayout(null);
		
		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setBounds(780, 50, 200, 400);
		
		JTable tabla = generarTabla(area);
		JScrollPane tablaScroll = new JScrollPane(tabla);
		tablaScroll.setBounds(30, 50, 700, 400);
		
		this.add(tablaScroll);
		this.add(area);
	}
	
	
	//METODOS:
	/**
	 * 
	 * @param textField 
	 * @param c_horas 
	 * @return
	 */
	public static JTable generarTabla(JTextArea area) {
		String[][] tuplas = null;
		String[] cabecera = {"FECHA", "HORA", "ACTIVIDAD"};
		
		try {
			tuplas = SecuencialClasesApuntadas.leerSecuencial(Ventana.DNI_EJEMPLO);
		} catch (IOException e) {
			e.getLocalizedMessage();
		}
		
		JTable tabla = new JTable(tuplas, cabecera);
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(JLabel.CENTER);
		
		for (int i = 1; i < cabecera.length; i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
		}
		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent evt) {
				int row = tabla.rowAtPoint(evt.getPoint());
		        int col = tabla.columnAtPoint(evt.getPoint());
		        
		        if(col != 0 && col != 1) {
		        	String[] seleccion = {tabla.getModel().getValueAt(row, 0).toString(), tabla.getModel().getValueAt(row, 1).toString(), tabla.getModel().getValueAt(row, 2).toString()};
		        	
		        	System.out.println("El dia [" + seleccion[0] + "], a las [" + seleccion[1] + ", Ud. [" + ClienteDAO.buscar_dni(Ventana.DNI_EJEMPLO).getNombre() + "]"
		        			+ ", asistió a la clase de: [" + seleccion[2] + "], la cual consiste en [" + ClaseDAO.buscar_clase_nombre(seleccion[2] + "]."));
		        	
		        	area.setText("El dia [" + seleccion[0] + "], a las [" + seleccion[1] + ", Ud. [" + ClienteDAO.buscar_dni(Ventana.DNI_EJEMPLO).getNombre() + "]"
		        			+ ", asistió a la clase de: [" + seleccion[2] + "], la cual consiste en [" + ClaseDAO.buscar_clase_nombre(seleccion[2] + "]."));
		        }
		    }
		});
		
		return tabla;
	}
}
