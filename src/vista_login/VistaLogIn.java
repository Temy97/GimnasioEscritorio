package vista_login;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.objetosDAO.NeodatisDAO;

@SuppressWarnings("serial")
public class VistaLogIn extends JFrame{
	
	//ATRIBUTOS:
	private JButton botonAceptar;
	private JButton botonSalir;
	
	
	//CONSTRUCTOR:
	public VistaLogIn() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) (screenSize.getWidth() / 4), (int) (screenSize.getHeight() / 2));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Log-Menu");
		
		
		//Panel de la ventna:
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(80, 60, 80, 20);
		JLabel pasword = new JLabel("Contraseña:");
		pasword.setBounds(80, 140, 80, 20);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(200, 60, 120, 20);
		JPasswordField txtPasword = new JPasswordField();
		txtPasword.setEchoChar('*');
		txtPasword.setBounds(200, 140, 120, 20);
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(60, 240, 100, 60);
		botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//comprobar escrito
				if(txtNombre.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Nombre erroneo", "", JOptionPane.ERROR_MESSAGE, null);
				}else {
					if(txtPasword.getPassword().length == 0) {
						JOptionPane.showMessageDialog(null, "Contraseña erronea", "", JOptionPane.ERROR_MESSAGE, null);
					}else {
						
					}
				}
				
				//comprobar db
				Object objeto = null;
				boolean encontrado = false;
				
				for (int i = 0; i < NeodatisDAO.listaClientes().size(); i++) {
					if(NeodatisDAO.listaClientes().get(i).getNombre().equals(txtNombre.getText())
							&& NeodatisDAO.listaClientes().get(i).getPasword().equals(txtPasword.getPassword().toString())){
						objeto = NeodatisDAO.listaClientes().get(i);
						encontrado = true;
					}
				}
				if(encontrado == false) {
					for (int i = 0; i < NeodatisDAO.listaAdministrativos().size(); i++) {
						if(NeodatisDAO.listaAdministrativos().get(i).getNombre().equals(txtNombre.getText())
								&& NeodatisDAO.listaAdministrativos().get(i).getPasword().equals(txtPasword.getPassword().toString())){
							objeto = NeodatisDAO.listaAdministrativos().get(i);
							encontrado = true;
						}
					}
				}
				
				//lanzar objeto
			}
		});
		botonSalir = new JButton("Salir");
		botonSalir.setBounds(200, 240, 100, 60);
		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		panel.add(nombre);
		panel.add(pasword);
		panel.add(txtNombre);
		panel.add(txtPasword);
		panel.add(botonAceptar);
		panel.add(botonSalir);
		
		this.setContentPane(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	//METODOS:
	public static void main(String[] args) {
		new VistaLogIn();
	}
	
	
	/*public static boolean vistaLogIn() {
		
	}*/
}
