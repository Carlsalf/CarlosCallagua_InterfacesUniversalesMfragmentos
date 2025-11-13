# Filmoteca – Prácticas 1–4
Proyecto Android (API 36) para la entrega de los ejercicios: interfaz de datos, edición, layout-land e implementación en Compose.

Ejercicio 1 — About / Información de la App

En este ejercicio implementé una pantalla informativa con dos versiones:

Versión con XML (Layouts)

Versión con Jetpack Compose

La pantalla muestra información básica de la app y un botón de contacto.
Se creó e integró la Activity AboutActivity y su navegación desde el menú principal.

Ejercicio 2 — Creación de Datos / FilmDataActivity

Aquí añadí una pantalla simple que muestra datos estáticos del proyecto usando TextView y layouts básicos.
La idea del ejercicio es familiarizarme con el manejo de Activities y navegación entre pantallas.

Ejercicio 3 — Listas básicas (ListView)

En este punto implementé la primera lista de películas usando un ArrayAdapter con un layout simple.

Funciones principales:

Mostrar el listado de películas.

Detectar clic en un elemento.

Pasar datos a la pantalla de detalle mediante intent.putExtra(…).

Fue mi primer acercamiento a trabajar con listas dinámicas en Android.

 Ejercicio 4 — Listas personalizadas + Edición

En este ejercicio mejoré la lista:

✔️ ListView con Adaptador Personalizado

Creé un layout personalizado para cada elemento, mostrando:

Título

Director

Año

Imagen miniatura

✔️ FilmDetailActivity

Muestra la información completa de una película seleccionada.

✔️ FilmEditActivity

Pantalla para:

Añadir una nueva película

Editar una existente

Incluye:

Entrada de texto

Spinners

Selección de imagen desde galería

Botón para tomar fotografía (con permisos en tiempo de ejecución)

✔️ Fuente de Datos

Implementé una clase simple FilmDataSource que almacena la lista de películas en memoria (modo demo para la práctica).
