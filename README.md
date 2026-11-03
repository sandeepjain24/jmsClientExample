# JMS Client Examples

Small Java Message Service (JMS) examples using Apache ActiveMQ Classic. The
project demonstrates queue producers and consumers, asynchronous delivery,
queue browsing, client acknowledgements, and durable topic subscribers.

## Requirements

- Java Development Kit (JDK) 25 (latest Java LTS)
- Apache Maven

The ActiveMQ client and broker libraries are provided by the
`org.apache.activemq:activemq-all:5.19.6` dependency in `pom.xml`.

## Build

```bash
mvn clean test-compile
```

## Run the examples

Start the embedded broker first when running the examples that connect to
`tcp://localhost:61616`:

```bash
mvn exec:java -Dexec.mainClass=com.javacodegeeks.jms.BrokerLauncher
```

If the Maven Exec plugin is not available in your environment, run the classes
from your IDE after importing the project as a Maven project.

### Queue examples

| Class | Description | Destination |
| --- | --- | --- |
| `JmsProducerQueueClient` | Sends start or cancel evaluation requests from an interactive menu. | `EVALUATION_REQUEST_QUEUE` |
| `JmsSyncReceiveQueueClientExample` | Receives text messages synchronously until it receives `END`. | `customerQueue` |
| `JmsAsyncReceiveQueueClientExample` | Receives messages through a `MessageListener` until it receives `END`. | `customerQueue` |
| `JmsBrowseQueueClientExample` | Browses messages without removing them, then receives one message. | `customerQueue` |

For the synchronous and asynchronous consumers, publish messages to
`customerQueue` and send `END` to stop the example.

### Topic example

`JmsSubscriberClientExample` publishes a persistent message to
`customerTopic` and demonstrates two durable subscribers:

- `consumer1`
- `consumer2`

### Acknowledgement example

`JmsClientAckExample` uses an embedded, non-persistent broker through
`vm://localhost?broker.persistent=false`. It demonstrates how a message remains
available after a session is closed until it is acknowledged.

## Project layout

```text
src/main/java/com/javacodegeeks/jms/
├── BrokerLauncher.java
├── ConsumerMessageListener.java
├── JmsAsyncReceiveQueueClientExample.java
├── JmsBrowseQueueClientExample.java
├── JmsClientAckExample.java
├── JmsProducerQueueClient.java
├── JmsSubscriberClientExample.java
└── JmsSyncReceiveQueueClientExample.java
```
