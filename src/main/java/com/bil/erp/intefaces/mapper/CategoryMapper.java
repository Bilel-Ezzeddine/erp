package com.bil.erp.intefaces.mapper;

import com.bil.erp.dto.category.CategoryRequest;
import com.bil.erp.dto.category.CategoryResponse;
import com.bil.erp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProductMapper.class})

public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequest categoryRequest);

    CategoryResponse toResponse(Category category);
}
