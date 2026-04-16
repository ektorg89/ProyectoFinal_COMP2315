// Validador.java
// Contiene métodos estáticos para validar los formatos de todos
// los campos del expediente médico: seguro social, fechas, sexo,
// texto libre, plan médico y rangos numéricos.

import java.util.regex.Pattern; // Importa la clase Pattern para compilar expresiones regulares
import java.util.regex.Matcher; // Importa Matcher para aplicar el patrón a un texto específico
import java.text.SimpleDateFormat; // Importa SimpleDateFormat para validar que la fecha sea real y no solo de formato correcto

public class Validador { // Clase utilitaria con métodos estáticos — no se instancia, solo se llama directamente

    // ─── Expresiones regulares (regex) ───────────────────────────────────────

    private static Pattern REGEX_SS =
        Pattern.compile("^\\d{3}-\\d{2}-\\d{4}$");

    private static Pattern REGEX_EXP =
        Pattern.compile("^EXP-\\d{5}$");

    private static Pattern REGEX_FECHA =
        Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])/\\d{4}$");

    // ─── Validación de Seguro Social ─────────────────────────────────────────

    public static boolean validarSeguroSocial(String ss) { // Recibe el seguro social como texto
        if (ss == null || ss.trim().isEmpty()) return false; // Si está vacío o nulo, retorna false inmediatamente
        Matcher m = REGEX_SS.matcher(ss.trim()); // Aplica el patrón al texto recibido
        return m.matches(); // Retorna true solo si el texto coincide exactamente con el patrón
    }

    // ─── Validación de número de expediente ──────────────────────────────────

    public static boolean validarNumeroExpediente(String exp) { // Recibe el número de expediente como texto
        if (exp == null || exp.trim().isEmpty()) return false; // Si está vacío o nulo, retorna false
        Matcher m = REGEX_EXP.matcher(exp.trim().toUpperCase()); // Convierte a mayúsculas antes de comparar
        return m.matches(); // Retorna true si cumple el formato EXP-00000
    }

    // ─── Validación de fecha ─────────────────────────────────────────────────

    public static boolean validarFecha(String fecha) { // Recibe la fecha como texto en formato MM/DD/YYYY
        if (fecha == null || fecha.trim().isEmpty()) return false; // Si está vacía, retorna false

        Matcher m = REGEX_FECHA.matcher(fecha.trim()); // Primero verifica el formato visual con regex
        if (!m.matches()) return false; // Si el formato no coincide, ni siquiera intenta parsear

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // Define el formato esperado
            sdf.setLenient(false); // Modo estricto: no acepta fechas inválidas como 02/30/2024
            sdf.parse(fecha.trim()); // Intenta convertir el texto a fecha — lanza excepción si falla
            return true; // Si no lanzó excepción, la fecha es válida
        } catch (Exception e) {
            return false; // Si la fecha no es real (ej: 13/45/2020), retorna false
        }
    }

    // ─── Validación de sexo ──────────────────────────────────────────────────

    public static boolean validarSexo(String sexo) { // Recibe el sexo ingresado por el usuario
        if (sexo == null || sexo.trim().isEmpty()) return false; // Si está vacío, no es válido
        String s = sexo.trim().toUpperCase(); // Elimina espacios y convierte a mayúsculas para comparar
        return s.equals("M") || s.equals("F"); // Solo acepta exactamente "M" o "F"
    }

    // ─── Validación de texto no vacío ────────────────────────────────────────

    public static boolean validarNoVacio(String texto) { // Verifica que el texto tenga contenido
        return texto != null && !texto.trim().isEmpty(); // Retorna true solo si no es nulo ni solo espacios
    }

    // ─── Validación de plan médico ───────────────────────────────────────────

    public static boolean validarOpcionPlanMedico(int opcion) { // Verifica que la opción esté en rango válido
        return opcion >= 1 && opcion <= 6; // Solo acepta valores del 1 al 6 (los 6 planes disponibles)
    }

    // ─── Validación de opción numérica en rango ──────────────────────────────

    public static boolean validarRango(int numero, int min, int max) { // Verifica que el número esté entre min y max
        return numero >= min && numero <= max; // Retorna true si el número está dentro del rango permitido
    }

    // ─── Formatear campo de nombre ───────────────────────────────────────────

    public static String capitalizarNombre(String nombre) { // Recibe un nombre en cualquier formato
        if (nombre == null || nombre.trim().isEmpty()) return ""; // Si está vacío, retorna cadena vacía

        String[] palabras = nombre.trim().toLowerCase().split("\\s+"); // Convierte a minúsculas y divide por espacios
        StringBuilder sb = new StringBuilder(); // Objeto para construir el resultado eficientemente

        for (String palabra : palabras) { // Recorre cada palabra del nombre
            if (palabra.length() > 0) { // Verifica que la palabra no esté vacía
                sb.append(Character.toUpperCase(palabra.charAt(0))); // Capitaliza la primera letra
                if (palabra.length() > 1) { // Si la palabra tiene más de una letra
                    sb.append(palabra.substring(1)); // Agrega el resto de la palabra en minúsculas
                }
                sb.append(" "); // Agrega un espacio entre palabras
            }
        }
        return sb.toString().trim(); // Elimina el espacio final y retorna el nombre formateado
    }
}
