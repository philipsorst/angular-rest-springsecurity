package net.dontdrinkandroot.example.angularrestspringsecurity.security

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
interface SecurityService {
    /**
     * Returns the currently logged in user or null if no user is logged in.
     */
    fun getCurrentUser(): User?

    /**
     * Logs in the given user.
     */
    fun setCurrentUser(user: User?)
}
