# WhatIndaWorld

An Android App that provides latest news from all around the world. 

<p align = "center">
<img src="https://github.com/abhishek-on-git/WhatIndaWorld_Android/blob/master/WhatIndaWorld_gif.gif" width="250">
</p>

This is a good example of how to use clean architecture for your android apps.
* The app follows the clean architectural style.
  Architectural components:
  - Presentation: Includes all UI elements and the ViewModel (MVVM architecture). It follows the single activity multiple fragment design and uses Jetpack Navigation component to navigate through the fragments.
  - Domain: Includes all your app's usecases and the repository interfaces (Not the implementations).
  - Data: Includes repository implementations, models, remote, local and cache datasources, Room DB class and Data Access Objects and the remote api service for Retrofit.
* When the app starts, it fetches the latest News from NewsAPI.
* User can save a news article and read it later. The app saves a News article entity it in a local database using Room Library.
* Uses Hilt for dependency injection.
