package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
class User : BaseEntity<Long>() {

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
