# postman-gradle-plugin

A Gradle plugin to run [postman](https://www.postman.com/) collections

The runner makes use of [newman](https://github.com/postmanlabs/newman) run by [gradle-node-plugin](https://github.com/node-gradle/gradle-node-plugin).


#### Usage

```groovy
        plugins {
            id "inc.kaizen.postman"
}
```
postman collection file is to be configured in gradle script to run.

```groovy

// postmanrunner configuration
postman {
    // specifies collection file pattern
    // default: src/test/**/*.postman_collection.json
    collection = file("./src/test/resources/sample-collection.json")
}

```

That's all needed to execute the postman task:
`./gradlew runCollection`, you can find the task under postman group


An optional configuration of postman itself is done with the `postman` extension:

```groovy

// postman configuration
postman {
    // specifies collection file pattern
    // default: src/test/**/*.postman_collection.json
    collection = file("./src/test/resources/sample-collection.json")
    
    // specifies the test environment to execute the collections with
    // default: no environment
    environment = file('src/test/some_environment.postman_environment.json')
    
    // specifies any environment variables to execute the collections with
    // will override any values from the environment file above
    // default: no environment variables
    envVars = [ "myVar" : "myVarValue" ]
    
    // specifies the a global variable file to execute the collections with
    // default: no globals
    globals = file('./postman_globals.json')

    // specifies any global variables to execute the collections with
    // will override any values from the globals file above
    // default: no global variables
    globalsVars = [ "myVar" : "myVarValue" ]
    
    // stops entire execution on first failing test in a collection
    // default: false
    stopOnError = true
   
    // enables/disables ssl verification checks and allows self-signed certificates
    // default: true
    secure = false
}

```

