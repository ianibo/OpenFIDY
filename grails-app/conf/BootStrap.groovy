import com.k_int.*;

class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        def user_role = AppRole.findByAuthority('user') ?: new AppRole(authority: 'user').save()
        def admin_role = AppRole.findByAuthority('admin') ?: new AppRole(authority: 'admin').save()

        println "User Role: ${user_role}"
        println "Admin Role: ${admin_role}"

        def admin_user = AppUser.findByUsername('admin') ?: new AppUser(username: 'admin', password: springSecurityService.encodePassword('admin'), enabled:true).save()
        def fidy_user = AppUser.findByUsername('fidy') ?: new AppUser(username: 'fidy', password: springSecurityService.encodePassword('fidy'), enabled:true).save()

        println "Admin user: ${admin_user}"
        println "fidy user: ${fidy_user}"

        UserRole.create admin_user, admin_role
        UserRole.create admin_user, user_role
        UserRole.create fidy_user, user_role
    }
    def destroy = {
    }

}
