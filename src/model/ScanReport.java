package model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*esta clase almacena el resultado despues de escannear una carpeta es como guarda la relacion de los archivos que se van a una categoria
 * concreta por ejemplo DOCUMENTOS*/ 
public class ScanReport {

	private final Path rutaCarpeta;
	private final Map<String, List<Path>> archivosCategorizados;
	private final List<Path> archivosNoCategorizados;


	public ScanReport(Path rutaCarpeta) {
		super();
		this.rutaCarpeta = rutaCarpeta;
		this.archivosCategorizados = new HashMap<>();
		this.archivosNoCategorizados = new ArrayList<>();
	}
	/*registra un archivo en su categoria especifica y si la categoria no existe en el mapa la crea automaticamente con una lista vacia*/
	public void addFile(String nombreCategoria, Path archivo) {
		this.archivosCategorizados.computeIfAbsent(nombreCategoria, k -> new ArrayList<>()).add(archivo);
	}

	/*anade la ruta del archivo a la lista de archivos con categoria no conocida*/	
	public void addUncategorizedFile(Path Path) {
		this.archivosNoCategorizados.add(Path);
	}


	public Path getRutaCarpeta() {
		return rutaCarpeta;
	}
	public Map<String, List<Path>> getArchivosCategorizados() {
		return archivosCategorizados;
	}
	public List<Path> getArchivosNoCategorizados() {
		return archivosNoCategorizados;
	}

	/*devuelve el numero de archivos que se pueden organizar*/	
	//EXPLICACION: seleciona los values - suma o conecta las listas en una - cambia cada lista por su tamaño - suma todo
	public int TotalCategorizedCount() {
		return archivosCategorizados.values().stream().mapToInt(List::size).sum();
	}

	/*imprime en consola un resumen limpio y visual con la vista previa para el usuario.*/	
	public void printPreview() {
		System.out.println("\n--------------------------------------------------");
		System.out.println(" 🔍 FILE TIDY - VISTA PREVIA DE ESCANEO");
		System.out.println(" Carpeta objetivo: " + rutaCarpeta.toAbsolutePath());
		System.out.println("--------------------------------------------------");

		if (archivosCategorizados.isEmpty() && archivosNoCategorizados.isEmpty()) {
			System.out.println(" No se encontraron archivos organizables en esta carpeta.");
			System.out.println("--------------------------------------------------");
			return;
		}

		// Recorremos cada categoría y mostramos cuántos archivos tiene
		archivosCategorizados.forEach((category, files) -> {
			System.out.printf(" 📂 [%s]: %d archivo(s)\n", category, files.size());
			// Mostramos hasta 3 ejemplos de archivos para no saturar la consola
			files.stream().limit(3).forEach(file -> 
			System.out.println("    ├── " + file.getFileName()));
			if (files.size() > 3) {
				System.out.println("    └── ... y " + (files.size() - 3) + " más");
			}});

		if (!archivosNoCategorizados.isEmpty()) {
			System.out.println("\n ❓ [Sin categoría / Ignorados]: " + archivosNoCategorizados.size() + " archivo(s)");
		}

		System.out.println("--------------------------------------------------");
		System.out.printf(" TOTAL A ORGANIZAR: %d archivo(s)\n", TotalCategorizedCount());
		System.out.println("--------------------------------------------------\n");
	}

}

