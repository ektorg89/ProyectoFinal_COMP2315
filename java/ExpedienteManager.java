/*
 * Nombre del archivo: ExpedienteManager.java
 * Propósito: Contiene la lógica CRUD para manejar expedientes médicos:
 *            crear, buscar por número, buscar por fecha, actualizar y listar.
 *            Usa arreglo fijo Paciente[100] en lugar de ArrayList (COMP 2315).
 * Autor(es): Ektor M. Gonzalez - A00617167
 *            [NOMBRE 2] - [NÚMERO ESTUDIANTE 2]
 *            [NOMBRE 3] - [NÚMERO ESTUDIANTE 3]
 *            [NOMBRE 4] - [NÚMERO ESTUDIANTE 4]
 * Curso: COMP 2315 - Programación Estructurada
 * Profesor: Dr. Edgardo Vargas Moya
 * Fecha de creación: 04/14/2026
 * Versión: 1.0
 */

// ExpedienteManager.java
// CRUD de expedientes médicos usando únicamente conceptos de COMP 2315:
// arreglos fijos, for indexado, while con bandera booleana, switch.

import javax.swing.JOptionPane;

public class ExpedienteManager {

    // ─── Arreglo y contador de pacientes ─────────────────────────────────────

    private Paciente[] listaPacientes = new Paciente[100]; /* Arreglo fijo de máximo
                                                               100 expedientes en memoria */
    private int totalPacientes = 0;        // Cantidad real de expedientes cargados
    private int contadorExpediente = 1;    // Contador para generar números únicos

    // ─── Constructor ──────────────────────────────────────────────────────────

    /*
     * Nombre: ExpedienteManager
     * Propósito: Carga los expedientes existentes del archivo al iniciar el sistema
     *            y configura el contador para el siguiente número disponible.
     * Precondiciones: Ninguna
     * Postcondiciones: listaPacientes y totalPacientes reflejan el estado del archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void (constructor)
     * Versión: 1.0
     */
    public ExpedienteManager() {
        listaPacientes = ArchivoManager.cargarTodosLosExpedientes(); // Lee el archivo
        totalPacientes = ArchivoManager.contarExpedientes(listaPacientes); // Cuenta registros
        contadorExpediente = totalPacientes + 1; // El siguiente número va después del último
    }

    // ─── Generar número de expediente ─────────────────────────────────────────

    /*
     * Nombre: generarNumeroExpediente
     * Propósito: Genera un número único en formato EXP-00000. Usa if/else para
     *            construir el cero-relleno sin String.format() (no está en COMP 2315).
     * Precondiciones: contadorExpediente debe estar inicializado
     * Postcondiciones: contadorExpediente queda incrementado
     * Argumentos: Ninguno
     * Valor que devuelve: String — número único en formato EXP-00000
     * Versión: 1.0
     */
    private String generarNumeroExpediente() {
        String numero   = "";     // Número candidato a verificar
        boolean ocupado = true;   // Bandera de control del bucle

        while (ocupado) {
            /* Construye el cero-relleno con if/else (sin String.format ni .length()) */
            String num;
            if      (contadorExpediente < 10)    num = "0000" + contadorExpediente;
            else if (contadorExpediente < 100)   num = "000"  + contadorExpediente;
            else if (contadorExpediente < 1000)  num = "00"   + contadorExpediente;
            else if (contadorExpediente < 10000) num = "0"    + contadorExpediente;
            else                                 num = ""      + contadorExpediente;

            numero = "EXP-" + num;
            contadorExpediente++;

            if (!ArchivoManager.existeExpediente(numero)) {
                ocupado = false; // El número está disponible — sale del bucle
            }
        }

        return numero;
    }

    // ─── Crear nuevo expediente ───────────────────────────────────────────────

