REST Style Guide
================

- See also HTML REST.

- Reference:

http://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api#restful

http://en.wikipedia.org/wiki/Representational_state_transfer

https://bourgeois.me/rest/


Use Plural End Point Names
--------------------------

- Endpoint names should be plural.
- Avoids having to deal with odd pluralization (person/people, goose/geese).


Other
-----

- Use camelCase for field names.
- JSON only responses.
- Use pretty print by default, ensure gzip compression is supported on server.
- Use mime type of 'application/json'.


GET
---
- List the URIs and perhaps other details of the resources or it's members.

- The GET method is a 'safe method' or nullipotent.
- Calling a GET method should not change the server state or produce side-effects.

- For an existing resource, get about the account profile:
```
GET /accounts/{EXISTING ACCOUNT} HTTP/1.1
```

- For an existing resource, get offers for the account:
```
GET /accounts/{EXISTING ACCOUNT}/offers HTTP/1.1
```


POST
----
- Create a new entry in the collection. 
- The new entry's URI is assigned automatically and is usually returned by the operation.

- The POST method is not a 'safe method' and calling it will cause changes to the server state.

- Used to modify and update an existing resource:
```
POST /accounts/{EXISTING ACCOUNT} HTTP/1.1
```

- NOTE: that the following is an error:
```
POST /accounts/{NEW ACCOUNT} HTTP/1.1
```

- If the URL is not yet created, you should not be using POST to create it while specifying the name. 
- This should result in a 'resource not found' error because {NEW ACCOUNT} does not exist yet. 
- You should PUT the {NEW ACCOUNT} resource on the server first.

- You could though do something like this to create a resources using POST:
```
POST /account HTTP/1.1
```


PUT
---
- Replace the resource, or if it doesn't exist, create it.

- PUT is idempotent.
- If you PUT an object twice it has no effect.

- The PUT method is not a 'safe method' and calling it will cause changes to the server state.

- You can update or create a resource with PUT with the same object URL.

- For a new resource:
```
PUT /accounts/{NEW ACCOUNT} HTTP/1.1
```

- To overwrite an existing resource:
```
PUT /accounts/{EXISTING ACCOUNT} HTTP/1.1
```


DELETE
------
- DELETE is idempotent.
- If you DELETE an object twice it has no effect.

- The DELETE method is not a 'safe method' and calling it will cause changes to the server state.

- To delete an existing resource:
```
DELETE /accounts/{EXISTING ACCOUNT} HTTP/1.1
```


Error Handling
--------------
- Align with HTTP status codes:

200 - OK
404 - Not Found
500 - Internal Server Error
201 - Created
304 - Not Modified
400 - Bad Request
401 - Unauthorized
403 - Forbidden

- Reference:
http://en.wikipedia.org/wiki/Http_error_codes
https://blog.apigee.com/detail/restful_api_design_what_about_errors/


PUT vs POST Discussion
----------------------
- Do you name your URL objects you create explicitly, or let the server decide? 
- If you name them then use PUT.
- If you let the server decide then use POST.
- You can update or create a resource with PUT with the same object URL.

- Reference:
http://stackoverflow.com/questions/630453/put-vs-post-in-rest


More Account Examples: Reset password
-------------------------------------
- The reset request should not be idempotent.
- A second request sends another reset email.

- To reset a password:
```
POST /accounts/{EXISTING ACCOUNT}/resetPassword
```

- Reference:
http://stackoverflow.com/questions/3077229/restful-password-reset


More Account Examples: Change password
--------------------------------------
- The change request should not be idempotent.

- To reset a password:
```
POST /accounts/{EXISTING ACCOUNT}/changePassword
```


Example APIs
------------

- OAuth 2:

http://developer.github.com/v3/

http://bostonpizza.api.onosys.com/Help

https://developers.google.com/accounts/docs/OAuth2UserAgent

- API Key:

https://developers.gadventures.com/docs/rest.html

- OAuth 1:

http://docs.appfigures.com/api/authentication


