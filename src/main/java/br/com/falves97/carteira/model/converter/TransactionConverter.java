package br.com.falves97.carteira.model.converter;

import br.com.falves97.carteira.model.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.util.List;

public class TransactionConverter implements AttributeConverter<List<Category>, String> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<Category> categories) {
        String categoriesJson;

        try {
            categoriesJson = objectMapper.writeValueAsString(categories);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return categoriesJson;
    }

    @Override
    public List<Category> convertToEntityAttribute(String s) {
        List<Category> categories;

        try {
            categories = objectMapper.readerForListOf(Category.class).readValue(s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }
}
