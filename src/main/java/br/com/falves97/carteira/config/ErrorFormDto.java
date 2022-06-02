package br.com.falves97.carteira.config;

public class ErrorFormDto {
    private String field;
    private String message;

    public ErrorFormDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
