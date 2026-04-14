# Arquitectura del Sistema — COMP 2315

## Visión General

El sistema está dividido en 6 clases con responsabilidades separadas. La comunicación fluye de `Main` hacia `MenuUtils` y `ExpedienteManager`, que a su vez usan `Validador`, `ArchivoManager` y `Paciente`.

```
Main
 ├── MenuUtils          (interfaz de usuario)
 └── ExpedienteManager  (lógica de negocio)
      ├── Paciente       (modelo de datos)
      ├── Validador      (validaciones)
      └── ArchivoManager (persistencia)
```

---

## Clases

### `Main.java`
**Rol:** Punto de entrada. Orquesta el flujo completo del programa.

**Flujo:**
1. `ArchivoManager.inicializarCarpeta()` — crea `data/` si no existe
2. `MenuUtils.mostrarMensajeBienvenida()` — pantalla de presentación
3. `MenuUtils.realizarLogin()` — hasta 3 intentos, retorna `boolean`
4. Si login falla → `System.exit(0)`
5. Crea `ExpedienteManager` (carga expedientes del archivo)
6. `while(ejecutando)` → `MenuUtils.mostrarMenuPrincipal()` → `switch(opcion)` → delega a `manager`
7. `MenuUtils.mostrarMensajeDespedida()` → `System.exit(0)`

**Importaciones usadas:** `java.io.*`, `java.util.*`, `javax.swing.*`, `java.text.*`, `java.util.regex.*`

---

### `Paciente.java`
**Rol:** Modelo de datos. Representa un expediente médico completo.

**Campos (13):**

| Campo | Tipo | Descripción |
|---|---|---|
| `nombre` | String | Primer nombre |
| `inicial` | String | Inicial del segundo nombre (puede estar vacía) |
| `apellidos` | String | Apellidos completos |
| `seguroSocial` | String | Formato XXX-XX-XXXX |
| `numeroExpediente` | String | Formato EXP-XXXXX (generado automáticamente) |
| `fechaNacimiento` | String | Formato MM/DD/YYYY |
| `sexo` | String | "M" o "F" |
| `direccion` | String | Calle, Ciudad, Estado, Zip |
| `planMedico` | String | Uno de 6 planes predefinidos |
| `fechaVisita` | String | Formato MM/DD/YYYY |
| `diagnostico` | String | Texto libre |
| `receta` | String | Texto libre (puede estar vacía) |
| `fechaSiguienteVisita` | String | Formato MM/DD/YYYY |

**Métodos clave:**
- `getNombreCompleto()` — retorna `"Nombre I. Apellidos"`
- `toFileString()` — serializa todos los campos separados por `|`
- `fromFileString(String)` — estático, parsea una línea del archivo y retorna un `Paciente`
- `toString()` — representación visual con bordes Unicode para JOptionPane

**Implements:** `Serializable`

---

### `Validador.java`
**Rol:** Utilidad de validaciones. Todos los métodos son `static`.

| Método | Parámetro | Retorna | Lógica |
|---|---|---|---|
| `validarSeguroSocial(ss)` | String | boolean | Regex `^\d{3}-\d{2}-\d{4}$` |
| `validarNumeroExpediente(exp)` | String | boolean | Regex `^EXP-[A-Z0-9]{5}$` |
| `validarFecha(fecha)` | String | boolean | Regex MM/DD/YYYY + `SimpleDateFormat` strict |
| `validarSexo(sexo)` | String | boolean | Igual a "M" o "F" |
| `validarNoVacio(texto)` | String | boolean | No nulo y no solo espacios |
| `validarOpcionPlanMedico(op)` | int | boolean | Entre 1 y 6 |
| `validarRango(n, min, max)` | int, int, int | boolean | n >= min && n <= max |
| `capitalizarNombre(nombre)` | String | String | Divide por espacios, capitaliza cada palabra |

**Patrones regex compilados como constantes:**
- `REGEX_SS` — Seguro Social
- `REGEX_EXP` — Número de expediente
- `REGEX_FECHA` — Fecha MM/DD/YYYY

---

### `ArchivoManager.java`
**Rol:** Persistencia. Lectura y escritura del archivo `data/expedientes.txt`. Todos los métodos son `static`.

**Constantes:**
- `CARPETA_DATA = "data/"` (usa `File.separator` para compatibilidad)
- `ARCHIVO_PRINCIPAL = "data/expedientes.txt"`

**Métodos:**

