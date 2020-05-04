package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import javax.persistence.*

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
class Comment {

    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne(optional = false)
    var blogPost: BlogPost? = null

    @ManyToOne(optional = false)
    @CreatedBy
    var author: User? = null

    @Basic(optional = false)
    @CreatedDate
    var createdTimestamp: Long? = null

    @Lob
    @Column(nullable = false)
    var content: String? = null
}
