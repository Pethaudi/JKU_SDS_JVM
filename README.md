# JKU_SDS_JVM
## this is a repo for research purposes

## INFO

1. save all your results under /results
2. save your all code and researches
3. entities under src/entities

## Setup for JDBC

1. install Maven (bin: https://maven.apache.org/download.cgi )
2. download ojdbc7 ( https://edufs.edu.htl-leonding.ac.at/ftp/Software/!Oracle/oracle.jdbc.12.1.0.2/ )
2. run script: /opt/apache-maven/bin/mvn install:install-file -Dfile="<path-to-file>"/ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1.0 -Dpackaging=jar

("<path-to-file>" durch den Pfad ersetzen)
