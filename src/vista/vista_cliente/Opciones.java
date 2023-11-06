package vista.vista_cliente;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Opciones extends JPanel {
	
	//Atributos:
	private static final String[] SIZES = {"10", "12", "14", "16"};
	private static final String[] FUENTES = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Opciones(JFrame ventana) {
		this.setLayout(null);
		
		//textos generales: -botones, -JLabels...
		JLabel textos_generales = new JLabel("Textos generales ->");
		textos_generales.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		textos_generales.setBounds(50, 50, 120, 20);
		
		JCheckBox negrita_general = new JCheckBox("Activar/Desactivar Negrita");
		negrita_general.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		negrita_general.setSelected(true);
		negrita_general.setBounds(240, 50, 200, 20);
		
		JComboBox<String> tamanos_general = new JComboBox<String>(SIZES);
		tamanos_general.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		tamanos_general.setBounds(440, 50, 100, 20);
		tamanos_general.setSelectedIndex(1);
		
		JComboBox<String> fuentes_general = new JComboBox<String>(FUENTES);
		fuentes_general.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		fuentes_general.setBounds(590, 50, 200, 20);
		int index_generales = 0;
		for (int i = 0; i < FUENTES.length; i++) {
			if(FUENTES[i].equalsIgnoreCase(((VentanaCliente) ventana).getFuenteGeneral().getName())){
				index_generales = i;
			}
		}
		fuentes_general.setSelectedIndex(index_generales);
		
		
		JSeparator separador1 = new JSeparator();
		separador1.setOrientation(JSeparator.HORIZONTAL);
		separador1.setBounds(0, 120, (int) (screenSize.getWidth() / 6) * 4, 5);
		
		
		//textos de las tablas: contenidos y cabeceras
		JLabel textos_tablas = new JLabel("Textos de las tablas ->");
		textos_tablas.setFont(((VentanaCliente) ventana).getFuenteTablas());
		textos_tablas.setBounds(50, 150, 120, 20);
		
		JCheckBox negrita_tablas = new JCheckBox("Activar/Desactivar Negrita");
		negrita_tablas.setFont(((VentanaCliente) ventana).getFuenteTablas());
		negrita_tablas.setSelected(false);
		negrita_tablas.setBounds(240, 150, 200, 20);
		
		JComboBox<String> tamanos_tablas = new JComboBox<String>(SIZES);
		tamanos_tablas.setFont(((VentanaCliente) ventana).getFuenteTablas());
		tamanos_tablas.setBounds(440, 150, 100, 20);
		tamanos_tablas.setSelectedIndex(1);
		
		JComboBox<String> fuentes_tablas = new JComboBox<String>(FUENTES);
		fuentes_tablas.setFont(((VentanaCliente) ventana).getFuenteTablas());
		fuentes_tablas.setBounds(590, 150, 200, 20);
		int index_tablas = 0;
		for (int i = 0; i < FUENTES.length; i++) {
			if(FUENTES[i].equalsIgnoreCase(((VentanaCliente) ventana).getFuenteTablas().getName())){
				index_tablas = i;
			}
		}
		fuentes_tablas.setSelectedIndex(index_tablas);
		
		
		JSeparator separador2 = new JSeparator();
		separador2.setOrientation(JSeparator.HORIZONTAL);
		separador2.setBounds(0, 220, (int) (screenSize.getWidth() / 6) * 4, 5);
		
		
		//textos de las areas de textos como JTextField y JTextArea
		JLabel textos_areas = new JLabel("Textos de las descripciones ->");
		textos_areas.setFont(((VentanaCliente) ventana).getFuenteAreas());
		textos_areas.setBounds(50, 250, 160, 20);
		
		JCheckBox negrita_areas = new JCheckBox("Activar/Desactivar Negrita");
		negrita_areas.setFont(((VentanaCliente) ventana).getFuenteAreas());
		negrita_areas.setSelected(false);
		negrita_areas.setBounds(240, 250, 200, 20);
		
		JComboBox<String> tamanos_areas = new JComboBox<String>(SIZES);
		tamanos_areas.setFont(((VentanaCliente) ventana).getFuenteAreas());
		tamanos_areas.setBounds(440, 250, 100, 20);
		tamanos_areas.setSelectedIndex(1);
		
		JComboBox<String> fuentes_areas = new JComboBox<String>(FUENTES);
		fuentes_areas.setFont(((VentanaCliente) ventana).getFuenteAreas());
		fuentes_areas.setBounds(590, 250, 200, 20);
		int index_areas = 0;
		for (int i = 0; i < FUENTES.length; i++) {
			if(FUENTES[i].equalsIgnoreCase(((VentanaCliente) ventana).getFuenteAreas().getName())){
				index_areas = i;
			}
		}
		fuentes_areas.setSelectedIndex(index_areas);
		
		
		//botón
		JButton aplicar = new JButton("Aplicar");
		aplicar.setFont(((VentanaCliente) ventana).getFuenteGeneral());
		aplicar.setToolTipText("Se aplican los cambios realizados instantáneamente");
		aplicar.setBounds((int)screenSize.getHeight() / 2, 370, 160, 60);
		aplicar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((VentanaCliente) ventana).setFuenteGeneral(new Font(fuentes_general.getSelectedItem().toString(), ((negrita_general.isSelected()) ? Font.BOLD : Font.PLAIN), Integer.parseInt(tamanos_general.getSelectedItem().toString())));
				((VentanaCliente) ventana).setFuenteTablas(new Font(fuentes_tablas.getSelectedItem().toString(), ((negrita_tablas.isSelected()) ? Font.BOLD : Font.PLAIN), Integer.parseInt(tamanos_tablas.getSelectedItem().toString())));
				((VentanaCliente) ventana).setFuenteAreas(new Font(fuentes_areas.getSelectedItem().toString(), ((negrita_areas.isSelected()) ? Font.BOLD : Font.PLAIN), Integer.parseInt(tamanos_areas.getSelectedItem().toString())));
				
				((VentanaCliente) ventana).llamarOpciones();
			}
		});
		
		this.add(textos_generales);
		this.add(negrita_general);
		this.add(tamanos_general);
		this.add(fuentes_general);
		
		this.add(separador1);
		
		this.add(textos_tablas);
		this.add(negrita_tablas);
		this.add(tamanos_tablas);
		this.add(fuentes_tablas);
		
		this.add(separador2);
		
		this.add(textos_areas);
		this.add(negrita_areas);
		this.add(tamanos_areas);
		this.add(fuentes_areas);
		
		this.add(aplicar);
	}
}
