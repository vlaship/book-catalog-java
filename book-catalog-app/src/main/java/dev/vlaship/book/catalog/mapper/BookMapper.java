package dev.vlaship.book.catalog.mapper;

import dev.vlaship.book.catalog.dto.*;
import dev.vlaship.book.catalog.model.Author;
import dev.vlaship.book.catalog.model.Book;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    unmappedSourcePolicy = ReportingPolicy.WARN
)
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author.authorId", source = "authorId")
    Book mapCreate(CreateBookRequest request);

    @Mapping(target = "bookId", source = "id")
    CreateBookDto mapCreate(Book saved);

    @Mapping(target = "bookId", source = "id")
    @Mapping(target = "authorName", source = "author")
    @Mapping(target = "authorId", source = "author.authorId")
    BookDto map(Book book);

    List<BookListDto> map(List<Book> content);

    @Mapping(target = "bookId", source = "id")
    @Mapping(target = "authorName", source = "author")
    @Mapping(target = "authorId", source = "author.authorId")
    BookListDto mapList(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author.authorId", source = "authorId")
    void merge(UpdateBookRequest request, @MappingTarget Book book);

    default String map(Author author){
        return author.getFirstName() + " " + author.getLastName();
    }
}
