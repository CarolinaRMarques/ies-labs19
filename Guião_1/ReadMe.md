## -------------1.1-----------
Usage : mvn exec:java -Dexec.mainClass="ies.lab1wradar.WeatherRadar" -Dexec.args="<cityID>"
Ex : mvn exec:java -Dexec.mainClass="ies.lab1wradar.WeatherRadar" -Dexec.args="1010500"

##--------------1.2-----------

##--------------1.3-----------
- A container is launched by running an image. An image is an executable package that includes everything needed to run an application--the code, a runtime, libraries, environment variables, and configuration files.

- A container is a runtime instance of an image--what the image becomes in memory when executed

- Dockerfile defines what goes on in the environment inside your container.

- Se exportar o porto e correr a app em background consigo aceder na mesma ao URL e ver o conteudo do container 
 
- In a distributed application, different pieces of the app are called “services”. Services are really just “containers in production.” A service only runs one image, but it codifies the way that image runs—what ports it should use, how many replicas of the container should run so the service has the capacity it needs, and so on. 

- A docker-compose.yml file is a YAML file that defines how Docker containers should behave in production.

- A single container running in a service is called a task. 

- Multi-container, multi-machine applications are made possible by joining multiple machines into a “Dockerized” cluster called a swarm.

- A stack is a group of interrelated services that share dependencies, and can be orchestrated and scaled together. A single stack is capable of defining and coordinating the functionality of an entire application

- The visualizer is a standalone service that can run in any app that includes it in the stack. It doesn’t depend on anything else.

COMANDOS USADOS/REMINDER:
# List Docker CLI commands
docker
docker container --help

# Display Docker version and info
docker --version
docker version
docker info

# Execute Docker image
docker run hello-world

# List Docker images
docker image ls

# List Docker containers (running, all, all in quiet mode)
docker container ls
docker container ls --all
docker container ls -aq

# Name Docker Image / Build Command
[docker build] --tag = [name] .

# Run an app, mapping your machine’s port to the container’s published port using -p:
docker run -p [machine's port]:[container's published port] [name//tag used]

# Run the app in the background, in detached mode:
docker run -d -p [machine's port]:[container's published port] [name//tag used] 

# Stop container (end process) using the CONTAINER ID:

docker container stop [CONTAINER_ID]

NOTA: para ver o container ID basta por exemplo executar docker container ls 

# run your app on any machine (if image is not in the repos docker will retrieve it;  it pulls your image, along with Python and all the dependencies from requirements.txt)
docker run -p 4000:80 username/repository:tag

# List all containers, even those not running
docker container ls -a

# Remove tag
docker rmi [name]

# Upload your tagged image to the repository:
docker push username/repository:tag
     
# Give your app a name. Here, it is set to getstartedlab:
docker stack deploy -c docker-compose.yml getstartedlab

# Get the service ID for the one service in our application:
docker service ls

# List stacks or apps
docker stack ls

# Run the specified Compose file                                       
docker stack deploy -c <composefile> <appname> 

# List running services associated with an app
docker service ls

# List tasks associated with an app             
docker service ps <service>

# Inspect task or container      
docker inspect <task or container>
         
# List container IDs      
docker container ls -q

# Tear down an application                                
docker stack rm <appname> 

# Take down a single node swarm from the manager                
docker swarm leave --force 

# Create VM (using the VirtualBox driver) :
docker-machine create --driver virtualbox <name>

# List the VM's
docker-machine ls

# Send commands to VMs 
docker-machine ssh

EX : docker-machine ssh myvm1 "docker swarm init --advertise-addr <myvm1 ip>"

# View basic information about your node
docker-machine env myvm1 
      
# List the nodes in your swarm    
docker-machine ssh myvm1 "docker node ls" 

# Open an SSH session with the VM
docker-machine ssh myvm1 

# show environment variables and command for myvm1
docker-machine env myvm1

# Deploy an app; command shell must be set to talk to manager (myvm1), uses local Compose file
docker stack deploy -c <file> <app>

# Copy file to node's home dir (only required if you use ssh to connect to manager and deploy the app)
docker-machine scp docker-compose.yml myvm1:~

# Deploy an app using ssh (you must have first copied the Compose file to myvm1) 
docker-machine ssh myvm1 "docker stack deploy -c <file> <app>"

# Disconnect shell from VMs, use native docker
eval $(docker-machine env -u)

# Stop all running VMs
docker-machine stop $(docker-machine ls -q)

# Delete all VMs and their disk images
docker-machine rm $(docker-machine ls -q) 

```
Dockerfile 
#
# example Dockerfile for https://docs.docker.com/engine/examples/postgresql_service/
#

FROM ubuntu:16.04

# Add the PostgreSQL PGP key to verify their Debian packages.
# It should be the same key as https://www.postgresql.org/media/keys/ACCC4CF8.asc
RUN apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8

# Add PostgreSQL's repository. It contains the most recent stable release
#     of PostgreSQL, ``9.3``.
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main" > /etc/apt/sources.list.d/pgdg.list

# Install ``python-software-properties``, ``software-properties-common`` and PostgreSQL 9.3
#  There are some warnings (in red) that show up during the build. You can hide
#  them by prefixing each apt-get statement with DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y python-software-properties software-properties-common postgresql-9.3 postgresql-client-9.3 postgresql-contrib-9.3

# Note: The official Debian and Ubuntu images automatically ``apt-get clean``
# after each ``apt-get``

# Run the rest of the commands as the ``postgres`` user created by the ``postgres-9.3`` package when it was ``apt-get installed``
USER postgres

# Create a PostgreSQL role named ``docker`` with ``docker`` as the password and
# then create a database `docker` owned by the ``docker`` role.
# Note: here we use ``&&\`` to run commands one after the other - the ``\``
#       allows the RUN command to span multiple lines.
RUN    /etc/init.d/postgresql start &&\
    psql --command "CREATE USER docker WITH SUPERUSER PASSWORD 'docker';" &&\
    createdb -O docker docker

# Adjust PostgreSQL configuration so that remote connections to the
# database are possible.
RUN echo "host all  all    0.0.0.0/0  md5" >> /etc/postgresql/9.3/main/pg_hba.conf

# And add ``listen_addresses`` to ``/etc/postgresql/9.3/main/postgresql.conf``
RUN echo "listen_addresses='*'" >> /etc/postgresql/9.3/main/postgresql.conf

# Expose the PostgreSQL port
EXPOSE 5432

# Add VOLUMEs to allow backup of config, logs and databases
VOLUME  ["/etc/postgresql", "/var/log/postgresql", "/var/lib/postgresql"]

# Set the default command to run when starting the container
CMD ["/usr/lib/postgresql/9.3/bin/postgres", "-D", "/var/lib/postgresql/9.3/main", "-c", "config_file=/etc/postgresql/9.3/main/postgresql.conf"]

```

## Authors

* **Carolina Resende Marques**  - [CarolinaRMarques](https://github.com/CarolinaRMarques)

