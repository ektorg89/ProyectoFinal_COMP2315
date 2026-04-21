/*
 * Nombre del archivo: Validador.java
 * Propósito: Contiene métodos estáticos para validar los campos del expediente
 *            médico: seguro social, fechas, sexo, texto libre, plan médico y rangos.
 *            Versión simplificada — solo usa conceptos de COMP 2315.
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            Diego L. Rodriguez Perez - A00636906
 *            Donovan Irizarry Figueroa - A00671799
 *            Carlo Ramos - A00642991
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// Validador.java
// Métodos estáticos de validación para los campos del expediente médico.
// Usa charAt() y length() para validar formatos — sin regex ni SimpleDateFormat.

public class Validador {

    // ─── Validación de Seguro Social ─────────────────────────────────────────

    /*
     * Nombre: validarSeguroSocial
     * Propósito: Verifica que el seguro social tenga el formato XXX-XX-XXXX
     *            usando charAt() y length() — sin expresiones regulares.
     *            Longitud exacta: 11 caracteres.
     *            Posiciones 0-2: dígitos; 3: '-'; 4-5: dígitos; 6: '-'; 7-10: dígitos.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: ss — cadena de texto con el seguro social a validar
     * Valor que devuelve: boolean — true si cumple el formato XXX-XX-XXXX
     * Versión: 2.0
     */
    public static boolean validarSeguroSocial(String ss) {
        if (ss == null || ss.length() != 11) { // Debe tener exactamente 11 caracteres
            return false;
        }

        /* Verifica cada posición: dígitos en 0-2, guión en 3,
           dígitos en 4-5, guión en 6, dígitos en 7-10 */
        boolean pos0 = ss.charAt(0) >= '0' && ss.charAt(0) <= '9';
        boolean pos1 = ss.charAt(1) >= '0' && ss.charAt(1) <= '9';
        boolean pos2 = ss.charAt(2) >= '0' && ss.charAt(2) <= '9';
        boolean gui1 = ss.charAt(3) == '-';
        boolean pos4 = ss.charAt(4) >= '0' && ss.charAt(4) <= '9';
        boolean pos5 = ss.charAt(5) >= '0' && ss.charAt(5) <= '9';
        boolean gui2 = ss.charAt(6) == '-';
        boolean pos7 = ss.charAt(7) >= '0' && ss.charAt(7) <= '9';
        boolean pos8 = ss.charAt(8) >= '0' && ss.charAt(8) <= '9';
        boolean pos9 = ss.charAt(9) >= '0' && ss.charAt(9) <= '9';
        boolean posA = ss.charAt(10) >= '0' && ss.charAt(10) <= '9';

        return pos0 && pos1 && pos2 && gui1 && pos4 && pos5
               && gui2 && pos7 && pos8 && pos9 && posA;
    }

    // ─── Validación de número de expediente ──────────────────────────────────

    /*
     * Nombre: validarNumeroExpediente
     * Propósito: Verifica que el número de expediente tenga el formato EXP-DDDDD
     *            usando charAt() y length() — sin expresiones regulares.
     *            Longitud exacta: 9 caracteres.
     *            Posiciones 0-2: "EXP"; 3: '-'; 4-8: dígitos.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: exp — cadena de texto con el número de expediente
     * Valor que devuelve: boolean — true si cumple el formato EXP-DDDDD
     * Versión: 2.0
     */
    public static boolean validarNumeroExpediente(String exp) {
        if (exp == null || exp.length() != 9) { // Debe tener exactamente 9 caracteres
            return false;
        }

        /* Verifica el prefijo "EXP-" y que los últimos 5 sean dígitos */
        boolean prefE  = exp.charAt(0) == 'E';
        boolean prefX  = exp.charAt(1) == 'X';
        boolean prefP  = exp.charAt(2) == 'P';
        boolean gui    = exp.charAt(3) == '-';
        boolean dig4   = exp.charAt(4) >= '0' && exp.charAt(4) <= '9';
        boolean dig5   = exp.charAt(5) >= '0' && exp.charAt(5) <= '9';
        boolean dig6   = exp.charAt(6) >= '0' && exp.charAt(6) <= '9';
        boolean dig7   = exp.charAt(7) >= '0' && exp.charAt(7) <= '9';
        boolean dig8   = exp.charAt(8) >= '0' && exp.charAt(8) <= '9';

        return prefE && prefX && prefP && gui && dig4 && dig5 && dig6 && dig7 && dig8;
    }

    // ─── Validación de fecha ─────────────────────────────────────────────────

    /*
     * Nombre: validarFecha
     * Propósito: Verifica que la fecha tenga el formato MM/DD/YYYY
     *            usando charAt() y length() — sin SimpleDateFormat ni regex.
     *            Longitud exacta: 10 caracteres.
     *            Posiciones 0-1: dígitos (mes); 2: '/'; 3-4: dígitos (día);
     *            5: '/'; 6-9: dígitos (año).
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: fecha — cadena de texto con la fecha a validar
     * Valor que devuelve: boolean — true si cumple el formato MM/DD/YYYY
     * Versión: 2.0
     */
    public static boolean validarFecha(String fecha) {
        if (fecha == null || fecha.length() != 10) { // Debe tener exactamente 10 caracteres
            return false;
        }

        /* Verifica dígitos en mes (0-1), barras en posición 2 y 5,
           dígitos en día (3-4) y año (6-9) */
        boolean mes1  = fecha.charAt(0) >= '0' && fecha.charAt(0) <= '9';
        boolean mes2  = fecha.charAt(1) >= '0' && fecha.charAt(1) <= '9';
        boolean bar1  = fecha.charAt(2) == '/';
        boolean dia1  = fecha.charAt(3) >= '0' && fecha.charAt(3) <= '9';
        boolean dia2  = fecha.charAt(4) >= '0' && fecha.charAt(4) <= '9';
        boolean bar2  = fecha.charAt(5) == '/';
        boolean any1  = fecha.charAt(6) >= '0' && fecha.charAt(6) <= '9';
        boolean any2  = fecha.charAt(7) >= '0' && fecha.charAt(7) <= '9';
        boolean any3  = fecha.charAt(8) >= '0' && fecha.charAt(8) <= '9';
        boolean any4  = fecha.charAt(9) >= '0' && fecha.charAt(9) <= '9';

        return mes1 && mes2 && bar1 && dia1 && dia2 && bar2 && any1 && any2 && any3 && any4;
    }

    // ─── Validación de sexo ──────────────────────────────────────────────────

    /*
     * Nombre: validarSexo
     * Propósito: Verifica que el sexo ingresado sea exactamente "M" o "F".
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: sexo — cadena de texto con el sexo a validar
     * Valor que devuelve: boolean — true si es "M" o "F"
     * Versión: 1.0
     */
    public static boolean validarSexo(String sexo) {
        return sexo != null && (sexo.equals("M") || sexo.equals("F"));
    }

    // ─── Validación de texto no vacío ────────────────────────────────────────

    /*
     * Nombre: validarNoVacio
     * Propósito: Verifica que la cadena de texto tenga al menos un carácter.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: texto — cadena de texto a verificar
     * Valor que devuelve: boolean — true si el texto tiene contenido
     * Versión: 1.0
     */
    public static boolean validarNoVacio(String texto) {
        return texto != null && !texto.equals("");
    }

    // ─── Validación de plan médico ───────────────────────────────────────────

    /*
     * Nombre: validarOpcionPlanMedico
     * Propósito: Verifica que la opción esté entre 1 y 6 (los 6 planes disponibles).
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: opcion — número entero de la opción de plan médico
     * Valor que devuelve: boolean — true si la opción está entre 1 y 6
     * Versión: 1.0
     */
    public static boolean validarOpcionPlanMedico(int opcion) {
        return opcion >= 1 && opcion <= 6;
    }

    // ─── Validación de opción numérica en rango ──────────────────────────────

    /*
     * Nombre: validarRango
     * Propósito: Verifica que un número esté dentro de un rango dado.
     * Precondiciones: min debe ser menor o igual a max
     * Postcondiciones: Ninguna
     * Argumentos: numero — valor a verificar; min — límite inferior; max — límite superior
     * Valor que devuelve: boolean — true si numero está entre min y max (inclusive)
     * Versión: 1.0
     */
    public static boolean validarRango(int numero, int min, int max) {
        return numero >= min && numero <= max;
    }

    // ─── Formatear campo de nombre ───────────────────────────────────────────

    /*
     * Nombre: capitalizarNombre
     * Propósito: Retorna el nombre tal como fue ingresado por el usuario.
     *            La capitalización automática no es parte del currículo de COMP 2315.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: nombre — cadena de texto con el nombre ingresado
     * Valor que devuelve: String — el mismo texto recibido sin modificaciones
     * Versión: 1.0
     */
    public static String capitalizarNombre(String nombre) {
        return nombre;   // Devuelve el nombre tal como fue ingresado
    }
}
