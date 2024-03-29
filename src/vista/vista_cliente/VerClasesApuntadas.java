package vista.vista_cliente;

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

import com.microsoft.sqlserver.jdbc.SQLServerException;

import controlador.SecuencialClasesApuntadas;
import modelo.objetos.Cliente;
import modelo.objetosDAO.ClaseDAO;
import modelo.objetosDAO.ClienteDAO;

@SuppressWarnings("serial")
public class VerClasesApuntadas extends JPanel{
	
	//ATRIBUTOS:
	private static Cliente datos_cliente;
	
	//CONSTRUCTOR:
	public VerClasesApuntadas(JFrame ventana, Cliente datos_cliente) {
		this.setLayout(null);
		this.datos_cliente = datos_cliente;
		
		JTextArea area = new JTextArea();
		area.setFont(((VentanaCliente) ventana).getFuenteAreas());
		area.setEditable(false);
		JScrollPane areaScroll = new JScrollPane(area);
		areaScroll.setBounds(760, 50, 220, 400);
		
		JTable tabla = generarTabla(area, ventana);
		JScrollPane tablaScroll = new JScrollPane(tabla);
		tablaScroll.setBounds(30, 50, 700, 400);
		
		this.add(tablaScroll);
		this.add(areaScroll);
	}
	
	
	//METODOS:
	/**
	 * 
	 * @param textField 
	 * @param c_horas 
	 * @return
	 */
	public static JTable generarTabla(JTextArea area, JFrame ventana) {
		String[][] tuplas = null;
		String[] cabecera = {"FECHA", "HORA", "ACTIVIDAD"};
		
		try {
			tuplas = SecuencialClasesApuntadas.leerSecuencial(datos_cliente.getDni());
		} catch (IOException e) {
			e.getLocalizedMessage();
		}
		
		JTable tabla = new JTable(tuplas, cabecera);
		tabla.getTableHeader().setFont(((VentanaCliente) ventana).getFuenteTablas());
		tabla.setFont(((VentanaCliente) ventana).getFuenteTablas());
		
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
		        	//try {
		        	area.setText("El dia [" + seleccion[0] + "], a las [" + seleccion[1] + "],\nUd. [" + ClienteDAO.buscar_dni(datos_cliente.getDni()).getNombre() + "]"
		        			+ ", asistió a la clase de:\n[" + seleccion[2] + "], la cual consiste en\n[" + ClaseDAO.buscar_clase_nombre(seleccion[2]).getDescripcion() + "].");
		        	//}catch(SQLServerException e) {
		        	//	area.setText("Err.404");
		        	//}
		        }
		    }
		});
		
		return tabla;
	}
	
	
	/**
	 * Se genera un nuveo {@code String} con saltos de línea, cada
	 * 30 caracteres, en base a la cadena de caracteres
	 * recivida por parámetro.
	 * @param str
	 * @return
	 */
	public static String saltosDeLinea(String str) {
		String cadena_apoyo = "";
		
		for (int i = 1; i < str.length() + 1; i++) {
			cadena_apoyo = cadena_apoyo + str.charAt(i - 1);
			if((i % 30) == 0) {
				cadena_apoyo = cadena_apoyo + "\n";
			}
		}
		
		return cadena_apoyo;
	}
}
