# spring-security-example

- The project has two models, using enums and database (PostgreSQL). Currently only working with database
- The database can be started using Docker, there's a docker-compose file and the scripts to be run inside de "postgres" folder
- The "mock" prefix/sufix are only for the previous example when users are located inside the enums
- Inside the security folder, there are the configuration classes. The ApplicationSecurityConfig.java contains the injection to the ApplicationUserService (which loads the users and authorizations via database) and the JwtConfiguration for JWT access (stateless)
- The controllers methods authorization are validated each one at request, not globally
