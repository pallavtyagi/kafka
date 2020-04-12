# download kafdrop jar from here - https://bintray.com/obsidiandynamics/kafdrop/download_file?file_path=com%2Fobsidiandynamics%2Fkafdrop%2Fkafdrop%2F3.24.0%2Fkafdrop-3.24.0.jar

java --add-opens=java.base/sun.nio.ch=ALL-UNNAMED -jar kafdrop-3.24.0.jar --kafka.brokerConnect=localhost:9092 --schemaregistry.connect=http://localhost:8081 &