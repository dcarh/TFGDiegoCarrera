# URLs de ejemplo para endpoints de reseñas

- http://localhost:8080/reviews?categories=episode   (GET)

- http://localhost:8080/review/6   (GET)

- http://localhost:8080/review/create   (POST, Con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 90
            },
            "userId": {
                "value": 2
            },
            "reviewedMediaId": {
                "type": "BookId",
                "value": "UU-VAAAACAAJ"
            },
            "review": "It's amazing how such a short and simple story can be so powerful",
            "likesIds": [],
            "allowReplies": true,
            "repliesIds": [],
            "spoilers": false
        }

- http://localhost:8080/review/5/edit   (PUT, Con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 5
            },
            "userId": {
                "value": 2
            },
            "reviewedMediaId": {
                "type": "BookId",
                "value": "UU-VAAAACAAJ"
            },
            "review": "In less than 100 pages, this book will make you appreciate your non-fishing hobbies.",
            "likesIds": [],
            "allowReplies": false,
            "repliesIds": [],
            "spoilers": false
        }

- http://localhost:8080/review/5/delete   (DELETE)
