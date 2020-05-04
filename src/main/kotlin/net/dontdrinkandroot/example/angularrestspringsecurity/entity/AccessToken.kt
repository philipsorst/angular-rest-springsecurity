package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
class AccessToken {

    @Id
    @GeneratedValue
    var id: Long? = null
}
