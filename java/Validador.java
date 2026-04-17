/*
 * Nombre del archivo: Validador.java
 * Propósito: Contiene métodos estáticos para validar los campos del expediente
 *            médico: seguro social, fechas, sexo, texto libre, plan médico y rangos.
 *            Versión simplificada — solo usa conceptos de COMP 2315.
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            Diego L. Rodriguez Perez - A00636906
 *            Donovan Irizarry Figueroa - A00671799
 *            Carlos Ramos - A00642991
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// Validador.java
// Métodos estáticos de validación para los campos del expediente médico.
// No usa expresiones regulares ni SimpleDateFormat (no son de COMP 2315).

public class Validador {

    // ─── Validación de Seguro Social ─────────────────────────────────────────

    /*
     * Nombre: validarSeguroSocial
     * Propósito: Verifica que el seguro social no sea nulo ni vacío.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: ss — cadena de texto con el seguro social a validar
     * Valor que devuelve: boolean — true si no es nulo ni vacío
     * Versión: 1.0
     */
    public static boolean validarSeguroSocial(String ss) {
        return ss != null && !ss.equals("");  // Acepta cualquier texto no vacío
    }

    // ─── Validación de número de expediente ──────────────────────────────────

    /*
     * Nombre: validarNumeroExpediente
     * Propósito: Verifica que el número de expediente no sea nulo ni vacío.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: exp — cadena de texto con el número de expediente
     * Valor que devuelve: boolean — true si no es nulo ni vacío
     * Versión: 1.0
     */
    public static boolean validarNumeroExpediente(String exp) {
        return exp != null && !exp.equals("");
    }

    // ─── Validación de fecha ─────────────────────────────────────────────────

    /*
     * Nombre: validarFecha
     * Propósito: Verifica que la fecha no sea nula ni vacía.
     *            El usuario es responsable de respetar el formato MM/DD/YYYY.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: fecha — cadena de texto con la fecha a validar
     * Valor que devuelve: boolean — true si no es nula ni vacía
     * Versión: 1.0
     */
    public static boolean validarFecha(String fecha) {
        return fecha != null && !fecha.equals("");
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
