# URLs de ejemplo para endpoints de puntuaciones

- http://localhost:8080/rating/8   (GET)

- http://localhost:8080/rating/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 27
            },
            "userId": {
                "value": 1
            },
            "mediaRatedId": {
                "type": "TvShowId",
                "value": 13421
            },
            "rating": 7
        }

- http://localhost:8080/rating/8/edit   (PUT, con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 8
            },
            "userId": {
                "value": 5
            },
            "mediaRatedId": {
                "type": "TvShowId",
                "value": 100565
            },
            "rating": 8
        }

- http://localhost:8080/rating/8/delete   (DELETE)