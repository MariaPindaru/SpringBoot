package com.example.demo.dto;

public class SearchFiltersDto {
    private String keyword;
    private Long rangeMin;
    private Long rangeMax;

    public SearchFiltersDto() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(Long rangeMin) {
        this.rangeMin = rangeMin;
    }

    public Long getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(Long rangeMax) {
        this.rangeMax = rangeMax;
    }
}
