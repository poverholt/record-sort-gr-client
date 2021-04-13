# record-sort-gr-client

A test client for record-sort-gr. See https://github.com/poverholt/record-sort-gr/.

## Installation

This test client and the target server are already installed on Heroku...
* https://record-sort-gr.herokuapp.com/
* https://record-sort-gr-client.herokuapp.com/

The server and test client projects are available on GitHub for download...
* https://github.com/poverholt/record-sort-gr/
* https://github.com/poverholt/record-sort-gr-client

## Usage

Visit the Heroku sites mentioned above to use the existing installations.

To use the downloaded project...
* lein run [args?]
* lein repl, (-main [args?])
* $java $JVM_OPTS -cp target/uberjar/record-sort-gr.jar clojure.main -m record-sort-gr.core [args?]
* $java $JVM_OPTS -cp <path-to-jar> clojure.main -m record-sort-gr.core [args?] => if you moved the jar

This client will test the connection to server on localhost at launch. If found, all further communication
is to localhost:port. Otherwise it will exclusively target https://record-sort-gr.herokuapp.com/.

### Options

Without arguments, the server defaults to port 8010.
You can override the default server port with the argument, for example, *lein run 5010*.
