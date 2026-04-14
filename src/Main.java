// Main.java
// Punto de entrada del Sistema de Expedientes Médicos del Dr. Rodríguez.
// Coordina el flujo completo: bienvenida, login, menú principal con switch
// y cierre del sistema. Importa todos los paquetes requeridos por el proyecto.

import java.io.*;           // Importa clases para manejo de archivos: File, BufferedReader, BufferedWriter, FileReader, FileWriter, IOException
import java.util.*;         // Importa clases de utilidades: ArrayList, Scanner y otras colecciones
import javax.swing.*;       // Importa JOptionPane y componentes gráficos de Swing para los diálogos
import java.text.*;         // Importa SimpleDateFormat para validar y formatear fechas
import java.util.regex.*;   // Importa Pattern y Matcher para validaciones con expresiones regulares

public class Main { // Clase principal del programa — contiene el método main que inicia la ejecución

    public static void main(String[] args) { // Método main: punto de entrada, Java lo ejecuta al iniciar el programa

        // ── Paso 1: Inicializar la carpeta de datos ──────────────────────────
        ArchivoManager.inicializarCarpeta(); // Crea la carpeta "data/" si no existe para guardar los expedientes

        // ── Paso 2: Mostrar mensaje de bienvenida ────────────────────────────
        MenuUtils.mostrarMensajeBienvenida(); // Presenta la pantalla inicial con los nombres del equipo y el propósito

        // ── Paso 3: Realizar el proceso de login ─────────────────────────────
        boolean loginExitoso = MenuUtils.realizarLogin(); // Solicita usuario y contraseña — permite hasta 3 intentos

        if (!loginExitoso) { // Si el login falla (credenciales incorrectas o se canceló)
            JOptionPane.showMessageDialog(null,
                "No se pudo autenticar. El sistema se cerrará.",
                "Acceso Denegado", JOptionPane.ERROR_MESSAGE); // Informa que el acceso fue denegado
            System.exit(0); // Termina la aplicación inmediatamente
        }

        // ── Paso 4: Crear el manejador de expedientes ────────────────────────
        ExpedienteManager manager = new ExpedienteManager(); /* Crea el objeto que maneja todos los expedientes —
                                                                 al crearse, carga automáticamente los expedientes del archivo */

        // ── Paso 5: Mostrar el menú principal en un bucle ────────────────────
        boolean ejecutando = true; // Variable de control que mantiene el programa activo

        while (ejecutando) { // Repite el menú principal hasta que el usuario elija "Salir"

            int opcion = MenuUtils.mostrarMenuPrincipal(); // Muestra el menú y obtiene la opción del usuario

            if (opcion == -1) { // Si el usuario cierra la ventana sin seleccionar
                opcion = 6; // Trata el cierre como si hubiera elegido "Salir"
            }

            switch (opcion) { // Evalúa la opción elegida y ejecuta la operación correspondiente

                case 1: // El usuario quiere crear un nuevo expediente
                    manager.crearExpediente(); // Inicia el flujo de captura de datos del nuevo paciente
                    break;

                case 2: // El usuario quiere buscar por número de expediente
                    manager.buscarPorNumero(); // Solicita el número y muestra el expediente encontrado
                    break;

                case 3: // El usuario quiere buscar expedientes por fecha de visita
                    manager.buscarPorFechaVisita(); // Solicita la fecha y muestra todos los expedientes de ese día
                    break;

                case 4: // El usuario quiere actualizar un expediente existente
                    manager.actualizarExpediente(); // Busca el expediente y muestra el submenú de actualización
                    break;

                case 5: // El usuario quiere ver todos los expedientes registrados
                    manager.listarTodosLosExpedientes(); // Muestra una tabla resumen con todos los expedientes
                    break;

                case 6: // El usuario quiere salir del sistema
                    int confirmar = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea salir del sistema?\n" +
                        "Todos los expedientes están guardados correctamente.",
                        "Confirmar Salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE); // Pide confirmación antes de cerrar

                    if (confirmar == JOptionPane.YES_OPTION) { // Si el usuario confirma con "Sí"
                        ejecutando = false; // Cambia la variable de control para salir del bucle while
                    }
                    break; // Si elige "No", el bucle continúa y vuelve al menú

                default: // Caso que no debería ocurrir, pero se maneja por seguridad
                    JOptionPane.showMessageDialog(null,
                        "Opción no reconocida. Por favor seleccione una opción válida.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Paso 6: Mostrar mensaje de despedida y cerrar ────────────────────
        MenuUtils.mostrarMensajeDespedida(); // Muestra el mensaje final de agradecimiento

        System.exit(0); // Cierra la aplicación limpiamente, liberando todos los recursos
    }
}
