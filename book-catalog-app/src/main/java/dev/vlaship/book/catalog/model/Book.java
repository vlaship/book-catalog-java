package dev.vlaship.book.catalog.model;

import dev.vlaship.book.catalog.generator.SnowflakeId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@NamedEntityGraph(
        name = "Book.author",
        attributeNodes = {
                @NamedAttributeNode(value = "author")
        }
)
public class Book {

    @Id
    @SnowflakeId
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "book_desc")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "published")
    private LocalDate published;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        var oEffectiveClass = o instanceof HibernateProxy p ? p.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        var thisEffectiveClass = this instanceof HibernateProxy p ? p.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        var book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy p ? p.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
