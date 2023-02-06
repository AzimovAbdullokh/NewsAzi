# NewsAzi Программа, полностью написанная на Kotlin с компонентами архитектуры MVVM и чистой архитектурой
# News App, News Of World

* Простое приложение, которое написано в чистой архитектуре с использованием языка Kotlin. Он показывает список новостей. В программе можно сортировать по определенным условиям. Также есть функция поиска и переход на сайт источника.

(![news 1 2 width=160](https://user-images.githubusercontent.com/114995936/216963559-3f548806-f3cc-45af-8a8c-3e80aa6ed445.jpg)





# Libraries
# Android Jetpack
* ViewBindin Привязка представлений - это функция, которая позволяет вам легче писать код, взаимодействующий с представлениями.

Lifecycle Интерфейс, который автоматически реагирует на события жизненного цикла.

* Paging 3.0
 Библиотека подкачки помогает загружать и отображать небольшие фрагменты данных одновременно. Загрузка частичных данных по требованию снижает использование пропускной способности сети и системных ресурсов.

 * Navigation 
 Навигация относится к взаимодействиям, которые позволяют пользователям перемещаться по различным частям контента в вашем приложении, входить в них и выходить из них. Навигационный компонент Android Jetpack помогает вам осуществлять навигацию, от простых нажатий кнопок до более сложных шаблонов, таких как панели приложений и панель навигации. Навигационный компонент также обеспечивает последовательный и предсказуемый пользовательский интерфейс, придерживаясь установленного набора принципов.

 * Coroutines Flow
 - это класс, который может возвращать несколько объектов по очереди или сразу. Ключевое слово тут «несколько»: это главное его отличие от suspend function, которая возвращает один объект и завершается.

 * ViewModel
 Данные, связанные с пользовательским интерфейсом, которые не уничтожаются при вращении приложения. Легко планируйте асинхронные задачи для оптимального выполнения.

* Image
Picasso Picasso позволяет вам легко загружать изображения в ваше приложение - часто в одной строке кода.
* HTTP
* Retrofit2 Типобезопасный HTTP-клиент для Android и Java.

* OkHttp Клиент HTTP + HTTP / 2 для приложений Android и Java.

* Coroutines
* Kotlin Coroutines Сопрограммы - это богатая библиотека для сопрограмм, разработанная компанией JetBrains. Он содержит ряд высокоуровневых примитивов с поддержкой сопрограмм, которые рассматриваются в этом руководстве, включая запуск, асинхронность и другие.
 * DI
* Hilt Hilt - это библиотека внедрения зависимостей для Android, которая сокращает время выполнения ручного внедрения зависимостей в ваш проект. Выполнение ручного внедрения зависимостей требует, чтобы вы создавали каждый класс и его зависимости вручную, а также использовали контейнеры для повторного использования зависимостей и управления ими.

