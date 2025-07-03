# URLs de ejemplo para endpoints de comentarios

- http://localhost:8080/reply/2   (GET)

- http://localhost:8080/reply/create   (POST, Con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 98
            },
            "userId": {
                "value": 2
            },
            "objectRepliedId": {
                "type": "ReplyId",
                "value": 5
            },
            "reply": "Yeah man",
            "likes": [],
            "replies": []
        }

- http://localhost:8080/reply/2/edit   (PUT, Con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 2
            },
            "userId": {
                "value": 2
            },
            "objectRepliedId": {
                "type": "ReviewId",
                "value": 5
            },
            "reply": "That was definitely something",
            "likes": [],
            "replies": []
        }

- http://localhost:8080/reply/2/delete   (DELETE)