    /*
     * Nombre: crearExpediente
     * Propósito: Guía al usuario para ingresar todos los datos del nuevo expediente,
     *            valida cada campo y guarda el registro en memoria y en archivo.
     * Precondiciones: totalPacientes debe ser menor a 100
     * Postcondiciones: El nuevo expediente queda en listaPacientes y en el archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void crearExpediente() {

        // ── Nombre (do-while — Lección 10) ──────────────────────────────────
        String nombre = "";
        do {
            nombre = JOptionPane.showInputDialog(null,
                "Ingrese el PRIMER NOMBRE del paciente:",
                "Nuevo Expediente - Nombre", JOptionPane.PLAIN_MESSAGE);
            if (nombre == null) { mostrarCancelado(); return; }
            nombre = Validador.capitalizarNombre(nombre); // Retorna el nombre tal como está
            if (!Validador.validarNoVacio(nombre)) {
                JOptionPane.showMessageDialog(null,
                    "El nombre no puede estar vacio.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!Validador.validarNoVacio(nombre));

        // ── Inicial (opcional — se guarda tal como la escribe el usuario) ────
        String inicial = JOptionPane.showInputDialog(null,
            "Ingrese la INICIAL del segundo nombre (deje vacio si no aplica):",
            "Nuevo Expediente - Inicial", JOptionPane.PLAIN_MESSAGE);
        if (inicial == null) { mostrarCancelado(); return; }
        /* La inicial se guarda directamente — el usuario debe ingresar
           una sola letra mayuscula o dejar el campo vacio */

