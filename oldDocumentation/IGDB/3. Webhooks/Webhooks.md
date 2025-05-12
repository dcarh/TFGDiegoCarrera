# Webhooks

### What?

Webhooks allow us to push data to you when it is added, updated, or deleted. Instead of polling the API for changes, you can listen on your own HTTP endpoint (Webhook) and we will deliver the data to you.

Using Webhooks will ensure that your data is always up to date!

    HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/ENDPOINT/webhooks/")
        .header("Client-ID", "Client ID")
        .header("Authorization", "Bearer access_token")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .field("url", "YOUR_WEBHOOK_URL")
        .field("secret", "YOUR_WEBHOOK_SECRET")
        .field("method", "create")
        .asJson();

### How to register your webhook

To register a new webhook you need to send a POST request to ENDPOINT/webhooks. The endpoint is required as it specifies what type of data you want from your webhook.

The post request should contain x-www-form-urlencoded body with three parameters:

- url. this is your prepared url that is ready to accept data from us.

- method. this is the type of data you are expecting to your url, there are three types of methods

    - create, sends new items from the API

    - delete, sends deleted items from the API

    - update, sends updated items from the API

- secret. this is your “secret” password for your webhook. Every request from the webhook service 
will have your secret in the header called X-Secret.

---

    // Example response upon registering your webhook

    { 
        "id": WEBHOOK_ID, // A unique ID for the webhook
        "url": "YOUR_WEBHOOK_URL", // Your chosen URL
        "category": 1, // Based on the endpoint you chose
        "sub_category": 0, // Based on your method (can be 0, 1, 2)
        "active": true, // Is the webhook currently active
        "api_key": "YOUR_CLIENT_ID", // Displays the api key the webhook is connected to
        "secret": "YOUR_SECRET", // Your chosen secret
        "created_at": "2018-11-25T23:00:00.000Z", // Created at date
        "updated_at": "2018-11-25T23:00:00.000Z" // Updated at date
    }

Registering your webhook in Postman Once your webhook is registered you will receive a response with the new webhook object

    // Delete Response from Webhook

    {
    "id": "1234"
    }

That’s it!

The data will now be sent to your webhook in the body of a post request. The data is a single json object representing an **unexpanded entity**.

Webhooks from **DELETE** do not send the entire object but only the **ID**.

    Tip! Always validate your received data with you secret!

Webhooks have an active field, as you can see in the JSON response above, The service will keep the webhook active as long as the webhook url is capable of receiving data from the service. If the url fails 5 times the webhook will be set to inactive (active: false) and the service will stop to send data to this webhook.

Reactivating the webhook is done by re-registering it, this will update the active status to true.

    Tip! Re-register your webhook on service start, to make sure it's always active!

### Viewing your webhooks

You can always get information about your webhooks from the API. To get ALL of your registered webhooks simply send a GET request to /webhooks, without the endpoint. This will return a JSON array of your webhooks

To get information about a specific webhook you can make a GET request with the webhook id to /webhooks/WEBHOOK_ID, without the endpoint. This will return the webhook of that id.

    // Get ALL registered Webhooks

    HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api.igdb.com/v4/webhooks/")
    .header("Client-ID", "Client ID")
    .header("Authorization", "Bearer access_token")
    .asJson();

### Removing a Webhook

To remove your existing webhook you need to send a DELETE request to /webhooks/WEBHOOK_ID, without the endpoint. The Webhook id is returned during the registration process or can be found with a GET request to /webhooks/.

The DELETE request will receive the deleted webhook as confirmation.

    HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/ENDPOINT/webhooks/test/WEBHOOK_ID?entityId=ENTITY_ID")
    .header("Client-ID", "Client ID")
    .header("Authorization", "Bearer access_token")
    .asJson();

### Testing

To make sure you have everything setup just right we have a test endpoint for the webhook service. This endpoint will send an object of your choosing to your newly created webhook.

Send a POST request to ENDPOINT/webhooks/test/WEBHOOK_ID?entityId=ENTITY_ID. The entity id is the id of the object from the endpoint you wish to test with, example:

POST to games/webhooks/test/42?entityId=1337:

This request will send the game object with id 1337 to your webhook url.

### Handling Webhooks on your end

When recieveing the webhook message on your end what we expect is to recieve a 200 OK back within 15 seconds. If the endpoint takes longer than 15 seconds to respond the event will be deemed as a failed event, fail 5 times and the webhook will be set to inactive.