| Método | Descripción | Modo de archivo |
|---|---|---|
| `inicializarCarpeta()` | Crea `data/` si no existe | `mkdirs()` |
| `guardarExpediente(p)` | Agrega una línea al final | `FileWriter(append=true)` |
| `cargarTodosLosExpedientes()` | Lee todas las líneas y retorna `ArrayList<Paciente>` | `BufferedReader` |
| `reescribirTodosLosExpedientes(lista)` | Sobrescribe el archivo con la lista completa | `FileWriter(append=false)` |
| `existeExpediente(numero)` | Verifica si un número ya está en uso | Carga + for loop |
| `getRutaArchivo()` | Retorna la ruta del archivo | — |

**Formato del archivo (`expedientes.txt`):**
```
EXP-A0001|Juan|A|García López|123-45-6789|01/15/1985|M|Calle 1, Aguadilla, PR, 00603|Triple-S|03/20/2025|Hipertensión|Losartán 50mg|06/20/2025
EXP-A0002|María||Rodríguez Pérez|987-65-4321|07/22/1990|F|Ave 2, Aguadilla, PR, 00604|MMM|03/20/2025|Control rutinario|Ninguna|06/20/2025
```

---

### `ExpedienteManager.java`
**Rol:** Lógica CRUD. Maneja las operaciones sobre expedientes.

**Atributos:**
- `listaPacientes` — `ArrayList<Paciente>` cargado en el constructor
- `contadorExpediente` — `int` para generar números únicos

**Métodos públicos:**

| Método | Descripción |
|---|---|
| `crearExpediente()` | 11 bucles `while` de validación → genera número → crea `Paciente` → guarda |
| `buscarPorNumero()` | Input → `for` sobre `listaPacientes` → muestra o avisa |
| `buscarPorFechaVisita()` | Input con validación → `for` colecta coincidencias → muestra lista |
| `actualizarExpediente()` | Busca → `while` con `switch` de 11 opciones → `reescribirTodos` |
| `listarTodosLosExpedientes()` | Recarga archivo → tabla formateada con `String.format` |
| `getListaPacientes()` | Getter de la lista |

**Método privado:**
- `generarNumeroExpediente()` — bucle `do-while` que genera `EXP-A0001`, `EXP-A0002`... hasta encontrar uno no usado
- `seleccionarPlanMedico()` — `JOptionPane.showInputDialog` con arreglo de 6 planes
- `mostrarCancelado()` — aviso cuando el usuario cancela

---

### `MenuUtils.java`
**Rol:** Interfaz de usuario. Mensajes, login y menú principal. Todos los métodos son `static`.

**Constantes:**
- `USUARIOS[]` — `{"admin", "doctor"}`
- `CONTRASENAS[]` — `{"admin123", "doc2315"}` (arreglos paralelos)
- `MAX_INTENTOS = 3`

**Métodos:**

| Método | Retorna | Descripción |
|---|---|---|
| `mostrarMensajeBienvenida()` | void | JOptionPane con nombres del equipo y propósito |
| `realizarLogin()` | boolean | `while < 3` intentos → valida credenciales |
| `validarCredenciales(u, p)` | boolean | `for` comparando arreglos paralelos |
| `mostrarMenuPrincipal()` | int | `showInputDialog` con 6 opciones → retorna número |
| `mostrarMensajeDespedida()` | void | JOptionPane de cierre |

---

## Flujo de Datos

```
Usuario
  │
  ▼ JOptionPane (MenuUtils)
Main.java
  │
  ▼ crearExpediente()
ExpedienteManager
  │── Validador.validarXxx()     ← valida cada campo
  │── new Paciente(...)          ← crea objeto en memoria
  │── listaPacientes.add(p)      ← agrega a ArrayList
  └── ArchivoManager.guardar()   ← escribe línea en expedientes.txt

  ▼ buscarPorNumero()
ExpedienteManager
  │── for(Paciente p : listaPacientes)
  └── p.toString()               ← muestra en JOptionPane

  ▼ actualizarExpediente()
ExpedienteManager
  │── busca en listaPacientes
  │── modifica campos vía setters
  └── ArchivoManager.reescribir() ← sobrescribe expedientes.txt
```

---

## Decisiones de Diseño

| Decisión | Razón |
|---|---|
| Todos los campos como `String` | Facilita lectura/escritura de archivos y JOptionPane siempre retorna String |
| Separador `|` en el archivo | Permite campos con espacios y comas (ej: dirección) |
| `append=false` en reescribir | Más simple que buscar y reemplazar una línea específica |
| Métodos `static` en Validador/ArchivoManager | No necesitan estado propio — son utilidades puras |
| `ArrayList` en memoria | Permite búsqueda por índice y filtrado sin releer el archivo en cada operación |
