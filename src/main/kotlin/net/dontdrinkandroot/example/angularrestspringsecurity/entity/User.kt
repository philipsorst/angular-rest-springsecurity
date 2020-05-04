package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import javax.persistence.*

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
open class User {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var username: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Basic(optional = false)
    var firstName: String? = null

    @Basic(optional = false)
    var lastName: String? = null

    @Column(name = "roles", nullable = false)
    private var _roles: String = ""

    var roles: Set<String>
        get() = _roles.split(",").toSet()
        set(value) {
            _roles = value.joinToString(",")
        }
}
