# Api-Leo Implementation

## Summary

This api is a compile of knowledge learned in this first years of programming.

## High-level overview

This service is based in the following ideas:

* As a manager i want to manage (register, edit, exclude and update) the departments of my company.
* I want to register, edit, exclude and update my employees.
* As a manager i want to grant accesses and permissions for my employees.
* In the course of time new features will be implemented and the configuration will be updated

## Modules

* app - The rest service that can be used to perform the CRUD operations for employees, departments, profiles and etc.

## API Specification

The OpenAPI Specification could be found [there](https://github.com/leonardoduarte1305/api-leo-spec/blob/master/spec/src/main/resources/api-leo.yaml)


## Build

To build this componen run:

```bash
mvn clean install
```

## Running the application with IDE

* Invoke the Maven target `spring-boot:run` in the __root folder__

## Running the application with Docker

* Build Docker container

```bash
mvn clean install -Pci -DHOST_MACHINE_IP=localhost
```

* Run the container
```bash
docker run -d -e HOST_MACHINE_IP="$(ip addr show enp2s0 | grep "inet " | awk '{print $2}' | cut -d/ -f1)" -p 8180:8383 leonardoduarte1305/api-leo:0.0.1-SNAPSHOT 
```

## Troubleshooting

* Anytime you have a problem with JDBC Connection regarding the server not found or receive no packages from the server, you can use this command below, could save your life.
* ```bash
  export HOST_MACHINE_IP="$(ip addr show enp2s0 | grep "inet " | awk '{print $2}' | cut -d/ -f1)"
  ```