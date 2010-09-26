package openfidy

import grails.plugins.springsecurity.Secured;
import com.k_int.*;

class AdminController {

    // Import security bean
    def springSecurityService

    @Secured(['admin', 'IS_AUTHENTICATED_FULLY'])
    def index = { 
      def current_user = currentUser();
    }

    def currentUser() {
      AppUser.get(springSecurityService.principal.id);
    }
}
