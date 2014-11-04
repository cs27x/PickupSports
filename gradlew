#/bin/bash

# heroku requires a gradlew script in the root of the project.  This is just a passthrough that 
# calls the gradlew script in the Server project.

cd Server
./gradlew "$@"
