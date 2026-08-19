package university

import org.springframework.security.crypto.password.PasswordEncoder

class BootStrap {

    PasswordEncoder passwordEncoder

    def init = { servletContext ->

        User.withTransaction {

            Role adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
                    new Role(
                            authority: 'ROLE_ADMIN'
                    ).save(failOnError: true)

            Role userRole = Role.findByAuthority('ROLE_USER') ?:
                    new Role(
                            authority: 'ROLE_USER'
                    ).save(failOnError: true)


            User admin = User.findByUsername('admin@ubs.com') ?:
                    new User(
                            username: 'admin@ubs.com',
                            password: passwordEncoder.encode('admin111')
                    ).save(failOnError: true)

            User intern = User.findByUsername('intern@ubs.com') ?:
                    new User(
                            username: 'intern@ubs.com',
                            password: passwordEncoder.encode('intern111')
                    ).save(failOnError: true)


            if (!UserRole.exists(admin.id, adminRole.id)) {
                UserRole.create(admin, adminRole, true)
            }

            if (!UserRole.exists(intern.id, userRole.id)) {
                UserRole.create(intern, userRole, true)
            }
        }
    }

    def destroy = {
    }
}