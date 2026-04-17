# TODO — Proyecto Final COMP 2315

## Código Java

### Implementación
- [x] `Paciente.java` — modelo con 13 campos, getters, setters, serialización
- [x] `Validador.java` — charAt()/length() SS y fechas, sexo, no vacío, capitalizar
- [x] `ArchivoManager.java` — guardar, cargar, reescribir, verificar existencia
- [x] `ExpedienteManager.java` — crear, buscar por número, buscar por fecha, actualizar, listar
- [x] `MenuUtils.java` — bienvenida, login con 3 intentos, menú principal, despedida
- [x] `Main.java` — flujo completo con todas las importaciones requeridas

### Estructuras de Control
- [x] `if/else` — validaciones, resultados de búsqueda, login
- [x] `while` — bucle del menú, reintentos de login, validación de cada campo
- [x] `switch` — menú principal (6 opciones) y submenú de actualización (11 opciones)

### Requisitos Técnicos
- [x] Mensaje de bienvenida con JOptionPane
- [x] Mensaje de despedida con JOptionPane
- [x] Login con usuario/contraseña (hardcoded)
- [x] `Paciente[100]` (arreglo fijo) para lista en memoria
- [x] Arreglos paralelos (USUARIOS / CONTRASENAS)
- [x] Métodos propios definidos
- [x] Métodos predefinidos de Java (`charAt()`, `length()`, `.equals()`, etc.)
- [x] `java.io.*` importado y usado (FileReader, PrintWriter)
- [x] `java.util.Scanner` importado y usado
- [x] `javax.swing.*` importado y usado
- [x] Guardar expedientes en archivo (`expedientes.txt`)
- [x] Buscar por número de expediente
- [x] Buscar por fecha de visita
- [x] Actualizar expediente con submenú
- [x] Listar todos los expedientes

### Formato de Código
- [x] Encabezado estilo `SumProd.java` en los 6 archivos
- [x] Comentarios en español línea por línea
- [x] Flujogramas separados del código (en `docs/Flujogramas.html`)
- [x] **Reemplazar placeholders** en los 6 archivos .java con nombres y datos reales

---

## Documentación (`docs/`)

### Flujogramas
- [x] `Flujogramas.html` — visual HTML/CSS para los 6 archivos
- [ ] Tomar capturas de pantalla de los flujogramas para insertar en el informe Word

### Informe (`Informe_ProyectoFinal.html`)
- [x] Portada con placeholders
- [x] Tabla de contenido
- [x] Introducción
- [x] Bitácora (con fechas placeholder)
- [x] Flujogramas (texto ASCII — reemplazar con imágenes del HTML)
- [x] Explicación del menú (con placeholders de capturas)
- [x] Código fuente completo de 6 clases
- [x] Conclusión
- [x] Referencias en APA
- [ ] **Reemplazar todos los placeholders** con datos reales
- [ ] **Insertar capturas de pantalla** del sistema corriendo (login, menú, crear, buscar, actualizar, listar)
- [ ] **Reemplazar flujogramas ASCII** con imágenes del `Flujogramas.html`
- [ ] **Completar fechas de la bitácora** con fechas reales de reuniones
- [ ] Convertir a Word (.docx) y aplicar formato Times New Roman 12, interlineado 1.5, márgenes 1"
- [ ] Numerar páginas (superior derecha)
- [ ] Revisar ortografía y gramática

### Presentación (`Presentacion_ProyectoFinal.html`)
- [x] 12 diapositivas con navegación
- [x] Portada, arquitectura, campos, demos, conceptos, retos, conclusión
- [ ] **Reemplazar placeholders** con nombres reales
- [ ] Practicar presentación oral
- [ ] Preparar demo en vivo del sistema

---

## Pendiente General

- [x] Llenar nombres y números de estudiante en los 6 `.java` y documentación
- [ ] Actualizar fechas de entregas cuando el profesor las anuncie
- [ ] Actualizar duración y fecha de presentación cuando el profesor las anuncie
- [ ] Tomar capturas de pantalla del sistema funcionando
- [ ] Probar flujo completo: crear → buscar → actualizar → listar → salir
- [ ] Verificar que `data/expedientes.txt` se genera correctamente
- [ ] Entregar

---

## Progreso

```
Código:          ████████████████████ 100%
Documentación:   ████████████████████ 100%
Placeholders:    ████████████████████ 100%
Informe Word:    ████████░░░░░░░░░░░░  40%  ← falta capturas + formato + bitácora
Presentación:    ░░░░░░░░░░░░░░░░░░░░   0%  ← por preparar
```
