# ::CUSTOM MOVIE SITE::

This project was prepared to pass the Kodilla Course. This is the backEnd part of this project. The FrontEnd (made in Vaadin) you may find here: https://github.com/Mc-Beton/Movie_FrontEnd

This is a custom movie API site, which uses access to the IMDB movie Database and the Utelly Database. It's a simple movie site. Details are enlisted below:

## Table of contents

1. [Technologies](#technologies-used)
2. [Features](#features)
3. [Getting Started](#getting-started)
4. [List of Endpoints](#list-of-endpoints)
5. [How to use the FrontEnd](#how-to-use-the-frontend)
6. [TODO list](#todo-list)


## Technologies Used
```
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.1)
 :: Kodilla CUSTOM MOVIE SITE::   (v0.0.1 RELEASE)
```
This project is based on Spring Boot in Java 11.

BackEnd:

* Java 11
* Spring FrameWork
* Hibernate/Spring Data JPA
* Gradle
* JUnit/Jupiter
* MySQL 
* Scheduler
* access to IMDB API
* access to UTELLY API

FrontEnd (https://github.com/Mc-Beton/Movie_FrontEnd):

* Vaadin
* crippled Spring Security

## Features

Currently Spring Security has not been yet provided (a simple sample is implemented in the FrontEnd part), but the features will be divided between USER and ADMIN roles, just as they are in the FrontEnd part:

User
- as an Anonymous I can access to the Movie database, movie details, rates, posts and movie availabilty online
- as an Anonymous I can create a account

- as an User I can access to the Movie database, movie details, rates, posts and movie availabilty online
- as an User I can add movies to my favorite or to watch lists
- as an User I can add posts and rates for movies
- as an User I can add other users as friends (to be implemented in the FrontEnd)
- as an User I can access other users movie lists (to be implemented in the FrontEnd)

Admin
- as an Admin I can access to the Movie database, movie details, rates, posts and movie availabilty online
- as an Admin I can see the full list of users, edit, create or delete them
- as an Admin I can access to users movie lists (to be implemented in the FrontEnd)


## Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system. Just clone or download the files into a folder and it's ready to be used.

### BackEnd

- Clone the repository
```
git clone https://github.com/Mc-Beton/KodillaFinalProject.git
```
- Its database is on MySQL, so create a connection as described in the application.properties.

- Build the project
```
./gradlew build
```

### FrontEnd

- Clone the repository
```
git clone https://github.com/Mc-Beton/Movie_FrontEnd.git
```

- Build the project
```
./gradlew build
```

### Ports
```
BackEnd: 8080
FrontEnd: 8085
```

## List of Endpoints

Movie:
```
GET /v1/movies/
GET /v1/movies/soon
GET /v1/movies/top250
GET /v1/movies/topTv
GET /v1/movies/movieImbd_details/{movieId}
GET /v1/movies/search/{content}
GET /v1/movies/watchmovie/{imdbId}
```

Post:
```
GET /posts/all/
GET /posts/{movieId}
GET /posts/byId/{postId}
POST /posts
DELETE /posts/delete/{postId}
```

Rates:
```
GET /rating/all
GET /rating/{username}/movie/{movieId}
GET /rating/movie/{movieId}
GET /rating/{ratingId}
POST /rating
DELETE /rating/delete/{ratingId}

GET /avgRating/{movieId}
```

User:
```
GET /user/getUserDataLogin/{username}
GET /user/all-users/{name}
POST /user/
PUT /user/
GET /user/{userId}
DELETE /user/delete/{userId}
PUT /user/{userId}/addFriend/{friendId}
GET /user/firends/{userId}
PUT /user/{userId}/addMovieToFav/{movieId}
PUT /user/{userId}/addMovieToWatch/{movieId}
GET /user/{userId}/favoriteList
GET /user/{userId}/toWatchList
```

## How to use the FrontEnd

Once you have the BackEnd and FrontEnd running start by this adress:
```
http://localhost:8085/movie
```
Here you will be directed to the movie main page. What can you do here:

-as a NOT logged in USER u may:
* register a new user
* login to the page
* see movie lists like top 250, coming soon, search etc.
* see movie details, where you can watch it online

Once you choose to see details of a movie you can get:
* movie description
* movie average rate
* movie posts

-as a logged in USER (register a new user and log in with those parameters) u can:

On the main page:
* see movie lists like top 250, coming soon, search etc.
* see movie details, where you can watch it online
* add movies to you lists
* a new button shows up right next to the search button - your movie lists

Once you choose to see details of a movie you can:
* movie rate this movie
* movie add posts to this movie

-as a logged ADMIN (log in as an admin by using these credentials: admin, admin) u can:

On the main page:
* see movie lists like top 250, coming soon, search etc.
* see movie details, where you can watch it online
* a new button shows up right next to the search button - user database

In the user database you can:
* have access to all users
* delete, edit or add a new user

## TODO list

For the BackEnd part I'll be continuing my works to add/fix these features (due to the deadline, i just didn't have enough time to add these):
* add Spring Security
* add JWT Authentication
* divide the project into microservices

For the FrontEnd, well to be honest I am not that really interested to add many features, just editing it to make it work together with the backEnd while I'll be upgrading it.


