package com.k_int

import org.apache.commons.lang.builder.HashCodeBuilder

class AppUserAppRole implements Serializable {

	AppUser appUser
	AppRole appRole

	boolean equals(other) {
		if (!(other instanceof AppUserAppRole)) {
			return false
		}

		other.appUser?.id == appUser?.id &&
			other.appRole?.id == appRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (appUser) builder.append(appUser.id)
		if (appRole) builder.append(appRole.id)
		builder.toHashCode()
	}

	static AppUserAppRole get(long appUserId, long appRoleId) {
		find 'from AppUserAppRole where appUser.id=:appUserId and appRole.id=:appRoleId',
			[appUserId: appUserId, appRoleId: appRoleId]
	}

	static AppUserAppRole create(AppUser appUser, AppRole appRole, boolean flush = false) {
		new AppUserAppRole(appUser: appUser, appRole: appRole).save(flush: flush, insert: true)
	}

	static boolean remove(AppUser appUser, AppRole appRole, boolean flush = false) {
		AppUserAppRole instance = AppUserAppRole.findByAppUserAndAppRole(appUser, appRole)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(AppUser appUser) {
		executeUpdate 'DELETE FROM AppUserAppRole WHERE appUser=:appUser', [appUser: appUser]
	}

	static void removeAll(AppRole appRole) {
		executeUpdate 'DELETE FROM AppUserAppRole WHERE appRole=:appRole', [appRole: appRole]
	}

	static mapping = {
		id composite: ['appRole', 'appUser']
		version false
	}
}
