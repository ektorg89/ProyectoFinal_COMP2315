# Proyecto Final — COMP 2315

## Información del Curso

| Campo | Detalle |
|---|---|
| Curso | COMP 2315 — Programación Estructurada |
| Universidad | Universidad Interamericana de PR, Recinto de Aguadilla |
| Profesor | Dr. Edgardo Vargas Moya |
| Sección | 202630.73752 |

## Grupo de Trabajo

| # | Nombre | Número de Estudiante |
|---|---|---|
| 1 | Ektor M. Gonzalez | A00617167 |
| 2 | Diego L. Rodriguez Perez | A00636906 |
| 3 | Donovan Irizarry Figueroa | A00671799 |
| 4 | Carlo Ramos | A00642991 |

## Descripción del Proyecto

Sistema de expedientes médicos para el consultorio del Dr. Rodríguez. Aplicación en Java con interfaz de consola y diálogos JOptionPane que permite crear, buscar, actualizar y listar expedientes de pacientes con persistencia en archivos de texto.

## Entregas

### Entrega 1 — Por anunciarse
- Diseño de clases y estructura del proyecto
- Diagrama de flujogramas
- Prototipo funcional básico

### Entrega 2 — Por anunciarse
- Código fuente completo y funcional
- Archivo de datos generado correctamente
- Todas las validaciones implementadas

### Entrega 3 (Final) — Por anunciarse
- Informe escrito completo
- Presentación oral
- Código final revisado y comentado

## Estructura de Archivos

```
ProyectoFinal_COMP2315/
├── java/
│   ├── Main.java
│   ├── Paciente.java
│   ├── ExpedienteManager.java
│   ├── ArchivoManager.java
│   ├── Validador.java
│   └── MenuUtils.java
├── class/                     (archivos .class compilados)
├── docs/
│   └── Documentacion_ProyectoFinal_v2.docx
├── expedientes.txt            (generado al correr)
├── PROJECT_OVERVIEW.md
├── REQUIREMENTS.md
├── ARCHITECTURE.md
├── TODO.md
└── README.md
```

## Cómo Compilar y Ejecutar

```bash
# Desde la carpeta ProyectoFinal_COMP2315/
javac -d class java/*.java
java -cp class Main
```

**Credenciales:** `admin` / `admin123` — `doctor` / `doc2315`
