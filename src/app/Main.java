package app;

import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;
import model.CategoryRule;
import model.ScanReport;
import service.FileOrganizer;

public class Main {
	
	private static List<CategoryRule> createDefaultRules() {
        List<CategoryRule> rules = new ArrayList<>();

        // Regla para Imágenes
        rules.add(new CategoryRule("Imágenes")
                .addExtention("jpg")
                .addExtention("jpeg")
                .addExtention("png")
                .addExtention("gif")
                .addExtention("webp"));

        // Regla para Documentos
        rules.add(new CategoryRule("Documentos")
                .addExtention("pdf")
                .addExtention("docx")
                .addExtention("doc")
                .addExtention("txt")
                .addExtention("xlsx")
                .addExtention("pptx"));

        // Regla para Multimedia (Audio y Video)
        rules.add(new CategoryRule("Multimedia")
                .addExtention("mp4")
                .addExtention("mkv")
                .addExtention("mp3")
                .addExtention("wav"));

        // Regla para Archivos Comprimidos
        rules.add(new CategoryRule("Comprimidos")
                .addExtention("zip")
                .addExtention("rar")
                .addExtention("7z"));

        return rules;
    }
	
	private static void main() {
		System.out.println("-----------BIENVENIDO A FILETIDY-----------");
		System.out.println("(1)Organizar Una Carpeta");
		System.out.println("(2)Como Funciona FileTidy?");
		System.out.println("(3)Informaciones");
	}

	public static void main(String[] args) {
		FileOrganizer fileOrganizer;
		ScanReport scanReport;
		List<CategoryRule> listaDeRules = createDefaultRules();
		Teclado teclado = new Teclado();
		int opcion;
		
		
		main(); 
		do {
			opcion = teclado.leerEntero("Tu Opcion?");
		}while(opcion != 0);

	}

}
