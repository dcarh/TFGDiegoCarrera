# URLs de ejemplo para endpoints de "me gusta"

- http://localhost:8080/like/3   (GET)

- http://localhost:8080/like/create   (POST, con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 5
            },
            "userId": {
                "value": 1
            },
            "elementLikedId": {
                "type": "ReviewId",
                "value": 4
            }
        }

- http://localhost:8080/like/4/delete   (DELETE)