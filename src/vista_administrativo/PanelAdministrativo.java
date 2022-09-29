package vista_administrativo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.Utiles;
import controlador.objetos.Administrativo;
import modelo.objetosDAO.AdministrativoDAO;

@SuppressWarnings("serial")
public class PanelAdministrativo extends JPanel {
	
	//Atributos:
	private final String[] cabecera = {"DNI", "Contraseña", "Nombre", "Apellido"};
	private final String[] opciones = {"Sí", "No"};
	private JScrollPane scroll = new JScrollPane(crearTabla("", "", ""));
	private PanelAdministrativo yo = this;
	private Administrativo administrativo = new Administrativo("", "", "", "");
	
	
	//CONSTRUCTOR:
	/**
	 * Constructor default con tabla vacía.
	 * @param ventana
	 */
	public PanelAdministrativo(JFrame ventana) {
		this.setLayout(null);
		
		rellenar(ventana, this);
	}
	
	
	/**
	 * Constructor que genera el panel con los campos rellenos
	 * segun el dni especificado.
	 * @param ventana
	 * @param dni
	 */
	public PanelAdministrativo(JFrame ventana, String dni) {
		this.setLayout(null);
		
		scroll = new JScrollPane(crearTabla(dni, "", ""));
		
		rellenar(ventana, this);
	}
	
	
	/**
	 * Cosntructor que genera el panel con los campos rellenos
	 * segun el nombre y/o apellido especificado.
	 * @param ventana
	 * @param nombre
	 * @param apellido
	 */
	public PanelAdministrativo(JFrame ventana, String nombre, String apellido) {
		this.setLayout(null);
		
		scroll = new JScrollPane(crearTabla("", nombre, apellido));
		
		rellenar(ventana, this);
	}
	
	
	/**
	 * Metodo interno que pinta y ubica cada
	 * componente del panel.
	 * @param ventana
	 * @param panelAdministrativo
	 */
	private void rellenar(JFrame ventana, PanelAdministrativo panelAdministrativo) {
		JLabel dni = new JLabel("DNI:");
		dni.setBounds(50, 50, 80, 30);
		JTextField dni_txt = new JTextField();
		dni_txt.setBounds(150, 50, 100, 30);
		
		JLabel pwd = new JLabel("Contraseña:");
		pwd.setBounds(50, 100, 80, 30);
		JTextField pwd_txt = new JTextField();
		pwd_txt.setBounds(150, 100, 100, 30);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(50, 150, 80, 30);
		JTextField nombre_txt = new JTextField();
		nombre_txt.setBounds(150, 150, 100, 30);
		
		JLabel apellido = new JLabel("Apellido:");
		apellido.setBounds(50, 200, 80, 30);
		JTextField apellido_txt = new JTextField();
		apellido_txt.setBounds(150, 200, 100, 30);
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(50, 300, 80, 40);
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!dni_txt.getText().isBlank() || !nombre_txt.getText().isBlank() || !apellido_txt.getText().isBlank()) {
					if(dni_txt.getText().length() == 9) {
						((VentanaAdministrativo) ventana).llamarPanelAdministrativos(dni_txt.getText());
					} else {
						if(nombre_txt.getText().isBlank() == false && apellido_txt.getText().isBlank() == false) {
							((VentanaAdministrativo) ventana).llamarPanelAdministrativos(nombre_txt.getText(), apellido_txt.getText());
						}else if(nombre_txt.getText().isBlank() == false && apellido_txt.getText().isBlank() == true) {
							((VentanaAdministrativo) ventana).llamarPanelAdministrativos(nombre_txt.getText(), "");
						}else if(nombre_txt.getText().isBlank() == true && apellido_txt.getText().isBlank() == false) {
							((VentanaAdministrativo) ventana).llamarPanelAdministrativos("", apellido_txt.getText());
						}
					}
					
					scroll  = new JScrollPane(crearTabla(dni_txt.getText(), nombre_txt.getText(), apellido_txt.getText()));
					colocarScroll();
				}
			}
		});
		
		JButton nuevo = new JButton("Crear nuevo");
		nuevo.setBounds(150, 300, 120, 40);
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dni_txt.getText().isBlank() == false && pwd_txt.getText().isBlank() == false && nombre_txt.getText().isBlank() == false && apellido_txt.getText().isBlank() == false) {
					if(Utiles.comprobar_dni_sin_coincidencia(dni_txt.getText()) == false) {
						JOptionPane.showMessageDialog(null, "El dni debe contener 8 dígitos y una letra [en mayúsculas].", "", JOptionPane.INFORMATION_MESSAGE, null);
					}else {
						if(pwd_txt.getText().length() > 20) {
							JOptionPane.showMessageDialog(null, "La contraseña no puede superar los 20 caracteres.", "", JOptionPane.INFORMATION_MESSAGE, null);
						}else {
							if(nombre_txt.getText().length() > 45 || apellido_txt.getText().length() > 45) {
								JOptionPane.showMessageDialog(null, "Ni el nombre ni el apellido pueden superar los 45 caracteres.", "", JOptionPane.INFORMATION_MESSAGE, null);
							}else {
								AdministrativoDAO.insert_admin(new Administrativo(dni_txt.getText(), pwd_txt.getText(), nombre_txt.getText(), apellido_txt.getText()));
							}
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Para crear un nuevo administrativo primero deben\nrellenarse correctamente todos los campos.", "", JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});
		
		JButton borrar = new JButton("Borrar");
		borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(administrativo.getDni().isBlank() == false) {
					if(JOptionPane.showOptionDialog(null, "Realmente desea borrar a: " + administrativo.getNombre() + " [dni: " + administrativo.getDni() + "]", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]) == 0){
						if(AdministrativoDAO.borrar_admin_por_dni(administrativo.getDni())){
							JOptionPane.showMessageDialog(null, "El adminnistrativo ha sido eliminado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE, null);
						}else {
							JOptionPane.showMessageDialog(null, "Se ha producido un error al borrar al administrativo.", "", JOptionPane.WARNING_MESSAGE, null);
						}
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "No hay ningun administrativo seleccionado correctamente.", "", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		borrar.setBounds(760, 300, 120, 40);
		
		
		this.add(dni);
		this.add(dni_txt);
		this.add(pwd);
		this.add(pwd_txt);
		this.add(nombre);
		this.add(nombre_txt);
		this.add(apellido);
		this.add(apellido_txt);
		
		colocarScroll();
		
		this.add(buscar);
		this.add(nuevo);
		this.add(borrar);
	}


	//METODOS:
	/**
	 * Genera un JTable con los datos recibidos, en el caso
	 * de recibir cadenas vacías solo devuelve una tabla
	 * con todos los huecos existentes pero vacíos.
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @return
	 */
	private JTable crearTabla(String dni, String nombre, String apellido) {
		ArrayList<Administrativo> admins = AdministrativoDAO.todos_los_admins();
		
		String[][] tuplas = new String[admins.size()][cabecera.length];
		
		for (int i = 0; i < admins.size(); i++) {
			for (int j = 0; j < cabecera.length; j++) {
				tuplas[i][j] = " ";
			}
		}
		
		int cont = 0;
		
		for (int i = 0; i < admins.size(); i++) {
			if(dni.length() == 9 && dni.equalsIgnoreCase(admins.get(i).getDni())) {
				tuplas = new String[admins.size()][cabecera.length];
				
				tuplas[0][0] = admins.get(i).getDni();
				tuplas[0][1] = admins.get(i).getPasword();
				tuplas[0][2] = admins.get(i).getNombre();
				tuplas[0][3] = admins.get(i).getApellidos();
				
				i = admins.size() +1;
				
			}else {
				if(nombre.isBlank() == false && apellido.isBlank() == false) {
					if(nombre.equalsIgnoreCase(admins.get(i).getNombre()) && apellido.equalsIgnoreCase(admins.get(i).getApellidos())) {
						tuplas[cont][0] = admins.get(i).getDni();
						tuplas[cont][1] = admins.get(i).getPasword();
						tuplas[cont][2] = admins.get(i).getNombre();
						tuplas[cont][3] = admins.get(i).getApellidos();
						
						cont++;
					}
				}else if(nombre.isBlank() == false && apellido.isBlank() == true) {
					if(nombre.equalsIgnoreCase(admins.get(i).getNombre())) {
						tuplas[cont][0] = admins.get(i).getDni();
						tuplas[cont][1] = admins.get(i).getPasword();
						tuplas[cont][2] = admins.get(i).getNombre();
						tuplas[cont][3] = admins.get(i).getApellidos();
						
						cont++;
					}
				}else if(nombre.isBlank() == true && apellido.isBlank() == false) {
					if(apellido.equalsIgnoreCase(admins.get(i).getApellidos())) {
						tuplas[cont][0] = admins.get(i).getDni();
						tuplas[cont][1] = admins.get(i).getPasword();
						tuplas[cont][2] = admins.get(i).getNombre();
						tuplas[cont][3] = admins.get(i).getApellidos();
						
						cont++;
					}
				}
			}
		}
		
		JTable tabla = new JTable(tuplas, cabecera);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent evt) {
				int row = tabla.rowAtPoint(evt.getPoint());
				
				try {
					administrativo = new Administrativo(tabla.getValueAt(row, 0).toString(), tabla.getValueAt(row, 1).toString(), tabla.getValueAt(row, 2).toString(), tabla.getValueAt(row, 3).toString());
				
				}catch(NullPointerException e) {
					administrativo = new Administrativo("", "", "", "");
				}
			}
		});
		
		return tabla;
	}
	
	
	/**
	 * Coloca el JScrollPane.
	 */
	private void colocarScroll() {
		yo.remove(scroll);
		scroll.setBounds(300, 50, 600, 200);
		yo.add(scroll);
	}
}
