/*
 * Nombre del archivo: ArchivoManager.java
 * Propósito: Gestiona todas las operaciones de lectura y escritura de expedientes
 *            médicos en el archivo "expedientes.txt". Usa FileReader + Scanner
 *            para leer y PrintWriter para escribir (Lección 15).
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            Diego L. Rodriguez Perez - A00636906
 *            Donovan Irizarry Figueroa - A00671799
 *            Carlo Ramos - A00642991
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// ArchivoManager.java
// Lectura con FileReader + Scanner, escritura con PrintWriter (Lección 15).
// Sin BufferedReader, BufferedWriter, FileWriter ni java.io.File.

import java.io.*;
import java.util.Scanner;

public class ArchivoManager {

    /* Ruta del archivo de expedientes — sin subcarpeta porque java.io.File
       no está en el currículo de COMP 2315 */
    private static String ARCHIVO_PRINCIPAL = "expedientes.txt";

    // ─── Guardar un expediente ────────────────────────────────────────────────

    /*
     * Nombre: guardarExpediente
     * Propósito: Agrega un nuevo paciente al sistema cargando la lista completa,
     *            añadiendo el nuevo registro y reescribiendo el archivo entero.
     *            (FileWriter en modo append no es parte del currículo — Lección 15.)
     * Precondiciones: pPaciente no debe ser nulo
     * Postcondiciones: El paciente queda guardado en el archivo
     * Argumentos: pPaciente — objeto Paciente con los datos a guardar
     * Valor que devuelve: boolean — true si se guardó correctamente
     * Versión: 1.0
     */
    public static boolean guardarExpediente(Paciente pPaciente) {
        Paciente[] lista = cargarTodosLosExpedientes();  // Carga los expedientes existentes
        int total = contarExpedientes(lista);            // Cuenta cuántos hay actualmente

        if (total < 100) {                               // Solo agrega si hay espacio en el arreglo
            lista[total] = pPaciente;
            total++;
        } else {                                         // Arreglo lleno — informa al usuario
            javax.swing.JOptionPane.showMessageDialog(null,
                "El sistema ha alcanzado el limite de 100 expedientes.\n" +
                "No se puede agregar un nuevo expediente.",
                "Capacidad Maxima Alcanzada",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;                                // No intenta reescribir el archivo
        }

        return reescribirTodosLosExpedientes(lista, total); // Reescribe el archivo completo
    }

    // ─── Cargar todos los expedientes ─────────────────────────────────────────

    /*
     * Nombre: cargarTodosLosExpedientes
     * Propósito: Lee el archivo línea por línea usando FileReader + Scanner y
     *            construye un arreglo de Paciente[100] con los registros leídos.
     *            Lee 13 líneas consecutivas por paciente (un campo por línea).
     * Precondiciones: Ninguna — si el archivo no existe retorna arreglo vacío
     * Postcondiciones: Retorna un arreglo con todos los pacientes del archivo
     * Argumentos: Ninguno
     * Valor que devuelve: Paciente[] — arreglo de 100 posiciones (slots no usados = null)
     * Versión: 1.0
     */
    public static Paciente[] cargarTodosLosExpedientes() {
        Paciente[] lista = new Paciente[100]; // Arreglo fijo de máximo 100 pacientes
        int total = 0;

        try {
            FileReader reader = new FileReader(ARCHIVO_PRINCIPAL); // Abre el archivo para lectura
            Scanner sc = new Scanner(reader);                      // Scanner lee línea a línea

            while (sc.hasNextLine() && total < 100) { // Lee mientras haya líneas y haya espacio
                String numExp   = sc.nextLine(); // Campo 1: número de expediente
                String nom      = sc.nextLine(); // Campo 2: nombre
                String ini      = sc.nextLine(); // Campo 3: inicial
                String ape      = sc.nextLine(); // Campo 4: apellidos
                String ss       = sc.nextLine(); // Campo 5: seguro social
                String fechaNac = sc.nextLine(); // Campo 6: fecha de nacimiento
                String sex      = sc.nextLine(); // Campo 7: sexo
                String dir      = sc.nextLine(); // Campo 8: dirección
                String plan     = sc.nextLine(); // Campo 9: plan médico
                String fVisita  = sc.nextLine(); // Campo 10: fecha de visita
                String diag     = sc.nextLine(); // Campo 11: diagnóstico
                String rec      = sc.nextLine(); // Campo 12: receta
                String fSig     = sc.nextLine(); // Campo 13: fecha siguiente visita

                lista[total] = new Paciente(nom, ini, ape, ss, numExp,
                                            fechaNac, sex, dir, plan,
                                            fVisita, diag, rec, fSig);
                total++;
            }

            sc.close(); // Cierra el scanner dentro del try (Lección 15 — sin try-with-resources)

        } catch (IOException e) {
            /* Si el archivo no existe aún (primera ejecución) simplemente
               retorna el arreglo vacío — no es un error crítico */
            System.out.println("Archivo no encontrado o error de lectura: " + e);
        }

        return lista; // Retorna el arreglo (slots sin usar son null)
    }

    // ─── Contar expedientes en el arreglo ────────────────────────────────────

    /*
     * Nombre: contarExpedientes
     * Propósito: Cuenta cuántos slots del arreglo están ocupados (no son null).
     * Precondiciones: lista no debe ser nulo
     * Postcondiciones: Ninguna
     * Argumentos: lista — arreglo de Paciente a contar
     * Valor que devuelve: int — número de expedientes no nulos en el arreglo
     * Versión: 1.0
     */
    public static int contarExpedientes(Paciente[] lista) {
        int count = 0;
        for (int i = 0; i < lista.length; i++) { // Recorre todo el arreglo
            if (lista[i] != null) {               // Cuenta solo los slots ocupados
                count++;
            }
        }
        return count;
    }

    // ─── Reescribir todos los expedientes ────────────────────────────────────

    /*
     * Nombre: reescribirTodosLosExpedientes
     * Propósito: Sobrescribe el archivo completo con los expedientes del arreglo.
     *            Usa PrintWriter (Lección 15) y escribe un campo por línea.
     * Precondiciones: lista no debe ser nulo; total indica cuántos escribir
     * Postcondiciones: El archivo contiene únicamente los expedientes del arreglo
     * Argumentos: lista — arreglo con los pacientes a guardar;
     *             total — número de pacientes válidos en el arreglo
     * Valor que devuelve: boolean — true si la escritura fue exitosa
     * Versión: 1.0
     */
    public static boolean reescribirTodosLosExpedientes(Paciente[] lista, int total) {
        try {
            PrintWriter output = new PrintWriter(ARCHIVO_PRINCIPAL); // Abre/crea el archivo

            for (int i = 0; i < total; i++) { // Escribe cada paciente campo por campo
                output.println(lista[i].getNumeroExpediente());  // Campo 1
                output.println(lista[i].getNombre());            // Campo 2
                output.println(lista[i].getInicial());           // Campo 3
                output.println(lista[i].getApellidos());         // Campo 4
                output.println(lista[i].getSeguroSocial());      // Campo 5
                output.println(lista[i].getFechaNacimiento());   // Campo 6
                output.println(lista[i].getSexo());              // Campo 7
                output.println(lista[i].getDireccion());         // Campo 8
                output.println(lista[i].getPlanMedico());        // Campo 9
                output.println(lista[i].getFechaVisita());       // Campo 10
                output.println(lista[i].getDiagnostico());       // Campo 11
                output.println(lista[i].getReceta());            // Campo 12
                output.println(lista[i].getFechaSiguienteVisita()); // Campo 13
            }

            output.close(); // Cierra el PrintWriter dentro del try (Lección 15)
            return true;    // Escritura exitosa

        } catch (IOException e) {
            System.out.println("Error al guardar expedientes: " + e);
            return false;   // Escritura fallida
        }
    }

    // ─── Verificar si existe un número de expediente ─────────────────────────

    /*
     * Nombre: existeExpediente
     * Propósito: Verifica si un número de expediente ya está en uso en el archivo
     *            para evitar duplicados al generar nuevos números.
     * Precondiciones: pNumeroExpediente no debe ser nulo
     * Postcondiciones: Ninguna — no modifica el archivo
     * Argumentos: pNumeroExpediente — número a buscar (ya en mayúsculas, ej: EXP-00001)
     * Valor que devuelve: boolean — true si el número ya existe
     * Versión: 1.0
     */
    public static boolean existeExpediente(String pNumeroExpediente) {
        Paciente[] lista = cargarTodosLosExpedientes(); // Carga todos los expedientes
        int total = contarExpedientes(lista);

        for (int i = 0; i < total; i++) { // Busca en todos los expedientes cargados
            if (lista[i].getNumeroExpediente().equals(pNumeroExpediente)) {
                return true; // El número ya está en uso
            }
        }
        return false; // El número está disponible
    }

    // ─── Obtener ruta del archivo ─────────────────────────────────────────────

    /*
     * Nombre: getRutaArchivo
     * Propósito: Retorna la ruta del archivo de expedientes.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: Ninguno
     * Valor que devuelve: String — ruta del archivo de expedientes
     * Versión: 1.0
     */
    public static String getRutaArchivo() {
        return ARCHIVO_PRINCIPAL;
    }
}
