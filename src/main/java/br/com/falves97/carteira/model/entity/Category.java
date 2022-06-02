package br.com.falves97.carteira.model.entity;


import java.util.stream.Stream;

public enum Category {
    FOOD("Alimentação"),
    HEALTH("Saúde"),
    HOME("Moradia"),
    TRANSPORT("Transporte"),
    EDUCATION("Educação"),
    LEISURE("Lazer"),
    UNFORESEEN("Imprevistos"),
    OTHERS("Outros");

    private String category;
    Category(String value) {
        category = value;
    }

    public String getCategory() {
        return category;
    }

    public static Category of(String category) {
        return Stream.of(Category.values())
                .filter(c -> category.equalsIgnoreCase(c.getCategory()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
