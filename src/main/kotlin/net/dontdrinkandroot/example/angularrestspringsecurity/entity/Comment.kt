package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.ReadOnlyProperty
import javax.persistence.*

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
class Comment : BaseEntity<Long>() {

    @ManyToOne(optional = false)
    var blogPost: BlogPost? = null

    @ManyToOne(optional = false)
    @CreatedBy
    @ReadOnlyProperty
    var author: User? = null

    @Basic(optional = false)
    @CreatedDate
    @ReadOnlyProperty
    var created: Long? = null

    @Lob
    @Column(nullable = false)
    var content: String? = null
}
