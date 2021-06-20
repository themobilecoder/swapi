# SWAPI App

A Star Wars app that uses swapi.dev API written in Android

## Setup

In Android Studio, select which product flavor you want to use.
This project has two product flavours: Mock and Production.

#### Mock
Mock flavor was made to try out the app using local, dummy data without depending on swapi.dev. The mock flavored app runs it's own local server using NanoHTTPd that serves data from different json files.

#### Production
Uses the actual data from https://swapi.dev

## Architecture
The app is written using MVVM

## External Libraries Used
- Kotlin
    -  Kotlin Serialization
- Jetpack Libraries
    - Paging 3
    - Hilt (DI)
- Volley
- Material Components
    - Swipe Refresh
    
## Assumptions / Constraints
- Refreshes data on resume (app minimisation, switching screens, screen orientation changes)
- No local db caching due to always refresh assumption 
- Added a mock flavor with local server because the swapi.dev server has been unstable
- Kotlin serialization was chosen over gson
- Hilt was used for dependency injection as it is part of Jetpack libraries


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)