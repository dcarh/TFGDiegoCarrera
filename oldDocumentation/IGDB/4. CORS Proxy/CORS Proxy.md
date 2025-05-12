# CORS Proxy

## CORS

If you intend to use our API from your website you will encounter an issue with security; namely CORS Cross-Origin Resource Sharing.

There are security mechanisms in place by all major browsers to stop websites from accessing other domains without getting explicit permission. This is done through HTTP headers. So, for example, amazinggameswebsite.com cannot access api.igdb.com without us explicitly stating in the HTTP headers (Access-Control-Allow-Origin) that they have permission.

We do not offer the configuration of these headers as a service, so any browser-based javascript and mobile javascript frameworks will not be able to communicate directly with the IGDB API.

Workaround

See the guide for setting up a proxy or set up a proxy using CORS Anywhere

## Proxy

There are a number of reasons why you may wish to proxy requests to the IGDB API.

- To have a backend that keeps track of your Oauth Application Tokens
- Caching requests to the API for better performance
- Enable application logging to track/debug usage
- Enable CORS between the proxy and applications

### How do I set up a proxy?

Proxies can be complex, but to get you started we have a simple guide to get you up and running quickly through AWS.

We have provided a single link that will let you deploy an AWS Api Gateway in your own AWS account that will serve as a proxy. This Stack will also handle your Access Token rotations automatically for you, so you don’t need to think about that.

### What will it cost?

AWS has a very generous free-tier for new users and the services used in the provided solution (Api Gateway, Secrets Manager, Lambda). Please use the AWS Pricing Calculator to gauge how much this will cost you before setting up your Stack.

### Stack Setup

**Prerequisites**: You need to have an AWS account with permissions to deploy CloudFormation stacks.

1. Click this link to get started.

2. Go over the Stack Details
    - You have to agree to the terms and conditions.

    - You have to fill in your Twitch 

    - Application Credentials

    - It’s recommended to protect your proxy by enabling Api Keys

    - NOTE: Enabling Caching will come with extra costs as this is NOT covered by the Free-tier

    - NOTE: Enabling CORS will ‘break’ Protobuf responses, some libraries might not work.

3. Click Next

4. Configure Stack Options - Nothing is required here, you can click Next

5. Verify Settings, click the checkbox at the bottom, then click “Create Stack”

6. You will now see the “Stack Details” screen, hit the refresh arrow button on the right until your stack name on the left says “UPDATE_COMPLETE”

7. Click on the “Outputs” tab to get the URL to your new proxy.

    - The “Resources” tab summarises all the services deployed on your account.

    - The “Template” tab displays the template used for deployment.

8. You can now post requests to your URL and it will proxy to our API

    - If you enabled Api Keys you will need to specify the header x-api-key and the key can be found via a link through the “Resources” tab for “ApiDefaultKey”

**Important Note**: The url generated will end in production, so you will want to post to
​​https://<your-api-gateway-unique-id>.execute-api.us-west-2.amazonaws.com/production/v4/games

### What’s next?

You can do a lot of things via API Gateway.

- You can improve the security of your proxy by creating another sort of Authentication, to prevent others from using up your RPS quota.
- You can also setup your own Domain name and SSL with Route53
- You can modify the path of the proxy to have it serve as the front-end to your own APIs
Perform a calculation? Lambda Integration
    - Just want to store some records? DynamoDB Integration
    - Want users to be able to upload/download files? S3 Integration
    - Enable request logging

### Alternatives

- CORS: Setup a proxy using CORS Anywhere