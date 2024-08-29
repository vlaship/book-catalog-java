package dev.vlaship.book.catalog.mapper;

import dev.vlaship.book.catalog.dto.*;
import dev.vlaship.book.catalog.model.Author;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorMapper {
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "birthDate", source = "dateOfBirth")
    Author map(CreateAuthorRequest request);

    CreateAuthorDto mapCreate(Author saved);

    @Mapping(target = "dateOfBirth", source = "birthDate")
    AuthorDto map(Author author);

    default List<AuthorListDto> map(List<Author> author) {
        return author.stream().map(this::mapList).toList();
    }

    AuthorListDto mapList(Author author);

    @Mapping(target = "books", ignore = true)
    @Mapping(target = "birthDate", source = "dateOfBirth")
    @Mapping(target = "authorId", ignore = true)
    void merge(UpdateAuthorRequest request, @MappingTarget Author author);
}
