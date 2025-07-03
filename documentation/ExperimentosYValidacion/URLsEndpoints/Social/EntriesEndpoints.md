# URLs de ejemplo para endpoints de registros

- http://localhost:8080/entries?categories=book

- http://localhost:8080/entries?sort_by=newest

- http://localhost:8080/entries?sort_by=nnnewest

- http://localhost:8080/entries?sort_by=newest&categories=movie,book

- http://localhost:8080/entry/18

- http://localhost:8080/entry/create   (POST,Con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 20
            },
            "userId": {
                "value": 4
            },
            "mediaId": {
                "type": "TvShowId",
                "value": 136315
            },
            "ratingId": null,
            "reviewId": null,
            "completed": false,
            "inProgress": true,
            "onHold": false,
            "dropped": false,
            "repeat": true,
            "startedDate": "2025-05-30",
            "finishedDate": null,
            "platform": null,
            "creationDate": "2025-05-30T21:36:02"
        }

- http://localhost:8080/entry/6/edit   (PUT, Con el siguiente JSON como cuerpo de la petición)

        {
            "id": {
                "value": 6
            },
            "userId": {
                "value": 2
            },
            "mediaId": {
                "type": "EpisodeNumber",
                "value": {
                "tvShowId": 1342234,
                "seasonNumber": 3,
                "episodeNumber": 15
                }
            },
            "rating": {
                "value": 3
            },
            "review": null,
            "completed": false,
            "paused": false,
            "dropped": true,
            "repeat": false,
            "finishedDate": "2024-12-28",
            "startedDate": "2025-01-04",
            "platform": 12,
            "creationDate": "2025-01-04T23:02:36"
        }

- http://localhost:8080/entry/18/delete   (DELETE)