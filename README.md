# Sample Reactive Sync Communication Axon Application

This sample application demonstrates an synchronous communication pattern using Spring WebFlux and Axon Framework. It showcases how subscription queries can be utilized to receive real-time updates to query models upon the occurrence of specific events.

## Overview

Upon receiving a command to start communication, the application emits an event to signify the initiation of the communication process. This event-driven architecture, combined with the reactive capabilities of Spring WebFlux, allows the service to handle requests in a non-blocking, asynchronous manner.

After submitting three such commands, the application performs an aggregation of these events to update the query model. This update reflects the cumulative result of the initiated communications, making the latest state of the data available for querying.

Clients can query the updated model at any time to retrieve the current data. Thanks to Spring WebFlux, these queries are handled reactively, ensuring that the application remains responsive and scalable under load. Despite the asynchronous nature of the processing, clients can achieve a synchronous-like behavior in terms of data retrieval, experiencing seamless access to the updated state as soon as it's available.

## Key Features

- **Reactive Command Handling**: Utilizes Spring WebFlux and Axon Framework to process commands asynchronously, ensuring efficient resource utilization and responsiveness.
- **Event-Driven Updates**: Leverages events to signal the start of communication processes, with an event-sourced approach to maintain the application's state.
- **Reactive Query Model**: Supports reactive queries, allowing clients to retrieve updated data in a non-blocking manner, thereby enhancing the user experience.
- **Aggregated Data Retrieval**: Demonstrates how to aggregate events and update the query model, providing clients with the latest data after multiple communications have been initiated.

## Usage

To interact with the application, use the following API endpoints:

- **Start Communication**: Sends a command to start the communication process.
    - **POST** `/api/sync`
    - Send this request 3 times to trigger the data aggregation process.

