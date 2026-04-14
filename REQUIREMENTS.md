# Requisitos del Proyecto — COMP 2315

## Requisitos Técnicos Obligatorios

### Interfaz
- [ ] Mensaje de entrada con JOptionPane (nombres creadores, propósito)
- [ ] Mensaje de salida con JOptionPane (despedida)
- [ ] Login con usuario y contraseña vía JOptionPane

### Estructuras de Control (mínimo 3 distintas)
- [ ] `if/else`
- [ ] `while`
- [ ] `switch`

### Arreglos
- [ ] Usar `ArrayList<Paciente>` para manejar la lista en memoria
- [ ] Usar arreglos paralelos (ej: usuarios y contraseñas)

### Métodos
- [ ] Métodos propios definidos por el programador
- [ ] Métodos predefinidos de Java (String, Math, etc.)

### Paquetes a Importar
- [ ] `java.io.*`
- [ ] `java.util.*`
- [ ] `javax.swing.*`
- [ ] `java.text.*`
- [ ] `java.util.regex.*`

### Archivos
- [ ] Guardar cada expediente en archivo
- [ ] Buscar por fecha de visita
- [ ] Buscar por número de expediente
- [ ] Actualizar expediente

### Menú Principal (con switch)
- [ ] Opción 1: Crear nuevo expediente
- [ ] Opción 2: Buscar expediente por número
- [ ] Opción 3: Buscar expediente por fecha de visita
- [ ] Opción 4: Actualizar expediente
- [ ] Opción 5: Listar todos los expedientes
- [ ] Opción 6: Salir

### Submenú de Actualización
- [ ] Permitir elegir qué campo actualizar

---

## Campos del Expediente

| # | Campo | Formato | Validación |
|---|---|---|---|
| 1 | Nombre, Inicial, Apellidos | `Nombre I. Apellido Apellido` | No vacío |
| 2 | Seguro Social | `XXX-XX-XXXX` | Regex |
| 3 | Número de Expediente | `EXP-XXXXX` | Automático |
| 4 | Fecha de Nacimiento | `MM/DD/YYYY` | Regex + parse |
| 5 | Sexo | `M` o `F` | Lista fija |
| 6 | Dirección | `Calle, Ciudad, Estado, Zip` | No vacío |
| 7 | Plan Médico | Lista predefinida | Selección |
| 8 | Fecha de Visita | `MM/DD/YYYY` | Regex + parse |
| 9 | Diagnóstico | Texto libre | No vacío |
| 10 | Receta | Texto libre | Opcional |
| 11 | Fecha de Siguiente Visita | `MM/DD/YYYY` | Regex + parse |

**Planes médicos aceptados:** Triple-S, MMM, MCS, Molina, First Medical, Otro

---

## Requisitos del Informe

### Formato
- [ ] Letra Times New Roman, tamaño 12
- [ ] Interlineado de 1.5
- [ ] Márgenes de 1 pulgada
- [ ] Alineado a la izquierda
- [ ] Páginas numeradas (superior derecha)
- [ ] Ortografía y gramática revisadas

### Secciones Obligatorias
- [ ] **Portada** — institución, título, nombres, #est., curso, sección, fecha, profesor
- [ ] **Tabla de contenido** — subtítulos y páginas
- [ ] **Introducción** — descripción del proyecto
- [ ] **Diseño**
  - [ ] Bitácora — fechas, horas, asistencia, tareas, avances (tabla)
  - [ ] Flujogramas — para todas las partes del proyecto
  - [ ] Menú — explicación con imágenes
  - [ ] Código — todo el código identificado por parte
- [ ] **Conclusión** — ¿por qué escoger este proyecto? ¿experiencia?
- [ ] **Referencias** — formato APA

---

## Requisitos de la Presentación

- [ ] Presentación oral del proyecto
- [ ] Demostración del sistema funcionando
- [ ] Todos los integrantes participan
- [ ] Duración: [DURACIÓN] minutos
- [ ] Fecha: [FECHA PRESENTACIÓN]