        // ── Apellidos ────────────────────────────────────────────────────────
        String apellidos = "";
        while (!Validador.validarNoVacio(apellidos)) {
            apellidos = JOptionPane.showInputDialog(null,
                "Ingrese los APELLIDOS del paciente:",
                "Nuevo Expediente - Apellidos", JOptionPane.PLAIN_MESSAGE);
            if (apellidos == null) { mostrarCancelado(); return; }
            apellidos = Validador.capitalizarNombre(apellidos);
            if (!Validador.validarNoVacio(apellidos)) {
                JOptionPane.showMessageDialog(null,
                    "Los apellidos no pueden estar vacios.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Seguro Social ────────────────────────────────────────────────────
        String ss = "";
        while (!Validador.validarSeguroSocial(ss)) {
            ss = JOptionPane.showInputDialog(null,
                "Ingrese el SEGURO SOCIAL (formato: XXX-XX-XXXX):",
                "Nuevo Expediente - Seguro Social", JOptionPane.PLAIN_MESSAGE);
            if (ss == null) { mostrarCancelado(); return; }
            if (!Validador.validarSeguroSocial(ss)) {
                JOptionPane.showMessageDialog(null,
                    "El seguro social no puede estar vacio.\n" +
                    "Use el formato: XXX-XX-XXXX",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Fecha de nacimiento ──────────────────────────────────────────────
        String fechaNac = "";
        while (!Validador.validarFecha(fechaNac)) {
            fechaNac = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE NACIMIENTO (formato: MM/DD/YYYY):",
                "Nuevo Expediente - Fecha Nacimiento", JOptionPane.PLAIN_MESSAGE);
            if (fechaNac == null) { mostrarCancelado(); return; }
            if (!Validador.validarFecha(fechaNac)) {
                JOptionPane.showMessageDialog(null,
                    "La fecha no puede estar vacia.\n" +
                    "Use el formato: MM/DD/YYYY",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Sexo ─────────────────────────────────────────────────────────────
        String sexo = "";
        while (!Validador.validarSexo(sexo)) {
            sexo = JOptionPane.showInputDialog(null,
                "Ingrese el SEXO del paciente (M = Masculino / F = Femenino):",
                "Nuevo Expediente - Sexo", JOptionPane.PLAIN_MESSAGE);
            if (sexo == null) { mostrarCancelado(); return; }
            if (!Validador.validarSexo(sexo)) {
                JOptionPane.showMessageDialog(null,
                    "Solo se acepta exactamente M o F (en mayuscula).",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        /* El sexo se guarda tal como fue ingresado — Validador.validarSexo()
           ya garantiza que es "M" o "F" exacto */

        // ── Dirección ────────────────────────────────────────────────────────
        String direccion = "";
        while (!Validador.validarNoVacio(direccion)) {
            direccion = JOptionPane.showInputDialog(null,
                "Ingrese la DIRECCION (Calle, Ciudad, Estado, Zip):",
                "Nuevo Expediente - Direccion", JOptionPane.PLAIN_MESSAGE);
            if (direccion == null) { mostrarCancelado(); return; }
            if (!Validador.validarNoVacio(direccion)) {
                JOptionPane.showMessageDialog(null,
                    "La direccion no puede estar vacia.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Plan Médico ──────────────────────────────────────────────────────
        String planMedico = seleccionarPlanMedico();
        if (planMedico == null) { mostrarCancelado(); return; }

        // ── Fecha de visita ──────────────────────────────────────────────────
        String fechaVisita = "";
        while (!Validador.validarFecha(fechaVisita)) {
            fechaVisita = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE VISITA (formato: MM/DD/YYYY):",
                "Nuevo Expediente - Fecha de Visita", JOptionPane.PLAIN_MESSAGE);
            if (fechaVisita == null) { mostrarCancelado(); return; }
            if (!Validador.validarFecha(fechaVisita)) {
                JOptionPane.showMessageDialog(null,
                    "La fecha no puede estar vacia.\n" +
                    "Use el formato: MM/DD/YYYY",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Diagnóstico ──────────────────────────────────────────────────────
        String diagnostico = "";
        while (!Validador.validarNoVacio(diagnostico)) {
            diagnostico = JOptionPane.showInputDialog(null,
                "Ingrese el DIAGNOSTICO:",
                "Nuevo Expediente - Diagnostico", JOptionPane.PLAIN_MESSAGE);
            if (diagnostico == null) { mostrarCancelado(); return; }
            if (!Validador.validarNoVacio(diagnostico)) {
                JOptionPane.showMessageDialog(null,
                    "El diagnostico no puede estar vacio.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Receta (opcional) ────────────────────────────────────────────────
        String receta = JOptionPane.showInputDialog(null,
            "Ingrese la RECETA (medicamentos e instrucciones):",
            "Nuevo Expediente - Receta", JOptionPane.PLAIN_MESSAGE);
        if (receta == null) { mostrarCancelado(); return; }

        // ── Fecha siguiente visita ───────────────────────────────────────────
        String fechaSig = "";
        while (!Validador.validarFecha(fechaSig)) {
            fechaSig = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE SIGUIENTE VISITA (formato: MM/DD/YYYY):",
                "Nuevo Expediente - Proxima Visita", JOptionPane.PLAIN_MESSAGE);
            if (fechaSig == null) { mostrarCancelado(); return; }
            if (!Validador.validarFecha(fechaSig)) {
                JOptionPane.showMessageDialog(null,
                    "La fecha no puede estar vacia.\n" +
                    "Use el formato: MM/DD/YYYY",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Crear y guardar el nuevo expediente ──────────────────────────────
        String numeroExp = generarNumeroExpediente();

        Paciente nuevo = new Paciente(nombre, inicial, apellidos, ss, numeroExp,
                                      fechaNac, sexo, direccion, planMedico,
                                      fechaVisita, diagnostico, receta, fechaSig);

        listaPacientes[totalPacientes] = nuevo; // Agrega al arreglo en memoria
        totalPacientes++;

        boolean guardado = ArchivoManager.reescribirTodosLosExpedientes(
                               listaPacientes, totalPacientes); // Guarda en archivo

        if (guardado) {
            JOptionPane.showMessageDialog(null,
                "Expediente creado exitosamente.\n" +
                "Numero de expediente asignado: " + numeroExp,
                "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "Error al guardar el expediente en el archivo.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Buscar por número de expediente ──────────────────────────────────────

    /*
     * Nombre: buscarPorNumero
     * Propósito: Solicita un número de expediente y muestra sus datos si lo encuentra.
     *            Usa bandera booleana en el for en lugar de break (COMP 2315).
     * Precondiciones: listaPacientes debe estar inicializado
     * Postcondiciones: Ninguna — no modifica datos
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void buscarPorNumero() {
        String busqueda = JOptionPane.showInputDialog(null,
            "Ingrese el NUMERO DE EXPEDIENTE a buscar (ejemplo: EXP-00001):",
            "Buscar Expediente", JOptionPane.PLAIN_MESSAGE);

        if (busqueda == null || busqueda.equals("")) {
            JOptionPane.showMessageDialog(null,
                "Busqueda cancelada.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        /* Búsqueda con bandera booleana — sin break dentro del for (COMP 2315) */
        Paciente encontrado = null;
        boolean terminado   = false;

        for (int i = 0; i < totalPacientes && !terminado; i++) {
            if (listaPacientes[i].getNumeroExpediente().equals(busqueda)) {
                encontrado = listaPacientes[i];
                terminado  = true; // Bandera detiene el for sin usar break
            }
        }

        if (encontrado != null) {
            JOptionPane.showMessageDialog(null,
                encontrado.toString(),
                "Expediente Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "No se encontro ningun expediente con el numero: " + busqueda,
                "No encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ─── Buscar por fecha de visita ───────────────────────────────────────────

    /*
     * Nombre: buscarPorFechaVisita
     * Propósito: Muestra todos los expedientes cuya fecha de visita coincida.
     *            Usa arreglo fijo para resultados y concatenación de String (COMP 2315).
     * Precondiciones: listaPacientes debe estar inicializado
     * Postcondiciones: Ninguna — no modifica datos
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void buscarPorFechaVisita() {
        String fecha = "";
        while (!Validador.validarFecha(fecha)) {
            fecha = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE VISITA a buscar (formato: MM/DD/YYYY):",
                "Buscar por Fecha", JOptionPane.PLAIN_MESSAGE);
            if (fecha == null) {
                JOptionPane.showMessageDialog(null,
                    "Busqueda cancelada.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!Validador.validarFecha(fecha)) {
                JOptionPane.showMessageDialog(null,
                    "La fecha no puede estar vacia.\n" +
                    "Use el formato: MM/DD/YYYY",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /* Recopila los expedientes que coinciden en un arreglo fijo (sin ArrayList) */
        Paciente[] resultados  = new Paciente[100];
        int totalResultados    = 0;

        for (int i = 0; i < totalPacientes; i++) {
            if (listaPacientes[i].getFechaVisita().equals(fecha)) {
                resultados[totalResultados] = listaPacientes[i];
                totalResultados++;
            }
        }

        if (totalResultados == 0) {
            JOptionPane.showMessageDialog(null,
                "No se encontraron expedientes con fecha de visita: " + fecha,
                "No encontrado", JOptionPane.WARNING_MESSAGE);
        } else {
            /* Construye el mensaje con concatenación de String (sin StringBuilder) */
            String sb = "Expedientes con fecha de visita " + fecha + ":\n\n";
            for (int i = 0; i < totalResultados; i++) {
                sb = sb + resultados[i].toString() + "\n\n";
            }
            JOptionPane.showMessageDialog(null,
                sb,
                "Resultados (" + totalResultados + " encontrados)",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ─── Actualizar expediente ────────────────────────────────────────────────

    /*
     * Nombre: actualizarExpediente
     * Propósito: Busca un expediente y permite modificar sus campos mediante un
     *            submenú de opciones. Guarda los cambios al terminar.
     * Precondiciones: listaPacientes debe estar inicializado
     * Postcondiciones: El expediente modificado queda actualizado en archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void actualizarExpediente() {
        String busqueda = JOptionPane.showInputDialog(null,
            "Ingrese el NUMERO DE EXPEDIENTE a actualizar (ejemplo: EXP-00001):",
            "Actualizar Expediente", JOptionPane.PLAIN_MESSAGE);

        if (busqueda == null || busqueda.equals("")) {
            JOptionPane.showMessageDialog(null,
                "Operacion cancelada.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        /* Búsqueda con bandera booleana — sin break dentro del for (COMP 2315) */
        Paciente paciente  = null;
        boolean encontrado = false;

        for (int i = 0; i < totalPacientes && !encontrado; i++) {
            if (listaPacientes[i].getNumeroExpediente().equals(busqueda)) {
                paciente   = listaPacientes[i];
                encontrado = true;
            }
        }

        if (paciente == null) {
            JOptionPane.showMessageDialog(null,
                "No se encontro ningun expediente con el numero: " + busqueda,
                "No encontrado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        /* Submenú de actualización — se repite hasta que el usuario elija Terminar */
        boolean continuar = true;

        while (continuar) {
            String[] opciones = {
                "1. Nombre / Inicial / Apellidos",
                "2. Seguro Social",
                "3. Fecha de Nacimiento",
                "4. Sexo",
                "5. Direccion",
                "6. Plan Medico",
                "7. Fecha de Visita",
                "8. Diagnostico",
                "9. Receta",
                "10. Fecha de Siguiente Visita",
                "11. Terminar actualizacion"
            };

            String seleccion = (String) JOptionPane.showInputDialog(null,
                "Paciente: "    + paciente.getNombreCompleto() + "\n" +
                "Expediente: " + paciente.getNumeroExpediente() +
                "\n\nSeleccione el campo a actualizar:",
                "Submenu de Actualizacion",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == null) {
                continuar = false; // Cierre de diálogo — sale del submenú
            } else {
                /* Identifica la opción con for + .equals() (sin .split() ni parseInt
                   sobre el string — en su lugar se busca directamente el texto) */
                int opcion = -1;
                for (int i = 0; i < opciones.length; i++) {
                    if (opciones[i].equals(seleccion)) {
                        opcion = i + 1;
                    }
                }

                switch (opcion) {

                    case 1: // Actualiza nombre, inicial y apellidos
                        String nuevoNombre = JOptionPane.showInputDialog(null,
                            "Nuevo NOMBRE (actual: " + paciente.getNombre() + "):",
                            "Actualizar Nombre", JOptionPane.PLAIN_MESSAGE);
                        if (nuevoNombre != null && Validador.validarNoVacio(nuevoNombre)) {
                            paciente.setNombre(nuevoNombre);
                        }
                        String nuevaInicial = JOptionPane.showInputDialog(null,
                            "Nueva INICIAL (actual: " + paciente.getInicial() +
                            ") — deje vacio si no aplica:",
                            "Actualizar Inicial", JOptionPane.PLAIN_MESSAGE);
                        if (nuevaInicial != null) {
                            paciente.setInicial(nuevaInicial);
                        }
                        String nuevosApellidos = JOptionPane.showInputDialog(null,
                            "Nuevos APELLIDOS (actual: " + paciente.getApellidos() + "):",
                            "Actualizar Apellidos", JOptionPane.PLAIN_MESSAGE);
                        if (nuevosApellidos != null && Validador.validarNoVacio(nuevosApellidos)) {
                            paciente.setApellidos(nuevosApellidos);
                        }
                        break;

                    case 2: // Actualiza el seguro social
                        String nuevoSS    = "";
                        boolean cancelSS  = false;
                        while (!Validador.validarSeguroSocial(nuevoSS) && !cancelSS) {
                            nuevoSS = JOptionPane.showInputDialog(null,
                                "Nuevo SEGURO SOCIAL (actual: " + paciente.getSeguroSocial() +
                                ")\nFormato: XXX-XX-XXXX:",
                                "Actualizar Seguro Social", JOptionPane.PLAIN_MESSAGE);
                            if (nuevoSS == null) {
                                cancelSS = true;
                            } else if (!Validador.validarSeguroSocial(nuevoSS)) {
                                JOptionPane.showMessageDialog(null,
                                    "El campo no puede estar vacio.\n" +
                                    "Use el formato: XXX-XX-XXXX",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (!cancelSS && Validador.validarSeguroSocial(nuevoSS)) {
                            paciente.setSeguroSocial(nuevoSS);
                        }
                        break;

                    case 3: // Actualiza la fecha de nacimiento
                        String nuevaFechaNac  = "";
                        boolean cancelFNac    = false;
                        while (!Validador.validarFecha(nuevaFechaNac) && !cancelFNac) {
                            nuevaFechaNac = JOptionPane.showInputDialog(null,
                                "Nueva FECHA DE NACIMIENTO (actual: " +
                                paciente.getFechaNacimiento() +
                                ")\nFormato: MM/DD/YYYY:",
                                "Actualizar Fecha Nacimiento", JOptionPane.PLAIN_MESSAGE);
                            if (nuevaFechaNac == null) {
                                cancelFNac = true;
                            } else if (!Validador.validarFecha(nuevaFechaNac)) {
                                JOptionPane.showMessageDialog(null,
                                    "La fecha no puede estar vacia.\n" +
                                    "Use el formato: MM/DD/YYYY",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (!cancelFNac && Validador.validarFecha(nuevaFechaNac)) {
                            paciente.setFechaNacimiento(nuevaFechaNac);
                        }
                        break;

                    case 4: // Actualiza el sexo
                        String nuevoSexo  = "";
                        boolean cancelSex = false;
                        while (!Validador.validarSexo(nuevoSexo) && !cancelSex) {
                            nuevoSexo = JOptionPane.showInputDialog(null,
                                "Nuevo SEXO (actual: " + paciente.getSexo() +
                                ")\nIngrese M o F (en mayuscula):",
                                "Actualizar Sexo", JOptionPane.PLAIN_MESSAGE);
                            if (nuevoSexo == null) {
                                cancelSex = true;
                            } else if (!Validador.validarSexo(nuevoSexo)) {
                                JOptionPane.showMessageDialog(null,
                                    "Solo se acepta exactamente M o F.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (!cancelSex && Validador.validarSexo(nuevoSexo)) {
                            paciente.setSexo(nuevoSexo);
                        }
                        break;

                    case 5: // Actualiza la dirección
                        String nuevaDireccion = JOptionPane.showInputDialog(null,
                            "Nueva DIRECCION (actual: " + paciente.getDireccion() + "):",
                            "Actualizar Direccion", JOptionPane.PLAIN_MESSAGE);
                        if (nuevaDireccion != null && Validador.validarNoVacio(nuevaDireccion)) {
                            paciente.setDireccion(nuevaDireccion);
                        }
                        break;

                    case 6: // Actualiza el plan médico
                        String nuevoPlan = seleccionarPlanMedico();
                        if (nuevoPlan != null) {
                            paciente.setPlanMedico(nuevoPlan);
                        }
                        break;

                    case 7: // Actualiza la fecha de visita
                        String nuevaFechaVisita = "";
                        boolean cancelFVis      = false;
                        while (!Validador.validarFecha(nuevaFechaVisita) && !cancelFVis) {
                            nuevaFechaVisita = JOptionPane.showInputDialog(null,
                                "Nueva FECHA DE VISITA (actual: " +
                                paciente.getFechaVisita() +
                                ")\nFormato: MM/DD/YYYY:",
                                "Actualizar Fecha de Visita", JOptionPane.PLAIN_MESSAGE);
                            if (nuevaFechaVisita == null) {
                                cancelFVis = true;
                            } else if (!Validador.validarFecha(nuevaFechaVisita)) {
                                JOptionPane.showMessageDialog(null,
                                    "La fecha no puede estar vacia.\n" +
                                    "Use el formato: MM/DD/YYYY",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (!cancelFVis && Validador.validarFecha(nuevaFechaVisita)) {
                            paciente.setFechaVisita(nuevaFechaVisita);
                        }
                        break;

                    case 8: // Actualiza el diagnóstico
                        String nuevoDiag = JOptionPane.showInputDialog(null,
                            "Nuevo DIAGNOSTICO (actual: " + paciente.getDiagnostico() + "):",
                            "Actualizar Diagnostico", JOptionPane.PLAIN_MESSAGE);
                        if (nuevoDiag != null && Validador.validarNoVacio(nuevoDiag)) {
                            paciente.setDiagnostico(nuevoDiag);
                        }
                        break;

                    case 9: // Actualiza la receta
                        String nuevaReceta = JOptionPane.showInputDialog(null,
                            "Nueva RECETA (actual: " + paciente.getReceta() + "):",
                            "Actualizar Receta", JOptionPane.PLAIN_MESSAGE);
                        if (nuevaReceta != null) {
                            paciente.setReceta(nuevaReceta); // La receta puede estar vacía
                        }
                        break;

                    case 10: // Actualiza la fecha de siguiente visita
                        String nuevaFechaSig = "";
                        boolean cancelFSig   = false;
                        while (!Validador.validarFecha(nuevaFechaSig) && !cancelFSig) {
                            nuevaFechaSig = JOptionPane.showInputDialog(null,
                                "Nueva FECHA DE SIGUIENTE VISITA (actual: " +
                                paciente.getFechaSiguienteVisita() +
                                ")\nFormato: MM/DD/YYYY:",
                                "Actualizar Proxima Visita", JOptionPane.PLAIN_MESSAGE);
                            if (nuevaFechaSig == null) {
                                cancelFSig = true;
                            } else if (!Validador.validarFecha(nuevaFechaSig)) {
                                JOptionPane.showMessageDialog(null,
                                    "La fecha no puede estar vacia.\n" +
                                    "Use el formato: MM/DD/YYYY",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (!cancelFSig && Validador.validarFecha(nuevaFechaSig)) {
                            paciente.setFechaSiguienteVisita(nuevaFechaSig);
                        }
                        break;

                    case 11: // Terminar actualización
                        continuar = false;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,
                            "Opcion no valida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        boolean guardado = ArchivoManager.reescribirTodosLosExpedientes(
                               listaPacientes, totalPacientes);
        if (guardado) {
            JOptionPane.showMessageDialog(null,
                "Expediente actualizado y guardado correctamente.",
                "Actualizacion exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "Error al guardar los cambios en el archivo.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Listar todos los expedientes ─────────────────────────────────────────

    /*
     * Nombre: listarTodosLosExpedientes
     * Propósito: Recarga los expedientes y los muestra en formato de tabla.
     *            Usa concatenación de String y for indexado (sin StringBuilder
     *            ni String.format() — COMP 2315).
     * Precondiciones: Ninguna
     * Postcondiciones: listaPacientes y totalPacientes quedan actualizados
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void listarTodosLosExpedientes() {
        listaPacientes = ArchivoManager.cargarTodosLosExpedientes();
        totalPacientes = ArchivoManager.contarExpedientes(listaPacientes);

        if (totalPacientes == 0) {
            JOptionPane.showMessageDialog(null,
                "No hay expedientes registrados en el sistema.",
                "Lista Vacia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        /* Construye las líneas separadoras con un for (sin .repeat()) */
        String lineaDoble  = "";
        String lineaSimple = "";
        for (int i = 0; i < 56; i++) {
            lineaDoble  = lineaDoble  + "=";
            lineaSimple = lineaSimple + "-";
        }

        /* Construye la tabla con concatenación de String (sin StringBuilder) */
        String sb = "LISTADO DE EXPEDIENTES - Total: " + totalPacientes + "\n";
        sb = sb + lineaDoble  + "\n";
        sb = sb + "Expediente   Paciente                        Fecha\n";
        sb = sb + lineaSimple + "\n";

        for (int i = 0; i < totalPacientes; i++) {
            sb = sb + listaPacientes[i].getNumeroExpediente() + "   ";
            sb = sb + listaPacientes[i].getNombreCompleto()   + "   ";
            sb = sb + listaPacientes[i].getFechaVisita()      + "\n";
        }

        sb = sb + lineaDoble;

        JOptionPane.showMessageDialog(null,
            sb,
            "Listado de Expedientes", JOptionPane.INFORMATION_MESSAGE);
    }

    // ─── Seleccionar plan médico ──────────────────────────────────────────────

    /*
     * Nombre: seleccionarPlanMedico
     * Propósito: Muestra una lista desplegable con los planes médicos disponibles.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna
     * Argumentos: Ninguno
     * Valor que devuelve: String — plan seleccionado, o null si se canceló
     * Versión: 1.0
     */
    private String seleccionarPlanMedico() {
        String[] planes = {
            "Triple-S",
            "MMM",
            "MCS",
            "Molina",
            "First Medical",
            "Otro"
        };

        String seleccion = (String) JOptionPane.showInputDialog(null,
            "Seleccione el PLAN MEDICO del paciente:",
            "Plan Medico",
            JOptionPane.PLAIN_MESSAGE,
            null,
            planes,
            planes[0]);

        return seleccion;
    }

    // ─── Mensaje de cancelación ───────────────────────────────────────────────

    /*
     * Nombre: mostrarCancelado
     * Propósito: Muestra un aviso cuando el usuario cancela la creación.
     * Precondiciones: Ninguna
     * Postcondiciones: El diálogo fue mostrado
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    private void mostrarCancelado() {
        JOptionPane.showMessageDialog(null,
            "Operacion cancelada. No se creo el expediente.",
            "Cancelado", JOptionPane.WARNING_MESSAGE);
    }

    // ─── Getter del arreglo de pacientes ─────────────────────────────────────

    /*
     * Nombre: getListaPacientes
     * Propósito: Retorna el arreglo de pacientes en memoria.
     * Precondiciones: El objeto debe estar inicializado
     * Postcondiciones: Ninguna
     * Argumentos: Ninguno
     * Valor que devuelve: Paciente[] — arreglo con los pacientes cargados
     * Versión: 1.0
     */
    public Paciente[] getListaPacientes() {
        return listaPacientes;
    }
}
