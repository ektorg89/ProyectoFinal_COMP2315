// ArchivoManager.java
// Gestiona todas las operaciones de lectura y escritura de expedientes
// médicos en el archivo de texto "data/expedientes.txt". Provee métodos
// para guardar, cargar, reescribir y verificar expedientes en disco.

import java.io.*; // Importa todas las clases necesarias para manejo de archivos (File, BufferedReader, BufferedWriter, etc.)
import java.util.ArrayList; // Importa ArrayList para almacenar la lista de pacientes cargados

public class ArchivoManager { // Clase utilitaria con métodos estáticos para operaciones de archivos

    // ─── Ruta de la carpeta de datos ─────────────────────────────────────────

    private static final String CARPETA_DATA = "data" + File.separator; /* Ruta relativa a la carpeta "data/"
                                                                            File.separator usa "/" en Mac/Linux y "\" en Windows */
    private static final String ARCHIVO_PRINCIPAL = CARPETA_DATA + "expedientes.txt"; // Ruta completa al archivo de expedientes

    // ─── Inicialización ───────────────────────────────────────────────────────

    public static void inicializarCarpeta() { // Verifica y crea la carpeta "data" si no existe
        File carpeta = new File(CARPETA_DATA); // Crea un objeto File apuntando a la carpeta "data"
        if (!carpeta.exists()) { // Si la carpeta no existe en el sistema de archivos
            carpeta.mkdirs(); // La crea junto con cualquier directorio padre necesario
        }
    }

    // ─── Guardar un expediente ────────────────────────────────────────────────

    public static boolean guardarExpediente(Paciente paciente) { /* Agrega una nueva línea al archivo
                                                                     sin borrar el contenido existente */
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(ARCHIVO_PRINCIPAL, true))) { // "true" activa el modo append (agregar al final)

            bw.write(paciente.toFileString()); // Escribe los datos del paciente en formato separado por "|"
            bw.newLine(); // Agrega un salto de línea para separar cada expediente
            return true; // Retorna true indicando que el guardado fue exitoso

        } catch (IOException e) { // Captura errores de entrada/salida (disco lleno, permisos, etc.)
            System.err.println("Error al guardar expediente: " + e.getMessage()); // Muestra el error en consola
            return false; // Retorna false indicando que el guardado falló
        }
    }

    // ─── Cargar todos los expedientes ─────────────────────────────────────────

    public static ArrayList<Paciente> cargarTodosLosExpedientes() { // Lee el archivo y retorna todos los expedientes
        ArrayList<Paciente> lista = new ArrayList<>(); // Crea una lista vacía para almacenar los pacientes
        File archivo = new File(ARCHIVO_PRINCIPAL); // Crea un objeto File apuntando al archivo principal

        if (!archivo.exists()) return lista; // Si el archivo no existe, retorna la lista vacía sin errores

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) { // Abre el archivo para lectura
            String linea; // Variable para almacenar cada línea leída

            while ((linea = br.readLine()) != null) { // Lee línea por línea hasta llegar al final del archivo
                linea = linea.trim(); // Elimina espacios en blanco al inicio y final de la línea
                if (!linea.isEmpty()) { // Ignora las líneas vacías
                    Paciente p = Paciente.fromFileString(linea); // Convierte la línea de texto en un objeto Paciente
                    if (p != null) { // Si la conversión fue exitosa (la línea tenía todos los campos)
                        lista.add(p); // Agrega el paciente a la lista
                    }
                }
            }

        } catch (IOException e) { // Captura errores de lectura del archivo
            System.err.println("Error al cargar expedientes: " + e.getMessage()); // Muestra el error en consola
        }

        return lista; // Retorna la lista con todos los pacientes cargados
    }

    // ─── Reescribir todos los expedientes ────────────────────────────────────

    public static boolean reescribirTodosLosExpedientes(ArrayList<Paciente> lista) { /* Sobrescribe el archivo
                                                                                         completo con la lista actualizada */
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(ARCHIVO_PRINCIPAL, false))) { // "false" sobrescribe el archivo desde cero

            for (Paciente p : lista) { // Recorre cada paciente en la lista
                bw.write(p.toFileString()); // Escribe los datos del paciente en el archivo
                bw.newLine(); // Agrega salto de línea entre cada expediente
            }
            return true; // Retorna true si todos los expedientes se escribieron correctamente

        } catch (IOException e) { // Captura errores de escritura
            System.err.println("Error al reescribir expedientes: " + e.getMessage()); // Muestra el error
            return false; // Retorna false si ocurrió algún error
        }
    }

    // ─── Verificar si existe un número de expediente ─────────────────────────

    public static boolean existeExpediente(String numeroExpediente) { // Verifica si un número ya está en uso
        ArrayList<Paciente> lista = cargarTodosLosExpedientes(); // Carga todos los expedientes del archivo
        for (Paciente p : lista) { // Recorre cada paciente en la lista
            if (p.getNumeroExpediente().equalsIgnoreCase(numeroExpediente)) { // Compara sin importar mayúsculas
                return true; // Retorna true si ya existe ese número de expediente
            }
        }
        return false; // Retorna false si el número no está en uso
    }

    // ─── Obtener ruta del archivo ─────────────────────────────────────────────

    public static String getRutaArchivo() { // Retorna la ruta completa del archivo de expedientes
        return ARCHIVO_PRINCIPAL;
    }
}
