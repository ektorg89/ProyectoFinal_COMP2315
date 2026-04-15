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
// Contiene los métodos para la interfaz de usuario: mensaje de bienvenida,
// proceso de login con hasta 3 intentos, menú principal y mensaje de despedida.
// Usa arreglos paralelos para validar las credenciales de acceso.

import javax.swing.JOptionPane; // Importa JOptionPane para mostrar todos los diálogos gráficos del sistema

public class MenuUtils { // Clase utilitaria con métodos estáticos para la interfaz de usuario

    // ─── Credenciales hardcoded (usuario/contraseña) ──────────────────────────

    private static String[] USUARIOS = { "admin", "doctor" }; /* Arreglo con los nombres de usuario válidos —
                                                                   cada posición corresponde a un usuario */
    private static String[] CONTRASENAS = { "admin123", "doc2315" }; /* Arreglo con las contraseñas correspondientes —
                                                                          la posición [i] de USUARIOS coincide con [i] de CONTRASENAS */

    private static int MAX_INTENTOS = 3; // Número máximo de intentos de login antes de bloquear el acceso

    // ─── Mensaje de bienvenida ────────────────────────────────────────────────

    /*
     * Nombre: mostrarMensajeBienvenida
     * Propósito: Muestra la pantalla de presentación al iniciar la aplicación con
     *            los nombres del equipo, el propósito del sistema y la información del curso.
     * Precondiciones: Ninguna
     * Postcondiciones: El diálogo de bienvenida fue mostrado y cerrado por el usuario
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public static void mostrarMensajeBienvenida() { // Muestra la pantalla de presentación al iniciar la aplicación
        String mensaje = // Construye el texto del mensaje con información del proyecto y los creadores
            "╔══════════════════════════════════════════════════════╗\n" +
            "         SISTEMA DE EXPEDIENTES MÉDICOS\n" +
            "       Consultorio del Dr. Rodríguez\n" +
            "══════════════════════════════════════════════════════\n\n" +
            "  Desarrollado por:\n" +
            "    • [NOMBRE 1]  — [NÚMERO ESTUDIANTE 1]\n" +
            "    • [NOMBRE 2]  — [NÚMERO ESTUDIANTE 2]\n\n" +
            "  COMP 2315 — Programación Estructurada\n" +
            "  Universidad Interamericana de PR\n" +
            "  Recinto de Aguadilla\n" +
            "  Prof. Dr. Edgardo Vargas Moya\n" +
            "  Sección: [SECCIÓN]\n" +
            "  Fecha de entrega: [FECHA DE ENTREGA]\n\n" +
            "  PROPÓSITO:\n" +
            "  Esta aplicación permite al Dr. Rodríguez organizar,\n" +
            "  crear, buscar y actualizar los expedientes médicos\n" +
            "  de sus pacientes de manera digital y segura.\n\n" +
            "╚══════════════════════════════════════════════════════╝";

        JOptionPane.showMessageDialog(null,
            mensaje,
            "Bienvenido al Sistema de Expedientes Médicos",
            JOptionPane.INFORMATION_MESSAGE); // Muestra el mensaje con ícono de información
    }

    // ─── Proceso de login ─────────────────────────────────────────────────────

    /*
     * Nombre: realizarLogin
     * Propósito: Solicita las credenciales de acceso al usuario y las valida
     *            contra los arreglos de usuarios y contraseñas. Permite hasta
     *            3 intentos antes de bloquear el acceso al sistema.
     * Precondiciones: Ninguna
     * Postcondiciones: Retorna true si el acceso fue concedido, false si fue denegado o cancelado
     * Argumentos: Ninguno
     * Valor que devuelve: boolean — true si el login fue exitoso, false si falló o se canceló
     * Versión: 1.0
     */
    public static boolean realizarLogin() { // Solicita credenciales y valida el acceso al sistema
        int intentos = 0; // Contador de intentos fallidos — inicia en cero

        while (intentos < MAX_INTENTOS) { // Permite hasta MAX_INTENTOS (3) intentos antes de bloquear
            String usuario = JOptionPane.showInputDialog(null,
                "Ingrese su NOMBRE DE USUARIO:",
                "Inicio de Sesión", JOptionPane.PLAIN_MESSAGE); // Solicita el nombre de usuario

            if (usuario == null) return false; // Si presiona Cancelar, termina el login con fallo

            String contrasena = JOptionPane.showInputDialog(null,
                "Ingrese su CONTRASEÑA:",
                "Inicio de Sesión", JOptionPane.PLAIN_MESSAGE); // Solicita la contraseña

            if (contrasena == null) return false; // Si presiona Cancelar en la contraseña, falla el login

            if (validarCredenciales(usuario.trim(), contrasena.trim())) { // Verifica usuario y contraseña
                JOptionPane.showMessageDialog(null,
                    "¡Bienvenido, " + usuario.trim() + "!\n" +
                    "Acceso concedido al sistema.",
                    "Login Exitoso", JOptionPane.INFORMATION_MESSAGE); // Informa el acceso exitoso
                return true; // Retorna true — el login fue exitoso
            } else { // Si las credenciales no coinciden
                intentos++; // Incrementa el contador de intentos fallidos
                int restantes = MAX_INTENTOS - intentos; // Calcula cuántos intentos quedan

                if (restantes > 0) { // Si aún quedan intentos disponibles
                    JOptionPane.showMessageDialog(null,
                        "Usuario o contraseña incorrectos.\n" +
                        "Intentos restantes: " + restantes,
                        "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                } else { // Si ya se agotaron todos los intentos
                    JOptionPane.showMessageDialog(null,
                        "Número máximo de intentos alcanzado.\n" +
                        "El sistema se cerrará por seguridad.",
                        "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        return false; // Si el bucle termina sin éxito, retorna false indicando acceso denegado
    }

    // ─── Validar credenciales ─────────────────────────────────────────────────

    /*
     * Nombre: validarCredenciales
     * Propósito: Compara el usuario y contraseña recibidos contra los arreglos
     *            paralelos de credenciales válidas del sistema.
     * Precondiciones: usuario y contrasena no deben ser nulos
     * Postcondiciones: Ninguna — no modifica el estado de la clase
     * Argumentos: usuario — nombre de usuario ingresado; contrasena — contraseña ingresada
     * Valor que devuelve: boolean — true si las credenciales coinciden con un par válido, false en caso contrario
     * Versión: 1.0
     */
    private static boolean validarCredenciales(String usuario, String contrasena) { // Compara credenciales contra los arreglos
        for (int i = 0; i < USUARIOS.length; i++) { // Recorre cada posición de los arreglos paralelos
            if (USUARIOS[i].equals(usuario) && CONTRASENAS[i].equals(contrasena)) { // Compara usuario Y contraseña
                return true; // Si ambos coinciden en la misma posición, las credenciales son válidas
            }
        }
        return false; // Si no encontró coincidencia, las credenciales son inválidas
    }

    // ─── Mostrar menú principal ───────────────────────────────────────────────

    /*
     * Nombre: mostrarMenuPrincipal
     * Propósito: Muestra el menú principal con las 6 opciones del sistema mediante
     *            una lista desplegable y retorna el número de la opción elegida.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna — no modifica el estado de la clase
     * Argumentos: Ninguno
     * Valor que devuelve: int — número de la opción seleccionada (1-6), o -1 si se cerró el diálogo
     * Versión: 1.0
     */
    public static int mostrarMenuPrincipal() { // Muestra el menú con las 6 opciones y retorna la opción elegida
        String[] opciones = { // Arreglo con las opciones del menú principal
            "1. Crear nuevo expediente",
            "2. Buscar expediente por número",
            "3. Buscar expediente por fecha de visita",
            "4. Actualizar expediente",
            "5. Listar todos los expedientes",
            "6. Salir del sistema"
        };

        String seleccion = (String) JOptionPane.showInputDialog(null,
            "╔════════════════════════════════════╗\n" +
            "    SISTEMA DE EXPEDIENTES MÉDICOS\n" +
            "╚════════════════════════════════════╝\n\n" +
            "Seleccione una opción:",
            "Menú Principal",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones, // Muestra el arreglo como lista desplegable
            opciones[0]); // La primera opción aparece seleccionada por defecto

        if (seleccion == null) return -1; // Si cierra la ventana o cancela, retorna -1 como señal especial

        return Integer.parseInt(seleccion.split("\\.")[0].trim()); // Extrae y retorna el número de la opción elegida
    }

    // ─── Mensaje de despedida ─────────────────────────────────────────────────

    /*
     * Nombre: mostrarMensajeDespedida
     * Propósito: Muestra el mensaje de cierre de la aplicación informando al usuario
     *            que todos los expedientes han sido guardados correctamente.
     * Precondiciones: Ninguna
     * Postcondiciones: El diálogo de despedida fue mostrado y cerrado por el usuario
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public static void mostrarMensajeDespedida() { // Muestra el mensaje final al cerrar la aplicación
        JOptionPane.showMessageDialog(null,
            "╔══════════════════════════════════════════════════════╗\n" +
            "     GRACIAS POR USAR EL SISTEMA DE EXPEDIENTES\n\n" +
            "  El sistema se ha cerrado correctamente.\n" +
            "  Todos los expedientes han sido guardados.\n\n" +
            "  Consultorio del Dr. Rodríguez\n" +
            "  COMP 2315 — Universidad Interamericana de PR\n" +
            "╚══════════════════════════════════════════════════════╝",
            "¡Hasta pronto!",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
