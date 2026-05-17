/*
 * Nombre del archivo: Main.java
 * Propósito: Punto de entrada del Sistema de Expedientes Médicos del Dr. Rodríguez.
 *            Coordina el flujo completo: bienvenida, login, menú principal con switch
 *            y cierre del sistema. Usa únicamente conceptos de COMP 2315.
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            Diego L. Rodriguez Perez - A00636906
 *            Donovan Irizarry Figueroa - A00671799
 *            Carlo Ramos - A00642991
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// Main.java
// Punto de entrada del sistema. Sin System.exit(), sin showConfirmDialog,
// sin imports innecesarios — solo JOptionPane (COMP 2315).

import javax.swing.JOptionPane;

public class Main {

    /*
     * Nombre: main
     * Propósito: Punto de entrada de la aplicación. Coordina la bienvenida,
     *            login, menú principal en bucle y cierre del sistema.
     *            El flujo completo se controla con variables booleanas (sin System.exit()).
     * Precondiciones: Ninguna
     * Postcondiciones: La aplicación se ejecuta y termina de forma natural
     * Argumentos: args — arreglo de argumentos de línea de comandos (no se usan)
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public static void main(String[] args) {

        // ── Paso 1: Mostrar mensaje de bienvenida ────────────────────────────
        MenuUtils.mostrarMensajeBienvenida();

        // ── Paso 2: Realizar el proceso de login ─────────────────────────────
        boolean loginExitoso = MenuUtils.realizarLogin();

        if (loginExitoso) { // Solo entra al sistema si el login fue exitoso

            // ── Paso 3: Crear el manejador de expedientes ────────────────────
            ExpedienteManager manager = new ExpedienteManager();

            // ── Paso 4: Mostrar el menú principal en bucle ───────────────────
            boolean ejecutando = true; // Variable de control del bucle principal

            while (ejecutando) { // Repite hasta que el usuario confirme salida

                int opcion = MenuUtils.mostrarMenuPrincipal();

                if (opcion == -1) { // Si cierra el diálogo sin seleccionar
                    opcion = 6;     // Trata el cierre como "Salir"
                }

                switch (opcion) {

                    case 1: // Crear nuevo expediente
                        manager.crearExpediente();
                        break;

                    case 2: // Buscar por número de expediente
                        manager.buscarPorNumero();
                        break;

                    case 3: // Buscar por fecha de visita
                        manager.buscarPorFechaVisita();
                        break;

                    case 4: // Actualizar expediente existente
                        manager.actualizarExpediente();
                        break;

                    case 5: // Listar todos los expedientes
                        manager.listarTodosLosExpedientes();
                        break;

                    case 6: // Solicitar confirmación de salida
                        /* showConfirmDialog no está en COMP 2315 —
                           se pide confirmación con showInputDialog */
                        String resp = JOptionPane.showInputDialog(null,
                            "Escriba S para confirmar la salida del sistema:",
                            "Confirmar Salida", JOptionPane.PLAIN_MESSAGE);

                        if (resp != null && resp.equals("S")) {
                            ejecutando = false; // Sale del bucle de forma natural
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,
                            "Opcion no reconocida.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // ── Paso 5: Mostrar mensaje de despedida ─────────────────────────
            MenuUtils.mostrarMensajeDespedida();

        } else { // Login fallido — informa y termina naturalmente (sin System.exit())
            JOptionPane.showMessageDialog(null,
                "No se pudo autenticar. El sistema se cerrara.",
                "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
        }

        /* El programa termina de forma natural al llegar aquí —
           sin System.exit() (no está en el currículo de COMP 2315) */
    }
}
