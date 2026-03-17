# Tip Calculator Multiplatform (Kotlin Compose Multiplatform)

Este proyecto es una adaptación del codelab "Tip Calculator" de Android (basado en Jetpack Compose) a una aplicación **Kotlin Multiplatform (KMP)** con **Compose Multiplatform**. El objetivo es compartir la lógica de negocio y la interfaz de usuario entre las plataformas **Android** y **Desktop (Windows/Linux/macOS)** , utilizando un **ViewModel común** y manteniendo el estado de forma reactiva.

##  Descripción de la aplicación

La calculadora de propinas permite ingresar:
- Monto de la cuenta.
- Porcentaje de propina.
- Opción para **redondear** la propina (al alza).

Ante cualquier cambio en los campos, el resultado se actualiza automáticamente. La lógica de cálculo reside en un `TipCalculatorViewModel` ubicado en el módulo compartido (`shared`), y la interfaz (`TipCalculatorScreen`) también es común para ambas plataformas.

##  Resultados y conclusiones

### Resultados obtenidos
- Se logró **refactorizar** la app original de Android (que usaba estados locales en la UI) a una arquitectura con **ViewModel multiplataforma**.
- El `TipCalculatorViewModel` (en `commonMain`) maneja el estado mediante `StateFlow` y expone funciones para modificar los valores.
- La pantalla Composable `TipCalculatorScreen` (también en `commonMain`) observa el estado y reacciona a los cambios, invocando los métodos del ViewModel.
- La aplicación se ejecuta sin problemas en **Android** (emulador o dispositivo físico) y en **Desktop** (como aplicación independiente).
- Se verificó el ciclo de vida del ViewModel:
  - Al abrir la ventana/actividad, se imprime en consola `TipCalculatorScreen enters composition`.
  - Al cerrar la ventana de escritorio, se imprime `TipCalculatorViewModel onCleared` y luego `TipCalculatorScreen leaves composition`.

### Conclusiones
- **Kotlin Multiplatform** permite compartir una cantidad significativa de código (lógica de negocio, ViewModel, UI) sin necesidad de duplicar esfuerzos.
- El uso de `androidx.lifecycle:lifecycle-viewmodel` en su versión multiplataforma facilita la gestión del ciclo de vida y la retención del estado ante recomposiciones.
- La integración con **Compose Multiplatform** hace que la UI sea declarativa y consistente en todas las plataformas soportadas.
- La separación de responsabilidades (UI → ViewModel → lógica pura) mejora la mantenibilidad y la testabilidad.
- Es posible utilizar `DisposableEffect` para monitorizar la entrada/salida de la composición y entender mejor el flujo de la aplicación.

##  Requisitos previos

- **JDK 11** o superior.
- **Android Studio** (recomendado: Flamingo o superior) con el plugin de Kotlin Multiplatform.
- Para ejecutar en Desktop: No se requiere nada adicional, ya que se genera un ejecutable nativo.
- Conexión a Internet para descargar dependencias (Gradle).

##  Instrucciones para ejecutar

### 1. Clonar el repositorio
```bash
git clone 

# Android (instalar en dispositivo conectado)
./gradlew androidApp:installDebug

# Desktop (ejecutar aplicación)
./gradlew desktopApp:run