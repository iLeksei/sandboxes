1) generate keyStore: 
    keytool -genkeypair -alias keystore_server -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore_server.p12 -validity 3650 -storepass 123456

2) check key:
    keytool -list -v -storetype pkcs12 -keystore keystore_server.p12
    
3) set properties in application.yml
4) configure SecurityConfig and ServerConfig
5) check GET-request in POSTMAN
6) extract key from keystore(creating a certificate):
    keytool -export -keystore keystore_server.p12 -alias keystore_server -file server_cert.crt
7) (check it later) Import an SSL certificate inside the JRE keystore:
    + keytool -importcert -file server_cert.crt -alias keystore_server -keystore \caserts
    - keytool -importcert -file server_cert.crt -alias keystore_server -keystore C:/Program Files/Java/jdk1.8.0_211/jre/lib/security/cacerts
    
8)  Import client and server certs to their keystores: 
    keytool -import -alias keystore_server -file server_cert.crt -keystore keystore_client.p12
    keytool -import -alias keystore_client -file client_cert.crt -keystore keystore_server.p12