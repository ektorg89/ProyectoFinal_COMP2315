# Proyecto Final — COMP 2315

## Información del Curso

| Campo | Detalle |
|---|---|
| Curso | COMP 2315 — Programación Estructurada |
| Universidad | Universidad Interamericana de PR, Recinto de Aguadilla |
| Profesor | Dr. Edgardo Vargas Moya |
| Sección | [SECCIÓN] |

## Grupo de Trabajo

| # | Nombre | Número de Estudiante |
|---|---|---|
| 1 | [NOMBRE 1] | [NÚMERO ESTUDIANTE 1] |
| 2 | [NOMBRE 2] | [NÚMERO ESTUDIANTE 2] |
| 3 | [NOMBRE 3] | [NÚMERO ESTUDIANTE 3] |
| 4 | [NOMBRE 4] | [NÚMERO ESTUDIANTE 4] |

## Descripción del Proyecto

Sistema de expedientes médicos para el consultorio del Dr. Rodríguez. Aplicación en Java con interfaz de consola y diálogos JOptionPane que permite crear, buscar, actualizar y listar expedientes de pacientes con persistencia en archivos de texto.

## Entregas

### Entrega 1 — [FECHA ENTREGA 1]
- Diseño de clases y estructura del proyecto
- Diagrama de flujogramas
- Prototipo funcional básico

### Entrega 2 — [FECHA ENTREGA 2]
- Código fuente completo y funcional
- Archivo de datos generado correctamente
- Todas las validaciones implementadas

### Entrega 3 (Final) — [FECHA ENTREGA FINAL]
- Informe escrito completo
- Presentación oral
- Código final revisado y comentado

## Estructura de Archivos

```
ProyectoFinal_COMP2315/
├── src/
│   ├── Main.java
│   ├── Paciente.java
│   ├── ExpedienteManager.java
│   ├── ArchivoManager.java
│   ├── Validador.java
│   └── MenuUtils.java
├── data/
│   └── expedientes.txt        (generado al correr)
├── docs/
│   ├── Flujogramas.html
│   ├── Informe_ProyectoFinal.html
│   └── Presentacion_ProyectoFinal.html
├── PROJECT_OVERVIEW.md
├── REQUIREMENTS.md
├── ARCHITECTURE.md
├── TODO.md
└── README.md
```

## Cómo Compilar y Ejecutar

```bash
# Desde la carpeta ProyectoFinal_COMP2315/
export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"
javac -d . src/*.java
java Main
```

**Credenciales:** `admin` / `admin123` — `doctor` / `doc2315`
