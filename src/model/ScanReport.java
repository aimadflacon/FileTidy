package model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**/ 
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
		
		public void addFile(String nombreCategoria, Path archivo) {
				archivosCategorizados.computeIfAbsent(nombreCategoria, k -> new ArrayList<>()).add(archivo);
		}
		
		
}

 