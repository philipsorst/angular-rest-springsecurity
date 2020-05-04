package net.dontdrinkandroot.example.angularrestspringsecurity.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import javax.persistence.*

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Entity
class BlogPost : BaseEntity<Long>() {

    @Basic(optional = false)
    var title: String? = null

    @Column(nullable = false, unique = true)
    var slug: String? = null

    @Lob
    @Column(nullable = false)
    var content: String? = null

    @ManyToOne(optional = false)
    @CreatedBy
    var author: User? = null

    @Basic(optional = false)
    @CreatedDate
    var created: Long? = null

    @Basic(optional = false)
    @LastModifiedDate
    var updated: Long? = null

    @OneToMany(mappedBy = "blogPost")
    var comments: MutableCollection<Comment>? = null
}
