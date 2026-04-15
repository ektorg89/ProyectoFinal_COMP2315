/*
 * Nombre del archivo: Paciente.java
 * Propósito: Representa el expediente médico completo de un paciente.
 *            Contiene todos los campos clínicos y personales, junto con
 *            métodos para serializar y deserializar el registro desde archivo.
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            [NOMBRE 2] - [NÚMERO ESTUDIANTE 2]
 *            [NOMBRE 3] - [NÚMERO ESTUDIANTE 3]
 *            [NOMBRE 4] - [NÚMERO ESTUDIANTE 4]
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// Paciente.java
// Representa el expediente médico completo de un paciente.
// Contiene todos los campos clínicos y personales, junto con
// métodos para serializar y deserializar el registro desde archivo.

public class Paciente { // Clase que representa el expediente médico completo de un paciente

    // ─── Campos del expediente ───────────────────────────────────────────────

    private String nombre;               // Primer nombre del paciente (ej: "Juan")
    private String inicial;              // Inicial del segundo nombre (ej: "A") — puede estar vacía
    private String apellidos;            // Apellidos completos del paciente (ej: "García López")
    private String seguroSocial;         // Número de seguro social en formato XXX-XX-XXXX
    private String numeroExpediente;     /* Identificador único del expediente,
                                            generado automáticamente en formato EXP-XXXXX */
    private String fechaNacimiento;      // Fecha de nacimiento del paciente en formato MM/DD/YYYY
    private String sexo;                 // Sexo del paciente: "M" para masculino, "F" para femenino
    private String direccion;            // Dirección postal completa (Calle, Ciudad, Estado, Zip)
    private String planMedico;           // Plan médico del paciente seleccionado de una lista predefinida
    private String fechaVisita;          // Fecha de la visita médica registrada en formato MM/DD/YYYY
    private String diagnostico;          // Descripción del diagnóstico médico (texto libre)
    private String receta;               // Medicamentos e instrucciones recetados al paciente (texto libre)
    private String fechaSiguienteVisita; // Fecha programada para la próxima cita en formato MM/DD/YYYY

    // ─── Constructor completo ────────────────────────────────────────────────

    /*
     * Nombre: Paciente
     * Propósito: Inicializa un objeto Paciente con todos los campos del expediente médico.
     * Precondiciones: Todos los parámetros deben estar validados por el llamador
     * Postcondiciones: El objeto Paciente queda completamente inicializado con los datos recibidos
     * Argumentos: nombre — primer nombre; inicial — inicial del segundo nombre;
     *             apellidos — apellidos completos; seguroSocial — número en formato XXX-XX-XXXX;
     *             numeroExpediente — identificador único; fechaNacimiento — fecha en MM/DD/YYYY;
     *             sexo — "M" o "F"; direccion — dirección postal; planMedico — plan seleccionado;
     *             fechaVisita — fecha de visita en MM/DD/YYYY; diagnostico — texto del diagnóstico;
     *             receta — medicamentos e instrucciones; fechaSiguienteVisita — próxima cita MM/DD/YYYY
     * Valor que devuelve: void (constructor)
     * Versión: 1.0
     */
    /* Recibe los 13 campos del expediente como parámetros
       y los asigna a los atributos del objeto */
    public Paciente(String nombre, String inicial, String apellidos,
                    String seguroSocial, String numeroExpediente,
                    String fechaNacimiento, String sexo, String direccion,
                    String planMedico, String fechaVisita, String diagnostico,
                    String receta, String fechaSiguienteVisita) {

        this.nombre              = nombre;              // Asigna el primer nombre al campo de instancia
        this.inicial             = inicial;             // Asigna la inicial del segundo nombre
        this.apellidos           = apellidos;           // Asigna los apellidos al campo de instancia
        this.seguroSocial        = seguroSocial;        // Asigna el número de seguro social
        this.numeroExpediente    = numeroExpediente;    // Asigna el número de expediente generado
        this.fechaNacimiento     = fechaNacimiento;     // Asigna la fecha de nacimiento
        this.sexo                = sexo;                // Asigna el sexo del paciente
        this.direccion           = direccion;           // Asigna la dirección del paciente
        this.planMedico          = planMedico;          // Asigna el plan médico seleccionado
        this.fechaVisita         = fechaVisita;         // Asigna la fecha de la visita
        this.diagnostico         = diagnostico;         // Asigna el diagnóstico médico
        this.receta              = receta;              // Asigna la receta médica
        this.fechaSiguienteVisita = fechaSiguienteVisita; // Asigna la fecha de la próxima cita
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    /*
     * Nombre: getNombre
     * Propósito: Obtiene el primer nombre del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — primer nombre del paciente
     * Versión: 1.0
     */
    public String getNombre()               { return nombre; }              // Retorna el primer nombre

    /*
     * Nombre: getInicial
     * Propósito: Obtiene la inicial del segundo nombre del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — inicial del segundo nombre (puede estar vacía)
     * Versión: 1.0
     */
    public String getInicial()              { return inicial; }             // Retorna la inicial del segundo nombre

    /*
     * Nombre: getApellidos
     * Propósito: Obtiene los apellidos completos del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — apellidos completos del paciente
     * Versión: 1.0
     */
    public String getApellidos()            { return apellidos; }           // Retorna los apellidos

    /*
     * Nombre: getSeguroSocial
     * Propósito: Obtiene el número de seguro social del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — número de seguro social en formato XXX-XX-XXXX
     * Versión: 1.0
     */
    public String getSeguroSocial()         { return seguroSocial; }        // Retorna el seguro social

    /*
     * Nombre: getNumeroExpediente
     * Propósito: Obtiene el número único de expediente del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — número de expediente en formato EXP-00000
     * Versión: 1.0
     */
    public String getNumeroExpediente()     { return numeroExpediente; }    // Retorna el número de expediente

    /*
     * Nombre: getFechaNacimiento
     * Propósito: Obtiene la fecha de nacimiento del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — fecha de nacimiento en formato MM/DD/YYYY
     * Versión: 1.0
     */
    public String getFechaNacimiento()      { return fechaNacimiento; }     // Retorna la fecha de nacimiento

    /*
     * Nombre: getSexo
     * Propósito: Obtiene el sexo del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — "M" para masculino o "F" para femenino
     * Versión: 1.0
     */
    public String getSexo()                 { return sexo; }                // Retorna el sexo

    /*
     * Nombre: getDireccion
     * Propósito: Obtiene la dirección postal del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — dirección postal completa del paciente
     * Versión: 1.0
     */
    public String getDireccion()            { return direccion; }           // Retorna la dirección

    /*
     * Nombre: getPlanMedico
     * Propósito: Obtiene el plan médico del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — nombre del plan médico seleccionado
     * Versión: 1.0
     */
    public String getPlanMedico()           { return planMedico; }          // Retorna el plan médico

    /*
     * Nombre: getFechaVisita
     * Propósito: Obtiene la fecha de la visita médica registrada.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — fecha de visita en formato MM/DD/YYYY
     * Versión: 1.0
     */
    public String getFechaVisita()          { return fechaVisita; }         // Retorna la fecha de visita

    /*
     * Nombre: getDiagnostico
     * Propósito: Obtiene el diagnóstico médico del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — descripción del diagnóstico médico
     * Versión: 1.0
     */
    public String getDiagnostico()          { return diagnostico; }         // Retorna el diagnóstico

    /*
     * Nombre: getReceta
     * Propósito: Obtiene la receta médica del paciente.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — medicamentos e instrucciones de la receta
     * Versión: 1.0
     */
    public String getReceta()               { return receta; }              // Retorna la receta

    /*
     * Nombre: getFechaSiguienteVisita
     * Propósito: Obtiene la fecha programada para la próxima visita médica.
     * Precondiciones: El objeto Paciente debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — fecha de la próxima cita en formato MM/DD/YYYY
     * Versión: 1.0
     */
    public String getFechaSiguienteVisita() { return fechaSiguienteVisita; } // Retorna la próxima cita

    // ─── Setters (usados en actualización de expediente) ────────────────────

    /*
     * Nombre: setNombre
     * Propósito: Actualiza el primer nombre del paciente.
     * Precondiciones: nombre no debe ser nulo ni vacío
     * Postcondiciones: El campo nombre queda actualizado con el nuevo valor
     * Argumentos: nombre — nuevo primer nombre del paciente
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setNombre(String nombre)                         { this.nombre = nombre; }                         // Actualiza el nombre

    /*
     * Nombre: setInicial
     * Propósito: Actualiza la inicial del segundo nombre del paciente.
     * Precondiciones: inicial puede ser vacío si no aplica
     * Postcondiciones: El campo inicial queda actualizado con el nuevo valor
     * Argumentos: inicial — nueva inicial del segundo nombre (puede estar vacía)
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setInicial(String inicial)                       { this.inicial = inicial; }                       // Actualiza la inicial

    /*
     * Nombre: setApellidos
     * Propósito: Actualiza los apellidos del paciente.
     * Precondiciones: apellidos no debe ser nulo ni vacío
     * Postcondiciones: El campo apellidos queda actualizado con el nuevo valor
     * Argumentos: apellidos — nuevos apellidos completos del paciente
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setApellidos(String apellidos)                   { this.apellidos = apellidos; }                   // Actualiza los apellidos

    /*
     * Nombre: setSeguroSocial
     * Propósito: Actualiza el número de seguro social del paciente.
     * Precondiciones: seguroSocial debe estar en formato XXX-XX-XXXX
     * Postcondiciones: El campo seguroSocial queda actualizado con el nuevo valor
     * Argumentos: seguroSocial — nuevo número de seguro social en formato XXX-XX-XXXX
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setSeguroSocial(String seguroSocial)             { this.seguroSocial = seguroSocial; }             // Actualiza el seguro social

    /*
     * Nombre: setFechaNacimiento
     * Propósito: Actualiza la fecha de nacimiento del paciente.
     * Precondiciones: fechaNacimiento debe estar en formato MM/DD/YYYY y ser una fecha válida
     * Postcondiciones: El campo fechaNacimiento queda actualizado con el nuevo valor
     * Argumentos: fechaNacimiento — nueva fecha de nacimiento en formato MM/DD/YYYY
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setFechaNacimiento(String fechaNacimiento)       { this.fechaNacimiento = fechaNacimiento; }       // Actualiza la fecha de nacimiento

    /*
     * Nombre: setSexo
     * Propósito: Actualiza el sexo del paciente.
     * Precondiciones: sexo debe ser "M" o "F"
     * Postcondiciones: El campo sexo queda actualizado con el nuevo valor
     * Argumentos: sexo — nuevo sexo del paciente ("M" o "F")
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setSexo(String sexo)                             { this.sexo = sexo; }                             // Actualiza el sexo

    /*
     * Nombre: setDireccion
     * Propósito: Actualiza la dirección postal del paciente.
     * Precondiciones: direccion no debe ser nula ni vacía
     * Postcondiciones: El campo direccion queda actualizado con el nuevo valor
     * Argumentos: direccion — nueva dirección postal del paciente
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setDireccion(String direccion)                   { this.direccion = direccion; }                   // Actualiza la dirección

    /*
     * Nombre: setPlanMedico
     * Propósito: Actualiza el plan médico del paciente.
     * Precondiciones: planMedico debe ser una de las opciones válidas del sistema
     * Postcondiciones: El campo planMedico queda actualizado con el nuevo valor
     * Argumentos: planMedico — nuevo plan médico seleccionado
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setPlanMedico(String planMedico)                 { this.planMedico = planMedico; }                 // Actualiza el plan médico

    /*
     * Nombre: setFechaVisita
     * Propósito: Actualiza la fecha de la visita médica del paciente.
     * Precondiciones: fechaVisita debe estar en formato MM/DD/YYYY y ser una fecha válida
     * Postcondiciones: El campo fechaVisita queda actualizado con el nuevo valor
     * Argumentos: fechaVisita — nueva fecha de visita en formato MM/DD/YYYY
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setFechaVisita(String fechaVisita)               { this.fechaVisita = fechaVisita; }               // Actualiza la fecha de visita

    /*
     * Nombre: setDiagnostico
     * Propósito: Actualiza el diagnóstico médico del paciente.
     * Precondiciones: diagnostico no debe ser nulo ni vacío
     * Postcondiciones: El campo diagnostico queda actualizado con el nuevo valor
     * Argumentos: diagnostico — nuevo diagnóstico médico
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setDiagnostico(String diagnostico)               { this.diagnostico = diagnostico; }               // Actualiza el diagnóstico

    /*
     * Nombre: setReceta
     * Propósito: Actualiza la receta médica del paciente.
     * Precondiciones: Ninguna — la receta puede estar vacía
     * Postcondiciones: El campo receta queda actualizado con el nuevo valor
     * Argumentos: receta — nueva receta (medicamentos e instrucciones)
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setReceta(String receta)                         { this.receta = receta; }                         // Actualiza la receta

    /*
     * Nombre: setFechaSiguienteVisita
     * Propósito: Actualiza la fecha programada para la próxima visita del paciente.
     * Precondiciones: f debe estar en formato MM/DD/YYYY y ser una fecha válida
     * Postcondiciones: El campo fechaSiguienteVisita queda actualizado con el nuevo valor
     * Argumentos: f — nueva fecha de siguiente visita en formato MM/DD/YYYY
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void setFechaSiguienteVisita(String f)                { this.fechaSiguienteVisita = f; }                // Actualiza la próxima cita

    // ─── Nombre completo formateado ──────────────────────────────────────────

    /*
     * Nombre: getNombreCompleto
     * Propósito: Retorna el nombre completo del paciente en formato "Nombre I. Apellidos".
     * Precondiciones: Los campos nombre y apellidos deben estar inicializados
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — nombre completo formateado (con o sin inicial)
     * Versión: 1.0
     */
    public String getNombreCompleto() { // Retorna el nombre en formato "Nombre I. Apellidos"
        if (inicial != null && !inicial.trim().isEmpty()) { // Si existe inicial, la incluye con punto
            return nombre + " " + inicial.toUpperCase() + ". " + apellidos;
        }
        return nombre + " " + apellidos; // Si no hay inicial, retorna solo nombre y apellidos
    }

    // ─── Representación en texto para guardar en archivo ────────────────────

    /*
     * Nombre: toFileString
     * Propósito: Convierte todos los campos del expediente en una línea de texto
     *            separada por "|" para guardar en el archivo de texto.
     * Precondiciones: Todos los campos del objeto deben estar inicializados
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — línea de texto con todos los campos separados por "|"
     * Versión: 1.0
     */
    public String toFileString() { /* Convierte todos los campos en una sola línea
                                      separada por "|" para guardar en el archivo de texto */
        return numeroExpediente + "|" +
               nombre          + "|" +
               inicial         + "|" +
               apellidos       + "|" +
               seguroSocial    + "|" +
               fechaNacimiento + "|" +
               sexo            + "|" +
               direccion       + "|" +
               planMedico      + "|" +
               fechaVisita     + "|" +
               diagnostico     + "|" +
               receta          + "|" +
               fechaSiguienteVisita;
    }

    /*
     * Nombre: fromFileString
     * Propósito: Crea un objeto Paciente a partir de una línea de texto del archivo
     *            que contiene todos los campos separados por "|".
     * Precondiciones: linea debe ser una cadena con al menos 13 campos separados por "|"
     * Postcondiciones: Retorna un Paciente inicializado, o null si la línea es inválida
     * Argumentos: linea — cadena de texto con los campos del expediente separados por "|"
     * Valor que devuelve: Paciente — objeto construido con los datos de la línea, o null si faltan campos
     * Versión: 1.0
     */
    public static Paciente fromFileString(String linea) { // Crea un Paciente a partir de una línea del archivo
        String[] partes = linea.split("\\|", -1); // Divide la línea usando "|" como separador
        if (partes.length < 13) return null; // Si faltan campos, retorna null para evitar errores

        return new Paciente( // Construye y retorna el objeto con los datos extraídos
            partes[1],  // nombre
            partes[2],  // inicial
            partes[3],  // apellidos
            partes[4],  // seguroSocial
            partes[0],  // numeroExpediente
            partes[5],  // fechaNacimiento
            partes[6],  // sexo
            partes[7],  // direccion
            partes[8],  // planMedico
            partes[9],  // fechaVisita
            partes[10], // diagnostico
            partes[11], // receta
            partes[12]  // fechaSiguienteVisita
        );
    }

    // ─── toString para mostrar en pantalla ──────────────────────────────────

    /*
     * Nombre: toString
     * Propósito: Retorna una representación visual completa del expediente
     *            formateada con bordes para mostrar en JOptionPane.
     * Precondiciones: Todos los campos del objeto deben estar inicializados
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — expediente completo formateado visualmente
     * Versión: 1.0
     */
    public String toString() { // Retorna una representación visual del expediente para mostrar en JOptionPane
        return "╔══════════════════════════════════════════════════════╗\n" +
               "  EXPEDIENTE MÉDICO\n" +
               "══════════════════════════════════════════════════════\n" +
               "  No. Expediente   : " + numeroExpediente + "\n" +
               "  Paciente         : " + getNombreCompleto() + "\n" +
               "  Seguro Social    : " + seguroSocial + "\n" +
               "  Fecha Nacimiento : " + fechaNacimiento + "\n" +
               "  Sexo             : " + sexo + "\n" +
               "  Dirección        : " + direccion + "\n" +
               "  Plan Médico      : " + planMedico + "\n" +
               "──────────────────────────────────────────────────────\n" +
               "  Fecha de Visita  : " + fechaVisita + "\n" +
               "  Diagnóstico      : " + diagnostico + "\n" +
               "  Receta           : " + receta + "\n" +
               "  Próxima Visita   : " + fechaSiguienteVisita + "\n" +
               "╚══════════════════════════════════════════════════════╝";
    }
}
