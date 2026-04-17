#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Genera docs/Flujogramas_COMP2315.pdf — 7 flujogramas estilo Raptor
Sistema de Expedientes Médicos del Dr. Rodríguez — COMP 2315
"""

from reportlab.pdfgen import canvas
from reportlab.lib.pagesizes import letter
from reportlab.lib import colors

W, H = letter   # 612 × 792 pt
CX   = W / 2    # 306

# ── Paleta Raptor ─────────────────────────────────────────────────────────────
CS = colors.Color(0.71, 0.92, 0.71)   # verde    Inicio/Fin
CI = colors.Color(0.65, 0.83, 0.96)   # azul     Entrada/Salida
CP = colors.Color(1.00, 0.97, 0.77)   # amarillo Proceso
CD = colors.Color(1.00, 0.84, 0.52)   # naranja  Decisión
CE = colors.Color(0.95, 0.72, 0.72)   # rojo     Fin alternativo
CK = colors.black

# ── Medidas ───────────────────────────────────────────────────────────────────
OW, OH = 140, 30    # Óvalo
RW, RH = 260, 36    # Rectángulo
PW, PH = 260, 36    # Paralelogramo
DW, DH = 230, 52    # Rombo  (derecha termina en CX+115)
SW, SH = 130, 28    # Figuras pequeñas laterales
GAP    = 18         # Espacio entre figuras

# Columna lateral derecha: el borde izquierdo de las figuras laterales arranca
# en CX+DW/2+20 = 306+115+20 = 441, centrado en CX+DW/2+20+SW/2 = 506
RC  = int(CX + DW/2 + 20 + SW/2)   # ≈ 506  centro de figuras laterales
SL  = RC - SW//2                    # borde izquierdo de figuras laterales

# Borde izquierdo para líneas de loop-back
LX  = 26

# ─────────────────────────────────────────────────────────────────────────────
# Primitivas de texto y figuras
# ─────────────────────────────────────────────────────────────────────────────

def _text(c, cx, cy, text, fs, bold=False):
    parts = [p.strip() for p in text.strip().split('\n')]
    lh = fs + 2
    sy = cy + (len(parts) - 1) * lh / 2 - 1
    for p in parts:
        c.setFont('Helvetica-Bold' if bold else 'Helvetica', fs)
        c.setFillColor(CK)
        c.drawCentredString(cx, sy, p)
        sy -= lh

def oval(c, cx, cy, text, color=None):
    if color is None: color = CS
    c.setFillColor(color); c.setStrokeColor(CK); c.setLineWidth(1.5)
    c.ellipse(cx-OW/2, cy-OH/2, cx+OW/2, cy+OH/2, fill=1)
    _text(c, cx, cy, text, 10, bold=True)

def rect(c, cx, cy, text, color=None, w=None, h=None, fs=8):
    if color is None: color = CP
    if w is None: w = RW
    if h is None: h = RH
    c.setFillColor(color); c.setStrokeColor(CK); c.setLineWidth(1.5)
    c.rect(cx-w/2, cy-h/2, w, h, fill=1)
    _text(c, cx, cy, text, fs)

def para(c, cx, cy, text, color=None, w=None, h=None, fs=8):
    if color is None: color = CI
    if w is None: w = PW
    if h is None: h = PH
    off = 12
    c.setFillColor(color); c.setStrokeColor(CK); c.setLineWidth(1.5)
    p = c.beginPath()
    p.moveTo(cx-w/2+off, cy+h/2); p.lineTo(cx+w/2+off, cy+h/2)
    p.lineTo(cx+w/2-off, cy-h/2); p.lineTo(cx-w/2-off, cy-h/2)
    p.close()
    c.drawPath(p, fill=1, stroke=1)
    _text(c, cx, cy, text, fs)

def diamond(c, cx, cy, text, color=None, w=None, h=None, fs=7.5):
    if color is None: color = CD
    if w is None: w = DW
    if h is None: h = DH
    c.setFillColor(color); c.setStrokeColor(CK); c.setLineWidth(1.5)
    p = c.beginPath()
    p.moveTo(cx, cy+h/2); p.lineTo(cx+w/2, cy)
    p.lineTo(cx, cy-h/2); p.lineTo(cx-w/2, cy)
    p.close()
    c.drawPath(p, fill=1, stroke=1)
    _text(c, cx, cy, text, fs)

# ─────────────────────────────────────────────────────────────────────────────
# Flechas  (todas alineadas: la línea llega exactamente a la base de la punta)
# ─────────────────────────────────────────────────────────────────────────────
AH = 10   # altura/ancho del triángulo de punta de flecha
AW = 5    # medio-ancho del triángulo

def av(c, x, y1, y2):
    """Flecha vertical hacia abajo: de y1 (top) a y2 (bottom, punta aquí)."""
    c.setStrokeColor(CK); c.setLineWidth(1.2)
    c.line(x, y1, x, y2 + AH)          # línea llega hasta la BASE de la punta
    c.setFillColor(CK)
    p = c.beginPath()
    p.moveTo(x,    y2)                  # punta (abajo)
    p.lineTo(x-AW, y2+AH)              # base izquierda
    p.lineTo(x+AW, y2+AH)              # base derecha
    p.close()
    c.drawPath(p, fill=1, stroke=0)

def ah_r(c, x1, x2, y):
    """Flecha horizontal hacia la derecha: de x1 a x2 (punta aquí)."""
    c.setStrokeColor(CK); c.setLineWidth(1.2)
    c.line(x1, y, x2 - AH, y)          # línea llega hasta la BASE de la punta
    c.setFillColor(CK)
    p = c.beginPath()
    p.moveTo(x2,    y)                  # punta (derecha)
    p.lineTo(x2-AH, y+AW)
    p.lineTo(x2-AH, y-AW)
    p.close()
    c.drawPath(p, fill=1, stroke=0)

def ah_l(c, x1, x2, y):
    """Flecha horizontal hacia la izquierda: de x1 a x2 (punta aquí)."""
    c.setStrokeColor(CK); c.setLineWidth(1.2)
    c.line(x1, y, x2 + AH, y)
    c.setFillColor(CK)
    p = c.beginPath()
    p.moveTo(x2,    y)
    p.lineTo(x2+AH, y+AW)
    p.lineTo(x2+AH, y-AW)
    p.close()
    c.drawPath(p, fill=1, stroke=0)

def ln(c, x1, y1, x2, y2):
    """Línea simple sin punta."""
    c.setStrokeColor(CK); c.setLineWidth(1.2)
    c.line(x1, y1, x2, y2)

def loop_back(c, from_x, from_y_bottom, target_cy, lx=LX):
    """
    Dibuja la flecha de retorno de un ciclo MIENTRAS:
      1. Baja verticalmente desde from_y_bottom hasta una línea base.
      2. Va a la izquierda hasta lx.
      3. Sube hasta target_cy.
      4. Flecha horizontal → entra al rombo por su borde izquierdo (CX - DW/2).
    """
    base_y = from_y_bottom - 14        # línea base debajo de la última figura
    diamond_left = CX - DW/2

    ln(c, from_x,  from_y_bottom, from_x, base_y)  # bajar
    ln(c, from_x,  base_y,  lx,   base_y)           # ir a la izquierda
    ln(c, lx,      base_y,  lx,   target_cy)        # subir
    ah_r(c, lx, diamond_left, target_cy)             # flecha → borde izq. del rombo

def lbl(c, x, y, text, fs=7, bold=False):
    c.setFillColor(CK)
    c.setFont('Helvetica-Bold' if bold else 'Helvetica', fs)
    c.drawString(x, y, text)

def si_abajo(c, cx, cy_diam):
    """Etiqueta 'Sí' justo debajo del vértice inferior del rombo."""
    lbl(c, cx+3, cy_diam - DH/2 - 11, 'Sí', bold=True)

def no_derecha(c, cx, cy_diam):
    """Etiqueta 'No' justo a la derecha del vértice derecho del rombo."""
    lbl(c, cx + DW/2 + 4, cy_diam + 3, 'No', bold=True)

def si_derecha(c, cx, cy_diam):
    lbl(c, cx + DW/2 + 4, cy_diam + 3, 'Sí', bold=True)

def no_abajo(c, cx, cy_diam):
    lbl(c, cx+3, cy_diam - DH/2 - 11, 'No', bold=True)

def no_izquierda(c, cx, cy_diam):
    lbl(c, cx - DW/2 - 22, cy_diam + 3, 'No', bold=True)

# Rama lateral: flecha + figura pequeña a la derecha del rombo
def rama_der(c, cy_diam, text, color=CI, shape='para'):
    """Dibuja flecha + figura pequeña a la derecha de un rombo estándar."""
    ah_r(c, CX + DW/2, SL, cy_diam)           # flecha → borde izq. figura lateral
    if shape == 'rect':
        rect(c, RC, cy_diam, text, color=color, w=SW, h=SH, fs=7)
    else:
        para(c, RC, cy_diam, text, color=color, w=SW, h=SH, fs=7)
    return RC, cy_diam   # retorna centro de la figura lateral

def fin_der(c, cy_diam, text='return → FIN', color=CE):
    """Flecha + figura lateral + flecha + óvalo FIN a la derecha de un rombo."""
    rama_der(c, cy_diam, text, color=CI)
    fin_cy = cy_diam - SH/2 - GAP - OH/2
    av(c, RC, cy_diam - SH/2, fin_cy + OH/2)
    oval(c, RC, fin_cy, 'FIN', color=color)
    return fin_cy

# ─────────────────────────────────────────────────────────────────────────────
# Cabecera y pie de página
# ─────────────────────────────────────────────────────────────────────────────

def hdr(c, t1, t2=''):
    c.setFillColor(colors.HexColor('#0D47A1'))
    c.setFont('Helvetica-Bold', 13)
    c.drawCentredString(CX, H-35, t1)
    if t2:
        c.setFillColor(colors.HexColor('#455A64'))
        c.setFont('Helvetica', 8.5)
        c.drawCentredString(CX, H-51, t2)
    c.setStrokeColor(colors.HexColor('#0D47A1')); c.setLineWidth(1.5)
    c.line(36, H-59, W-36, H-59)

def ftr(c, n):
    c.setFillColor(colors.HexColor('#757575'))
    c.setFont('Helvetica', 6)
    c.drawCentredString(CX, 11,
        f'COMP 2315 — Programación Estructurada — Sistema de Expedientes Médicos  |  Página {n}')
    items = [(CS,'Inicio/Fin'),(CI,'Entrada/Salida'),(CP,'Proceso'),(CD,'Decisión')]
    lx = 36
    for col, name in items:
        c.setFillColor(col); c.setStrokeColor(CK); c.setLineWidth(0.5)
        c.rect(lx, 21, 18, 10, fill=1)
        c.setFillColor(CK); c.setFont('Helvetica', 6)
        c.drawString(lx+21, 25, name)
        lx += 110


# ─────────────────────────────────────────────────────────────────────────────
# Pág. 1 — Flujo Principal (Main.java)
# ─────────────────────────────────────────────────────────────────────────────

def page1(c):
    hdr(c, 'Flujograma 1: Flujo Principal del Sistema',
        'Archivo: Main.java — método main()')
    ftr(c, 1)

    # Centros Y de cada figura, calculados de arriba hacia abajo
    y0  = H - 82
    y1  = y0  - OH/2 - GAP - PH/2
    y2  = y1  - PH/2 - GAP - RH/2
    y3  = y2  - RH/2 - GAP - DH/2
    y4  = y3  - DH/2 - GAP - RH/2
    y5  = y4  - RH/2 - GAP - RH/2
    y6  = y5  - RH/2 - GAP - DH/2   # MIENTRAS ejecutando  ← loop top
    y7  = y6  - DH/2 - GAP - PH/2
    y8  = y7  - PH/2 - GAP - DH/2
    y9  = y8  - DH/2 - GAP - RH/2
    y10 = y9  - RH/2 - GAP - DH/2

    # INICIO
    oval(c, CX, y0, 'INICIO')
    av(c, CX, y0-OH/2, y1+PH/2)

    # mostrarMensajeBienvenida()
    para(c, CX, y1, 'SALIDA: mostrarMensajeBienvenida()')
    av(c, CX, y1-PH/2, y2+RH/2)

    # loginExitoso = realizarLogin()
    rect(c, CX, y2, 'loginExitoso = realizarLogin()')
    av(c, CX, y2-RH/2, y3+DH/2)

    # ¿loginExitoso?
    diamond(c, CX, y3, '¿loginExitoso?')
    si_abajo(c, CX, y3)
    av(c, CX, y3-DH/2, y4+RH/2)
    no_derecha(c, CX, y3)
    fin_der(c, y3, 'SALIDA: "Acceso\nDenegado"')

    # new ExpedienteManager()
    rect(c, CX, y4, 'manager = new ExpedienteManager()')
    av(c, CX, y4-RH/2, y5+RH/2)

    # ejecutando = true
    rect(c, CX, y5, 'ejecutando = true')
    av(c, CX, y5-RH/2, y6+DH/2)

    # MIENTRAS ejecutando  ← inicio del ciclo
    diamond(c, CX, y6, 'MIENTRAS\n¿ejecutando?')
    si_abajo(c, CX, y6)
    av(c, CX, y6-DH/2, y7+PH/2)
    no_derecha(c, CX, y6)
    fin_der(c, y6, 'SALIDA:\nmostrarMensajeDespedida()')

    # opcion = mostrarMenuPrincipal()
    para(c, CX, y7, 'ENTRADA: opcion = mostrarMenuPrincipal()')
    av(c, CX, y7-PH/2, y8+DH/2)

    # ¿opcion == -1?
    diamond(c, CX, y8, '¿opcion == -1?')
    no_abajo(c, CX, y8)
    av(c, CX, y8-DH/2, y9+RH/2)
    # Sí → derecha: opcion = 6, luego regresa al flujo principal
    si_derecha(c, CX, y8)
    ah_r(c, CX+DW/2, SL, y8)
    rect(c, RC, y8, 'opcion = 6', color=CP, w=SW, h=SH, fs=8)
    ln(c,  RC, y8-SH/2,  RC,     y9+RH/2+4)
    ah_l(c, RC, CX+RW/2, y9+RH/2+4)

    # switch(opcion): Ejecutar acción
    rect(c, CX, y9, 'PROCESO: switch(opcion)\nEjecutar acción (cases 1–5)')
    av(c, CX, y9-RH/2, y10+DH/2)

    # case 6: ¿resp == "S"?
    diamond(c, CX, y10, 'case 6: pedir confirmación\n¿resp == "S"?')
    # Sí → ejecutando = false
    si_derecha(c, CX, y10)
    ah_r(c, CX+DW/2, SL, y10)
    rect(c, RC, y10, 'ejecutando = false', color=CP, w=SW, h=SH, fs=8)
    # No y Sí (ejecutando=false) → loop-back al MIENTRAS
    no_izquierda(c, CX, y10)
    # rama No: desde borde izq. del rombo → loop-back
    loop_back(c, CX, y10-DH/2, y6)
    # rama Sí: desde bottom del rect lateral → misma base del loop
    base_y = y10 - DH/2 - 14
    ln(c, RC, y10-SH/2, RC, base_y)
    ln(c, RC, base_y,   LX, base_y)


# ─────────────────────────────────────────────────────────────────────────────
# Pág. 2 — Proceso de Login (MenuUtils.realizarLogin)
# ─────────────────────────────────────────────────────────────────────────────

def page2(c):
    hdr(c, 'Flujograma 2: Proceso de Login',
        'Archivo: MenuUtils.java — método realizarLogin()')
    ftr(c, 2)

    y0 = H - 82
    y1 = y0  - OH/2 - GAP - RH/2
    y2 = y1  - RH/2 - GAP - DH/2   # MIENTRAS intentos < MAX  ← loop top
    y3 = y2  - DH/2 - GAP - PH/2
    y4 = y3  - PH/2 - GAP - DH/2
    y5 = y4  - DH/2 - GAP - PH/2
    y6 = y5  - PH/2 - GAP - DH/2
    y7 = y6  - DH/2 - GAP - DH/2
    y8 = y7  - DH/2 - GAP - PH/2
    y9 = y8  - PH/2 - GAP - OH/2

    oval(c, CX, y0, 'INICIO')
    av(c, CX, y0-OH/2, y1+RH/2)

    rect(c, CX, y1, 'intentos = 0  /  MAX_INTENTOS = 3')
    av(c, CX, y1-RH/2, y2+DH/2)

    # MIENTRAS intentos < MAX_INTENTOS
    diamond(c, CX, y2, 'MIENTRAS\n¿intentos < MAX_INTENTOS?')
    si_abajo(c, CX, y2)
    av(c, CX, y2-DH/2, y3+PH/2)
    no_derecha(c, CX, y2)
    fin_der(c, y2, 'SALIDA: "Número máx.\nde intentos alcanzado"')

    # ENTRADA: usuario
    para(c, CX, y3, 'ENTRADA: usuario = showInputDialog(...)')
    av(c, CX, y3-PH/2, y4+DH/2)

    # ¿usuario == null?
    diamond(c, CX, y4, '¿usuario == null?')
    no_abajo(c, CX, y4)
    av(c, CX, y4-DH/2, y5+PH/2)
    si_derecha(c, CX, y4)
    fin_der(c, y4, 'return false → FIN')

    # ENTRADA: contrasena
    para(c, CX, y5, 'ENTRADA: contrasena = showInputDialog(...)')
    av(c, CX, y5-PH/2, y6+DH/2)

    # ¿contrasena == null?
    diamond(c, CX, y6, '¿contrasena == null?')
    no_abajo(c, CX, y6)
    av(c, CX, y6-DH/2, y7+DH/2)
    si_derecha(c, CX, y6)
    fin_der(c, y6, 'return false → FIN')

    # ¿validarCredenciales()?
    diamond(c, CX, y7, '¿validarCredenciales\n(usuario, contrasena)?')
    si_abajo(c, CX, y7)
    av(c, CX, y7-DH/2, y8+PH/2)
    # No → loop-back: intentos++ → volver al MIENTRAS
    no_izquierda(c, CX, y7)
    lbl(c, LX+2, (y2+y7)/2 + 15, 'intentos++', fs=6.5)
    loop_back(c, CX, y7-DH/2, y2)

    # SALIDA: Bienvenido
    para(c, CX, y8, 'SALIDA: "Bienvenido, " + usuario')
    av(c, CX, y8-PH/2, y9+OH/2)

    oval(c, CX, y9, 'return true → FIN')


# ─────────────────────────────────────────────────────────────────────────────
# Pág. 3 — Crear Expediente
# ─────────────────────────────────────────────────────────────────────────────

def page3(c):
    hdr(c, 'Flujograma 3: Crear Nuevo Expediente',
        'Archivo: ExpedienteManager.java — método crearExpediente()')
    ftr(c, 3)

    y0  = H - 82
    y1  = y0  - OH/2  - GAP - PH/2   # DO: ENTRADA nombre
    y2  = y1  - PH/2  - GAP - DH/2   # ¿nombre cancelado?
    y3  = y2  - DH/2  - GAP - DH/2   # ¿nombre vacío?
    y4  = y3  - DH/2  - GAP - PH/2   # ENTRADA inicial
    y5  = y4  - PH/2  - GAP - DH/2   # ¿inicial cancelada?
    y6  = y5  - DH/2  - GAP - RH/2 - 7  # PROCESO: recolectar campos restantes
    y7  = y6  - RH/2 - 7 - GAP - RH/2   # Generar número
    y8  = y7  - RH/2  - GAP - RH/2   # Crear objeto Paciente
    y9  = y8  - RH/2  - GAP - RH/2   # guardar
    y10 = y9  - RH/2  - GAP - DH/2   # ¿guardado?
    y11 = y10 - DH/2  - GAP - PH/2   # SALIDA éxito
    y12 = y11 - PH/2  - GAP - OH/2   # FIN

    oval(c, CX, y0, 'INICIO')
    av(c, CX, y0-OH/2, y1+PH/2)

    # DO-WHILE nombre
    lbl(c, CX-RW/2+2, y1+PH/2+4, 'HACER:', fs=7, bold=True)
    para(c, CX, y1, 'ENTRADA: nombre = showInputDialog(...)')
    av(c, CX, y1-PH/2, y2+DH/2)

    diamond(c, CX, y2, '¿nombre == null?\n(usuario canceló)')
    no_abajo(c, CX, y2)
    av(c, CX, y2-DH/2, y3+DH/2)
    si_derecha(c, CX, y2)
    fin_der(c, y2, 'SALIDA: "Cancelado"\nreturn → FIN')

    diamond(c, CX, y3, '¿!validarNoVacio(nombre)?')
    no_abajo(c, CX, y3)
    av(c, CX, y3-DH/2, y4+PH/2)
    # Sí (nombre inválido) → error + loop-back DO al y1
    si_derecha(c, CX, y3)
    ah_r(c, CX+DW/2, SL, y3)
    para(c, RC, y3, 'SALIDA: "Nombre no\npuede estar vacío"', w=SW, h=SH, fs=7)
    lbl(c, RC-SW//2, y3-SH/2-13, 'MIENTRAS(!validarNoVacio)', fs=5.5)
    ln(c, RC, y3-SH/2, RC, y1+PH/2)   # sube por la derecha
    ah_l(c, RC, CX+PW/2, y1+PH/2)     # flecha ← al borde der. del paralelogramo

    # ENTRADA inicial (opcional)
    para(c, CX, y4, 'ENTRADA: inicial = showInputDialog(...)\n(campo opcional — puede estar vacío)')
    av(c, CX, y4-PH/2, y5+DH/2)

    diamond(c, CX, y5, '¿inicial == null?')
    no_abajo(c, CX, y5)
    av(c, CX, y5-DH/2, y6+RH/2+7)
    si_derecha(c, CX, y5)
    fin_der(c, y5, 'SALIDA: "Cancelado"\nreturn → FIN')

    # PROCESO: recolectar campos restantes (box más alto)
    rect(c, CX, y6,
         'PROCESO: recolectar y validar campos restantes\n'
         '(apellidos, SS, fechaNac, sexo, dirección, planMedico,\n'
         ' fechaVisita, diagnóstico, receta, fechaSiguienteVisita)\n'
         '  → cada campo: MIENTRAS(!válido) pedir de nuevo',
         w=RW+10, h=50, fs=7)
    av(c, CX, y6-25, y7+RH/2)

    rect(c, CX, y7, 'numeroExp = generarNumeroExpediente()')
    av(c, CX, y7-RH/2, y8+RH/2)

    rect(c, CX, y8, 'nuevo = new Paciente(nombre, inicial, apellidos, ss, numeroExp, ...)')
    av(c, CX, y8-RH/2, y9+RH/2)

    rect(c, CX, y9, 'guardado = reescribirTodosLosExpedientes(listaPacientes, total)')
    av(c, CX, y9-RH/2, y10+DH/2)

    diamond(c, CX, y10, '¿guardado exitoso?')
    si_abajo(c, CX, y10)
    av(c, CX, y10-DH/2, y11+PH/2)
    no_derecha(c, CX, y10)
    fin_der(c, y10, 'SALIDA: "Error al guardar"')

    para(c, CX, y11, 'SALIDA: "Expediente " + numeroExp + " creado exitosamente"')
    av(c, CX, y11-PH/2, y12+OH/2)
    oval(c, CX, y12, 'FIN')


# ─────────────────────────────────────────────────────────────────────────────
# Pág. 4 — Buscar por Número
# ─────────────────────────────────────────────────────────────────────────────

def page4(c):
    hdr(c, 'Flujograma 4: Buscar Expediente por Número',
        'Archivo: ExpedienteManager.java — método buscarPorNumero()')
    ftr(c, 4)

    y0 = H - 82
    y1 = y0  - OH/2 - GAP - PH/2
    y2 = y1  - PH/2 - GAP - DH/2
    y3 = y2  - DH/2 - GAP - RH/2
    y4 = y3  - RH/2 - GAP - RH/2
    y5 = y4  - RH/2 - GAP - DH/2
    y6 = y5  - DH/2 - GAP - PH/2
    y7 = y6  - PH/2 - GAP - OH/2

    oval(c, CX, y0, 'INICIO')
    av(c, CX, y0-OH/2, y1+PH/2)

    para(c, CX, y1, 'ENTRADA: busqueda = showInputDialog("Número de expediente:")')
    av(c, CX, y1-PH/2, y2+DH/2)

    diamond(c, CX, y2, '¿busqueda == null\no vacío?')
    no_abajo(c, CX, y2)
    av(c, CX, y2-DH/2, y3+RH/2)
    si_derecha(c, CX, y2)
    fin_der(c, y2, 'SALIDA: "Búsqueda\ncancelada"\nreturn → FIN')

    rect(c, CX, y3, 'encontrado = null  /  terminado = false')
    av(c, CX, y3-RH/2, y4+RH/2)

    rect(c, CX, y4,
         'PARA i=0; i < totalPacientes && !terminado; i++\n'
         '  SI listaPacientes[i].getNumeroExpediente().equals(busqueda)\n'
         '    encontrado = listaPacientes[i]  /  terminado = true')
    av(c, CX, y4-RH/2, y5+DH/2)

    diamond(c, CX, y5, '¿encontrado != null?')
    si_abajo(c, CX, y5)
    av(c, CX, y5-DH/2, y6+PH/2)
    no_derecha(c, CX, y5)
    fin_der(c, y5, 'SALIDA: "No se encontró\nexpediente: " + busqueda')

    para(c, CX, y6, 'SALIDA: showMessageDialog(encontrado.toString())')
    av(c, CX, y6-PH/2, y7+OH/2)
    oval(c, CX, y7, 'FIN')


# ─────────────────────────────────────────────────────────────────────────────
# Pág. 5 — Buscar por Fecha de Visita
# ─────────────────────────────────────────────────────────────────────────────

def page5(c):
    hdr(c, 'Flujograma 5: Buscar Expediente por Fecha de Visita',
        'Archivo: ExpedienteManager.java — método buscarPorFechaVisita()')
    ftr(c, 5)

    y0 = H - 82
    y1 = y0  - OH/2 - GAP - PH/2   # ENTRADA fecha  ← loop top validación
    y2 = y1  - PH/2 - GAP - DH/2
    y3 = y2  - DH/2 - GAP - DH/2
    y4 = y3  - DH/2 - GAP - RH/2
    y5 = y4  - RH/2 - GAP - RH/2
    y6 = y5  - RH/2 - GAP - DH/2
    y7 = y6  - DH/2 - GAP - PH/2
    y8 = y7  - PH/2 - GAP - OH/2

    oval(c, CX, y0, 'INICIO')
    av(c, CX, y0-OH/2, y1+PH/2)

    # MIENTRAS(!validarFecha) — loop de validación
    lbl(c, CX-RW/2+2, y1+PH/2+4, 'MIENTRAS(!validarFecha):', fs=7, bold=True)
    para(c, CX, y1, 'ENTRADA: fecha = showInputDialog("Fecha MM/DD/YYYY:")')
    av(c, CX, y1-PH/2, y2+DH/2)

    diamond(c, CX, y2, '¿fecha == null?')
    no_abajo(c, CX, y2)
    av(c, CX, y2-DH/2, y3+DH/2)
    si_derecha(c, CX, y2)
    fin_der(c, y2, 'SALIDA: "Búsqueda\ncancelada"\nreturn → FIN')

    diamond(c, CX, y3, '¿!validarFecha(fecha)?\n(formato incorrecto)')
    no_abajo(c, CX, y3)
    av(c, CX, y3-DH/2, y4+RH/2)
    # Sí (inválida) → error + loop-back
    si_derecha(c, CX, y3)
    ah_r(c, CX+DW/2, SL, y3)
    para(c, RC, y3, 'SALIDA: "Use el formato\nMM/DD/YYYY"', w=SW, h=SH, fs=7)
    lbl(c, RC-SW//2, y3-SH/2-11, 'Volver a pedir fecha', fs=5.5)
    ln(c, RC, y3-SH/2, RC, y1+PH/2)
    ah_l(c, RC, CX+PW/2, y1+PH/2)

    rect(c, CX, y4, 'resultados[] = new Paciente[100]  /  totalResultados = 0')
    av(c, CX, y4-RH/2, y5+RH/2)

    rect(c, CX, y5,
         'PARA i=0; i < totalPacientes; i++\n'
         '  SI listaPacientes[i].getFechaVisita().equals(fecha)\n'
         '    resultados[totalResultados] = listaPacientes[i]  /  totalResultados++')
    av(c, CX, y5-RH/2, y6+DH/2)

    diamond(c, CX, y6, '¿totalResultados == 0?')
    no_abajo(c, CX, y6)
    av(c, CX, y6-DH/2, y7+PH/2)
    si_derecha(c, CX, y6)
    fin_der(c, y6, 'SALIDA: "No se encontraron\nexpedientes con esa fecha"')

    para(c, CX, y7,
         'SALIDA: construir y mostrar lista de resultados\n'
         '(PARA i=0; i<totalResultados → sb = sb + resultados[i].toString())')
    av(c, CX, y7-PH/2, y8+OH/2)
    oval(c, CX, y8, 'FIN')


# ─────────────────────────────────────────────────────────────────────────────
# Pág. 6 — Actualizar Expediente
# ─────────────────────────────────────────────────────────────────────────────

def page6(c):
    hdr(c, 'Flujograma 6: Actualizar Expediente',
        'Archivo: ExpedienteManager.java — método actualizarExpediente()')
    ftr(c, 6)

    y0  = H - 82
    y1  = y0  - OH/2 - GAP - PH/2
    y2  = y1  - PH/2 - GAP - DH/2
    y3  = y2  - DH/2 - GAP - DH/2
    y4  = y3  - DH/2 - GAP - RH/2
    y5  = y4  - RH/2 - GAP - DH/2   # MIENTRAS continuar  ← loop top
    y6  = y5  - DH/2 - GAP - PH/2
    y7  = y6  - PH/2 - GAP - DH/2
    y8  = y7  - DH/2 - GAP - RH/2
    y9  = y8  - RH/2 - GAP - DH/2
    y10 = y9  - DH/2 - GAP - RH/2
    y11 = y10 - RH/2 - GAP - PH/2
    y12 = y11 - PH/2 - GAP - OH/2

    oval(c, CX, y0, 'INICIO')
    av(c, CX, y0-OH/2, y1+PH/2)

    para(c, CX, y1, 'ENTRADA: busqueda = showInputDialog("Número de expediente:")')
    av(c, CX, y1-PH/2, y2+DH/2)

    diamond(c, CX, y2, '¿busqueda == null\no vacío?')
    no_abajo(c, CX, y2)
    av(c, CX, y2-DH/2, y3+DH/2)
    si_derecha(c, CX, y2)
    fin_der(c, y2, 'SALIDA: "Operación\ncancelada"\nreturn → FIN')

    diamond(c, CX, y3,
            'PARA i=0; i<totalPacientes && !encontrado\n'
            '¿listaPacientes[i].getNumeroExp().equals(busqueda)?', fs=7)
    si_abajo(c, CX, y3)
    av(c, CX, y3-DH/2, y4+RH/2)
    no_derecha(c, CX, y3)
    fin_der(c, y3, 'SALIDA: "Expediente\nno encontrado"\nreturn → FIN')

    rect(c, CX, y4, 'paciente = listaPacientes[i]  /  continuar = true')
    av(c, CX, y4-RH/2, y5+DH/2)

    # MIENTRAS continuar  ← inicio del ciclo de actualización
    diamond(c, CX, y5, 'MIENTRAS\n¿continuar?')
    si_abajo(c, CX, y5)
    av(c, CX, y5-DH/2, y6+PH/2)
    no_derecha(c, CX, y5)
    # No (salir del while) → guardar y FIN
    ah_r(c, CX+DW/2, SL, y5)
    rect(c, RC, y5, 'reescribirTodos()\nSALIDA: "Actualización exitosa"',
         color=CP, w=SW, h=SH+8, fs=7)
    fin_ok = y5 - (SH+8)/2 - GAP - OH/2
    av(c, RC, y5-(SH+8)/2, fin_ok+OH/2)
    oval(c, RC, fin_ok, 'FIN')

    # ENTRADA submenú
    para(c, CX, y6, 'ENTRADA: seleccion = showInputDialog(opciones 1–11)')
    av(c, CX, y6-PH/2, y7+DH/2)

    diamond(c, CX, y7, '¿seleccion == null?\n(usuario cerró el diálogo)')
    no_abajo(c, CX, y7)
    av(c, CX, y7-DH/2, y8+RH/2)
    # Sí → continuar = false → siguiente iteración termina el MIENTRAS
    si_derecha(c, CX, y7)
    ah_r(c, CX+DW/2, SL, y7)
    rect(c, RC, y7, 'continuar = false', color=CP, w=SW, h=SH, fs=8)
    # esta rama baja para unirse al loop-back
    ln(c, RC, y7-SH/2, RC, y9-DH/2-14)

    # Identificar opción y ejecutar switch
    rect(c, CX, y8,
         'opcion = buscar en opciones[] con FOR + .equals()\n'
         'switch(opcion) { cases 1-10: actualizar campo }')
    av(c, CX, y8-RH/2, y9+DH/2)

    diamond(c, CX, y9, '¿opcion == 11?\n(Terminar actualización)')
    no_abajo(c, CX, y9)
    av(c, CX, y9-DH/2, y10+RH/2)
    # Sí → continuar = false + loop-back
    si_derecha(c, CX, y9)
    ah_r(c, CX+DW/2, SL, y9)
    rect(c, RC, y9, 'continuar = false', color=CP, w=SW, h=SH, fs=8)
    # ambas ramas (Sí y No) → loop-back al MIENTRAS
    loop_back(c, CX, y10-RH/2, y5)
    base_y = y10 - RH/2 - 14
    ln(c, RC, y9-SH/2, RC, base_y)
    ln(c, RC, base_y,   LX, base_y)

    rect(c, CX, y10, 'Aplicar cambio: paciente.setXxx(nuevoValor)')
    av(c, CX, y10-RH/2, y11+PH/2)

    para(c, CX, y11, 'SALIDA: confirmar cambio al usuario')
    av(c, CX, y11-PH/2, y12+OH/2)
    oval(c, CX, y12, 'FIN')


# ─────────────────────────────────────────────────────────────────────────────
# Pág. 7 — Listar Todos los Expedientes
# ─────────────────────────────────────────────────────────────────────────────

def page7(c):
    hdr(c, 'Flujograma 7: Listar Todos los Expedientes',
        'Archivo: ExpedienteManager.java — método listarTodosLosExpedientes()')
    ftr(c, 7)

    y0 = H - 82
    y1 = y0  - OH/2 - GAP - RH/2
    y2 = y1  - RH/2 - GAP - DH/2
    y3 = y2  - DH/2 - GAP - RH/2
    y4 = y3  - RH/2 - GAP - RH/2
    y5 = y4  - RH/2 - GAP - RH/2
    y6 = y5  - RH/2 - GAP - PH/2
    y7 = y6  - PH/2 - GAP - OH/2

    oval(c, CX, y0, 'INICIO')
    av(c, CX, y0-OH/2, y1+RH/2)

    rect(c, CX, y1,
         'listaPacientes = cargarTodosLosExpedientes()\n'
         'totalPacientes = contarExpedientes(listaPacientes)')
    av(c, CX, y1-RH/2, y2+DH/2)

    diamond(c, CX, y2, '¿totalPacientes == 0?')
    no_abajo(c, CX, y2)
    av(c, CX, y2-DH/2, y3+RH/2)
    si_derecha(c, CX, y2)
    fin_der(c, y2, 'SALIDA: "No hay\nexpedientes registrados"\nreturn → FIN')

    rect(c, CX, y3,
         'PROCESO: construir separadores con FOR\n'
         '  PARA i=0; i<56; i++ → lineaDoble = lineaDoble + "="')
    av(c, CX, y3-RH/2, y4+RH/2)

    rect(c, CX, y4,
         'PROCESO: construir encabezado de tabla\n'
         '  sb = "LISTADO..." + lineaDoble + cabecera + lineaSimple')
    av(c, CX, y4-RH/2, y5+RH/2)

    rect(c, CX, y5,
         'PARA i=0; i < totalPacientes; i++\n'
         '  sb = sb + getNumeroExpediente() + getNombreCompleto() + getFechaVisita()')
    av(c, CX, y5-RH/2, y6+PH/2)

    para(c, CX, y6, 'SALIDA: showMessageDialog(sb)  — tabla de expedientes')
    av(c, CX, y6-PH/2, y7+OH/2)
    oval(c, CX, y7, 'FIN')


# ─────────────────────────────────────────────────────────────────────────────
# Main
# ─────────────────────────────────────────────────────────────────────────────

def main():
    out = 'docs/Flujogramas_COMP2315.pdf'
    c = canvas.Canvas(out, pagesize=letter)
    c.setTitle('Flujogramas — Sistema de Expedientes Médicos — COMP 2315')
    c.setAuthor('Ektor M. Gonzalez, Diego Rodriguez, Donovan Irizarry, Carlos Ramos')

    pages = [page1, page2, page3, page4, page5, page6, page7]
    for i, fn in enumerate(pages):
        fn(c)
        if i < len(pages) - 1:
            c.showPage()

    c.save()
    print(f'PDF generado: {out}')


if __name__ == '__main__':
    main()
