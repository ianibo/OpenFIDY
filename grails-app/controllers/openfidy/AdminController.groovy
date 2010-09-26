package openfidy

import grails.plugins.springsecurity.Secured;
import com.k_int.*;

class AdminController {

    // Import security bean
    def springSecurityService

    @Secured(["hasRole('admin')", "hasRole('IS_AUTHENTICATED_FULLY')"])
    def index = { 
      def current_user = currentUser();
    }

    def currentUser() {
      println("get current user ${springSecurityService.principal.id}")
      AppUser.get(springSecurityService.principal.id);
    }
}
