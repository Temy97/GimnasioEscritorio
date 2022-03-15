package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CambiarDatos extends JPanel {
	
	//ATRIBUTOS:
	private boolean asteriscos = false;
	
	
	//CONSTRUCTOR:
	public CambiarDatos(JFrame ventana) {
		this.setLayout(null);
		
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
		
		JTextField nombre_area = new JTextField();
		nombre_area.setToolTipText("Nombre del cliente.");
		JTextField apellidos_area = new JTextField();
		apellidos_area.setToolTipText("Apellidos del cliente.");
		JTextField dni_area = new JTextField();
		dni_area.setToolTipText("DNI del cliente.");
		JPasswordField pasword_area = new JPasswordField();
		pasword_area.setToolTipText("Contraseña del cliente. Por defecto esta visible.");
		JTextField edad_area = new JTextField();
		edad_area.setToolTipText("Edad del cliente.");
		edad_area.setEnabled(true);
		JTextField empleado_area = new JTextField();
		empleado_area.setToolTipText("Estado del cliente, si esta trabajando o en paro.");
		empleado_area.setEnabled(true);
		JTextField cuota_area = new JTextField();
		cuota_area.setToolTipText("Cuota del cliente, solo visible.");
		cuota_area.setEnabled(true);
		
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
		JButton refresh = new JButton("Refrescar");
		JButton inicio = new JButton("Volver a Inicio");
		
		//horizontal - vertical - ancho - alto
		nombre.setBounds(100, 60, 80, 20);
		nombre_area.setBounds(160, 60, 80, 20);
		
		this.add(nombre);
		this.add(nombre_area);
	}
	
	
	//METODOS:
	
}
