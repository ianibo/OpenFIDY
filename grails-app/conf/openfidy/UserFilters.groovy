package openfidy

import com.k_int.*;

class UserFilters {

    def springSecurityService

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                
            }
            after = { model ->
                if ( model && springSecurityService.isLoggedIn()) {
                   model['user'] = AppUser.get(springSecurityService.principal.id)
                }
            }
            afterView = {
                
            }
        }
    }
    
}
