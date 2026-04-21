# Sistema de Expedientes Médicos — COMP 2315

**Universidad Interamericana de PR, Recinto de Aguadilla**
**Prof. Dr. Edgardo Vargas Moya**

Creado por: Ektor M. Gonzalez (A00617167), Diego L. Rodriguez Perez (A00636906),
Donovan Irizarry Figueroa (A00671799), Carlo Ramos (A00642991)
Sección: 202630.73752 | Fecha de entrega: Por anunciarse

---

## Descripción

Aplicación en Java para gestionar los expedientes médicos del Dr. Rodríguez.
Permite crear, buscar, actualizar y listar expedientes mediante diálogos JOptionPane.

## Cómo compilar y ejecutar

Desde la carpeta raíz del proyecto (`ProyectoFinal_COMP2315/`):

```bash
# Compilar todos los archivos Java (output a class/)
javac -d class java/*.java

# Ejecutar la aplicación
java -cp class Main
```

## Credenciales de acceso

| Usuario | Contraseña |
|---------|------------|
| admin   | admin123   |
| doctor  | doc2315    |

## Estructura del proyecto

```
ProyectoFinal_COMP2315/
├── java/
│   ├── Main.java              → Punto de entrada, login, menú principal
│   ├── Paciente.java          → Clase con todos los campos del expediente
│   ├── ExpedienteManager.java → Lógica CRUD de expedientes
│   ├── ArchivoManager.java    → Lectura/escritura de archivos .txt
│   ├── Validador.java         → Validaciones de formato (charAt, length)
│   └── MenuUtils.java         → Menús, login, mensajes de entrada/salida
├── class/                     → Archivos .class compilados
├── docs/
│   └── Documentacion_ProyectoFinal_v2.docx → Documentación del proyecto
├── expedientes.txt            → Expedientes guardados (generado automáticamente)
└── README.md
```

## Requisitos técnicos implementados

- **JOptionPane** — todos los diálogos de entrada/salida (showInputDialog, showMessageDialog)
- **if/else** — validaciones de credenciales, campos, y resultados de búsqueda
- **while / do-while** — bucle del menú principal, reintentos de login, validación de campos
- **for (indexado)** — recorrido de arreglos, construcción de separadores, búsquedas
- **switch** — menú principal (opciones 1-6) y submenú de actualización (campos 1-11)
- **Arreglo fijo Paciente[100]** — lista en memoria de todos los expedientes
- **Arreglos paralelos** — credenciales de acceso (USUARIOS[] / CONTRASENAS[])
- **Métodos propios** — en todas las clases (validarFecha, generarNumeroExpediente, etc.)
- **Archivos** — FileReader + Scanner para leer, PrintWriter para escribir (Lección 15)
- **Validaciones con charAt() y length()** — Seguro Social (XXX-XX-XXXX), fechas (MM/DD/YYYY)
- **Paquetes importados** — java.io.*, java.util.Scanner, javax.swing.*

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
