package vista_administrativo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.objetos.Administrativo;
import modelo.objetosDAO.AdministrativoDAO;

@SuppressWarnings("serial")
public class PanelAdministrativo extends JPanel {
	
	//Atributos:
	private final String[] cabecera = {"DNI", "Contraseña", "Nombre", "Apellido"};
	private JScrollPane scroll = new JScrollPane(crearTabla("", "", ""));
	private PanelAdministrativo yo = this;
	
	
	//CONSTRUCTOR:
	public PanelAdministrativo(JFrame ventana) {
		this.setLayout(null);
		
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
				scroll  = new JScrollPane(crearTabla(dni_txt.getText(), nombre_txt.getText(), apellido_txt.getText()));
				colocarScroll();
			}
		});
		
		JButton nuevo = new JButton("Crear nuevo");
		nuevo.setBounds(150, 300, 120, 40);
		JButton borrar = new JButton("Borrar");
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
		
		return new JTable(tuplas, cabecera);
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
