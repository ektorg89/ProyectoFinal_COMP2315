// Paciente.java
// Representa el expediente médico completo de un paciente.
// Contiene todos los campos clínicos y personales, junto con
// métodos para serializar y deserializar el registro desde archivo.

import java.io.Serializable; // Permite que los objetos Paciente puedan ser serializados si es necesario

public class Paciente implements Serializable { // La clase implementa Serializable para compatibilidad con flujos de E/S

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

    public String getNombre()               { return nombre; }              // Retorna el primer nombre
    public String getInicial()              { return inicial; }             // Retorna la inicial del segundo nombre
    public String getApellidos()            { return apellidos; }           // Retorna los apellidos
    public String getSeguroSocial()         { return seguroSocial; }        // Retorna el seguro social
    public String getNumeroExpediente()     { return numeroExpediente; }    // Retorna el número de expediente
    public String getFechaNacimiento()      { return fechaNacimiento; }     // Retorna la fecha de nacimiento
    public String getSexo()                 { return sexo; }                // Retorna el sexo
    public String getDireccion()            { return direccion; }           // Retorna la dirección
    public String getPlanMedico()           { return planMedico; }          // Retorna el plan médico
    public String getFechaVisita()          { return fechaVisita; }         // Retorna la fecha de visita
    public String getDiagnostico()          { return diagnostico; }         // Retorna el diagnóstico
    public String getReceta()               { return receta; }              // Retorna la receta
    public String getFechaSiguienteVisita() { return fechaSiguienteVisita; } // Retorna la próxima cita

    // ─── Setters (usados en actualización de expediente) ────────────────────

    public void setNombre(String nombre)                         { this.nombre = nombre; }                         // Actualiza el nombre
    public void setInicial(String inicial)                       { this.inicial = inicial; }                       // Actualiza la inicial
    public void setApellidos(String apellidos)                   { this.apellidos = apellidos; }                   // Actualiza los apellidos
    public void setSeguroSocial(String seguroSocial)             { this.seguroSocial = seguroSocial; }             // Actualiza el seguro social
    public void setFechaNacimiento(String fechaNacimiento)       { this.fechaNacimiento = fechaNacimiento; }       // Actualiza la fecha de nacimiento
    public void setSexo(String sexo)                             { this.sexo = sexo; }                             // Actualiza el sexo
    public void setDireccion(String direccion)                   { this.direccion = direccion; }                   // Actualiza la dirección
    public void setPlanMedico(String planMedico)                 { this.planMedico = planMedico; }                 // Actualiza el plan médico
    public void setFechaVisita(String fechaVisita)               { this.fechaVisita = fechaVisita; }               // Actualiza la fecha de visita
    public void setDiagnostico(String diagnostico)               { this.diagnostico = diagnostico; }               // Actualiza el diagnóstico
    public void setReceta(String receta)                         { this.receta = receta; }                         // Actualiza la receta
    public void setFechaSiguienteVisita(String f)                { this.fechaSiguienteVisita = f; }                // Actualiza la próxima cita

    // ─── Nombre completo formateado ──────────────────────────────────────────

    public String getNombreCompleto() { // Retorna el nombre en formato "Nombre I. Apellidos"
        if (inicial != null && !inicial.trim().isEmpty()) { // Si existe inicial, la incluye con punto
            return nombre + " " + inicial.toUpperCase() + ". " + apellidos;
        }
        return nombre + " " + apellidos; // Si no hay inicial, retorna solo nombre y apellidos
    }

    // ─── Representación en texto para guardar en archivo ────────────────────

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

    @Override
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
