Helium
======

Helium is a DSL for REST API specifications and also a Java API for processing descriptions written in this language.
The main goal of this project is to create a single source of truth about some REST API. Taking a spec as an input,
Helium generates a lot of useful stuff that can be used to rapidly develop REST clients.
![Diagram](docs/helium.png)

[![Build Status](https://travis-ci.org/stanfy/helium.png?branch=master)](https://travis-ci.org/stanfy/helium)


Specification Example
---------------------

```groovy
note "Twitter REST API example"

type "UserProfile" message {
  id long required
  screen_name 'string' required
  profile_image_url_https 'string' optional
}

service {
  name 'Twitter API'
  description 'Piece of Twitter API'
  version 1.1

  location "https://api.twitter.com/${version}"

  get "/users/show.json" spec {
    name 'Get user profile'
    description '''
      Returns a variety of information about the user specified by the required user_id
      or screen_name parameter. The author's most recent Tweet will be returned inline when possible.
    '''
    parameters {
      user_id long optional
      screen_name 'string' optional
      include_entities boolean optional
    }
    response "UserProfile"
  }
}

```


Documentation
-------------
The main idea is to keep the single source of truth about data structures and contracts used in interactions via
your REST API.

After writing a Helium spec you'll get automatically generated tests for described API and will be able to generate
useful source code. Generated tests validate received responses to match data structures described in the spec.

On our [wiki](https://github.com/stanfy/helium/wiki) you'll find information about
* how to write Helium specs
* [what tests can be generated by Helium](https://github.com/stanfy/helium/wiki/API-Tests-Generation)
* [what source code Helium generates](https://github.com/stanfy/helium/wiki/Java-Source-Code-Generation)

Currently we support JSON, when generate API tests, and Java, when generate source code.

Both tests and code generation are implemented via Java API provided by Helium for processing specs.
This API allows you to access all the types, services, and methods described in specification.

You may use Helium Java API directly from your Java code or utilize our [Gradle plugin](/gradle-plugin)
to integrate Helium spec with your Gradle project.

You can find an [Android project integration sample](/samples/android)
in this repository.

Java API Examples
-----------------

```java
// read from string
new Helium().from("service {name 'Example Service'}").processBy(new Handler() {
  public void process(Project p) {
    System.out.println(p.getServices());
  }
});

// read from file and generate POJOs
new Helium().from(new File("twitter.spec"))
  .processBy(new EntitiesGenerator(
      new File("/build/gen"),
      EntitiesGeneratorOptions.defaultOptions("com.example.twitter")
  ));
```

*With Groovy :)*

You can write the specification code directly in your client:
```groovy
new Helium().from {
  service {
    name 'Example service'
  }
}.processBy({ Project p ->
  println p.services
} as Handler)
```


License
-------

     Copyright 2013 Stanfy Corp.

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
