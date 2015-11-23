# mjs-oauth-server

Taken from Roy Clarkson's [Spring OAuth Service](https://github.com/royclarkson/spring-rest-service-oauth) sample code.


# Usage: #

Test the 'greeting' endpoint:

    curl -i -XGET http://localhost:9999/greeting

This generates an 'unauth' exception:

    {
       "error": "unauthorized",
       "error_description": "An Authentication object was not found in the SecurityContext"
    }

In order to access the resource, you need to generate a token (here the application acts as a trusted client):

    curl -i -X POST \
    -vu clientapp:123456 \
    http://localhost:9999/oauth/token \
    -H "Accept: application/json" \
    -d "password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"

A successfuly authorisation generates a response like this:

    {
       "access_token": "ff16372e-38a7-4e29-88c2-1fb92897f558",
       "token_type": "bearer",
       "refresh_token": "f554d386-0b0a-461b-bdb2-292831cecd57",
       "expires_in": 43199,
       "scope": "read write"
    }

Use the 'access_token' in your call:

    curl http://localhost:9999/greeting -H "Authorization: Bearer ff16372e-38a7-4e29-88c2-1fb92897f558"

After the specified time period, the access_token will expire. Use the refresh_token that was returned in the original OAuth authorization to retrieve a new access_token:

    curl -X POST \
    -vu clientapp:123456 \
    http://localhost:9999/oauth/token \
    -H "Accept: application/json" \
    -d "grant_type=refresh_token&refresh_token=ca6b8936-8756-459e-9b4a-f3c2e6decac4&client_secret=123456&client_id=clientapp"


