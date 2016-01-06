Why?
. To evaluate and compare AngularJS, RiotJS with ReactJS implementations
. AngularJS (1.4+) supposed to be the "legacy" UI
. RiotJS supposed to be 10 times smaller and faster compared to ReactJS
. CRUD of SR is used as a model after app

What else I need to know?
. To ensure compatibility between the future's Web Component, please define all custom element names with more than one words separated by a hypen e.g. my-first-element!

Apimatic (AngularJS)
. c.f. https://apimatic.zendesk.com/hc/en-us/articles/210119918-Using-your-generated-Angular-JS-SDK
. Apimatic artifacts were generated based on /swagger/swagger.json, renamed from ServiceManager-AngularJS to apimatic and moved into js/

Swagger Client
. The JavaScript client is based on https://www.npmjs.com/package/swagger-client

JSON Web Token (JWT)
. The working codes are based on http://niels.nu/blog/2015/json-web-tokens.html
. JWT protected api has to be /api/jwt/* as /api is already reserved for Spring MVC api (which is not JWT-enabled), aka /api/jwt/* are JWT-enabled REST services while /api/* where * is not jwt is not!
. The configurations are done inside dispatcher-servlet.xml, web.xml as well as via @RequestMapping of UserController and ApiController
. It's main dependency (if you are not using Maven, obviously this project does not use Maven), is https://github.com/jwtk/jjwt e.g. jjwt-0.7.0-SNAPSHOT.jar

FalcorJS
. It is experimental and it is not ready (due to a need to have the JSON response in a form of JSONGraph mainly)

Results
. The non-scientific preliminary results
AngularJS 25 hours
ReactJS
RiotJS
. It does not include APIMATIC integrations and/or anything non-UI related

SwaggerClient 2.1.10
. No code generations needed
. Just look at the swagger.json and invoke anything in the format of tags.operationId e.g. pet.getPetById
. Sadly by looking at the defacto Swagger UI interface, it is confusing to see what are the operation ids, thus tools like http://json2table.com/ is probably better
. Good reference is at https://libraries.io/npm/swagger-client/2.1.10

AngularJS to ReactJS in few sentences
. Move all HTML codes into .jsx file
. Added SwaggerClient codes to retrive the results from the backend/datastore
. Add an HTML tag to mount a React component
. Render the tag with the React component
. Decorate all properties with {this.property} if required (for read only view)
. Add event handler if applicable (for saving form etc)

Other Useful Resources:

https://facebook.github.io/react/html-jsx.html

https://cdnjs.com/libraries/riot

https://www.npmjs.com/package/htmltojsx

http://tablestrap.com/

http://sssslide.com/speakerdeck.com/btholt/falcorjs-and-react

http://www.infoq.com/news/2015/08/falcor

React.js Conf 2015 - The complementarity of React and Web Components
https://www.youtube.com/watch?v=g0TD0efcwVg