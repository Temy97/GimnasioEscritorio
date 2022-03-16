package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.objetos.Cliente;
import modelo.objetosDAO.ClienteDAO;

@SuppressWarnings("serial")
public class CambiarDatos extends JPanel {
	
	//ATRIBUTOS:
	private boolean asteriscos = false;
	
	
	//CONSTRUCTOR:
	public CambiarDatos(JFrame ventana) {
		this.setLayout(null);
		
		Cliente cliente = ClienteDAO.buscar_dni(Ventana.DNI_EJEMPLO);
		
		
		//JLbels:
		JLabel nombre = new JLabel("Nombre:");
		nombre.setToolTipText("Nombre del cliente.");
		
		JLabel apellidos = new JLabel("Apellidos:");
		apellidos.setToolTipText("Apellidos del cliente.");
		
		JLabel dni = new JLabel("DNI:");
		dni.setToolTipText("DNI del cliente.");
		
		JLabel pasword = new JLabel("Contraseña:");
		pasword.setToolTipText("Contraseña del cliente. Por defecto esta visible.");
		
		JLabel edad = new JLabel("Edad:");
		edad.setToolTipText("Edad del cliente.");
		
		JLabel empleado = new JLabel("Empleado:");
		empleado.setToolTipText("Estado del cliente, si esta trabajando o en paro.");
		
		JLabel cuota = new JLabel("Cuota mensual:");
		cuota.setToolTipText("Cuota del cliente, solo visible.");
		
		
		//JTextFields:
		JTextField nombre_area = new JTextField();
		nombre_area.setToolTipText("Nombre del cliente.");
		nombre_area.setText(cliente.getNombre());
		
		JTextField apellidos_area = new JTextField();
		apellidos_area.setToolTipText("Apellidos del cliente.");
		apellidos_area.setText(cliente.getApellidos());
		
		JTextField dni_area = new JTextField();
		dni_area.setToolTipText("DNI del cliente.");
		dni_area.setEnabled(false);
		dni_area.setText(cliente.getDni());
		
		JPasswordField pasword_area = new JPasswordField();
		pasword_area.setToolTipText("Contraseña del cliente. Por defecto esta visible.");
		pasword_area.setText(cliente.getPasword());
		
		JTextField edad_area = new JTextField();
		edad_area.setToolTipText("Edad del cliente.");
		edad_area.setEnabled(false);
		edad_area.setText(Integer.toString(cliente.getEdad()));
		
		JTextField empleado_area = new JTextField();
		empleado_area.setToolTipText("Estado del cliente, si esta trabajando o en paro.");
		empleado_area.setEnabled(false);
		empleado_area.setText(cliente.isEstadoChar());
		
		JTextField cuota_area = new JTextField();
		cuota_area.setToolTipText("Cuota del cliente, solo visible.");
		cuota_area.setEnabled(false);
		cuota_area.setText(Double.toString(cliente.getCuota()));
		
		
		JCheckBox pwd_asteriscos_check = new JCheckBox("Contraseña visible.");
		pwd_asteriscos_check.setSelected(false);
		pwd_asteriscos_check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(asteriscos == true) {
					pasword_area.setEchoChar('*');
					asteriscos = false;
				}else {
					pasword_area.setEchoChar((char)0);
					asteriscos = true;
				}
			}
		});
		
		JButton guardar = new JButton("Guardar datos");
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarDatos(nombre_area.getText(), apellidos_area.getText(), dni_area.getText(), String.valueOf(pasword_area.getPassword()), edad_area.getText(), empleado_area.getText(), cuota_area.getText());
			}
		});
		JButton refresh = new JButton("Refrescar");
		JButton inicio = new JButton("Volver a Inicio");
		
		//horizontal - vertical - ancho - alto
		nombre.setBounds(100, 60, 80, 20);
		nombre_area.setBounds(200, 60, 120, 20);
		
		apellidos.setBounds(100, 100, 80, 20);
		apellidos_area.setBounds(200, 100, 120, 20);
		
		dni.setBounds(100, 140, 80, 20);
		dni_area.setBounds(200, 140, 120, 20);
		
		pasword.setBounds(100, 180, 80, 20);
		pasword_area.setBounds(200, 180, 120, 20);
		
		edad.setBounds(100, 220, 80, 20);
		edad_area.setBounds(200, 220, 120, 20);
		
		empleado.setBounds(100, 260, 80, 20);
		empleado_area.setBounds(200, 260, 120, 20);
		
		cuota.setBounds(100, 300, 100, 20);
		cuota_area.setBounds(200, 300, 120, 20);
		
		pwd_asteriscos_check.setBounds(700, 100, 180, 20);
		
		guardar.setBounds(100, 400, 160, 60);
		refresh.setBounds(400, 400, 160, 60);
		inicio.setBounds(700, 400, 160, 60);
		
		this.add(nombre);
		this.add(nombre_area);
		
		this.add(apellidos);
		this.add(apellidos_area);
		
		this.add(dni);
		this.add(dni_area);
		
		this.add(pasword);
		this.add(pasword_area);
		
		this.add(edad);
		this.add(edad_area);
		
		this.add(empleado);
		this.add(empleado_area);
		
		this.add(cuota);
		this.add(cuota_area);
		
		this.add(pwd_asteriscos_check);
		
		this.add(guardar);
		this.add(refresh);
		this.add(inicio);
		
	}
	
	
	//METODOS:
	private void guardarDatos(String...strings) {
		//OJO con el orden de insercion de los datos y como estan en los constructores, metodos y columnas de la dbo.
		Cliente cliente = new Cliente(strings[2], strings[3], strings[0], strings[1], Integer.parseInt(strings[4]), Cliente.generarEstado(strings[5]), Double.parseDouble(strings[6]));
		
		if(ClienteDAO.editar_datos_cliente(Ventana.DNI_EJEMPLO, cliente)) {
			JOptionPane.showMessageDialog(null, "Se han actualizado los datos correctamente", "", JOptionPane.PLAIN_MESSAGE, null);
		}else {
			JOptionPane.showMessageDialog(null, "No se han podido actualizar los datos", "", JOptionPane.WARNING_MESSAGE, null);
		}
	}
}
