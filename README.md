# HackerNews

Esta aplicación muestra las diferentes noticias.

![image](https://user-images.githubusercontent.com/24800779/122154691-22eff980-ce2b-11eb-800e-28984ee59c38.png)

El objetivo de esta aplicación es mostrar un ejemplo de una aplicaicón android con las siguientes características:

1.- Desarrollada en Android con el ,lenguaje Kotlin </br>
2.- ViewBinding para el enlazado de componentes de la vista</br>
3.- Patrón de diseño MVVM, Observer y ViewHolder</br>
4.- Se incorpora LiveData para comunicar la capa de viewModel con la vista.</br>
5.- Se crea| una capa para los logs y se deja lista para integración de crashalitycs</br>
6.- Consumo de servicios web de tipo REST con retrofit</br>
7.- Programación reactiva (RX) para obtener los datos del servicio REST</br>
8.- Inyección de dependencias con Dagger Hilt</br>
9.- Persistencia con Room</br>
10.- Pruebas unitarias del ViewModel</br>
11.- Ejemplo de coroutina para ejecutar un servicio de Room de la base de datos. El propósito es solod emostrativo y cumple con el obtener los datos desde otro hilo (IO) y no en el hilo principal. Aprovechando el scope del viewModel para el dispose del recurso.</br>



La aplicación esta desarrollada bajo una arquitectura MVVM y trabaja bajo el principio de sólo un origen de datos, es decir, se obtienen los datos desde el servicio REST y los almacena en una base de datos local (SQLite). El proceso regresa los datos desde la base de datos de tal forma que si no hay servicio de internet regresará siempre los ultimos datos que se encuentren en la base de datos.

![image](https://user-images.githubusercontent.com/24800779/122154319-57af8100-ce2a-11eb-87a5-0be93bc21a6f.png)


En caso de eliminar una publicación, la aplicación guarda en una tabla de items eliminados el ID y cuando vuelve a consultar los datos simplemente excluye los ID's que han sido eliminados.

La app carga los datos al inicio y al hacer "Pull To Refresh" desde la lista. En este proceso de actualización excluye los items eliminados.

Al seleccionar un Item de la liste se abrirá un modal con un  dialog presentando en un webView la pagina de referencia o en su defecto que no exista una URL que presentar, mostrará los comentarios de la publicación.

Este es un video demostrativo de la app.



https://user-images.githubusercontent.com/24800779/122154266-35b5fe80-ce2a-11eb-9dec-7f923ab93482.mov


Se presenta un ejempplo para las pruebas unitarias del ViewModel. Este ejemplo trata de demostrar como hacer los objetos Mock para pasarlos al caso de prueba, hace una verificación del caso considerando que el objeto que se compara es un LiveData del ViewModel y se agregan las reglas para RX.

![image](https://user-images.githubusercontent.com/24800779/122154611-fdfb8680-ce2a-11eb-896c-406fb4fa1c83.png)


