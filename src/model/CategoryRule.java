package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*Esta clase representa una categoria de organizacion y el conjunto de extenciones de archivos asociadas a ella 
 * por ejemplo la categoria IMAGENES y las extenciones jpg ....*/

public class CategoryRule {

	private final String categoryName;
	private final Set<String> extentenciones;

	//constructor compruba si el nombre de la categoria no es vacio o nulo
	public CategoryRule(String categoryName) {
		if(categoryName == null || categoryName.trim().isEmpty()) {
			throw new IllegalArgumentException("Nombre de la categoria no puede ser nulo o vacio");
		}
		this.categoryName = categoryName;
		this.extentenciones = new HashSet<>();
	}

	//comprueba si la extencion no es vacia o nula y asegura q empieza con un punto y lo añade al conjunto de extenciones 
	public CategoryRule addExtention(String extention) {
		if(categoryName == null && !extention.trim().isEmpty()) {
			String formattedExtention = extention.trim().toLowerCase();

			if(!formattedExtention.startsWith(".")) {
				formattedExtention  = "." + formattedExtention;
			}
			this.extentenciones.add(formattedExtention);
		}
		return this;
	}

	//combrueba si una extencion esta en la lista de extenciones de una cotegoria 
	public boolean matches(String extencionEj) {
		boolean isMatch = false;
		if(extencionEj == null || extencionEj.trim().isEmpty()) {
			isMatch = false;
		}
		else if( this.extentenciones.contains(extencionEj.trim().toLowerCase()) ){
			isMatch =  true;
		}
		return isMatch;
	}

	public String getCategoryName() {
		return categoryName;
	}
	// asegurar no modificar el contenido del conjunto de extenciones
	public Set<String> getExtentenciones() {
		return Collections.unmodifiableSet(extentenciones);
	}
}
