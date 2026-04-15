/*
 * Nombre del archivo: ExpedienteManager.java
 * Propósito: Contiene la lógica CRUD para manejar expedientes médicos:
 *            crear, buscar por número, buscar por fecha, actualizar y listar.
 *            Usa JOptionPane para la interacción con el usuario.
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
// Contiene la lógica CRUD para manejar expedientes médicos:
// crear, buscar por número, buscar por fecha, actualizar y listar.
// Usa JOptionPane para la interacción con el usuario.

import java.util.ArrayList; // Importa ArrayList para manejar la lista de pacientes en memoria
import javax.swing.JOptionPane; // Importa JOptionPane para mostrar diálogos gráficos al usuario

public class ExpedienteManager { // Clase principal de lógica — maneja todas las operaciones con expedientes

    // ─── Lista en memoria de todos los pacientes ──────────────────────────────

    private ArrayList<Paciente> listaPacientes; /* Lista que contiene todos los expedientes cargados
                                                    desde el archivo al iniciar la aplicación */

    private int contadorExpediente; // Contador usado para generar números de expediente únicos y secuenciales

    // ─── Constructor ──────────────────────────────────────────────────────────

    /*
     * Nombre: ExpedienteManager
     * Propósito: Inicializa el manejador cargando todos los expedientes existentes
     *            desde el archivo y configurando el contador para el siguiente expediente.
     * Precondiciones: La carpeta "data/" debe existir (inicializada por ArchivoManager)
     * Postcondiciones: listaPacientes contiene todos los expedientes del archivo y
     *                  contadorExpediente apunta al siguiente número disponible
     * Argumentos: Ninguno
     * Valor que devuelve: void (constructor)
     * Versión: 1.0
     */
    public ExpedienteManager() { // Al crear el objeto, carga automáticamente los expedientes existentes
        listaPacientes = ArchivoManager.cargarTodosLosExpedientes(); // Lee el archivo y llena la lista
        contadorExpediente = listaPacientes.size() + 1; // El contador empieza después del último expediente existente
    }

    // ─── Generar número de expediente ─────────────────────────────────────────

    /*
     * Nombre: generarNumeroExpediente
     * Propósito: Genera un número de expediente único en formato EXP-00000,
     *            incrementando el contador hasta encontrar uno que no esté en uso.
     * Precondiciones: contadorExpediente debe estar inicializado
     * Postcondiciones: contadorExpediente queda incrementado al siguiente valor disponible
     * Argumentos: Ninguno
     * Valor que devuelve: String — número de expediente único en formato EXP-00000
     * Versión: 1.0
     */
    private String generarNumeroExpediente() { // Genera un número único en formato EXP-00000
        String numero; // Variable para almacenar el número generado
        do { // Repite hasta encontrar un número que no esté en uso
            numero = String.format("EXP-%05d", contadorExpediente); // Genera "EXP-00001", "EXP-00002", etc.
            contadorExpediente++; // Incrementa el contador para el próximo expediente
        } while (ArchivoManager.existeExpediente(numero)); // Verifica que el número no exista ya en el archivo
        return numero; // Retorna el número único generado
    }

    // ─── Crear nuevo expediente ───────────────────────────────────────────────

    /*
     * Nombre: crearExpediente
     * Propósito: Guía al usuario mediante diálogos para ingresar todos los datos
     *            del nuevo expediente, valida cada campo y guarda el registro en
     *            memoria y en el archivo de texto.
     * Precondiciones: La carpeta "data/" debe existir y el archivo debe ser accesible
     * Postcondiciones: El nuevo expediente queda guardado en listaPacientes y en el archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void crearExpediente() { // Guía al usuario para ingresar todos los datos del nuevo expediente

        // ── Nombre ──────────────────────────────────────────────────────────
        String nombre = ""; // Inicializa vacío para la validación inicial
        do { // Estructura do...while: ejecuta el cuerpo al menos una vez (Lección 10 — do...while)
            nombre = JOptionPane.showInputDialog(null,
                "Ingrese el PRIMER NOMBRE del paciente:",
                "Nuevo Expediente — Nombre", JOptionPane.PLAIN_MESSAGE); // Muestra diálogo de entrada
            if (nombre == null) { mostrarCancelado(); return; } // Si presiona Cancelar, aborta la operación
            nombre = Validador.capitalizarNombre(nombre); // Formatea el nombre con mayúsculas correctas
            if (!Validador.validarNoVacio(nombre)) { // Si sigue vacío después de formatear
                JOptionPane.showMessageDialog(null,
                    "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE); // Muestra error
            }
        } while (!Validador.validarNoVacio(nombre)); // Continúa hasta obtener un nombre no vacío

        // ── Inicial ──────────────────────────────────────────────────────────
        String inicial = JOptionPane.showInputDialog(null,
            "Ingrese la INICIAL del segundo nombre (deje vacío si no aplica):",
            "Nuevo Expediente — Inicial", JOptionPane.PLAIN_MESSAGE); // La inicial es opcional
        if (inicial == null) { mostrarCancelado(); return; } // Si cancela, aborta
        inicial = inicial.trim().length() > 0 ? inicial.trim().substring(0, 1).toUpperCase() : ""; // Toma solo el primer carácter en mayúscula

        // ── Apellidos ────────────────────────────────────────────────────────
        String apellidos = ""; // Inicializa vacío para validación
        while (!Validador.validarNoVacio(apellidos)) { // Repite hasta obtener apellidos válidos
            apellidos = JOptionPane.showInputDialog(null,
                "Ingrese los APELLIDOS del paciente:",
                "Nuevo Expediente — Apellidos", JOptionPane.PLAIN_MESSAGE);
            if (apellidos == null) { mostrarCancelado(); return; }
            apellidos = Validador.capitalizarNombre(apellidos); // Formatea los apellidos
            if (!Validador.validarNoVacio(apellidos)) {
                JOptionPane.showMessageDialog(null,
                    "Los apellidos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Seguro Social ────────────────────────────────────────────────────
        String ss = ""; // Inicializa vacío para validación
        while (!Validador.validarSeguroSocial(ss)) { // Repite hasta que el formato XXX-XX-XXXX sea correcto
            ss = JOptionPane.showInputDialog(null,
                "Ingrese el SEGURO SOCIAL (formato: XXX-XX-XXXX):",
                "Nuevo Expediente — Seguro Social", JOptionPane.PLAIN_MESSAGE);
            if (ss == null) { mostrarCancelado(); return; }
            if (!Validador.validarSeguroSocial(ss)) { // Si el formato no es correcto
                JOptionPane.showMessageDialog(null,
                    "Formato inválido. Use: XXX-XX-XXXX (solo números y guiones).",
                    "Error de formato", JOptionPane.ERROR_MESSAGE); // Informa al usuario del formato correcto
            }
        }

        // ── Fecha de nacimiento ──────────────────────────────────────────────
        String fechaNac = ""; // Inicializa vacío para validación
        while (!Validador.validarFecha(fechaNac)) { // Repite hasta obtener una fecha válida
            fechaNac = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE NACIMIENTO (formato: MM/DD/YYYY):",
                "Nuevo Expediente — Fecha Nacimiento", JOptionPane.PLAIN_MESSAGE);
            if (fechaNac == null) { mostrarCancelado(); return; }
            if (!Validador.validarFecha(fechaNac)) {
                JOptionPane.showMessageDialog(null,
                    "Fecha inválida. Use formato MM/DD/YYYY.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Sexo ─────────────────────────────────────────────────────────────
        String sexo = ""; // Inicializa vacío para validación
        while (!Validador.validarSexo(sexo)) { // Repite hasta que el usuario ingrese M o F
            sexo = JOptionPane.showInputDialog(null,
                "Ingrese el SEXO del paciente (M = Masculino / F = Femenino):",
                "Nuevo Expediente — Sexo", JOptionPane.PLAIN_MESSAGE);
            if (sexo == null) { mostrarCancelado(); return; }
            if (!Validador.validarSexo(sexo)) {
                JOptionPane.showMessageDialog(null,
                    "Opción inválida. Solo se acepta M o F.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        sexo = sexo.trim().toUpperCase(); // Normaliza a mayúscula antes de guardar

        // ── Dirección ────────────────────────────────────────────────────────
        String direccion = ""; // Inicializa vacío para validación
        while (!Validador.validarNoVacio(direccion)) { // Repite hasta obtener una dirección
            direccion = JOptionPane.showInputDialog(null,
                "Ingrese la DIRECCIÓN (Calle, Ciudad, Estado, Zip):",
                "Nuevo Expediente — Dirección", JOptionPane.PLAIN_MESSAGE);
            if (direccion == null) { mostrarCancelado(); return; }
            if (!Validador.validarNoVacio(direccion)) {
                JOptionPane.showMessageDialog(null,
                    "La dirección no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Plan Médico ──────────────────────────────────────────────────────
        String planMedico = seleccionarPlanMedico(); // Muestra una lista desplegable con los planes disponibles
        if (planMedico == null) { mostrarCancelado(); return; } // Si cancela la selección, aborta

        // ── Fecha de visita ──────────────────────────────────────────────────
        String fechaVisita = ""; // Inicializa vacío para validación
        while (!Validador.validarFecha(fechaVisita)) { // Repite hasta obtener una fecha válida
            fechaVisita = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE VISITA (formato: MM/DD/YYYY):",
                "Nuevo Expediente — Fecha de Visita", JOptionPane.PLAIN_MESSAGE);
            if (fechaVisita == null) { mostrarCancelado(); return; }
            if (!Validador.validarFecha(fechaVisita)) {
                JOptionPane.showMessageDialog(null,
                    "Fecha inválida. Use formato MM/DD/YYYY.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Diagnóstico ──────────────────────────────────────────────────────
        String diagnostico = ""; // Inicializa vacío para validación
        while (!Validador.validarNoVacio(diagnostico)) { // Repite hasta obtener un diagnóstico
            diagnostico = JOptionPane.showInputDialog(null,
                "Ingrese el DIAGNÓSTICO:",
                "Nuevo Expediente — Diagnóstico", JOptionPane.PLAIN_MESSAGE);
            if (diagnostico == null) { mostrarCancelado(); return; }
            if (!Validador.validarNoVacio(diagnostico)) {
                JOptionPane.showMessageDialog(null,
                    "El diagnóstico no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Receta ───────────────────────────────────────────────────────────
        String receta = JOptionPane.showInputDialog(null,
            "Ingrese la RECETA (medicamentos e instrucciones):",
            "Nuevo Expediente — Receta", JOptionPane.PLAIN_MESSAGE); // La receta es opcional (puede estar vacía)
        if (receta == null) { mostrarCancelado(); return; }

        // ── Fecha siguiente visita ───────────────────────────────────────────
        String fechaSig = ""; // Inicializa vacío para validación
        while (!Validador.validarFecha(fechaSig)) { // Repite hasta obtener una fecha válida
            fechaSig = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE SIGUIENTE VISITA (formato: MM/DD/YYYY):",
                "Nuevo Expediente — Próxima Visita", JOptionPane.PLAIN_MESSAGE);
            if (fechaSig == null) { mostrarCancelado(); return; }
            if (!Validador.validarFecha(fechaSig)) {
                JOptionPane.showMessageDialog(null,
                    "Fecha inválida. Use formato MM/DD/YYYY.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ── Crear el objeto Paciente ─────────────────────────────────────────
        String numeroExp = generarNumeroExpediente(); // Genera el número único de expediente automáticamente

        Paciente nuevo = new Paciente( // Crea el objeto Paciente con todos los datos recopilados
            nombre, inicial, apellidos, ss, numeroExp,
            fechaNac, sexo, direccion, planMedico,
            fechaVisita, diagnostico, receta, fechaSig
        );

        // ── Guardar en memoria y en archivo ──────────────────────────────────
        listaPacientes.add(nuevo); // Agrega el nuevo paciente a la lista en memoria
        boolean guardado = ArchivoManager.guardarExpediente(nuevo); // Guarda también en el archivo de texto

        if (guardado) { // Si el archivo se guardó correctamente
            JOptionPane.showMessageDialog(null,
                "Expediente creado exitosamente.\n" +
                "Número de expediente asignado: " + numeroExp,
                "Éxito", JOptionPane.INFORMATION_MESSAGE); // Informa al usuario del número asignado
        } else { // Si hubo un error al guardar el archivo
            JOptionPane.showMessageDialog(null,
                "Error al guardar el expediente en el archivo.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Buscar por número de expediente ──────────────────────────────────────

    /*
     * Nombre: buscarPorNumero
     * Propósito: Solicita al usuario un número de expediente y muestra todos los
     *            datos del paciente si el expediente es encontrado en la lista.
     * Precondiciones: listaPacientes debe estar inicializada
     * Postcondiciones: Ninguna — no modifica la lista ni el archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void buscarPorNumero() { // Busca y muestra un expediente según su número único
        String busqueda = JOptionPane.showInputDialog(null,
            "Ingrese el NÚMERO DE EXPEDIENTE a buscar (formato: EXP-XXXXX):",
            "Buscar Expediente", JOptionPane.PLAIN_MESSAGE); // Solicita el número al usuario

        if (busqueda == null || busqueda.trim().isEmpty()) { // Si cancela o deja vacío
            JOptionPane.showMessageDialog(null,
                "Búsqueda cancelada.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return; // Sale del método sin buscar
        }

        busqueda = busqueda.trim().toUpperCase(); // Normaliza a mayúsculas para comparación
        Paciente encontrado = null; // Variable para guardar el resultado de la búsqueda

        for (int i = 0; i < listaPacientes.size(); i++) { // Recorre cada paciente en la lista con índice
            Paciente p = listaPacientes.get(i); // Obtiene el paciente en la posición actual
            if (p.getNumeroExpediente().equalsIgnoreCase(busqueda)) { // Compara el número sin importar mayúsculas
                encontrado = p; // Guarda la referencia al paciente encontrado
                break; // Sale del bucle — no necesita seguir buscando
            }
        }

        if (encontrado != null) { // Si encontró un expediente con ese número
            JOptionPane.showMessageDialog(null,
                encontrado.toString(),
                "Expediente Encontrado", JOptionPane.INFORMATION_MESSAGE); // Muestra todos los datos
        } else { // Si no encontró ningún expediente
            JOptionPane.showMessageDialog(null,
                "No se encontró ningún expediente con el número: " + busqueda,
                "No encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ─── Buscar por fecha de visita ───────────────────────────────────────────

    /*
     * Nombre: buscarPorFechaVisita
     * Propósito: Solicita al usuario una fecha y muestra todos los expedientes
     *            cuya fecha de visita coincida exactamente con la fecha ingresada.
     * Precondiciones: listaPacientes debe estar inicializada
     * Postcondiciones: Ninguna — no modifica la lista ni el archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void buscarPorFechaVisita() { // Busca todos los expedientes con una fecha de visita específica
        String fecha = ""; // Inicializa vacío para validación
        while (!Validador.validarFecha(fecha)) { // Repite hasta obtener una fecha válida
            fecha = JOptionPane.showInputDialog(null,
                "Ingrese la FECHA DE VISITA a buscar (formato: MM/DD/YYYY):",
                "Buscar por Fecha", JOptionPane.PLAIN_MESSAGE);
            if (fecha == null) { // Si el usuario cancela
                JOptionPane.showMessageDialog(null,
                    "Búsqueda cancelada.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!Validador.validarFecha(fecha)) {
                JOptionPane.showMessageDialog(null,
                    "Fecha inválida. Use formato MM/DD/YYYY.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        ArrayList<Paciente> resultados = new ArrayList<>(); // Lista para guardar todos los pacientes que coincidan
        for (int i = 0; i < listaPacientes.size(); i++) { // Recorre todos los expedientes con índice
            Paciente p = listaPacientes.get(i); // Obtiene el paciente en la posición actual
            if (p.getFechaVisita().equals(fecha.trim())) { // Compara la fecha de visita exactamente
                resultados.add(p); // Agrega el paciente a los resultados si coincide la fecha
            }
        }

        if (resultados.isEmpty()) { // Si no encontró ningún expediente con esa fecha
            JOptionPane.showMessageDialog(null,
                "No se encontraron expedientes con fecha de visita: " + fecha,
                "No encontrado", JOptionPane.WARNING_MESSAGE);
        } else { // Si encontró uno o más expedientes
            StringBuilder sb = new StringBuilder(); // Objeto para construir el mensaje con todos los resultados
            sb.append("Expedientes con fecha de visita ").append(fecha).append(":\n\n");
            for (int i = 0; i < resultados.size(); i++) { // Agrega cada expediente encontrado al mensaje con índice
                Paciente p = resultados.get(i); // Obtiene el resultado en la posición actual
                sb.append(p.toString()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null,
                sb.toString(),
                "Resultados (" + resultados.size() + " encontrados)",
                JOptionPane.INFORMATION_MESSAGE); // Muestra cuántos resultados encontró
        }
    }

    // ─── Actualizar expediente ────────────────────────────────────────────────

    /*
     * Nombre: actualizarExpediente
     * Propósito: Busca un expediente por número y permite al usuario modificar
     *            cualquiera de sus campos mediante un submenú de opciones.
     *            Los cambios se guardan en memoria y en el archivo al terminar.
     * Precondiciones: listaPacientes debe estar inicializada
     * Postcondiciones: El expediente modificado queda actualizado en listaPacientes y en el archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void actualizarExpediente() { // Busca un expediente y permite modificar sus campos
        String busqueda = JOptionPane.showInputDialog(null,
            "Ingrese el NÚMERO DE EXPEDIENTE a actualizar (formato: EXP-XXXXX):",
            "Actualizar Expediente", JOptionPane.PLAIN_MESSAGE); // Solicita el número a actualizar

        if (busqueda == null || busqueda.trim().isEmpty()) { // Si cancela o deja vacío
            JOptionPane.showMessageDialog(null,
                "Operación cancelada.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        busqueda = busqueda.trim().toUpperCase(); // Normaliza a mayúsculas
        Paciente paciente = null; // Variable para guardar el expediente encontrado

        for (int i = 0; i < listaPacientes.size(); i++) { // Busca el expediente en la lista con índice
            Paciente p = listaPacientes.get(i); // Obtiene el paciente en la posición actual
            if (p.getNumeroExpediente().equalsIgnoreCase(busqueda)) {
                paciente = p; // Guarda la referencia al paciente para modificarlo
                break;
            }
        }

        if (paciente == null) { // Si no encontró el expediente
            JOptionPane.showMessageDialog(null,
                "No se encontró ningún expediente con el número: " + busqueda,
                "No encontrado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean continuar = true; // Variable de control para mantener el submenú activo
        while (continuar) { // Repite el submenú hasta que el usuario elija "Terminar"
            String[] opciones = { // Arreglo con las opciones del submenú de actualización
                "1. Nombre / Inicial / Apellidos",
                "2. Seguro Social",
                "3. Fecha de Nacimiento",
                "4. Sexo",
                "5. Dirección",
                "6. Plan Médico",
                "7. Fecha de Visita",
                "8. Diagnóstico",
                "9. Receta",
                "10. Fecha de Siguiente Visita",
                "11. Terminar actualización"
            };

            String seleccion = (String) JOptionPane.showInputDialog(null,
                "Paciente: " + paciente.getNombreCompleto() +
                "\nExpediente: " + paciente.getNumeroExpediente() +
                "\n\nSeleccione el campo a actualizar:",
                "Submenú de Actualización",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]); // Muestra lista desplegable

            if (seleccion == null) break; // Si cierra el diálogo, sale del submenú

            int opcion = Integer.parseInt(seleccion.split("\\.")[0].trim()); // Extrae el número de la opción seleccionada

            switch (opcion) { // Evalúa cuál campo actualizar según la opción elegida

                case 1: // Actualiza nombre, inicial y apellidos
                    String nuevoNombre = JOptionPane.showInputDialog(null,
                        "Nuevo NOMBRE (actual: " + paciente.getNombre() + "):",
                        "Actualizar Nombre", JOptionPane.PLAIN_MESSAGE);
                    if (nuevoNombre != null && Validador.validarNoVacio(nuevoNombre)) {
                        paciente.setNombre(Validador.capitalizarNombre(nuevoNombre)); // Formatea y guarda
                    }
                    String nuevaInicial = JOptionPane.showInputDialog(null,
                        "Nueva INICIAL (actual: " + paciente.getInicial() + "):",
                        "Actualizar Inicial", JOptionPane.PLAIN_MESSAGE);
                    if (nuevaInicial != null) {
                        paciente.setInicial(nuevaInicial.trim().length() > 0
                            ? nuevaInicial.trim().substring(0, 1).toUpperCase() : ""); // Toma solo el primer carácter
                    }
                    String nuevosApellidos = JOptionPane.showInputDialog(null,
                        "Nuevos APELLIDOS (actual: " + paciente.getApellidos() + "):",
                        "Actualizar Apellidos", JOptionPane.PLAIN_MESSAGE);
                    if (nuevosApellidos != null && Validador.validarNoVacio(nuevosApellidos)) {
                        paciente.setApellidos(Validador.capitalizarNombre(nuevosApellidos));
                    }
                    break;

                case 2: // Actualiza el seguro social con validación de formato
                    String nuevoSS = "";
                    while (!Validador.validarSeguroSocial(nuevoSS)) { // Repite hasta formato correcto
                        nuevoSS = JOptionPane.showInputDialog(null,
                            "Nuevo SEGURO SOCIAL (actual: " + paciente.getSeguroSocial() +
                            ")\nFormato: XXX-XX-XXXX:",
                            "Actualizar Seguro Social", JOptionPane.PLAIN_MESSAGE);
                        if (nuevoSS == null) break; // Sale si cancela
                        if (!Validador.validarSeguroSocial(nuevoSS)) {
                            JOptionPane.showMessageDialog(null,
                                "Formato inválido. Use: XXX-XX-XXXX",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (nuevoSS != null && Validador.validarSeguroSocial(nuevoSS)) {
                        paciente.setSeguroSocial(nuevoSS); // Guarda el nuevo seguro social
                    }
                    break;

                case 3: // Actualiza la fecha de nacimiento con validación
                    String nuevaFechaNac = "";
                    while (!Validador.validarFecha(nuevaFechaNac)) {
                        nuevaFechaNac = JOptionPane.showInputDialog(null,
                            "Nueva FECHA DE NACIMIENTO (actual: " + paciente.getFechaNacimiento() +
                            ")\nFormato: MM/DD/YYYY:",
                            "Actualizar Fecha Nacimiento", JOptionPane.PLAIN_MESSAGE);
                        if (nuevaFechaNac == null) break;
                        if (!Validador.validarFecha(nuevaFechaNac)) {
                            JOptionPane.showMessageDialog(null,
                                "Fecha inválida. Use MM/DD/YYYY.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (nuevaFechaNac != null && Validador.validarFecha(nuevaFechaNac)) {
                        paciente.setFechaNacimiento(nuevaFechaNac);
                    }
                    break;

                case 4: // Actualiza el sexo con validación M o F
                    String nuevoSexo = "";
                    while (!Validador.validarSexo(nuevoSexo)) {
                        nuevoSexo = JOptionPane.showInputDialog(null,
                            "Nuevo SEXO (actual: " + paciente.getSexo() + ")\nM o F:",
                            "Actualizar Sexo", JOptionPane.PLAIN_MESSAGE);
                        if (nuevoSexo == null) break;
                        if (!Validador.validarSexo(nuevoSexo)) {
                            JOptionPane.showMessageDialog(null,
                                "Solo se acepta M o F.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (nuevoSexo != null && Validador.validarSexo(nuevoSexo)) {
                        paciente.setSexo(nuevoSexo.trim().toUpperCase());
                    }
                    break;

                case 5: // Actualiza la dirección postal
                    String nuevaDireccion = JOptionPane.showInputDialog(null,
                        "Nueva DIRECCIÓN (actual: " + paciente.getDireccion() + "):",
                        "Actualizar Dirección", JOptionPane.PLAIN_MESSAGE);
                    if (nuevaDireccion != null && Validador.validarNoVacio(nuevaDireccion)) {
                        paciente.setDireccion(nuevaDireccion.trim());
                    }
                    break;

                case 6: // Actualiza el plan médico con la lista desplegable
                    String nuevoPlan = seleccionarPlanMedico();
                    if (nuevoPlan != null) {
                        paciente.setPlanMedico(nuevoPlan);
                    }
                    break;

                case 7: // Actualiza la fecha de visita con validación
                    String nuevaFechaVisita = "";
                    while (!Validador.validarFecha(nuevaFechaVisita)) {
                        nuevaFechaVisita = JOptionPane.showInputDialog(null,
                            "Nueva FECHA DE VISITA (actual: " + paciente.getFechaVisita() +
                            ")\nFormato: MM/DD/YYYY:",
                            "Actualizar Fecha de Visita", JOptionPane.PLAIN_MESSAGE);
                        if (nuevaFechaVisita == null) break;
                        if (!Validador.validarFecha(nuevaFechaVisita)) {
                            JOptionPane.showMessageDialog(null,
                                "Fecha inválida. Use MM/DD/YYYY.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (nuevaFechaVisita != null && Validador.validarFecha(nuevaFechaVisita)) {
                        paciente.setFechaVisita(nuevaFechaVisita);
                    }
                    break;

                case 8: // Actualiza el diagnóstico médico
                    String nuevoDiag = JOptionPane.showInputDialog(null,
                        "Nuevo DIAGNÓSTICO (actual: " + paciente.getDiagnostico() + "):",
                        "Actualizar Diagnóstico", JOptionPane.PLAIN_MESSAGE);
                    if (nuevoDiag != null && Validador.validarNoVacio(nuevoDiag)) {
                        paciente.setDiagnostico(nuevoDiag.trim());
                    }
                    break;

                case 9: // Actualiza la receta médica
                    String nuevaReceta = JOptionPane.showInputDialog(null,
                        "Nueva RECETA (actual: " + paciente.getReceta() + "):",
                        "Actualizar Receta", JOptionPane.PLAIN_MESSAGE);
                    if (nuevaReceta != null) {
                        paciente.setReceta(nuevaReceta.trim()); // La receta puede estar vacía
                    }
                    break;

                case 10: // Actualiza la fecha de la siguiente visita con validación
                    String nuevaFechaSig = "";
                    while (!Validador.validarFecha(nuevaFechaSig)) {
                        nuevaFechaSig = JOptionPane.showInputDialog(null,
                            "Nueva FECHA DE SIGUIENTE VISITA (actual: " +
                            paciente.getFechaSiguienteVisita() + ")\nFormato: MM/DD/YYYY:",
                            "Actualizar Próxima Visita", JOptionPane.PLAIN_MESSAGE);
                        if (nuevaFechaSig == null) break;
                        if (!Validador.validarFecha(nuevaFechaSig)) {
                            JOptionPane.showMessageDialog(null,
                                "Fecha inválida. Use MM/DD/YYYY.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (nuevaFechaSig != null && Validador.validarFecha(nuevaFechaSig)) {
                        paciente.setFechaSiguienteVisita(nuevaFechaSig);
                    }
                    break;

                case 11: // El usuario decide terminar la actualización
                    continuar = false; // Cambia la variable de control para salir del bucle while
                    break;

                default: // Si por alguna razón llega un número fuera de rango
                    JOptionPane.showMessageDialog(null,
                        "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        boolean guardado = ArchivoManager.reescribirTodosLosExpedientes(listaPacientes); // Guarda todos los cambios en el archivo
        if (guardado) { // Si el archivo se actualizó correctamente
            JOptionPane.showMessageDialog(null,
                "Expediente actualizado y guardado correctamente.",
                "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else { // Si hubo un error al guardar
            JOptionPane.showMessageDialog(null,
                "Error al guardar los cambios en el archivo.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Listar todos los expedientes ─────────────────────────────────────────

    /*
     * Nombre: listarTodosLosExpedientes
     * Propósito: Recarga los expedientes desde el archivo y los muestra en formato
     *            de tabla con número de expediente, nombre del paciente y fecha de visita.
     * Precondiciones: Ninguna — recarga automáticamente desde el archivo
     * Postcondiciones: listaPacientes queda actualizada con los datos más recientes del archivo
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    public void listarTodosLosExpedientes() { // Muestra un resumen en tabla de todos los expedientes
        listaPacientes = ArchivoManager.cargarTodosLosExpedientes(); // Recarga desde el archivo para tener datos actualizados

        if (listaPacientes.isEmpty()) { // Si no hay expedientes registrados
            JOptionPane.showMessageDialog(null,
                "No hay expedientes registrados en el sistema.",
                "Lista Vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder(); // Objeto para construir el texto de la tabla
        sb.append("LISTADO DE EXPEDIENTES — Total: ")
          .append(listaPacientes.size()).append("\n"); // Muestra el total de expedientes
        sb.append("═".repeat(60)).append("\n"); // Línea separadora visual
        sb.append(String.format("%-12s %-28s %-12s%n",
            "Expediente", "Paciente", "Fecha Visita")); // Encabezados de columna
        sb.append("─".repeat(60)).append("\n"); // Línea separadora bajo los encabezados

        for (int i = 0; i < listaPacientes.size(); i++) { // Recorre cada paciente con índice (Lección 10 — estructura for)
            Paciente p = listaPacientes.get(i); // Obtiene el paciente en la posición actual
            sb.append(String.format("%-12s %-28s %-12s%n",
                p.getNumeroExpediente(),
                p.getNombreCompleto().length() > 27 // Si el nombre es muy largo, lo trunca con "…"
                    ? p.getNombreCompleto().substring(0, 27) + "…"
                    : p.getNombreCompleto(),
                p.getFechaVisita()));
        }

        sb.append("═".repeat(60)); // Línea de cierre de la tabla

        JOptionPane.showMessageDialog(null,
            sb.toString(),
            "Listado de Expedientes", JOptionPane.INFORMATION_MESSAGE);
    }

    // ─── Seleccionar plan médico ──────────────────────────────────────────────

    /*
     * Nombre: seleccionarPlanMedico
     * Propósito: Muestra una lista desplegable con los 6 planes médicos disponibles
     *            y retorna el plan seleccionado por el usuario.
     * Precondiciones: Ninguna
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: String — nombre del plan médico seleccionado, o null si se canceló
     * Versión: 1.0
     */
    private String seleccionarPlanMedico() { // Muestra una lista desplegable con los planes médicos disponibles
        String[] planes = { // Arreglo con los 6 planes médicos aceptados por el sistema
            "Triple-S",
            "MMM",
            "MCS",
            "Molina",
            "First Medical",
            "Otro"
        };

        String seleccion = (String) JOptionPane.showInputDialog(null,
            "Seleccione el PLAN MÉDICO del paciente:",
            "Plan Médico",
            JOptionPane.PLAIN_MESSAGE,
            null,
            planes, // Muestra el arreglo de planes como opciones
            planes[0]); // "Triple-S" aparece seleccionado por defecto

        return seleccion; // Retorna el plan seleccionado, o null si canceló
    }

    // ─── Mensaje de cancelación ───────────────────────────────────────────────

    /*
     * Nombre: mostrarCancelado
     * Propósito: Muestra un aviso al usuario informando que la operación de
     *            creación de expediente fue cancelada.
     * Precondiciones: Ninguna
     * Postcondiciones: El diálogo de cancelación fue mostrado y cerrado por el usuario
     * Argumentos: Ninguno
     * Valor que devuelve: void
     * Versión: 1.0
     */
    private void mostrarCancelado() { // Muestra un aviso cuando el usuario cancela la creación
        JOptionPane.showMessageDialog(null,
            "Operación cancelada. No se creó el expediente.",
            "Cancelado", JOptionPane.WARNING_MESSAGE);
    }

    // ─── Getter de la lista ───────────────────────────────────────────────────

    /*
     * Nombre: getListaPacientes
     * Propósito: Retorna la referencia a la lista de pacientes actualmente en memoria.
     * Precondiciones: El objeto ExpedienteManager debe estar inicializado
     * Postcondiciones: Ninguna — no modifica el estado del objeto
     * Argumentos: Ninguno
     * Valor que devuelve: ArrayList<Paciente> — lista con todos los pacientes cargados en memoria
     * Versión: 1.0
     */
    public ArrayList<Paciente> getListaPacientes() { // Retorna la lista de pacientes en memoria
        return listaPacientes;
    }
}
