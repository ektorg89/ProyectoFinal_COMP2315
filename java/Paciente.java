/*
 * Nombre del archivo: Paciente.java
 * Propósito: Representa el expediente médico completo de un paciente.
 *            Contiene todos los campos clínicos y personales, junto con
 *            métodos para acceder, modificar y serializar los datos del expediente.
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            Diego L. Rodriguez Perez - A00636906
 *            Donovan Irizarry Figueroa - A00671799
 *            Carlo Ramos - A00642991
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// Paciente.java
// Representa el expediente médico completo de un paciente.
// Usa únicamente conceptos del currículo de COMP 2315.

public class Paciente {

    // ─── Campos del expediente ───────────────────────────────────────────────

    private String nombre;               // Primer nombre del paciente
    private String inicial;              // Inicial del segundo nombre (puede ser "")
    private String apellidos;            // Apellidos completos
    private String seguroSocial;         // Formato XXX-XX-XXXX
    private String numeroExpediente;     // Formato EXP-00000
    private String fechaNacimiento;      // Formato MM/DD/YYYY
    private String sexo;                 // "M" o "F"
    private String direccion;            // Dirección postal completa
    private String planMedico;           // Plan médico seleccionado
    private String fechaVisita;          // Formato MM/DD/YYYY
    private String diagnostico;          // Texto del diagnóstico
    private String receta;               // Medicamentos e instrucciones
    private String fechaSiguienteVisita; // Formato MM/DD/YYYY

    // ─── Constructor completo ────────────────────────────────────────────────

    /*
     * Nombre: Paciente
     * Propósito: Inicializa un objeto Paciente con los 13 campos del expediente.
     *            Los parámetros usan prefijo "p" para evitar la palabra clave this.
     * Precondiciones: Todos los parámetros deben estar validados por el llamador
     * Postcondiciones: El objeto Paciente queda completamente inicializado
     * Argumentos: pNombre, pInicial, pApellidos, pSeguroSocial, pNumeroExpediente,
     *             pFechaNacimiento, pSexo, pDireccion, pPlanMedico, pFechaVisita,
     *             pDiagnostico, pReceta, pFechaSiguienteVisita
     * Valor que devuelve: void (constructor)
     * Versión: 1.0
     */
    public Paciente(String pNombre, String pInicial, String pApellidos,
                    String pSeguroSocial, String pNumeroExpediente,
                    String pFechaNacimiento, String pSexo, String pDireccion,
                    String pPlanMedico, String pFechaVisita, String pDiagnostico,
                    String pReceta, String pFechaSiguienteVisita) {

        nombre               = pNombre;
        inicial              = pInicial;
        apellidos            = pApellidos;
        seguroSocial         = pSeguroSocial;
        numeroExpediente     = pNumeroExpediente;
        fechaNacimiento      = pFechaNacimiento;
        sexo                 = pSexo;
        direccion            = pDireccion;
        planMedico           = pPlanMedico;
        fechaVisita          = pFechaVisita;
        diagnostico          = pDiagnostico;
        receta               = pReceta;
        fechaSiguienteVisita = pFechaSiguienteVisita;
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    /*
     * Nombre: get[Campo]
     * Propósito: Retornar el valor de cada campo del expediente.
     * Precondiciones: El objeto debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — valor del campo correspondiente
     * Versión: 1.0
     */
    public String getNombre()               { return nombre; }
    public String getInicial()              { return inicial; }
    public String getApellidos()            { return apellidos; }
    public String getSeguroSocial()         { return seguroSocial; }
    public String getNumeroExpediente()     { return numeroExpediente; }
    public String getFechaNacimiento()      { return fechaNacimiento; }
    public String getSexo()                 { return sexo; }
    public String getDireccion()            { return direccion; }
    public String getPlanMedico()           { return planMedico; }
    public String getFechaVisita()          { return fechaVisita; }
    public String getDiagnostico()          { return diagnostico; }
    public String getReceta()               { return receta; }
    public String getFechaSiguienteVisita() { return fechaSiguienteVisita; }

    // ─── Setters (usados en actualización de expediente) ────────────────────

    /*
     * Nombre: set[Campo]
     * Propósito: Actualizar el valor de cada campo del expediente.
     * Precondiciones: El parámetro debe contener un valor válido
     * Postcondiciones: El campo queda actualizado con el nuevo valor
     * Argumentos: p[Campo] — nuevo valor para el campo
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setNombre(String pNombre)                   { nombre               = pNombre; }
    public void setInicial(String pInicial)                 { inicial              = pInicial; }
    public void setApellidos(String pApellidos)             { apellidos            = pApellidos; }
    public void setSeguroSocial(String pSeguroSocial)       { seguroSocial         = pSeguroSocial; }
    public void setFechaNacimiento(String pFechaNacimiento) { fechaNacimiento      = pFechaNacimiento; }
    public void setSexo(String pSexo)                       { sexo                 = pSexo; }
    public void setDireccion(String pDireccion)             { direccion            = pDireccion; }
    public void setPlanMedico(String pPlanMedico)           { planMedico           = pPlanMedico; }
    public void setFechaVisita(String pFechaVisita)         { fechaVisita          = pFechaVisita; }
    public void setDiagnostico(String pDiagnostico)         { diagnostico          = pDiagnostico; }
    public void setReceta(String pReceta)                   { receta               = pReceta; }
    public void setFechaSiguienteVisita(String pF)          { fechaSiguienteVisita = pF; }

    // ─── Nombre completo formateado ──────────────────────────────────────────

    /*
     * Nombre: getNombreCompleto
     * Propósito: Retorna el nombre completo. Si hay inicial la incluye con punto.
     * Precondiciones: nombre y apellidos deben estar inicializados
     * Postcondiciones: Ninguna
     * Argumentos: Ninguno
     * Valor que devuelve: String — nombre completo formateado
     * Versión: 1.0
     */
    public String getNombreCompleto() {
        if (!inicial.equals("")) {                     // Si hay inicial, la incluye con punto
            return nombre + " " + inicial + ". " + apellidos;
        }
        return nombre + " " + apellidos;               // Sin inicial
    }

    // ─── Serialización para archivo ──────────────────────────────────────────

    /*
     * Nombre: toFileString
     * Propósito: Convierte todos los campos en texto separado por saltos de línea
     *            para ser escrito en el archivo usando PrintWriter.println().
     * Precondiciones: Todos los campos deben estar inicializados
     * Postcondiciones: Ninguna
     * Argumentos: Ninguno
     * Valor que devuelve: String — 13 campos separados por "\n"
     * Versión: 1.0
     */
    public String toFileString() {
        return numeroExpediente     + "\n" +
               nombre               + "\n" +
               inicial              + "\n" +
               apellidos            + "\n" +
               seguroSocial         + "\n" +
               fechaNacimiento      + "\n" +
               sexo                 + "\n" +
               direccion            + "\n" +
               planMedico           + "\n" +
               fechaVisita          + "\n" +
               diagnostico          + "\n" +
               receta               + "\n" +
               fechaSiguienteVisita;
    }

    // ─── toString para mostrar en pantalla ──────────────────────────────────

    /*
     * Nombre: toString
     * Propósito: Retorna el expediente formateado visualmente para JOptionPane.
     *            Las líneas separadoras se construyen con un bucle for.
     * Precondiciones: Todos los campos deben estar inicializados
     * Postcondiciones: Ninguna
     * Argumentos: Ninguno
     * Valor que devuelve: String — expediente completo formateado
     * Versión: 1.0
     */
    public String toString() {
        /* Construye las líneas separadoras con un bucle for (sin .repeat()) */
        String lineaDoble  = "";
        String lineaSimple = "";
        for (int i = 0; i < 54; i++) {
            lineaDoble  = lineaDoble  + "=";
            lineaSimple = lineaSimple + "-";
        }

        return lineaDoble  + "\n" +
               "  EXPEDIENTE MEDICO\n"                              +
               lineaDoble  + "\n" +
               "  No. Expediente   : " + numeroExpediente          + "\n" +
               "  Paciente         : " + getNombreCompleto()       + "\n" +
               "  Seguro Social    : " + seguroSocial              + "\n" +
               "  Fecha Nacimiento : " + fechaNacimiento           + "\n" +
               "  Sexo             : " + sexo                      + "\n" +
               "  Direccion        : " + direccion                 + "\n" +
               "  Plan Medico      : " + planMedico                + "\n" +
               lineaSimple + "\n" +
               "  Fecha de Visita  : " + fechaVisita               + "\n" +
               "  Diagnostico      : " + diagnostico               + "\n" +
               "  Receta           : " + receta                    + "\n" +
               "  Proxima Visita   : " + fechaSiguienteVisita      + "\n" +
               lineaDoble;
    }
}
