package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@MappedSuperclass
abstract class BaseEntity<T : Serializable> : Serializable {

    @Id
    @GeneratedValue
    var id: T? = null
        protected set

    override fun equals(other: Any?): Boolean {

        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return if (null == this.id) false else this.id == other.id
    }

    override fun hashCode(): Int = 31

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"

    companion object {
        private const val serialVersionUID = -5554308939380869754L
    }
}
