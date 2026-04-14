# Sistema de Expedientes Médicos — COMP 2315

**Universidad Interamericana de PR, Recinto de Aguadilla**
**Prof. Dr. Edgardo Vargas Moya**

Creado por: [NOMBRE 1] ([NÚMERO ESTUDIANTE 1]) y [NOMBRE 2] ([NÚMERO ESTUDIANTE 2])
Sección: [SECCIÓN] | Fecha de entrega: [FECHA DE ENTREGA]

---

## Descripción

Aplicación en Java para gestionar los expedientes médicos del Dr. Rodríguez.
Permite crear, buscar, actualizar y listar expedientes mediante diálogos JOptionPane.

## Cómo compilar y ejecutar

Desde la carpeta raíz del proyecto (`ProyectoFinal_COMP2315/`):

```bash
# Compilar todos los archivos Java (output a bin/)
javac -d bin src/*.java

# Ejecutar la aplicación
java -cp bin Main
```

## Credenciales de acceso

| Usuario | Contraseña |
|---------|------------|
| admin   | admin123   |
| doctor  | doc2315    |

## Estructura del proyecto

```
ProyectoFinal_COMP2315/
├── src/
│   ├── Main.java              → Punto de entrada, login, menú principal
│   ├── Paciente.java          → Clase con todos los campos del expediente
│   ├── ExpedienteManager.java → Lógica CRUD de expedientes
│   ├── ArchivoManager.java    → Lectura/escritura de archivos .txt
│   ├── Validador.java         → Validaciones de formato (regex, fechas, etc.)
│   └── MenuUtils.java         → Menús, login, mensajes de entrada/salida
├── bin/                       → Archivos .class compilados
├── data/
│   └── expedientes.txt        → Expedientes guardados (generado automáticamente)
├── docs/
│   ├── Informe_ProyectoFinal.docx      → Informe en Word
│   ├── Presentacion_ProyectoFinal.pptx → Presentación en PowerPoint
│   └── html/                           → Versiones HTML de respaldo
└── README.md
```

## Requisitos técnicos implementados

- **JOptionPane** — todos los diálogos de entrada/salida
- **if/else** — validaciones de credenciales, campos, y resultados de búsqueda
- **while** — bucle del menú principal, reintentos de login, validación de campos
- **switch** — menú principal (opciones 1-6) y submenú de actualización (campos 1-11)
- **ArrayList<Paciente>** — lista en memoria de todos los expedientes
- **Métodos propios** — en todas las clases (validarFecha, generarNumeroExpediente, etc.)
- **Métodos predefinidos de Java** — String.format, Pattern.compile, SimpleDateFormat, etc.
- **Archivos** — lectura y escritura en `data/expedientes.txt`
- **Regex** — validación de Seguro Social (XXX-XX-XXXX) y fechas (MM/DD/YYYY)
- **Paquetes importados** — java.io.*, java.util.*, javax.swing.*, java.text.*, java.util.regex.*

## Formatos de campos

| Campo              | Formato                        |
|--------------------|--------------------------------|
| Nombre completo    | Nombre I. Apellido Apellido    |
| Seguro Social      | XXX-XX-XXXX                    |
| Número expediente  | EXP-XXXXX (generado automático)|
| Fechas             | MM/DD/YYYY                     |
| Sexo               | M o F                          |
| Dirección          | Calle, Ciudad, Estado, Zip     |
| Plan médico        | Triple-S / MMM / MCS / Molina / First Medical / Otro |
