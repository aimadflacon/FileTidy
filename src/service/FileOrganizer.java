package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import model.CategoryRule;
import model.ScanReport;

public class FileOrganizer {
	private final DuplicateResolver duplicateResolver;

	public FileOrganizer(DuplicateResolver duplicateResolver) {
		super();
		this.duplicateResolver = duplicateResolver;
	}
	
	/**
     * Escanea la carpeta de origen y clasifica sus archivos según las reglas dadas.
     * No mueve nada, solo crea y devuelve un informe (ScanReport) con la vista previa.
     */
    public ScanReport scanFolder(Path sourceFolder, List<CategoryRule> rules) throws IOException {
        ScanReport report = new ScanReport(sourceFolder);

        // Verificamos que la ruta de origen exista y sea un directorio válido
        if (!Files.exists(sourceFolder) || !Files.isDirectory(sourceFolder)) {
            throw new IllegalArgumentException("La ruta de origen no existe o no es un directorio válido: " + sourceFolder);
        }

        // Usamos Files.list dentro de un try-with-resources para cerrar el flujo de archivos de forma segura
        try (Stream<Path> pathStream = Files.list(sourceFolder)) {
            pathStream.filter(Files::isRegularFile) // Solo procesamos archivos regulares (ignoramos subcarpetas)
                      .forEach(file -> processFileScan(file, rules, report));
        }

        return report;
    }
    
     
    private void processFileScan(Path file, List<CategoryRule> rules, ScanReport report) {
        String fileName = file.getFileName().toString();
        String extension = getFileExtension(fileName);

        // Buscamos si el archivo coincide con alguna de las reglas de categoría
        boolean matched = false;
        for (CategoryRule rule : rules) {
            if (rule.matches(extension)) {
                report.addFile(rule.getCategoryName(), file);
                matched = true;
                break; // Una vez que encuentra su categoría, salimos del bucle
            }
        }

        // Si ninguna regla coincide, se marca como no categorizado
        if (!matched) {
            report.addUncategorizedFile(file);
        }
    }
    
  //aqui el metodo organizeFiles
    
    
    /**
     * Extrae la extensión de un nombre de archivo (incluyendo el punto, ej. ".jpg").
     */
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex);
    }
}
