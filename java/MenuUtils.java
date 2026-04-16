/*
 * Nombre del archivo: MenuUtils.java
 * Propósito: Contiene los métodos para la interfaz de usuario: mensaje de bienvenida,
 *            proceso de login con hasta 3 intentos, menú principal y mensaje de despedida.
 *            Usa arreglos paralelos para validar las credenciales de acceso.
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            [NOMBRE 2] - [NÚMERO ESTUDIANTE 2]
 *            [NOMBRE 3] - [NÚMERO ESTUDIANTE 3]
 *            [NOMBRE 4] - [NÚMERO ESTUDIANTE 4]
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// MenuUtils.java
// Métodos de interfaz de usuario usando exclusivamente conceptos de COMP 2315.
// Sin .split(), sin .trim(), sin Integer.parseInt() sobre strings procesados.

import javax.swing.JOptionPane;

public class MenuUtils {

    // ─── Credenciales de acceso ───────────────────────────────────────────────

    /* Arreglo con los nombres de usuario válidos —
       cada posición corresponde a un usuario */
    private static String[] USUARIOS = { "admin", "doctor" };

    /* Arreglo con las contraseñas correspondientes —
       la posición [i] de USUARIOS coincide con [i] de CONTRASENAS */
    private static String[] CONTRASENAS = { "admin123", "doc2315" };

    private static int MAX_INTENTOS = 3; // Número máximo de intentos antes de bloquear

    // ─── Mensaje de bienvenida ────────────────────────────────────────────────

    /*
     * Nombre: mostrarMensajeBienvenida
     * Propósito: Muestra la pantalla de presentación al iniciar la aplicación.
     * Precondiciones: Ninguna
     * Postcondiciones: El diálogo fue mostrado y cerrado por el usuario
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public static void mostrarMensajeBienvenida() {
        String mensaje =
            "======================================================\n" +
            "       SISTEMA DE EXPEDIENTES MEDICOS\n"                   +
            "     Consultorio del Dr. Rodriguez\n"                      +
            "======================================================\n\n"+
            "  Desarrollado por:\n"                                     +
            "    * Ektor M. Gonzalez   - A00617167\n"                   +
            "    * [NOMBRE 2]          - [NUMERO ESTUDIANTE 2]\n"       +
            "    * [NOMBRE 3]          - [NUMERO ESTUDIANTE 3]\n"       +
            "    * [NOMBRE 4]          - [NUMERO ESTUDIANTE 4]\n\n"     +
            "  COMP 2315 - Programacion Estructurada\n"                 +
            "  Universidad Interamericana de PR\n"                      +
            "  Recinto de Aguadilla\n"                                  +
            "  Prof. Dr. Edgardo Vargas Moya\n"                        +
            "  Seccion: [SECCION]\n"                                    +
            "  Fecha de entrega: [FECHA DE ENTREGA]\n\n"               +
            "  PROPOSITO:\n"                                            +
            "  Esta aplicacion permite al Dr. Rodriguez organizar,\n"   +
            "  crear, buscar y actualizar los expedientes medicos\n"    +
            "  de sus pacientes de manera digital y segura.\n\n"        +
            "======================================================";

        JOptionPane.showMessageDialog(null,
            mensaje,
            "Bienvenido al Sistema de Expedientes Medicos",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // ─── Proceso de login ─────────────────────────────────────────────────────

    /*
     * Nombre: realizarLogin
     * Propósito: Solicita credenciales y las valida contra los arreglos paralelos.
     *            Permite hasta MAX_INTENTOS intentos antes de bloquear el acceso.
     * Precondiciones: Ninguna
     * Postcondiciones: Retorna true si el acceso fue concedido
     * Argumentos: Ninguno
     * Valor que devuelve: boolean — true si el login fue exitoso
     * Versión: 1.0
     */
    public static boolean realizarLogin() {
        int intentos = 0; // Contador de intentos fallidos

        while (intentos < MAX_INTENTOS) { // Permite hasta MAX_INTENTOS intentos

            String usuario = JOptionPane.showInputDialog(null,
                "Ingrese su NOMBRE DE USUARIO:",
                "Inicio de Sesion", JOptionPane.PLAIN_MESSAGE);

            if (usuario == null) return false; // Si presiona Cancelar, falla el login

            String contrasena = JOptionPane.showInputDialog(null,
                "Ingrese su CONTRASENA:",
                "Inicio de Sesion", JOptionPane.PLAIN_MESSAGE);

            if (contrasena == null) return false; // Si presiona Cancelar, falla el login

            if (validarCredenciales(usuario, contrasena)) { // Verifica las credenciales
                JOptionPane.showMessageDialog(null,
                    "Bienvenido, " + usuario + "!\n" +
                    "Acceso concedido al sistema.",
                    "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
                return true; // Login exitoso
            } else {
                intentos++;
                int restantes = MAX_INTENTOS - intentos;

                if (restantes > 0) {
                    JOptionPane.showMessageDialog(null,
                        "Usuario o contrasena incorrectos.\n" +
                        "Intentos restantes: " + restantes,
                        "Error de Autenticacion", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Numero maximo de intentos alcanzado.\n" +
                        "El sistema se cerrara por seguridad.",
                        "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        return false; // Se agotaron los intentos — acceso denegado
    }

    // ─── Validar credenciales ─────────────────────────────────────────────────

    /*
     * Nombre: validarCredenciales
     * Propósito: Compara usuario y contraseña contra los arreglos paralelos.
     * Precondiciones: pUsuario y pContrasena no deben ser nulos
     * Postcondiciones: Ninguna
     * Argumentos: pUsuario — nombre de usuario ingresado;
     *             pContrasena — contraseña ingresada
     * Valor que devuelve: boolean — true si las credenciales son válidas
     * Versión: 1.0
     */
    private static boolean validarCredenciales(String pUsuario, String pContrasena) {
        for (int i = 0; i < USUARIOS.length; i++) { // Recorre los arreglos paralelos
            if (USUARIOS[i].equals(pUsuario) && CONTRASENAS[i].equals(pContrasena)) {
                return true; // Credenciales válidas en la posición i
            }
        }
        return false; // No se encontró coincidencia
    }

    // ─── Mostrar menú principal ───────────────────────────────────────────────

    /*
     * Nombre: mostrarMenuPrincipal
     * Propósito: Muestra el menú con las 6 opciones del sistema y retorna el número
     *            de la opción elegida. Usa un for loop con .equals() para identificar
     *            la opción seleccionada (sin .split() ni Integer.parseInt()).
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: Ninguno
     * Valor que devuelve: int — número de opción (1-6), o -1 si se cerró el diálogo
     * Versión: 1.0
     */
    public static int mostrarMenuPrincipal() {
        String[] opciones = { // Arreglo con las 6 opciones del menú principal
            "1. Crear nuevo expediente",
            "2. Buscar expediente por numero",
            "3. Buscar expediente por fecha de visita",
            "4. Actualizar expediente",
            "5. Listar todos los expedientes",
            "6. Salir del sistema"
        };

        String seleccion = (String) JOptionPane.showInputDialog(null,
            "==============================\n" +
            "  SISTEMA DE EXPEDIENTES\n"       +
            "==============================\n\n"+
            "Seleccione una opcion:",
            "Menu Principal",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]); // Primera opción seleccionada por defecto

        if (seleccion == null) return -1; // Cerró el diálogo sin seleccionar

        /* Busca cuál opción fue seleccionada comparando con .equals()
           y retorna su posición + 1 como número de opción (sin .split()) */
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(seleccion)) {
                return i + 1; // Retorna 1..6 según la posición encontrada
            }
        }

        return -1; // No debería ocurrir, pero se maneja por seguridad
    }

    // ─── Mensaje de despedida ─────────────────────────────────────────────────

    /*
     * Nombre: mostrarMensajeDespedida
     * Propósito: Muestra el mensaje de cierre al terminar la aplicación.
     * Precondiciones: Ninguna
     * Postcondiciones: El diálogo fue mostrado y cerrado por el usuario
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public static void mostrarMensajeDespedida() {
        JOptionPane.showMessageDialog(null,
            "======================================================\n" +
            "    GRACIAS POR USAR EL SISTEMA DE EXPEDIENTES\n\n"        +
            "  El sistema se ha cerrado correctamente.\n"               +
            "  Todos los expedientes han sido guardados.\n\n"           +
            "  Consultorio del Dr. Rodriguez\n"                         +
            "  COMP 2315 - Universidad Interamericana de PR\n"          +
            "======================================================",
            "Hasta pronto!",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
