package vista.vista_cliente;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import controlador.GenerarXML;
import controlador.SecuencialClasesApuntadas;
import modelo.objetos.Cliente;
import modelo.objetosDAO.ClaseDAO;

@SuppressWarnings("serial")
public class ApuntarClase extends JPanel {
	
	//ATRIBUTOS:
	public static final String[] CABECERA = {"Hora", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
	public static final String[] HORAS = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "", "16:00", "17:00", "18:00", "19:00", "20:00"};
	private static Cliente datos_cliente;
	
	
	//CONSTRUCTOR:
	public ApuntarClase(JFrame ventana, Cliente datos_cliente) {
		this.setLayout(null);
		this.datos_cliente = datos_cliente;
		
		JLabel n_clase = new JLabel("Nombre de la clase:");
		n_clase.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		n_clase.setBounds(50, 300, 200, 40);
		
		JTextField n_text = new JTextField();
		n_text.setFont(((VentanaCliente) ventana).getFuenteAreas());
		n_text.setEditable(false);
		n_text.setBounds(50, 350, 150, 30);
		
		JLabel s_hora = new JLabel("Seleccionar hora:");
		s_hora.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		s_hora.setBounds(250, 300, 100, 40);
		
		JComboBox<String> c_horas = new JComboBox<String>(HORAS);
		c_horas.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		c_horas.setBounds(250, 350, 100, 30);
		
		JTable tabla = generarTabla(n_text, c_horas, ventana);
		JScrollPane tablaScroll = new JScrollPane(tabla);
		tablaScroll.setBounds(50, 50, 900, 215);
		
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		confirmar.setBounds(700, 320, 120, 40);
		confirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(n_text.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una clase");
				}else {
					if(c_horas.getSelectedItem().toString().length() == 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una hora válida");
					}else {
						//if(comprobarNombre(n_text.getText()) == true) {
							SecuencialClasesApuntadas.guardarClase(n_text.getText(), datos_cliente.getDni(), c_horas.getSelectedItem().toString());
						//}else {
						//	JOptionPane.showMessageDialog(null, "La clase seleccionada no es válida,\nescriba o seleccioe una clase válida.");
						//}
					}
				}
			}
		});
		
		JButton imprimir = new JButton("Imprimir horario");
		imprimir.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		imprimir.setBounds(680, 400, 160, 40);
		imprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GenerarXML.imprimirHorario(tabla);
			}
		});
		
		this.add(tablaScroll);
		this.add(n_clase);
		this.add(n_text);
		this.add(s_hora);
		this.add(c_horas);
		this.add(confirmar);
		this.add(imprimir);
	}
	
	
	//METODOS:
	/**
	 * Genera un JTable con los dias de la semana, las
	 * horas y las actividades, esto último segun el archivo
	 * 'datos\\horarios_secuenciales.dat' encontrado.
	 * En caso de no encontrarlo solo aparecen los dias
	 * y las horas.
	 * @param textField 
	 * @param c_horas 
	 * @return
	 */
	public static JTable generarTabla(JTextField textField, JComboBox<String> c_horas, JFrame ventana) {
		String[][] tuplas = new String[HORAS.length][CABECERA.length];
		
		for (int i = 0; i < HORAS.length; i++) {
			tuplas[i][0] = HORAS[i];
		}
		
		try {
			rellenarHorarios(tuplas);
		} catch (IOException e) {
			e.getLocalizedMessage();
		}
		
		JTable tabla = new JTable(tuplas, CABECERA);
		tabla.getTableHeader().setFont(((VentanaCliente) ventana).getFuenteTablas());
		tabla.setFont(((VentanaCliente) ventana).getFuenteTablas());
		
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(JLabel.CENTER);
		
		for (int i = 1; i < CABECERA.length; i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
		}
		
		tabla.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent evt) {
				int row = tabla.rowAtPoint(evt.getPoint());
		        int col = tabla.columnAtPoint(evt.getPoint());
		        
		        if(row != 6 && col != 0) {
		        	try {
		        		textField.setText(tabla.getModel().getValueAt(row, col).toString());
		        	}catch(NullPointerException e) {
		        		textField.setText("");
		        	}
		        	c_horas.setSelectedIndex(row);
		        }
		    }
		});
		
		return tabla;
	}
	
	
	/**
	 * Rellena las actividades de la tabla generada
	 * por el metodo publico 'generarTabla()' con
	 * los datos recibidos del archivo
	 * 'datos\\horarios_secuenciales.dat'.
	 * @param tuplas
	 * @throws IOException 
	 */
	private static void rellenarHorarios(String[][] tuplas) throws IOException {
		char[] dia = new char[10];
		int posDia = 0;
		char[] hora = new char[5];
		int posHora = 0;
		char[]actividad = new char[10];
		
		File fichero = new File("datos\\horarios_secuenciales.dat");
		RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
		int posicionLectura = 0;
		
		while(raf.getFilePointer() != fichero.length()) {
			raf.seek(posicionLectura);
			
			for (int a = 0; a < dia.length; a++) {
				dia[a] = raf.readChar();
			}
			for (int b = 1; b < CABECERA.length; b++) {
				if(new String(dia).trim().equals(CABECERA[b]) == true) {
					posDia = b;
					b = CABECERA.length +1;
				}
			}
			
			for (int c = 0; c < hora.length; c++) {
				hora[c] = raf.readChar();
			}
			for (int d = 0; d < HORAS.length; d++) {
				if(new String(hora).trim().equals(HORAS[d]) == true) {
					posHora = d;
					d = HORAS.length +1;
				}
			}
			
			for (int i = 0; i < actividad.length; i++) {
				actividad[i] = raf.readChar();
			}
			tuplas[posHora][posDia] = new String(actividad);
			posicionLectura += 50;
		}
		raf.close();
	}
	
	
	/**
	 * Se comprueba si el nombre de la clase seleccionada existe en la base de datos.
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean comprobarNombre(String nombre) {
		boolean valido = false;
		//try {
			if(ClaseDAO.buscar_clase_nombre(nombre).getNombre().length() > 0) {
				valido = true;
			}
		//}catch(NullPointerException | SQLServerException e) {}
		
		return valido;
	}
}
