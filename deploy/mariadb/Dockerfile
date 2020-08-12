FROM mariadb:10.1.38

COPY . /docker-entrypoint-initdb.d

ENV MYSQL_ALLOW_EMPTY_PASSWORD=true

COPY oe.cnf /etc/mysql/conf.d

RUN chmod -R +x /docker-entrypoint-initdb.d 
