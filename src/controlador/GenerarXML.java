package controlador;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class GenerarXML {
	/*
	 * <horario>
	 * 		<lunes>
	 * 			<actividad hora="hh:mm">"actividad"</actividad>
	 * 			<actividad hora="hh:mm">"actividad"</actividad>
	 * 		</lunes>
	 * </horario>
	 */
	//METODOS:
	public static void imprimirHorario(JTable tabla) {
		try {
			File fichero = new File("datos\\horario.xml");

			int cont = 0;
			while(fichero.exists() == true) {
				fichero = new File("datos\\horario(" + cont+").xml");
				cont++;
			}
			fichero.createNewFile();
			
			DocumentBuilderFactory factori = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factori.newDocumentBuilder();
			
			DOMImplementation domImplementation = builder.getDOMImplementation();
			Document documento = domImplementation.createDocument(null, "horario", null);
			documento.setXmlVersion("1.0");
			
			
			for(int i = 1; i < tabla.getColumnCount(); i++) {
				Element etiquetaPadre = documento.createElement(tabla.getColumnName(i));
				documento.getDocumentElement().appendChild(etiquetaPadre);
				
				for (int j = 0; j < tabla.getRowCount(); j++) {
					
					String valorEtiqueta;
					String valorAtributo;
					
					try {
						valorEtiqueta = tabla.getValueAt(j, i).toString();
					}catch(NullPointerException e) {
						valorEtiqueta = "N/A";
					}
					try {
						valorAtributo = tabla.getValueAt(j, 0).toString();
					}catch(NullPointerException e) {
						valorAtributo = "";
					}
					
					if(valorAtributo.length() != 0) {
						crearElemento("actividad", valorEtiqueta.trim(), "hora", valorAtributo, etiquetaPadre, documento);
					}
				}
			}
			
			
			Source origen = new DOMSource(documento);
			Result destino = new StreamResult(fichero);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(origen, destino);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void crearElemento(String etiqueta, String valorEtiqueta, String atributo, String valorAtributo, Element raiz, Document documento) {
		Element elementoEtiqueta = documento.createElement(etiqueta);
		elementoEtiqueta.setAttribute(atributo, valorAtributo);
		Text texto = documento.createTextNode(valorEtiqueta);
		raiz.appendChild(elementoEtiqueta);
		elementoEtiqueta.appendChild(texto);
	}
}
